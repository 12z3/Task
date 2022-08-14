package Task;

public class SpentTime {

    private static void spentTime(int hour, int min) {
        int countDay = 0, countHour = 0;
        if (min >= 60) {
            while (min >= 60) {
                min -= 60;
                countHour++;
            }
            hour += countHour;
        }
        if (hour >= 24) {
            while (hour >= 24) {
                hour -= 24;
                countDay++;
            }
        }
        System.out.printf("%02d day %02d hour and %02d min ", countDay, hour, min);     // 15 day 05 hour and 15 min
    }
    public static void main(String[] args) {

        int hour = 15 * 24;
        int min = 5 * 63;

        spentTime(hour, min);
    }
}
