package Task;

public class SpentTime {

    private static void spentTime(double hour, double min) {
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
        System.out.printf("%d day %.0f hour and %.0f min ", countDay, hour, min);
    }
    public static void main(String[] args) {

        double hour = 5 * 24;
        double min = 24 * 60;

        spentTime(hour, min);
    }
}
