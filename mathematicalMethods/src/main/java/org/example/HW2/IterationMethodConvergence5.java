package org.example.HW2;

import javax.accessibility.AccessibleKeyBinding;
import java.util.Arrays;

import java.util.Arrays;

public class IterationMethodConvergence5 {

    public static void main(String[] args) {
        // Матрица B (5B из условия)
        double[][] BFive = {
                {5, -5, 0},
                {10, 15, 20},
                {-25, 0, 35}
        };

        double[][] B = {
                {1, -1, 0},
                {2, 3, 4},
                {-5, 0, 7}
        };



        // Матрица A
        double[][] A = {
                {-1, 2, 5},
                {3, -1, 1},
                {4, -3, 2}
        };

        // Вектор f
        double[] f = {1, 0, 3};

        // Начальное приближение
        double[] y0 = {0, 0, 0};
        double tolerance = 1e-6;

        // Запуск итерационного процесса
        double[] solution = iterate(BFive, A, f, y0, tolerance, B);

        System.out.println("Приближенное решение: " + Arrays.toString(solution));
    }

    // Итерационный процесс
    private static double[] iterate(double[][] BFive, double[][] A, double[] f,
                                    double[] y0, double tol, double[][] B) {
        int n = y0.length;
        double[] yCurrent = Arrays.copyOf(y0, n);

        // Вычисление матрицы перехода C = (5B - A)/5 и B^{-1}
        double[][] fiveBMinusA = subtractMatrices(BFive, A);
        double[][] BInv = invertMatrix(B);

        // Матрица C = (1/5) * B^{-1} * (5B - A)
        double[][] C = multiplyMatrices(BInv, fiveBMinusA);
        C = multiplyMatrixByScalar(C, 0.2);

        if(check(C)) {
            System.out.println("Метод расходится! Проверьте условие сходимости.");
            return yCurrent;
        }

        // Вектор d = (1/5) * B^{-1} * f
        double[] d = matrixVectorMultiply(BInv, f);
        d = multiplyVectorByScalar(d, 0.2);

        while(true) {
            int iter = 0;
            double[] yNext = vectorAdd(matrixVectorMultiply(C, yCurrent), d);


            // Проверка сходимости
            if (norm(vectorSubtract(yNext, yCurrent)) < tol) {
                System.out.println("Сходимость достигнута на итерации " + iter);
                return yNext;
            }
            iter++;
            yCurrent = yNext;
        }
    }

    // Норма вектора
    private static double norm(double[] vec) {
        double sum = 0;
        for (double v : vec) {
            sum += v * v;
        }
        return Math.sqrt(sum);
    }

    // Вычитание векторов
    private static double[] vectorSubtract(double[] v1, double[] v2) {
        double[] result = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            result[i] = v1[i] - v2[i];
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

    public static double[][] multiplyMatrices(double[][] A, double[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int rowsB = B.length;
        int colsB = B[0].length;

        // Проверка совместимости матриц
        if (colsA != rowsB) {
            throw new IllegalArgumentException("Несовместимые размеры матриц!");
        }

        // Создание матрицы-результата
        double[][] C = new double[rowsA][colsB];

        // Вычисление каждого элемента
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    // Обращение матрицы методом Гаусса-Жордана
    private static double[][] invertMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] augmented = new double[n][2*n];
        // Создаем расширенную матрицу [matrix | I]
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);
            augmented[i][n + i] = 1;
        }

        // Прямой ход
        for (int i = 0; i < n; i++) {
            // Поиск ведущего элемента
            int pivot = i;
            for (int j = i; j < n; j++) {
                if (Math.abs(augmented[j][i]) > Math.abs(augmented[pivot][i])) {
                    pivot = j;
                }
            }
            // Перестановка строк
            double[] temp = augmented[i];
            augmented[i] = augmented[pivot];
            augmented[pivot] = temp;

            // Нормализация ведущей строки
            double div = augmented[i][i];
            for (int j = i; j < 2*n; j++) {
                augmented[i][j] /= div;
            }

            // Исключение
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double factor = augmented[j][i];
                    for (int k = i; k < 2*n; k++) {
                        augmented[j][k] -= factor * augmented[i][k];
                    }
                }
            }
        }

        // Извлечение обратной матрицы
        double[][] inv = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmented[i], n, inv[i], 0, n);
        }

        return inv;
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

    public static double[] multiplyVectorByScalar(double[] v, double scalar) {
        double[] result = new double[v.length];
        for (int i = 0; i < v.length; i++) {
            result[i] = v[i] * scalar;
        }
        return result;
    }

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

    private static boolean check(double [][] matrix) {
        boolean flag = false;
        for (int i = 0 ;i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(Math.abs(matrix[i][j]) > 1) {
                    flag = true;
                }
            }
        }
        return flag;
    }

}