package task;

import training.Methods;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LittlePermutations extends Methods {
    public static void main(String[] args) {

        /*
        TODO: Какъв е броя на трицифрените числа,
              в който всеки две цифри имат разлика 3, 4 или 7?
        */

        int count = 0;
        boolean is;
        StringBuilder stb = new StringBuilder();
        List<Integer> result = new ArrayList<>();

        long start = getStartTime();

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                is = false;
                if (((Math.abs(i - j) == 3 || Math.abs(i - j) == 4 || Math.abs(i - j) == 7))) {
                    is = true;
                } else continue;
                for (int k = 0; k < 10; k++) {
                    if (((Math.abs(k - j) == 3 || Math.abs(k - j) == 4 || Math.abs(k - j) == 7)
                            && (Math.abs(k - i) == 3 || Math.abs(k - i) == 4 || Math.abs(k - i) == 7))
                            && is) {
                        count++;
                        stb.append(i).append(j).append(k);
                        result.add(Integer.parseInt(String.valueOf(stb)));
                        stb.delete(0, stb.length());
                    }
                }
            }
        }

        System.out.println("options = " + count);
        for (int i = 0; i < result.size(); i++) {
            if (i < result.size() - 1) {
                System.out.print(result.get(i) + ", ");
            } else System.out.print(result.get(i));
        }
        getEndTime(start);
    }
}

/* options = 32:
148, 158, 184, 185, 259, 269, 295, 296, 307, 370, 407,
418, 470, 481, 518, 529, 581, 592, 629, 692, 703, 704,
730, 740, 814, 815, 841, 851, 925, 926, 952, 962 */

/*
for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (((Math.abs(k - j) == 3 || Math.abs(k - j) == 4 || Math.abs(k - j) == 7)
                            && (Math.abs(i - j) == 3 || Math.abs(i - j) == 4 || Math.abs(i - j) == 7))
                            && (Math.abs(k - i) == 3 || Math.abs(k - i) == 4 || Math.abs(k - i) == 7)) {
                        count++;
                        stb.append(i).append(j).append(k);
                        result.add(Integer.parseInt(String.valueOf(stb)));
                        stb.delete(0, stb.length());
                    }
                }
            }
        }
*/