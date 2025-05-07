package org.example.HW2;

import java.util.Arrays;

public class GradientDescentSolver3 {

    public static void main(String[] args) {
        // Тестовые данные
        double[][] A = {
                {1, 1},
                {1, -1}
        };
        double[] b = {2, 0};
        double[] xInitial = {0, 0}; // Начальное приближение
        double alpha = 0.4;
        double epsilon = 1e-6;


        // Градиентный спуск
        double[] x = gradientDescent(A, b, xInitial, alpha, epsilon);
        System.out.println("Результат градиентного метода: " + Arrays.toString(x));
    }

    // Градиентный спуск
    private static double[] gradientDescent(double[][] A, double[] b, double[] x0,
                                            double alpha, double epsilon) {
        int m = x0.length;
        double[] x = Arrays.copyOf(x0, m);
        int iter = 0;

        while(true) {
            double[] grad = computeGradient(A, b, x);

            if (norm(grad) < epsilon) {
                System.out.println("Сходимость достигнута");
                break;
            }


            // Обновление x: x = x - alpha * grad
            for (int i = 0; i < m; i++) {
                x[i] -= alpha * grad[i];
            }
            iter++;
        }
        System.out.println("Итерация:" + iter + 1);
        return x;
    }

    // Вычисление градиента: 2*(A*x - b)*A^T
    private static double[] computeGradient(double[][] A, double[] b, double[] x) {
        double[] AxMinusB = matrixVectorMultiply(A, x);
        for (int i = 0; i < AxMinusB.length; i++) {
            AxMinusB[i] -= b[i];
        }

        double[] grad = transposeMultiply(A, AxMinusB);
        for (int i = 0; i < grad.length; i++) {
            grad[i] *= 2;
        }
        return grad;
    }

    // Умножение матрицы на вектор
    private static double[] matrixVectorMultiply(double[][] matrix, double[] vector) {
        int n = matrix.length;
        int m = vector.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    // Умножение транспонированной матрицы на вектор
    private static double[] transposeMultiply(double[][] matrix, double[] vector) {
        int n = matrix[0].length;
        int m = matrix.length;
        double[] result = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i] += matrix[j][i] * vector[j];
            }
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
