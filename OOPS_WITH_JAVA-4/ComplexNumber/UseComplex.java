package ComplexNumber;
/*Create a JAVA class called Complex with the member variables real and cimaginary. Develop a java program to perform addition and substraction of two complex numbers using method add and subtract respectively by passing object as a parameter and display the result using method display. Initialise the real and imaginary values of a complex number using parameteriszed constructor. Also demonstrate overloading constructors and methods.*/
import java.util.Scanner;

class Complex {
    private double r,i;
    public Complex()
    {
        this.r = this.i = 0;
    }
    public Complex(double re, double im)
    {
        this.r = re;
        this.i = im;
    }
    public double getReal()
    {
        return this.r;
    }
    public double getImaginary()
    {
        return this.i;
    }
    public String display()
    {
        return String.format("%f%+fi",this.r, this.i);
    }
    public void setReal(double re)
    {
        this.r = re;
    }
    public void setImaginary(double im)
    {
        this.i = im;
    }
    void add(Complex num1, Complex num2)
    {
        this.r = num1.getReal()+ num2.getReal();
        this.i = num1.getImaginary() + num2.getImaginary();
    }
    void subtract(Complex num1, Complex num2)
    {
        this.r = num1.getReal() - num2.getReal();
        this.i = num1.getImaginary() - num2.getImaginary();
    }
}


class UseComplex
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Complex num1 = new Complex();
        Complex num2;
        Complex result = new Complex();
        System.out.println("Enter the Complex number 1:");
        num1.setReal(sc.nextDouble());
        num1.setImaginary(sc.nextDouble());
        System.out.println("Enter the Complex number 2:");  
        num2 = new Complex(sc.nextDouble(),sc.nextDouble());
        sc.close();
        result.add(num1, num2);
        System.out.println("The sum of numbers is " + result.display());
        result.subtract(num1, num2);
        System.out.println("The difference of numbers is " + result.display());
    }
}