package org.example.HW2;
//последнее задание
public class IterationProcess8 {
    private static final int SIZE = 4; // Размерность системы 4x4
    private static final int K_MAX = 5000;
    private static final double[] EPSILONS = {1e-2, 1e-3, 1e-4, 1e-6}; // Заданные точности
    private static int amountOfOperations;

    // Матрица системы из условия
    private static final double[][] MATRIX = {
            {2.8, 2.1, -1.3, 0.3},
            {-1.4, 4.5, -7.7, 1.3},
            {0.6, 2.1, -5.8, 2.4},
            {3.5, -6.5, 3.2, -7.9}
    };

    // Вектор правой части [1, 1, 1, 1]
    private static final double[] F_VECTOR = {1, 1, 1, 1};

    public static double[] multiply(double[][] matrix, double[] x) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * x[j];
            }
        }
        return result;
    }

    public static double multiply(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    public static double[] subtract(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }
    //минимальных невязок
    public static double[] solveMinimalResidual(double[][] A, double[] b, double epsilon) {
        double[] x = new double[SIZE];
        x = new double[]{0.5, 0.5, 0.5, 0.5};// Начальное приближение [0, 0, 0, 0]
        double[] r = subtract(multiply(A, x), b);
        amountOfOperations = 0;

        while (getNorm(r) > epsilon && amountOfOperations < K_MAX) {
            double[] Ar = multiply(A, r);
            double tau = multiply(r, Ar) / multiply(Ar, Ar);
            x = subtract(x, scalarMultiply(r, tau));
            r = subtract(multiply(A, x), b);
            amountOfOperations++;
        }
        return x;
    }
    //градиентный спуск
    public static double[] solveSteepestDescent(double[][] A, double[] b, double epsilon) {
        double[] x = new double[SIZE];
        x = new double[]{0.0, 0.0, 0.0, 0.0};
        double[] r = subtract(multiply(A, x), b);
        amountOfOperations = 0;

        while (getNorm(r) > epsilon && amountOfOperations < K_MAX) {
            double[] Ar = multiply(A, r);
            double tau = multiply(r, r) / multiply(r, Ar);
            x = subtract(x, scalarMultiply(r, tau));
            r = subtract(multiply(A, x), b);
            amountOfOperations++;
        }
        return x;
    }

    public static double[] scalarMultiply(double[] v, double scalar) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i] * scalar;
        }
        return result;
    }

    public static double getNorm(double[] v) {
        double sum = 0;
        for (double val : v) {
            sum += val * val;
        }
        return Math.sqrt(sum);
    }

    public static void main(String[] args) {
        // Решение для разных точностей
        for (double eps : EPSILONS) {
            System.out.println("\nТочность: " + eps);

            // Метод минимальных невязок
            double[] xResidual = solveMinimalResidual(MATRIX, F_VECTOR, eps);
            System.out.println("Метод минимальных невязок:");
            System.out.println("Итераций: " + amountOfOperations);
            System.out.println("Решение: " + java.util.Arrays.toString(xResidual));

            // Метод скорейшего спуска
            double[] xGradient = solveSteepestDescent(MATRIX, F_VECTOR, eps);
            System.out.println("Метод скорейшего спуска:");
            System.out.println("Итераций: " + amountOfOperations);
            System.out.println("Решение: " + java.util.Arrays.toString(xGradient));
        }
    }
}