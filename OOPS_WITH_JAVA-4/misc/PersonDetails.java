/* 
import java.util.Scanner;

class Address{
    int street_no;
    String city, state, country;
    Address(int street_no, String city, String state, String country)
    {
        this.street_no = street_no;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}

class Student{
    String USN, name;
    Address address;
    Student(String USN, String name, Address address)
    {
        this.USN = USN;
        this.name = name;
        this.address = address;
    }
}

class College{
    String name;
    Address address;
    College( String name, Address address)
    {
        this.name = name;
        this.address = address;
    }
}

class Employee{
    String name, empID;
    Address address;
    Employee(String empID, String name, Address address)
    {
        this.empID = empID;
        this.name = name;
        this.address = address;
    }
}
public class PersonDetails {
    public static void main(String[] args)
    {
        int num;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter number of each object:");
        num = Integer.parseInt(scan.next());
        Address addr[] = new Address[(num*3)];
        Student stud[] = new Student[num];
        College coll[] = new College[num];
        Employee emp[] = new Employee[num];
        {
            System.out.println("Enter the student details:");
            for(int i = 0; i < num; i++)
            {
                System.out.println("\nEnter details of Student "+(i+1));
                System.out.println("1) USN 2)Name   Address--> 3)Street No 4)City 5)State 6)Country\n");
                stud[i] = new Student(scan.next(), scan.next(), addr[i] = new Address(scan.nextInt(),scan.next(),scan.next(),scan.next()));
            }
        }
        {
            System.out.println("Enter the college details:");
            for(int i = 0; i < num; i++)
            {
                System.out.println("\nEnter details of College "+(i+1));
                System.out.println(" 1)Name   Address--> 2)Street No 3)City 4)State 5)Country\n");
                coll[i] = new College(scan.next(), addr[num + i] = new Address(scan.nextInt(),scan.next(),scan.next(),scan.next()));
            }
        }
        {
            System.out.println("Enter the employee details:");
            for(int i = 0; i < num; i++)
            {
                System.out.println("\nEnter details of Employee "+(i+1));
                System.out.println("1) EmpID 2)Name   Address--> 3)Street No 4)City 5)State 6)Country\n");
                emp[i] = new Employee(scan.next(), scan.next(), addr[2*num + i] = new Address(scan.nextInt(),scan.next(),scan.next(),scan.next()));
            }
        }
        System.out.println("Stored data loading..........................\n");
        {
            System.out.println("Student details\n");
            System.out.printf("%5s %10s %15s %5s %10s %10s %10s\n","S.No.","USN","Name","Street","City","State","Country");
            for(int i = 0; i < num; i++)
            {
                System.out.printf("%5s %10s %15s %5d %10s %10s %10s\n" ,(i+1), stud[i].USN, stud[i].name, stud[i].address.street_no, stud[i].address.city, stud[i].address.state, stud[i].address.country); 
            }
            System.out.println();
        }
        {
            System.out.println("College details\n");
            System.out.printf("%5d %15s %5d %10s %10s %10s\n","S.No.","Name","Street No","City","State","Country");
            for(int i = 0; i < num; i++)
            {
                System.out.printf("%5d %15s %5d %10s %10s %10s\n",(i+1), coll[i].name, coll[i].address.street_no, coll[i].address.city, coll[i].address.state, coll[i].address.country); 
            }
            System.out.println();
        }
        {
            System.out.println("Employee details\n");
            System.out.printf("%5s %10s %15s %5d %10s %10s %10s\n","S.No.","EmpID","Name","Street No","City","State","Country");
            for(int i = 0; i < num; i++)
            {
                System.out.printf("%5d %10s %15s %5d %10s %10s %10s\n",(i+1), emp[i].empID, emp[i].name, emp[i].address.street_no, emp[i].address.city, emp[i].address.state, emp[i].address.country); 
            }
            System.out.println();
        }
        System.out.println("<--------Destructed Successfully-------->");
        scan.close();
    }
}
*/