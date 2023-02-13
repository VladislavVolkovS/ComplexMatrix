import java.util.Scanner;
public class MatrixWithComplex {
    //задаем матрицу
    public Complex[][] Matrix;

    //конструкторы

    //без параметров
    public MatrixWithComplex(){

        Scanner sc = new Scanner(System.in);
        System.out.println("Введите размеры матрицы n, m");
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n < 0 || m < 0) {
            throw new ArithmeticException("Задан неверный размер матрицы");
        }
        this.Matrix = new Complex[n][m];
        for (int i = 0; i < this.Matrix.length; i++){
            for (int j = 0; j < this.Matrix[0].length; j++){
                System.out.println("Введите параметры элемента матрицы[" + i + "][" + j + "] через пробел");
                this.Matrix[i][j] = new Complex(sc.nextDouble(), sc.nextDouble());
            }
        }
    }

    //по двум параметрам (прямоугольная или квадратная)
    public MatrixWithComplex(int n, int m){
        if (n < 0 || m < 0) {
            throw new ArithmeticException("Задан неверный размер матрицы");
        }
        this.Matrix = new Complex[n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                this.Matrix[i][j] = new Complex(0); //изначально инициализируем нулями
            }
        }
    }

    //по одному параметру (квадратная)
    public MatrixWithComplex(int n){
        if (n < 0) {
            throw new ArithmeticException("Задан неверный размер матрицы!");
        }
        this.Matrix = new Complex[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                this.Matrix[i][j] = new Complex(0); //изначально инициализируем нулями
            }
        }
    }

    //условно копирование матрицы
    public MatrixWithComplex(Complex[][] matrix){
        this.Matrix = matrix;
    }

    //____________________________________________________________________
    //Методы
    //операции с матрицами

    //заполнение пустой матрицы с клавиатуры

    public MatrixWithComplex Input(){
        Scanner sc = new Scanner(System.in);
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix.length, this.Matrix[0].length);
        for (int i = 0; i < result.Matrix.length; i++){
            for (int j = 0; j < result.Matrix[0].length; j++){
                System.out.println("Введите параметры элемента матрицы[" + i + "][" + j + "] через пробел");
                result.Matrix[i][j] = new Complex(sc.nextDouble(), sc.nextDouble());
            }
        }
        return result;
    }

    //умножение на константу
    public MatrixWithComplex multconst(Complex number){
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix.length, this.Matrix[0].length);
        for (int i = 0; i < this.Matrix.length; i++){
            for (int j = 0; j < this.Matrix[0].length; j++){
                Complex a = new Complex(number);
                result.Matrix[i][j] = this.Matrix[i][j].multiply(a);
            }
        }
        return result;
    }

    //прибавление матрицы
    public MatrixWithComplex addition(MatrixWithComplex matrix){
        if ((this.Matrix.length != matrix.Matrix.length) || (this.Matrix[0].length != matrix.Matrix[0].length)){
            throw new ArithmeticException("Размеры матриц не совпадают!");
        }
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix.length, this.Matrix[0].length);
        for (int i = 0; i < this.Matrix.length; i++){
            for (int j = 0; j < this.Matrix[0].length; j++){
                result.Matrix[i][j] = this.Matrix[i][j].addition(matrix.Matrix[i][j]);
            }
        }
        return result;
    }

    //умножение матрицы на матрицу
    public MatrixWithComplex multiply(MatrixWithComplex matrix){
        if ((this.Matrix.length != matrix.Matrix[0].length) && (this.Matrix[0].length != matrix.Matrix.length)){
            throw new ArithmeticException("Матрицы с данными размерами невозможно перемножить!");
        }
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix.length, this.Matrix.length);
        for (int i = 0; i < result.Matrix.length; i++){
            for (int j = 0; j < result.Matrix.length; j++){
                result.Matrix[i][j] = new Complex(0);
                for (int k = 0; k < this.Matrix[0].length; k++){
                    result.Matrix[i][j] = result.Matrix[i][j].addition(this.Matrix[i][k].multiply(matrix.Matrix[k][j]));
                }
            }
        }
        return result;
    }

    //транспонирование матрицы
    public MatrixWithComplex transpose(){
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix[0].length, this.Matrix.length);
        for (int i = 0; i < this.Matrix[0].length; i++){
            for (int j = 0; j < this.Matrix.length; j++){
                result.Matrix[i][j] = this.Matrix[j][i];
            }
        }
        return result;
    }
    //детерминант матрицы

    public MatrixWithComplex GetMinorIJelement(int i, int j){
        MatrixWithComplex result = new MatrixWithComplex(this.Matrix.length - 1);
        int c1=0;
        int c2=0;
        for (int k = 0; k < this.Matrix.length; k++){
            if (k==i){
                continue;
            }
            for (int l=0; l<this.Matrix.length; l++){
                if (l==j){
                    continue;
                }
                result.Matrix[c1][c2] = this.Matrix[k][l];
                c2++;
            }
            c1++;
            c2=0;
        }
        return result;
    }
    public Complex determinant(){
        if (this.Matrix.length != this.Matrix[0].length){
            throw new ArithmeticException("Матрицы должна быть квадратной!");
        }
        if (this.Matrix.length == 1) {
            return this.Matrix[0][0];
        }
        if (this.Matrix.length == 2){
            return (this.Matrix[0][0].multiply(this.Matrix[1][1]).addition((this.Matrix[1][0].multiply(this.Matrix[0][1])).multiply(new Complex(-1))));
        }
        Complex result = new Complex(0,0);
        for (int i = 0; i < this.Matrix.length; i++){
            int degree = (this.Matrix.length - 1) + i;
            MatrixWithComplex Minor = GetMinorIJelement(this.Matrix.length - 1, i);
            Complex deg = new Complex(Math.pow(-1, degree));
            result = result.addition((this.Matrix[this.Matrix.length - 1][i].multiply(deg)).multiply((Minor.determinant())));
        }
        return result;
    }

    public void out(){
        for (int i = 0; i < this.Matrix.length; i++){
            for (int j = 0; j < this.Matrix[0].length; j++){
                this.Matrix[i][j].out_2();
                if (j<this.Matrix[0].length - 1){
                    System.out.print(' ');
                }
            }
            System.out.print('\n');
        }
    }
}