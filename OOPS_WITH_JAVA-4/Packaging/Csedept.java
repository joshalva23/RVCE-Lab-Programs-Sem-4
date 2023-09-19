package RVCE;
import CSE.Thirdsem;

public class Csedept extends Thirdsem {
    @Override public void Welcomemsg()
    {
        System.out.println("Welcome to RVCE");
        super.Welcomemsg();
    }
    /* 
    @Override private void privateMethod() 
    {
        System.out.println("This is private method from RVCE");
        super.privateMethod();
    }
    */ //Error due to no inheritance
    //@Override
    void defaultMethod()
    {
        System.out.println("This is default method from RVCE");
    }

    public static void main(String[] args)
    {
        Csedept test = new Csedept();
        test.Welcomemsg();
        test.defaultMethod();
        
    }

}
