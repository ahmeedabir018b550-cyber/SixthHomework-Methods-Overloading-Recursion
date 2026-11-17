
package lt.vcd;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateDifference {

    public static long calculateDaysBetween(
            int year_from,
            int month_from,
            int day_from,
            int year_to,
            int month_to,
            int day_to) {

        LocalDate dateFrom = LocalDate.of(
                year_from, month_from, day_from);

        LocalDate dateTo = LocalDate.of(
                year_to, month_to, day_to);

        return Math.abs(ChronoUnit.DAYS.between(dateFrom, dateTo));
    }

    public static void main(String[] args) {

        long result = calculateDaysBetween(
                2026, 1, 1,
                2026, 1, 31);

        System.out.println("Number of days: " + result);
    }
}
