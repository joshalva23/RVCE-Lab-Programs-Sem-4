package Interface;
interface Animal {
    public void eat();
    public void sound();
}

interface TiredAnimal extends Animal{
    public void appetite();
    public void cryforhelp();
}

class Lion implements Animal{
    public void eat()
    {
        System.out.println("A lion eats deer");
    }
    public void sound()
    {
        System.out.println("A Lion Roars");
    }
}

class Snake implements Animal{
    public void eat()
    {
        System.out.println("A snake eats rodents");
    }
    public void sound()
    {
        System.out.println("A Snake Hisses");
    }
    public void watch()
    {
        System.out.println("Snake watches Deer");
    }
}

class Deer implements TiredAnimal{
    public void eat()
    {
        System.out.println("A Deer eats grass and other plants");
    }
    public void sound()
    {
        System.out.println("A Deer barks");
    }
    public void appetite()
    {
        System.out.println("A Deer has a regular appetite");
    }
    public void cryforhelp()
    {
        System.out.println("Help! Lion! Help!");
    }
}

public class Anim {
    public static void main(String[] args)
    {
        Lion lion = new Lion();
        Snake snake = new Snake();
        Deer deer = new Deer();
        deer.appetite();
        deer.eat();
        snake.eat();
        deer.sound();
        snake.sound();
        lion.sound();
        deer.cryforhelp();
        lion.eat();
        snake.watch();
        return;
    }
}
