package org.example;

public class Main {

    // Метод простой итерации с индивидуальным лямбда
    public static double simpleIteration(double initialGuess, double lambda, double epsilon) {
        double x = initialGuess;
        int maxIterations = 1000;

        for (int i = 0; i < maxIterations; i++) {
            double xNext = x + lambda * (Math.pow(x, 3) + 3 * Math.pow(x, 2) - 1);

            if (Math.abs(xNext - x) < epsilon) {
                return xNext;
            }

            x = xNext;
        }

        throw new RuntimeException("Метод не сошелся за " + maxIterations + " итераций");
    }

    public static double simpleIterationB(double initialGuess, double alpha, double epsilon) {
        double x = initialGuess;
        int maxIterations = 10000;
        for (int i = 0; i < maxIterations; i++) {
            double xNext = x  + alpha * Math.pow(x, 4) - Math.pow(x, 3);
            if(Math.abs(xNext - x) < epsilon) {
                return xNext;
            }
            x = xNext;
        }
        throw new RuntimeException("Метод не сошелся за " + maxIterations + " итераций");
    }

    public static double simpleIterationC(double initialGuess, double alpha, double epsilon) {
        double x = initialGuess;
        int maxIterations = 10000;
        for (int i = 0; i < maxIterations; i++) {
            double xNext = x  + alpha * Math.pow(x,2) - alpha * 3 * x + 2 * alpha;
            if(Math.abs(xNext - x) < epsilon) {
                return xNext;
            }
            x = xNext;
        }
        throw new RuntimeException("Метод не сошелся за " + maxIterations + " итераций");
    }


    // Проверка на сходимость метода в данной точке
    public static boolean isConverging(double x, double lambda) {
        double derivative = Math.abs(1 + lambda * (3 * x * x + 6 * x));
        return derivative < 1;
    }

    public static boolean isConvergingB(double x, double lambda) {
        double derivative = Math.abs(1 + lambda * (4* Math.pow(x, 3) - 3 * Math.pow(x, 2)));
        return derivative < 1;
    }

    public static boolean isConvergingC(double x, double lambda) {
        double derivative = Math.abs(1 + lambda * 2 * x - 3 * lambda);
        return derivative < 1;
    }

    public static void main(String[] args) {
        double epsilon = 1e-6;

        // Подбираем лямбда для каждого начального приближения
        double[][] initialGuessesWithLambdas = {
                {-3.0, -0.01},
                {-1.0, 0.01},
                {0.5, -0.01}
        };

        for (double[] pair : initialGuessesWithLambdas) {
            double guess = pair[0];
            double lambda = pair[1];

            if (!isConverging(guess, lambda)) {
                System.out.println("Метод может не сойтись для x0 = " + guess);
                continue;
            }

            try {
                double root = simpleIteration(guess, lambda, epsilon);
//                if(Math.abs(root) < 0.5) {
//                    root = 0.0;
//                }
                System.out.println("Найденный корень: x ≈ " + root);
            } catch (RuntimeException e) {
                System.out.println("Ошибка для x0 = " + guess + ": " + e.getMessage());
            }
        }

        double[][] initialGuessesWithLambdasB = {
                {1.0, -0.01},
                {1.3, -0.05},

        };

        for (double[] pair : initialGuessesWithLambdasB) {
            double guess = pair[0];
            double lambda = pair[1];

            if (!isConvergingB(guess, lambda)) {
                System.out.println("Метод может не сойтись для x0 = " + guess);
                continue;
            }

            try {
                double root = simpleIterationB(guess, lambda, epsilon);
                System.out.println("Найденный корень: x ≈ " + root);
            } catch (RuntimeException e) {
                System.out.println("Ошибка для x0 = " + guess + ": " + e.getMessage());
            }
        }

        double[][] initialGuessesWithLambdasC = {
                {0.7, 0.01},
                {2.5, -0.05},

        };

        for (double[] pair : initialGuessesWithLambdasC) {
            double guess = pair[0];
            double lambda = pair[1];

            if (!isConvergingC(guess, lambda)) {
                System.out.println("Метод может не сойтись для x0 = " + guess);
                continue;
            }

            try {
                double root = simpleIterationC(guess, lambda, epsilon);
                System.out.println("Найденный корень: x ≈ " + root);
            } catch (RuntimeException e) {
                System.out.println("Ошибка для x0 = " + guess + ": " + e.getMessage());
            }
        }
    }
}
