package org.example;

public class Main {

    public static double f(double x) {
        return Math.pow((x - 1), 3) * Math.sin(3.14 * x) * (Math.cos(2 * 3.14 * x - 1));
    }

    public static double fDerivative(double x) {
        return Math.pow((x - 1), 2) * (-2 * 3.14 * (x - 1) * Math.sin(3.14 * x) * Math.sin(2 * 3.14 * x) +
                3.14 * (x - 1) * Math.cos(3.14 * x) * (Math.cos(2 * 3.14 * x) - 1) + 3 * Math.sin(3.14 * x));
    }

    public static double newtonMethod(double initialGuess, double epsilon) {
        double x = initialGuess;
        while (Math.abs(f(x)) > epsilon) {
            x = x - f(x) / fDerivative(x);
        }
        return x;
    }

    public static void main(String[] args) {
        double epsilon = 1e-6;
        System.out.println("Корень для функции: " + newtonMethod(1.5, epsilon));

    }
}
