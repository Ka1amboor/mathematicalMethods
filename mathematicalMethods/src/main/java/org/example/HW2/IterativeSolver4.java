package org.example.HW2;

import java.util.Arrays;
//TODO:перепроверить!!
public class IterativeSolver4 {

    public static void main(String[] args) {
        // Матрица A и вектор f из условия
        double[][] A = {
                {28, -4, 16},
                {-36, 72, -40},
                {164, -8, -16}
        };
        double[] f = {1, -2, 4};

        // Выбираем матрицу B как диагональную с элементами A[i][i]
        double[][] B = {
                {28, 0, 0},
                {0, 72, 0},
                {0, 0, 16}  // Используем абсолютное значение для третьего элемента
        };

        // Начальное приближение
        double[] y0 = {0, 0, 0};
        int maxIterations = 100;
        double tolerance = 1e-6;

        // Запуск итерационного процесса
        double[] solution = solve(A, B, f, y0, maxIterations, tolerance);

        System.out.println("Приближенное решение: " + Arrays.toString(solution));
    }

    private static double[] solve(double[][] A, double[][] B, double[] f,
                                  double[] y0, int maxIter, double tol) {
        int n = y0.length;
        double[] yCurrent = Arrays.copyOf(y0, n);
        double[] yNext = new double[n];

        for (int iter = 0; iter < maxIter; iter++) {
            // Вычисляем (2B - A) * yCurrent
            double[] term1 = matrixVectorMultiply(subtractMatrices(multiplyMatrixByScalar(B, 2), A), yCurrent);

            // Вычисляем B^{-1} * (term1 + f)
            double[] rhs = vectorAdd(term1, f);
            double[] temp = solveDiagonalSystem(B, rhs); // Решаем B * temp = rhs

            // yNext = 0.5 * temp
            for (int i = 0; i < n; i++) {
                yNext[i] = 0.5 * temp[i];
            }

            // Проверка сходимости
            if (norm(vectorSubtract(yNext, yCurrent)) < tol) {
                System.out.println("Сходимость достигнута на итерации " + iter);
                return yNext;
            }

            // Обновляем текущее приближение
            yCurrent = Arrays.copyOf(yNext, n);
        }
        return yCurrent;
    }

    // Решение диагональной системы B * x = rhs
    private static double[] solveDiagonalSystem(double[][] B, double[] rhs) {
        double[] x = new double[rhs.length];
        for (int i = 0; i < B.length; i++) {
            x[i] = rhs[i] / B[i][i]; // Простое деление для диагональной матрицы
        }
        return x;
    }

    // Вспомогательные методы:
    // Умножение матрицы на скаляр
    private static double[][] multiplyMatrixByScalar(double[][] matrix, double scalar) {
        double[][] result = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = matrix[i][j] * scalar;
            }
        }
        return result;
    }

    // Вычитание матриц
    private static double[][] subtractMatrices(double[][] m1, double[][] m2) {
        double[][] result = new double[m1.length][m1[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                result[i][j] = m1[i][j] - m2[i][j];
            }
        }
        return result;
    }

    // Умножение матрицы на вектор
    private static double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        double[] result = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    // Сложение векторов
    private static double[] vectorAdd(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] + v2[i];
        }
        return result;
    }

    // Вычитание векторов
    private static double[] vectorSubtract(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
        }
        return result;
    }

    // Норма вектора
    private static double norm(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }
}
