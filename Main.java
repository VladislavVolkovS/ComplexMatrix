import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //Для ввода с клавиатуры
        int menu = 1;
        MatrixWithComplex test = new MatrixWithComplex(0);
        while (menu>0) {
            System.out.println("Выберите действие:");
            System.out.println("1 - Проверить класс комплексных чисел");
            System.out.println("2 - Задать матрицу c клавиатуры с элементами");
            System.out.println("3 - Задать пустую матрицу c клавиатуры указав размеры(по двум параметрам)");
            System.out.println("4 - Задать пустую квадратную матрицу c клавиатуры указав размеры(по одному параметру)");
            System.out.println("5 - Умножить на комплексное число(константу) текущую матрицу");
            System.out.println("6 - Сложить текущую матрицу с новой(ввод с клавиатуры) и посмотреть результат");
            System.out.println("7 - Умножить текущую матрицу на новую(ввод с клавиатуры) и посмотреть результат");
            System.out.println("8 - Показать транспонированную текущую матрицу");
            System.out.println("9 - Вычислить детерминант текущей матрицы");
            System.out.println("10 - !!!Вывести текущую матрицу");
            System.out.println("0 - Завершить программу");
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("Проверка конструкторов:");
                    System.out.println("Проверка конструктора без аргументов(z1):");
                    Complex z1 = new Complex();
                    z1.out();
                    System.out.println("Проверка конструктора с 1 аргументом(действительная часть) (z2):");
                    Complex z2 = new Complex(45.1);
                    z2.out();
                    System.out.println("Проверка конструктора с 2 аргументами(z3):");
                    Complex z3 = new Complex(8.3, -3.6);
                    z3.out();
                    System.out.println("Проверка конструктора копирования z3(z4):");
                    Complex z4 = new Complex(z3);
                    z4.out();
                    System.out.println("Проверка ввода с клавиатуры(z5):");
                    System.out.println("Введите параметры комплексного числа через пробел:");
                    Complex z5 = new Complex(sc.nextDouble(), sc.nextDouble());
                    z5.out();
                    System.out.println("Вывод тригонометрической формы z4:");
                    z4.out_trig_form();
                    System.out.println("Вычисление корня n степени z2:");
                    System.out.println("Введите степень n:");
                    int b = sc.nextInt();
                    Complex[] z = z2.sqrt(b);
                    for (int i = 0; i < b; i++) {
                        if (z[i].im < 0) {
                            System.out.println("z[" + i + "] = " + z[i].real + z[i].im + "i");
                        } else if (z[i].im == 0) {
                            System.out.println("z[" + i + "] = " + z[i].real);
                        } else if (z[i].im > 0) {
                            System.out.println("z[" + i + "] = " + z[i].real + "+" + z[i].im + "i");
                        }
                    }
                    break;

                case 2:
                    //ввод матрицы с клавиатуры
                    test = new MatrixWithComplex();
                    test.out();
                    break;

                case 3:
                    System.out.println("Введите размеры матрицы n, m");
                    int n = sc.nextInt();
                    int m = sc.nextInt();
                    if (n < 0 || m < 0) {
                        throw new ArithmeticException("Задан неверный размер матрицы");
                    }
                    test = new MatrixWithComplex(n, m);
                    test.out();
                    break;
                case 4:
                    System.out.println("Введите размер матрицы n");
                    int p = sc.nextInt();
                    if (p < 0) {
                        throw new ArithmeticException("Задан неверный размер матрицы");
                    }
                    test = new MatrixWithComplex(p);
                    test.out();
                    break;
                case 5:
                    //тестируем умножение на константу
                    System.out.println("Тест умножения на комплексное число(константу):");
                    System.out.println("Введите параметры комплексного числа через пробел:");
                    Complex a = new Complex(sc.nextDouble(), sc.nextDouble());
                    MatrixWithComplex testmultconst = test.multconst(a);
                    testmultconst.out();
                    test = testmultconst;
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Тест сложения матриц:");
                    System.out.println("Задайте матрицу которую хотите прибавить:");
                    MatrixWithComplex test2 = new MatrixWithComplex();
                    MatrixWithComplex testaddition = test.addition(test2);
                    testaddition.out();
                    test=testaddition;
                    break;

                case 7:
                    System.out.println("Тест умножения матриц");
                    System.out.println("Задайте матрицу на которую хотите умножить:");
                    MatrixWithComplex test3 = new MatrixWithComplex();
                    MatrixWithComplex testmultiply = test.multiply(test3);
                    testmultiply.out();
                    test=testmultiply;
                    break;

                case 8:
                    System.out.println("Тест транспонирования матрицы");
                    MatrixWithComplex testtranspose = test.transpose();
                    testtranspose.out();
                    test = testtranspose;
                    break;

                case 9:
                    Complex determinant = new Complex(test.determinant());
                    determinant.out();
                    break;
                case 10:
                    test.out();
                    break;
                case 0:
                    break;

            }
        }
    }
}