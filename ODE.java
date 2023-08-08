package task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ODE {
    /*
    https://www.geeksforgeeks.org/runge-kutta-2nd-order-method-to-solve-differential-equations/

    RK2: Ограничавам се до ДВЕ точки напред (X0 и X1).
    Идеята е: Знам първата производна f'(x) на дадена функция в дадена точка, хайде да немерим стойността на търсената
              функция в други такива точки. Т.е търсим функцията чиято първа производна удоволетворява уравнението:
              f'(x,y) = f(x,y)
              Решението на едно ОДУ е функция, докато решението на квадратно или кубично уравнение
              е алгебричен израз или число.

    Първата производна на функция в дадена точка е = на тангенса от ъгъла, който сключва допирателната на функцията
              в тази точка с оста x.

    Oiler:
       Y' = X^2 + Y + 1;
       Y' = f(X,Y)                                            -> Yn = Yn-1 + (h * f(Xn-1, Yn-1))
       Y(X0) = Y0


       Runge - Kutta;
       A = f(X0,Y0)
       Yt = Y0 + (h * f(X0,Y0)) -> Y0 + (h * A)                -> Y1 = Y0 + (h * C);
       X1 = X0 + h
       B = f(X1,Y1);
       C = (A + B) / 2
    */
    public static void main(String[] args) {
        double xi = 0;
        double yi = 1;
        double h = 0.2;
        int n = 20;
        boolean isPrint = true;

        List<Double> dh = new ArrayList<>();
        Map<Double, Double> xErr = new TreeMap<>();
        oiler(xi, yi, h, isPrint, n);
        System.out.println();
        rungeKutta2(xi, yi, h, isPrint, n);
        System.out.println();

        for (double i = 0; i < getRange(xi, h, n); i += h) {
            double dhX = ((rungeKutta2(xi, yi, h, !isPrint, n).get(i)) - (oiler(xi, yi, h, !isPrint, n).get(i)));
            // dh.add(dhX);
            xErr.put(i, dhX);
        }
        System.out.println("Error between ");
        // for (int i = 0; i < dh.size(); i++) System.out.printf(" &d: %.2f %% %n", dh.get(i) * 100);
        System.out.printf("x:      ∆y: %n");
        for (Map.Entry<Double, Double> el : xErr.entrySet()) {
            System.out.printf("%.2f -> %.2f %% %n", el.getKey(), el.getValue() * 100);
        }
    }

    private static Map<Double, Double> rungeKutta2(double xi, double yi, double h, boolean isPrint, int n) {
        double A, B, C, y, yt;
        Map<Double, Double> xy = new TreeMap<>();

        for (double i = 0; i < n; i++) {
            // А = (((Math.pow(xi, 2)) + yi + 1));
            xy.put(xi, yi);
            A = ((xi - yi) / 2);                            // Налкона на ф-ята (tan(a) в точката X0, Y0)
            yt = yi + (h * A);
            xi += h;
            //B = (((Math.pow(xi, 2)) + yt + 1));
            B = ((xi - yt) / 2);                            // Налкона на ф-ята (tan(a) в точката (X1, Y1))
            C = (A + B) / 2;                                // Възможно най-добрият наклон в точката (X1, Y1)
            y = yi + (h * C);                               // Стойноста на функцията в точката (X1, Y1) и С
            yi = y;
        }

        if (isPrint) {
            System.out.println("Runge - Kutta ");
            System.out.printf("x:       y: %n");
            for (Map.Entry<Double, Double> el : xy.entrySet()) {
                // System.out.printf("%.2f%n", el.getKey());
                System.out.printf("%.2f -> %.2f%n", el.getKey(), el.getValue());
            }
        }
        return xy;
    }

    private static Map<Double, Double> oiler(double xi, double yi, double h, boolean isPrint, int n) {
        double y;
        Map<Double, Double> xy = new TreeMap<>();

        for (double i = 0; i < n; i++) {
            //  y = yi + (h * ((Math.pow(xi, 2)) + yi + 1));
            xy.put(xi, yi);
            y = yi + (h * ((xi - yi) / 2));                  //  y = y0 + (h * f(x0, y0));
            yi = y;
            xi += h;
        }

        if (isPrint) {
            System.out.println("Oiler methods ");
            System.out.printf("x:       y: %n");
            for (Map.Entry<Double, Double> el : xy.entrySet()) {
                // System.out.printf("%.2f%n", el.getKey());
                System.out.printf("%.2f -> %.2f%n", el.getKey(), el.getValue());
            }
        }
        //  for (Map.Entry<Double, Double> el : xy.entrySet()) System.out.printf("%.2f%n", el.getValue());
        return xy;
    }

    private static double getRange(double xi, double h, int n) {
        return xi + (n * h);
    }
}
