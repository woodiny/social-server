package woodiny.socialserver.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import woodiny.socialserver.model.user.Email;
import woodiny.socialserver.security.Claims;
import woodiny.socialserver.security.Jwt;
import woodiny.socialserver.security.UserAuthentication;
import woodiny.socialserver.security.UserAuthenticationToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final Jwt jwt;

    public JwtRequestFilter(Jwt jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            log.debug("SecurityContextHolder already exists: {}", authentication);
        } else {
            try {
                Claims claims = null;
                String authorization = request.getHeader("Authorization");
                if (authorization != null && authorization.startsWith("Bearer ")) {
                    String token = authorization.substring(7);
                    claims = jwt.validate(token);
                }

                Long userId = claims.getUserId();
                Email email = claims.getEmail();
                String[] roles = claims.getRoles();

                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (String role : roles) {
                    SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);
                    grantedAuthorities.add(simpleGrantedAuthority);
                }

                if (userId != null && email != null && !grantedAuthorities.isEmpty()) {
                    UserAuthenticationToken userAuthenticationToken =
                            new UserAuthenticationToken(null, new UserAuthentication(userId, email), grantedAuthorities);
                    userAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
                }
            } catch (Exception e) {
                log.error("Authentication failed: {}", e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
