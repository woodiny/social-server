package woodiny.socialserver.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ConvertUtil {
    private ConvertUtil() {
        throw new UnsupportedOperationException("Util Class. Cannot be used as instance.");
    }

    public static Timestamp timestampOf(LocalDateTime localDateTime) {
        return localDateTime == null ? null : Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime localDateTimeOf(Timestamp timestamp) {
        return timestamp == null ? null : timestamp.toLocalDateTime();
    }
}
