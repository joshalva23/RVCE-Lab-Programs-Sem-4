package Exceptions;
import java.util.Scanner;
import java.text.DecimalFormat;
class DemonitizationException extends Exception
{
    private int amount;
    public DemonitizationException(int amount)
    {
        this.amount = amount;
    }
    public String getMessage()
    {
        return "Deposit of Old currency of amount Rs." +this.amount +" crosses Rs. 5,000 threshold and cannot be deposited";
    }
}

class MinimumException extends Exception
{
    public String getMessage()
    {
        return "\nMinimum Balance error. Money not withdrawn."; 
    }
}

class Account
{
    private int balance;
    private DecimalFormat formatter = new DecimalFormat("##,##,##,##,##,##,##0.00");
    public Account()
    {
        this.balance = 500;
    }
    public void deposit(int amount, String currencyType)
    {
        try{
            if(currencyType.equalsIgnoreCase("OLD") && amount > 5000)
            {
                throw new DemonitizationException(amount);
            }
        }
        catch(DemonitizationException excep)
        {
            System.out.println(excep.getMessage());
            return;
        }
        this.balance += amount;
        System.out.printf("\nDeposit of amount Rs. %d of type %s Successful\n",amount, currencyType.toUpperCase());
    }
    public void currBalance()
    {
        System.out.println("\nYour account balance is Rs." + this.formatter.format(this.balance));
    }
    public void withdraw(int amount)
    {
        try{
            if(amount > this.balance - 500 )
            {
                throw new MinimumException();
            }
        }
        catch(MinimumException excep)
        {
            System.out.println(excep.getMessage());
            return;
        }
        this.balance -= amount;
        System.out.println("\nAmount of Rs." + this.formatter.format(amount) + " withdrawn.\n");
    }
}
public class Customer {
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        Account ac = new Account();
        int choice;
        System.out.println("Welcome to Monsoon Banking Solutions. Your account has been created.");
        loop: 
            while(true)
            {
                System.out.println("\nOptions:\n1.Deposit\n2.Withdraw\n3.Check Balance\n4.EXIT\n\nEnter your choice:");
                choice = scan.nextInt();
                switch(choice)
                {
                    case 1: System.out.println("Enter the amount and currencyType(OLD/NEW) to be deposited");
                            ac.deposit(scan.nextInt(),scan.next());
                    break;
                    case 2: 
                            System.out.println("Enter amount to be withdrawn");
                            ac.withdraw(scan.nextInt());
                    break;
                    case 3: ac.currBalance();
                    break;
                    case 4: break loop;
                    default : System.out.println("Invalid Entry");
                }
            }
        scan.close();
        System.out.println("\n<----------Thank You for BANKING with US--------->\n");
        
        return;
    }
}
