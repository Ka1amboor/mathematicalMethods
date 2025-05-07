package org.example.HW2;

import java.util.Arrays;

public class iterativeSolver4 {

    public static void main(String[] args) {
        // Матрица A и вектор f из условия
        double[][] A = {
                {28, -4, 16},
                {-36, 72, -40},
                {164, -8, -16}
        };
        double[] f = {1, -2, 4};

        // Решение системы Ax = f (A^{-1}f)
        double[] invA_f = solveLinearSystem(A, f);

        double[] y_k = new double[f.length];


        double epsilon = 1e-6;
        int iteration = 0;
        double normDiff;

        do {
            double[] y_k_plus_1 = new double[y_k.length];
            for (int i = 0; i < y_k.length; i++) {
                y_k_plus_1[i] = 0.5 * y_k[i] + 0.5 * invA_f[i];
            }

            normDiff = norm(subtractVectors(y_k_plus_1, y_k));
            y_k = y_k_plus_1;
            iteration++;
        } while (normDiff > epsilon);

        System.out.println("Решение:");
        printVector(y_k);
        System.out.println("Итераций: " + iteration);
    }

    // Решение СЛАУ методом Гаусса
    private static double[] solveLinearSystem(double[][] A, double[] f) {
        int n = f.length;
        double[][] augmented = new double[n][n + 1];

        // Создаем расширенную матрицу [A|f]
        for (int i = 0; i < n; i++) {
            System.arraycopy(A[i], 0, augmented[i], 0, n);
            augmented[i][n] = f[i];
        }

        // Прямой ход метода Гаусса с выбором ведущего элемента
        for (int i = 0; i < n; i++) {
            int maxRow = i;
            for (int k = i + 1; k < n; k++) {
                if (Math.abs(augmented[k][i]) > Math.abs(augmented[maxRow][i])) {
                    maxRow = k;
                }
            }
            swapRows(augmented, i, maxRow);

            double pivot = augmented[i][i];
            for (int j = i; j <= n; j++) {
                augmented[i][j] /= pivot;
            }

            for (int k = i + 1; k < n; k++) {
                double factor = augmented[k][i];
                for (int j = i; j <= n; j++) {
                    augmented[k][j] -= factor * augmented[i][j];
                }
            }
        }

        // Обратный ход
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            solution[i] = augmented[i][n];
            for (int j = i + 1; j < n; j++) {
                solution[i] -= augmented[i][j] * solution[j];
            }
        }

        return solution;
    }

    private static void swapRows(double[][] matrix, int i, int j) {
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }

    // Норма вектора (евклидова)
    private static double norm(double[] vector) {
        double sum = 0;
        for (double v : vector) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }


    private static double[] subtractVectors(double[] a, double[] b) {
        double[] result = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            result[i] = a[i] - b[i];
        }
        return result;
    }


    private static void printVector(double[] vector) {
        for (double v : vector) {
            System.out.printf("%.6f ", v);
        }
        System.out.println();
    }
}