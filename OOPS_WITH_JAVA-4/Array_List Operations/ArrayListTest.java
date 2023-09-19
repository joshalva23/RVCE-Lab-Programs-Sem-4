import java.util.ArrayList;
import java.util.Scanner;
//import java.util.stream.*;
class ArrayListOper {
    private ArrayList<String> arrl;
    public ArrayListOper()
    {
        this.arrl = new ArrayList<String>();
    }
    public void printarr()
    {
        for(String i : arrl)
        {
            System.out.print(i +"\t");
        }
    }
    public void add(String val)
    {
        boolean status = arrl.add(val);
        if(!status){
            System.out.println("\nInsertion of element failed\n");
        }
    }
    public String[] Array()
    {
        String arr[] = new String[arrl.size()];
        arr = arrl.toArray(arr); //arrl.stream().mapToInt(i->i).toArray();
        return arr;
        //return this.arrl.toArray(); Integer Array
    }
    public void Reverse()
    {
        for(int i = arrl.size() - 2; i >= 0; i--)
        {
            arrl.add(arrl.get(i));
            arrl.remove(i);
        }
    }
    public ArrayList<String> subList(int fromIndex, int endIndex)
    {
        if(fromIndex < 0 || endIndex > this.arrl.size())
        {
            System.out.println("Invalid Indices\n");
            return new ArrayList<String>();
        }
        ArrayList<String> sub = new ArrayList<String>();
        for(int i = fromIndex; i <= endIndex; i++)
        {
            sub.add(arrl.get(i));
        }
        return sub;
    }
    //@SuppressWarnings("unchecked") // for type safety issue
    public ArrayList<String> clone()
    {
        //ArrayList<String> ClonedArrayList = new ArrayList<String>();
        //ClonedArrayList.addAll(arrl);
        //return ClonedArrayList;
        //return (ArrayList<String>)(arrl.clone()); //type safety issues
        //return arrl.stream().collect(Collectors.toCollection(ArrayList::new));//stream api
        return new ArrayList<String>(this.arrl);
    }
    
}
public class ArrayListTest
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        ArrayListOper aLO = new ArrayListOper();
        int choice;
        loop: while(true)
        {
            System.out.println("---------------------");
            System.out.print("Options\n1.Add Element\t2.To Array\t3.Sub List\t4.Reverse List\t5.Clone\t6.Print List\t7.EXIT\nEnter your choice:");
            choice = scan.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.print("\nEnter element:");
                    aLO.add(scan.next());
                break;
                case 2:
                    System.out.println("After Arraying");
                    String arr[] = aLO.Array();
                    for(int i = 0; i < arr.length; i++)
                    {
                        System.out.print(arr[i]+"\t");
                    }
                    System.out.println();
                    
                break;
                case 3:
                    System.out.println("\nEnter Indices");
                    ArrayList<String> sub = aLO.subList(scan.nextInt(), scan.nextInt());
                    if(sub.size() != 0)
                    {
                        System.out.println("The elements of the sublist are ");
                        for(String i : sub)
                        {
                            System.out.print(i+"\t");
                        }
                    }
                    System.out.println();
                break;
                case 4:
                    aLO.Reverse();
                break;
                case 5:
                    ArrayList<String> cloned = aLO.clone();
                    System.out.println("The elements of the clone are\t");
                    for(String i : cloned)
                    {
                        System.out.print(i+"\t");
                    }
                    System.out.println();
                break;
                case 6:
                    System.out.print("\nElements of ArrayList are ");
                    aLO.printarr();
                    System.out.println();
                break;
                case 7:
                    break loop;
                default: System.out.println("\nInvalid Choice\n");
            }
        }
        scan.close();
    }
}
