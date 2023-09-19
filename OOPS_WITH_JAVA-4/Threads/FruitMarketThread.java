package Threads;
import java.util.ArrayList;
import java.util.Arrays;

class Market {
    private int capacity;
    private ArrayList<String> fruits;

    public Market(int capacity) {
        this.capacity = capacity;
        fruits = new ArrayList<>();
    }

    public synchronized boolean isFull() {
        return fruits.size() >= capacity;
    }

    public synchronized boolean isEmpty() {
        return fruits.isEmpty();
    }

    public synchronized void farmer(String fruit, int farmer_no) {
        if (isFull()) {
            try {
                wait(); // Wait if the market is full
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fruits.add(fruit);
        System.out.println("Farmer " + farmer_no +" added " + fruit + " to the market.");
        notifyAll(); // Notify consumers that new fruit is available
    }

    public synchronized String consumer(int consumer_no) {
        if (isEmpty()) {
            try {
                wait(); // Wait if the market is empty
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String fruit = fruits.remove(0);
        System.out.println("Consumer " + consumer_no +" bought " + fruit + " from the market.");
        notifyAll(); // Notify farmers that space is available
        return fruit;
    }
}

class Farmer extends Thread {
    private Market market;
    private String fruit;
    private int farmer_no;

    public Farmer(Market market, String fruit, int farmer_no) {
        this.market = market;
        this.fruit = fruit;
        this.farmer_no = farmer_no;
    }

    public void run() {
        while(true){
            market.farmer(this.fruit,this.farmer_no);
            try {
                Thread.sleep(1000); // Simulate some time for consuming the fruit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Thread {
    private Market market;
    private int consumer_no;

    public Consumer(Market market, int consumer_no) {
        this.market = market;
        this.consumer_no = consumer_no;
    }

    public void run() {
        while (true) {
            market.consumer(this.consumer_no);
            
            // Perform actions with the purchased fruit
            // ...
            try {
                Thread.sleep(2000); // Simulate some time for consuming the fruit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class FruitMarketThread {
    public static void main(String[] args) {
        Market market = new Market(5);
        int maxFarmers = 4;
        int maxConsumers = 4;
        ArrayList<String> Fruitlist = new ArrayList<>(Arrays.asList("apple","orange","grape","watermelon"));
        Farmer farmerThread[] = new Farmer[maxFarmers];
        Consumer consumerThread[] = new Consumer[maxConsumers];
        if(Fruitlist.size() != maxFarmers)
        {
            System.out.printf("Improper Params\n");
            System.exit(0);
        }
        for(int i = 0; i < maxFarmers ; i++)
        {
            farmerThread[i] = new Farmer(market, Fruitlist.remove(0), i);
        }
        for(int i = 0; i < maxConsumers ; i++)
        {
            consumerThread[i] = new Consumer(market,i);
        }
        
        for(int i = 0; i < maxFarmers ; i++)
        {
            farmerThread[i].start();
        }
        for(int i = 0; i < maxConsumers ; i++)
        {
            consumerThread[i].start();
        }
        
        try{
            for(int i = 0; i < maxFarmers ; i++)
            {
                farmerThread[i].join();
            }
            for(int i = 0; i < maxConsumers ; i++)
            {
                consumerThread[i].join();
            }
        }catch(InterruptedException e)
        {
            e.getStackTrace();
        }
    }
}