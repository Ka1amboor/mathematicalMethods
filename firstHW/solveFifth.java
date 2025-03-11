package org.example;

public class Main {

    static double gA(double x) { return Math.pow(2, x - 1); }

    static double gB(double x) { return -Math.log(x); }

    static double gC(double x) { return Math.exp(-x); }

    static double gD(double x) { return (x + Math.exp(-x)) / 2; }

    static double gE(double x) { return (3 * x + 5 * Math.exp(-x)) / 8; }

    static double gF(double x) { return Math.exp(2*x) - 1;}

    static double gG(double x) { return 0.5  -  Math.log(x); }

    static double gH(double x) { return Math.tan(x); }

    public static double simpleIterationMethod(double initialGuess, double epsilon, java.util.function.Function<Double, Double> gFunction) {
        double x = initialGuess;
        double xPrevious;
        do {
            xPrevious = x;
            x = gFunction.apply(xPrevious);
        } while (Math.abs(x - xPrevious) > epsilon);
        return x;
    }

    public static void main(String[] args) {
        double epsilon = 1e-6;
        System.out.println("Корень для функции A: " + simpleIterationMethod(1.0, epsilon, Main::gA));
        System.out.println("Корень для функции B: " + simpleIterationMethod(1.5, epsilon, Main::gB));
        System.out.println("Корень для функции C: " + simpleIterationMethod(1.0, epsilon, Main::gC));
        System.out.println("Корень для функции D: " + simpleIterationMethod(1.0, epsilon, Main::gD));
        System.out.println("Корень для функции E: " + simpleIterationMethod(1.0, epsilon, Main::gE));
        System.out.println("Корень для функции F: " + simpleIterationMethod(0.5, epsilon, Main::gF));
        System.out.println("Корень для функции G: " + simpleIterationMethod(1.5, epsilon, Main::gG));
        System.out.println("Корень для функции H: " + simpleIterationMethod(0.5, epsilon, Main::gH));
    }
}
