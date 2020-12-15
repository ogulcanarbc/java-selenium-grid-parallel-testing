package util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtil {

    private static final String ZONE_ID = "Europe/Istanbul";

    public static Timestamp getDate(Long occurrenceTime) {
        return Timestamp.from(Instant.ofEpochMilli(occurrenceTime).truncatedTo(ChronoUnit.HOURS));
    }

    public static Timestamp startOfToday() {
        return Timestamp.from(todayStartZone().toInstant());
    }

    public static Timestamp endOfToday() {
        return Timestamp.from(todayStartZone().plusDays(1).minusNanos(1).toInstant());
    }

    public static Timestamp endOfYesterday() {
        return Timestamp.from(todayStartZone().minusNanos(1).toInstant());
    }

    public static Timestamp startOfYesterday() {
        return Timestamp.from(todayStartZone().minusDays(1).toInstant());
    }

    private static ZonedDateTime todayStartZone() {
        ZoneId zoneId = ZoneId.of(ZONE_ID);
        LocalDate today = LocalDate.now(zoneId);
        return today.atStartOfDay(zoneId);
    }

    public static String getTodayEpochMiliSecAsString() {
        return String.valueOf(Instant.now().toEpochMilli());
    }

}
