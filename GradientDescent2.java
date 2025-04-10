package org.example.HW2;

import java.util.Arrays;

public class GradientDescent2 {

    public static void main(String[] args) {
        // Тестовые данные (2D пример)
        double[][] points = {{0, 0}, {2, 2}};

        // Аналитическое решение
        double[] analyticSolution = computeAnalyticSolution(points);
        System.out.println("Аналитическое решение: " + Arrays.toString(analyticSolution));

        // Градиентный метод
        double[] initialGuess = {5, 5}; // Начальное приближение
        int p = points.length;
        double L = 2.5; // Константа Липшица (для данной задачи)
        double alpha = 2.0 / L; // Оптимальный шаг

        double[] gradientSolution = gradientDescent(points, initialGuess, alpha, 1e-6);
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

    // Градиентный спуск с бесконечным циклом и условием остановки
    private static double[] gradientDescent(double[][] points, double[] initial,
                                            double alpha, double epsilon) {
        int n = initial.length;
        int p = points.length;
        double L = 0.5; // Константа Липшица
        double[] x = Arrays.copyOf(initial, n);
        double[] prevX = Arrays.copyOf(x, n); // Предыдущее значение x

        while (true) {
            double[] grad = new double[n];

            // Вычисление градиента: 2 * (p * x - sum(x_k))
            for (int i = 0; i < n; i++) {
                double sum = 0;
                for (double[] point : points) {
                    sum += point[i];
                }
                grad[i] = 2 * (p * x[i] - sum);
            }

            // Проверка условия Липшица: ||grad_new - grad_prev|| <= L * ||x_new - x_prev||
            if (normDifference(grad, prevX) <= L * normDifference(x, prevX)) {
                System.out.println("Условие Липшица выполнено");
                break;
            }

            // Обновление x: x = x - alpha * grad
            prevX = Arrays.copyOf(x, n); // Сохраняем предыдущее значение
            for (int i = 0; i < n; i++) {
                x[i] -= alpha * grad[i];
            }
        }
        return x;
    }

    // Норма разницы между вектором градиента и предыдущими параметрами
    private static double normDifference(double[] vec1, double[] vec2) {
        double sum = 0;
        for (int i = 0; i < vec1.length; i++) {
            sum += Math.pow(vec1[i] - vec2[i], 2);
        }
        return Math.sqrt(sum);
    }
    
}