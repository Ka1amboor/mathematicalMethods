package org.example.HW2;

import java.util.Arrays;
public class GradientDescent2 {

    public static void main(String[] args) {
        
        double[][] points = {
                {3, 1},
                {2, 2}

        };

        // Аналитическое решение
        double[] analyticSolution = computeAnalyticSolution(points);
        System.out.println("Аналитическое решение: " + Arrays.toString(analyticSolution));

        // Градиентный метод
        double[] initialGuess = {0, 0}; // Начальное приближение
        double alpha = 0.1;
        double[] gradientSolution = gradientDescent(points, initialGuess, alpha, 1e-6);

        System.out.println("Решение с помощью градиентного спуска: " + Arrays.toString(gradientSolution));
    }

    // Аналитическое решение (среднее арифметическое)
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
                                            double alpha, double epsilon) {
        int n = initial.length;
        int p = points.length;
        double[] x = Arrays.copyOf(initial, n);

        while(true) {
            double[] grad = new double[n];

            // Вычисление градиента: 2*(p * x - sum(x_k))
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (double[] point : points) {
                    sum += point[i];
                }
                grad[i] = 2*(p * x[i] - sum);
            }

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