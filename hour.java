package Task;

public class hour {
    public static void main(String[] args) {

        double hour = 48;
        double min = 60;

        time(hour, min);
    }

    private static void time(double hour, double min) {
        int countDay = 0, countHour = 0;
        if (hour >= 24) {
            while (hour >= 24) {
                hour -= 24;
                countDay++;
            }
        }
        if (min >= 60) {
            while (min >= 60) {
                min -= 60;
                countHour++;
                hour += countHour;
            }
        }
        System.out.printf("%d day %.0f hour and %.0f min ", countDay, hour, min);
    }
}
