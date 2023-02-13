public class Complex {
    //значения
    public double real; //действительная часть
    public double im; //мнимая часть


    //конструкторы
    public Complex(double real, double im){
        this.real = real;
        this.im = im;
    }
    public Complex(double real){
        this.real = real;
        this.im = 0.0;
    }
    public Complex(){
        this.real = 0.0;
        this.im = 0.0;
    }

    public Complex(Complex val){
        this.real = val.real;
        this.im = val.im;
    }

    //----------------------------------------------------------------------------------

    // МЕТОДЫ

    //прибавление комплексного числа
    public Complex addition(Complex val){
        return new Complex(this.real + val.real, this.im + val.im);
    }


    //вычитание комплексного числа
    public Complex subtraction(Complex val){
        return new Complex(this.real - val.real, this.im - val.im);
    }


    //умножение на комплексное число
    public Complex multiply(Complex val){
        return new Complex(this.real * val.real - this.im * val.im, this.real * val.im + this.im * val.real);
    }


    //деление на комплексное число
    public Complex divide(Complex val){
        if ((val.real * val.real + val.im * val.im)==0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Complex((this.real * val.real + this.im * val.im)/(val.real * val.real + val.im * val.im), (this.im * val.real - this.real * val.im)/(val.real * val.real + val.im * val.im));
    }


    //модуль комплексного числа
    public double abs(){
        return Math.sqrt(this.im * this.im + this.real * this.real);
    }


    //аргумент комплексного числа
    public double arg(){
        if (this.im==0 && this.real==0){
            throw new ArithmeticException("Невозможно вычислить аргумент для 0!");
        }
        return Math.toDegrees(Math.atan2(this.im, this.real));
    }

    //комлексно-сопряженное
    public Complex conjugate(){
        return new Complex(this.real, - this.im);
    }

    public Complex[] sqrt(int n){
        if (n<1){
            throw new ArithmeticException("Некорректная степень!");
        }
        Complex[] result = new Complex[n];
        for (int i=0; i<n; i++){
            result[i] = new Complex(Math.pow(this.abs(), 1.0 /n)*(Math.cos((this.arg()+2*Math.PI*i)/n)), Math.pow(this.abs(), 1.0 /n)*(Math.sin((this.arg()+2*Math.PI*i)/n)));
        }
        return result;
    }

    //вывод в тригонометрической форме
    public void out_trig_form(){
        if (this.im==0 && this.real==0){
            throw new ArithmeticException("Невозможно представить в тригонометрической форме!");
        }
        System.out.println(this.abs() + "(cos(" + this.arg() + ") + i*sin(" + this.arg() + "))");
    }

    //вывод комплексного числа
    public void out(){
        if (this.im>=0) {
            System.out.println(this.real + "+" + this.im + "i");
        }
        else if(this.im<0){
            double i = - this.im;
            System.out.println(this.real + "-" + i + "i");
        }
    }
    public void out_2(){
        if (this.im>0) {
            System.out.print(this.real + "+" + this.im + "i");
        }
        else if (this.im == 0){
            System.out.print(this.real);
        }
        else if(this.im<0){
            double i = - this.im;
            System.out.print(this.real + "-" + i + "i");
        }
    }
}
