package org.example;


public class Main {
    /*................................................ R E G I O N                 F I R S T       F U N C T I O N .................................................*/
    // Первая функция: x^3 + 3*x^2 - 1
    //корни по Desmos: -2.9, -0.65, 0.53 рассматриваем интервалы где есть эти точки
    static double functionFirst(double x) {
        return Math.pow(x, 3) + 3 * Math.pow(x, 2) - 1;
    }

    static double gFirst(double x) {
        return Math.sqrt((1 - Math.pow(x, 3)) / 3);
    }

    /*................................................ R E G I O N                 S E C O N D     F U N C T I O N .................................................*/
    // Вторая функция: x^4 - x^3
    static double functionSecond(double x) {
        return Math.pow(x, 4) - Math.pow(x, 3);
    }

    static double gSecond(double x) {
        return Math.pow(x, (double) 4 / 3);
    }

    /*................................................ R E G I O N                 T H I R D     F U N C T I O N .................................................*/
    // Третья функция: x^2 - 3*x + 2
    //корни 1 и 2
    static double functionThird(double x) {
        return Math.pow(x, 2) - 3 * x + 2;
    }

    static double gThird(double x) {
        return Math.sqrt(3 * x - 2);
    }

    // Метод простых итераций для нахождения корня уравнения
    public static double simpleIterationMethodFirst(double initialGuess, double epsilon) {
        double x = initialGuess;
        double xPrevious;
        do {
            xPrevious = x;
            x = gFirst(xPrevious);
        } while (Math.abs(x - xPrevious) > epsilon);
        return x;
    }

    public static double simpleIterationMethodSecond(double initialGuess, double epsilon) {
        double x = initialGuess;
        double xPrevious;
        do {
            xPrevious = x;
            x = gSecond(xPrevious);
        } while (Math.abs(x - xPrevious) > epsilon);
        return x;
    }

    public static double simpleIterationMethodThird(double initialGuess, double epsilon) {
        double x = initialGuess;
        double xPrevious;
        do {
            xPrevious = x;
            x = gThird(xPrevious);
        } while (Math.abs(x - xPrevious) > epsilon);
        return x;
    }

    public static void main(String[] args) {

        double firstInitialGuess = 1.0;
        double secondInitialGuess = 0.5; //?у меня вопросы
        double thirdInitialGuess = 3.0;
        double epsilon = 1e-6;

        System.out.println("Найденный корень: " + simpleIterationMethodFirst(firstInitialGuess, epsilon));
        System.out.println("Найденный корень: " + simpleIterationMethodSecond(secondInitialGuess, epsilon));
        System.out.println("Найденный корень: " + simpleIterationMethodThird(thirdInitialGuess, epsilon));
    }
}
