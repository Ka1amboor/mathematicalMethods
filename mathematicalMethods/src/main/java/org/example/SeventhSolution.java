package org.example;

public class SeventhSolution{

    public static double f(double x) {
        return Math.pow((x - 1), 3) * Math.sin(Math.PI * x) * (Math.cos(2 * Math.PI * x) - 1);
    }

    public static double fDerivative(double x) {
        return 3 * Math.pow((x - 1), 2) * Math.sin(Math.PI * x) * (Math.cos(2 * Math.PI * x) - 1) +
                Math.pow((x - 1), 3) * Math.PI * Math.cos(Math.PI * x) * (Math.cos(2 * Math.PI * x) - 1) -
                2 * Math.PI * Math.pow((x - 1), 3) * Math.sin(Math.PI * x) * Math.sin(2 * Math.PI * x);
    }

    public static double newtonMethod(double initialGuess, double epsilon) {
        double x = initialGuess;
        int iterations = 0;
        while (Math.abs(f(x)) > epsilon) {
            iterations++;
            x = x - f(x) / fDerivative(x);
        }
        System.out.println("Итерации обычного метода Ньютона: " + iterations);
        return x;
    }

    public static double newtonModifiedMethod(double initialGuess, double epsilon, int p) {
        double x = initialGuess;
        int iterations = 0;
        double sigma = -p;
        while (Math.abs(f(x)) > epsilon) {
            iterations++;
            x = x + sigma * f(x) / fDerivative(x);
        }
        System.out.println("Итерации модифицированного метода Ньютона: " + iterations);
        return x;
    }

    public static void main(String[] args) {
        double[] epsilons = {1e-3, 1e-4, 1e-5, 1e-6};
        int p = 3; // Кратность корня
        double[] initialGuesses = {1.5, 2.4, 3.4}; // Приближения к корням

        for (double epsilon : epsilons) {
            System.out.println("\nТочность: " + epsilon);
            for (double guess : initialGuesses) {
                System.out.println("\nНачальное приближение: " + guess);
                double rootNewton = newtonMethod(guess, epsilon);
                double rootNewtonMod = newtonModifiedMethod(guess, epsilon, p);
                System.out.println("Обычный метод Ньютона: " + rootNewton);
                System.out.println("Модифицированный метод Ньютона: " + rootNewtonMod);
            }
        }
    }
}