package Inheritance;
import java.util.Scanner;
class Circle{
    private double radius;
    private String color;
    public Circle(double radius)
    {
        this.radius = radius;
        this.color = "";
    }
    public Circle(double radius, String color)
    {
        this.radius = radius;
        this.color = color;
    }
    public double getRadius()
    {
        return this.radius;
    }
    public double getArea()
    {
        return Math.PI*Math.pow(getRadius(), 2);
    }
    public String getColor()
    {
        return this.color;
    }
}
class Cylinder extends Circle
{
    private double height;
    public Cylinder(double height, double radius)
    {
        super(radius);
        this.height = height;
    } 
    public Cylinder(double height, double radius, String color)
    {
        super(radius,color);
        this.height = height;
    }
    public double getHeight()
    {
        return this.height;
    }
    public double getVolume()
    {
        return Math.PI*Math.pow(getRadius(),2)*getHeight();
    }
    public double getArea()
    {
        return 2*(super.getArea()+ Math.PI*getRadius()*getHeight());
    }
}
public class Inheritance {
    
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Cylinder cylin[] = new Cylinder[2];
        System.out.println("Enter the details for Cylinder 1:");
        System.out.println("1)Height\t2)Radius\t3)Color\n");
        cylin[0] = new Cylinder(Double.parseDouble(scan.next()),Double.parseDouble(scan.next()),scan.next());
        System.out.println("\nEnter the details for Cylinder 2:");
        System.out.println("1)Height\t2)Radius\t3)Color\n");
        cylin[1] = new Cylinder(Double.parseDouble(scan.next()),Double.parseDouble(scan.next()),scan.next());
        scan.close();
        if((cylin[0].getArea() == cylin[1].getArea()) && (cylin[0].getVolume() == cylin[1].getVolume()) && cylin[0].getColor().equalsIgnoreCase(cylin[1].getColor()))
            System.out.println("\nCylinders are same\n");
        else
            System.out.println("Cylinders are not same\n");
    }
    

}
