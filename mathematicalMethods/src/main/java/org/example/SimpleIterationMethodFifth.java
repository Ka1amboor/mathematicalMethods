package org.example;

public class SimpleIterationMethodFifth {
    /*                           a) регион                                                                        */
    // Функция итерации phi(x) = 2^(x - 1)
    public static double phi(double x) {
        return Math.pow(2, x - 1);
    }

    // Производная phi'(x) = ln(2) * 2^(x - 1)
    public static double phiDerivative(double x) {
        return Math.log(2) * Math.pow(2, x - 1);
    }

    // Проверка условия сходимости |phi'(x)| < 1
    public static boolean checkConvergence(double x) {
        return Math.abs(phiDerivative(x)) < 1;
    }

    // Метод простой итерации
    public static void simpleIteration(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergence(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iter = 0;
        while (true) {
            double xNext = phi(x);
            System.out.printf("Итерация %d: x = %.6f\n", iter + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iter++;
            x = xNext;
        }

    }
    /*                           б) регион                                                                         */
    public static double phiB(double x) {
        return -Math.log(x);
    }

    public static double phiDerivativeB(double x) {
        if(x != 0) {
            return - 1.0/x;
        }
        else {
            throw new RuntimeException("division by zero detected");
        }

    }

    public static boolean checkConvergenceB(double x) {
        return Math.abs(phiDerivativeB(x)) < 1;
    }

    public static void simpleIterationB(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceB(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iteration = 0;
        while (true) {
            double xNext = phiB(x);
            System.out.printf("Итерация %d: x = %.6f\n", iteration + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            if(iteration >= 100) {
                System.out.println("расходится");
                return;
            }
            iteration++;
            x = xNext;
        }

    }
    /*                           в) регион                                                                         */
    public static double phiV(double x) {
        return Math.pow(2.7182818459, -x);
    }

    public static double phiDerivativeV(double x) {
        return - Math.pow(2.7182818459, -x);

    }

    public static boolean checkConvergenceV(double x) {
        return Math.abs(phiDerivativeB(x)) < 1;
    }

    public static void simpleIterationV(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceV(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iteration = 0;
        while(true) {
            double xNext = phiV(x);
            System.out.printf("Итерация %d: x = %.6f\n", iteration + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iteration++;
            x = xNext;
        }

    }

    /*                           d) регион                                                                         */
    public static double phiD(double x) {
        return ((x + Math.pow(2.7182818459, -x))/2.0);
    }

    public static double phiDerivativeD(double x) {
        return 1.0/2.0 - (1.0/2.0*Math.pow(2.7182818459, -x));

    }

    public static boolean checkConvergenceD(double x) {
        return Math.abs(phiDerivativeB(x)) < 1;
    }

    public static void simpleIterationD(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceD(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iteration = 0;
        while (true) {
            double xNext = phiD(x);
            System.out.printf("Итерация %d: x = %.6f\n", iteration + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iteration++;
            x = xNext;
        }

    }
    /*                           e) регион                                                                         */
    public static double phiE(double x) {
        return (3*x + 5*Math.pow(2.7182818459, -x))/8.0;
    }

    public static double phiDerivativeE(double x) {
        return 3.0/8.0 - (5.0/8.0 * Math.pow(2.7182818459, -x));

    }

    public static boolean checkConvergenceE(double x) {
        return Math.abs(phiDerivativeE(x)) < 1;
    }

    public static void simpleIterationE(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceE(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iteration = 0;
        while (true) {
            double xNext = phiE(x);
            System.out.printf("Итерация %d: x = %.6f\n", iteration + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iteration++;
            x = xNext;
        }


    }
    /*                           f) регион                                                                         */
    public static double phiF(double x) {
        return Math.pow(2.7182818459, 2*x) - 1;
    }

    public static double phiDerivativeF(double x) {
        return 2*Math.pow(2.7182818459, 2*x);

    }

    public static boolean checkConvergenceF(double x) {
        return Math.abs(phiDerivativeE(x)) < 1;
    }

    public static void simpleIterationF(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceF(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        int iteration = 0;
        double x = x0;
        while(true) {
            double xNext = phiF(x);
            System.out.printf("Итерация %d: x = %.6f\n", iteration + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iteration++;
            x = xNext;
        }

    }
    /*                           g) регион                                                                         */
    public static double phiG(double x) {
        return 1.0/2.0 - Math.log(x);
    }

    public static double phiDerivativeG(double x) {
        if(x != 0)
        {
            return -1.0/x;
        }
        else {
            throw new RuntimeException("detected division by zero");
        }

    }

    public static boolean checkConvergenceG(double x) {
        return Math.abs(phiDerivativeG(x)) < 1;
    }

    public static void simpleIterationG(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceG(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }

        double x = x0;
        int iterations = 0;
        while (true) {
            double xNext = phiF(x);
            System.out.printf("Итерация %d: x = %.6f\n", iterations + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            if(iterations >= 100) {
                System.out.println("расходится");
                return;
            }
            iterations++;
            x = xNext;
        }


    }
    /*                           h) регион                                                                         */
    public static double phiH(double x) {
        return Math.tan(x);
    }

    public static double phiDerivativeH(double x) {
        return 1.0/Math.pow(Math.cos(x), 2);

    }

    public static boolean checkConvergenceH(double x) {
        return Math.abs(phiDerivativeH(x)) < 1;
    }

    public static void simpleIterationH(double x0, double tol, int maxIter) {
        System.out.println("Начальное приближение x0 = " + x0);

        if (!checkConvergenceH(x0)) {
            System.out.println("Метод может не сойтись, так как x0 не удовлетворяет |φ'(x)| < 1");
            return;
        }
        int iterations = 0;
        double x = x0;
        while(true)
        {
            double xNext = phiH(x);
            System.out.printf("Итерация %d: x = %.6f\n", iterations + 1, xNext);

            if (Math.abs(xNext - x) < tol) {
                System.out.println("Метод сошелся к " + xNext);
                return;
            }
            iterations++;
            x = xNext;
        }

    }

    public static void main(String[] args) {
/*                           a) регион  x < 1,52                                                                       */

//        System.out.println("------------------------------a) 2^(x-1)");
       double[] x0Values = {-1.0, 2.0, 1.15, 3.53};
        double tolerance = 1e-6;
        int maxIterations = 100;

        for (double x0 : x0Values) {
            System.out.println("------------------------------");
            simpleIteration(x0, tolerance, maxIterations);
        }
        /*                           б) регион                                                                         */
//        System.out.println("------------------------------b) -ln(x)");
//        double[] x0ValuesB = {-7.0, 1.0, 1.15, -8.0};
//
//
//        for (double x0 : x0ValuesB) {
//            System.out.println("------------------------------");
//            simpleIterationB(x0, tolerance, maxIterations);
//        }
        /*                           в) регион                                                                         */
//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesV = {1.1, 1.2, 1.15, 1.53};
//
//
//        for (double x0 : x0ValuesV) {
//            System.out.println("------------------------------");
//            simpleIterationV(x0, tolerance, maxIterations);
//        }

        /*                           d) регион                                                                        */
//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesD = {1.09861, 1.1, 1.15, 1.53};
//
//
//        for (double x0 : x0ValuesD) {
//            System.out.println("------------------------------");
//            simpleIterationD(x0, tolerance, maxIterations);
//        }

        /*                           e) регион          x > ln(11/5) 0,788457                                                               */
//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesE = {-800.0, 1.0, 1.15, 1.53};
//
//
//        for (double x0 : x0ValuesE) {
//            System.out.println("------------------------------");
//            simpleIterationE(x0, tolerance, maxIterations);
//        }
        /*                           f) регион                x < -1/2*ln(2)     -0.345674                                                    */
//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesF = {-0.0001, 0.0, -0.346574, -0.4};
//
//
//        for (double x0 : x0ValuesF) {
//            System.out.println("------------------------------");
//            simpleIterationF(x0, tolerance, maxIterations);
//        }

        /*                           g) регион                                                                    */

//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesG = {1.2, -0.01, 1.1, 1.53};
//
//
//        for (double x0 : x0ValuesG) {
//            System.out.println("------------------------------");
//            simpleIterationG(x0, tolerance, maxIterations);
//        }

        /*                           h) регион         не сходится                                                           */
//        System.out.println("------------------------------b) e^(-x)");
//        double[] x0ValuesH = {-7.0, 1.0, 1.15, 1.53};
//
//
//        for (double x0 : x0ValuesH) {
//            System.out.println("------------------------------");
//            simpleIterationH(x0, tolerance, maxIterations);
//        }

    }
}