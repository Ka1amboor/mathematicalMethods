package org.example.HW2;

import java.util.Arrays;

public class GradientDescent2 {

    public static void main(String[] args) {
        // Тестовые данные (2D пример)
        double[][] points = {
                {0, 0},
                {2, 2}
        };

        // Аналитическое решение
        double[] analyticSolution = computeAnalyticSolution(points);
        System.out.println("Аналитическое решение: " + Arrays.toString(analyticSolution));

        // Градиентный метод
        double[] initialGuess = {5, 5}; // Начальное приближение
        double alpha = 0.1; // Шаг обучения
        double[] gradientSolution = gradientDescent(points, initialGuess, alpha, 1000, 1e-6);

        System.out.println("Градиентное решение: " + Arrays.toString(gradientSolution));
    }

    // Аналитическое решение (среднее по координатам)
    private static double[] computeAnalyticSolution(double[][] points) {
        int n = points[0].length;
        double[] sum = new double[n];
        for (double[] point : points) {
            for (int i = 0; i < n; i++) {
                sum[i] += point[i];
            }
        }
        for (int i = 0; i < n; i++) {
            sum[i] /= points.length;
        }
        return sum;
    }

    // Градиентный спуск
    private static double[] gradientDescent(double[][] points, double[] initial,
                                            double alpha, int maxIter, double epsilon) {
        int n = initial.length;
        int p = points.length;
        double[] x = Arrays.copyOf(initial, n);

        for (int iter = 0; iter < maxIter; iter++) {
            double[] grad = new double[n];

            // Вычисление градиента: 2 * (p * x - sum(x_k))
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (double[] point : points) {
                    sum += point[i];
                }
                grad[i] = 2 * (p * x[i] - sum);
            }

            // Проверка сходимости
            if (norm(grad) < epsilon) break;

            // Обновление x: x = x - alpha * grad
            for (int i = 0; i < n; i++) {
                x[i] -= alpha * grad[i];
            }
        }
        return x;
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
