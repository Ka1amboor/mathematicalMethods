package org.example.HW2;

public class SteepestDescent1 {

    public static void main(String[] args) {
        double[][] A = {
                {4, -1, 0, -1, 0, 0},
                {-1, 4, -1, 0, -1, 0},
                {0, -1, 4, 0, 0, -1},
                {-1, 0, 0, 4, -1, 0},
                {0, -1, 0, -1, 4, -1},
                {0, 0, -1, 0, -1, 4}
        };
        double[] b = {0, 5, 0, 6, -2, 6};
        double[] x = new double[6]; // Начальное приближение (нулевой вектор)
        double epsilon = 1e-6;

        double[] grad;
        int maxIterations = 1000;

        for (int iter = 0; iter < maxIterations; iter++) {
            grad = subtract(matrixVectorMultiply(A, x), b);

            if (norm(grad) < epsilon) {
                System.out.println("Сошлось на итерации: " + iter);
                break;
            }

            double[] d = vectorMultiply(grad, -1.0);
            double alpha = dotProduct(d, d) / dotProduct(d, matrixVectorMultiply(A, d));

            x = vectorAdd(x, vectorMultiply(d, alpha));
        }

        System.out.println("Решение x:");
        for (double xi : x) {
            System.out.printf("%.6f\n", xi);
        }
    }

    // Умножение матрицы на вектор
    private static double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        double[] result = new double[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    // Скалярное произведение векторов
    private static double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    // Норма вектора
    private static double norm(double[] vector) {
        return Math.sqrt(dotProduct(vector, vector));
    }

    // Сложение векторов
    private static double[] vectorAdd(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] + b[i];
        }
        return result;
    }

    // Умножение вектора на скаляр
    private static double[] vectorMultiply(double[] vector, double scalar) {
        double[] result = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            result[i] = vector[i] * scalar;
        }
        return result;
    }

    // Вычитание векторов
    private static double[] subtract(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }
}