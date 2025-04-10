package org.example;

public class SimpleIterationMethodThirdTask {

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
//            System.out.println(i);
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
            double xNext = x  + alpha * (Math.pow(x,3)  -  3 * Math.pow(x,2) + 2);
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
                {-2.5, -2.0/9.0},
                {-1.0, 1.0/3.0},
                {0.5, -2.0/9.0}
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
                System.out.println("Найденный корень: x ≈ " + root);
            } catch (RuntimeException e) {
                System.out.println("Ошибка для x0 = " + guess + ": " + e.getMessage());
            }
        }

        double[][] initialGuessesWithLambdasB = {
                {0.6, 1.0/6.75},
               {1.3, -1.0/10.0},
//                {0.95, -0.1},
//                {0.3, 1.0/1.25},


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
//                {0.7, 1.0/3.0},
//                {3.0, -1.0/5.0},
                {0.9, 1.0/3.0},
                {1.9, -2.0/10.0},

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
