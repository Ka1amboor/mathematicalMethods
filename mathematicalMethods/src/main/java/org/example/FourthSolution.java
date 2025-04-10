package org.example;

import java.util.Scanner;

public class FourthSolution {
    static final double EPSILON = 1e-6; // Заданная точность
    static final int MAX_ITERATIONS = 1000; // Ограничение по числу итераций

    // Функция phi(x) для первого случая
    public static double phi1(double x, double a, double b, double c) {
        return c + a * Math.sin(x) * Math.sin(x) + b * Math.cos(x) * Math.cos(x);
    }

    // Производная phi1'(x)
    public static double phi1Derivative(double x, double a, double b) {
        return 2 * (a - b) * Math.sin(x) * Math.cos(x);
    }

    // Функция phi(x) для второго случая
    public static double phi2(double x, double a, double b, double c) {
        return c + a * Math.exp(-b * x * x);
    }

    // Производная phi2'(x)
    public static double phi2Derivative(double x, double a, double b) {
        return -2 * a * b * x * Math.exp(-b * x * x);
    }

    // Метод проверки условия сходимости |phi'(x)| < 1
    public static boolean checkConvergence(double a, double b, int caseNumber) {
        if (caseNumber == 1) {
            return Math.abs(a - b) < 1;
        } else if (caseNumber == 2) {
            return a * Math.sqrt(2 * b) < Math.exp(0.5);
        }
        return false;
    }

    // Метод простой итерации
    public static void simpleIteration(double a, double b, double c, int caseNumber) {
        if (!checkConvergence(a, b, caseNumber)) {
            System.out.println("Условие сходимости не выполняется, метод может не сойтись.");
            return;
        }

        double x = 0.0; // Начальное приближение
        int iterations = 0;
        double xNext;

        System.out.println("Итерации:");
        do {
            xNext = (caseNumber == 1) ? phi1(x, a, b, c) : phi2(x, a, b, c);
            System.out.printf("Итерация %d: x = %.6f%n", iterations + 1, xNext);

            if (Math.abs(xNext - x) < EPSILON) {
                System.out.printf("Решение найдено: x = %.6f%n", xNext);
                return;
            }

            x = xNext;
            iterations++;
             }while(true);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int caseNumber = 2;


        double a = 0.3;


        double b = 2.0;


        double c = 1.0;

        simpleIteration(a, b, c, caseNumber);

        scanner.close();
    }
}