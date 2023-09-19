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

    public synchronized void consumer(int consumer_no) {
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
        return;
    }
}

class Farmer implements Runnable {
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
            market.farmer(fruit,farmer_no);
            
            try {
                Thread.sleep(1000); // Simulate some time for consuming the fruit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable {
    private Market market;
    private int consumer_no;

    public Consumer(Market market, int consumer_no) {
        this.market = market;
        this.consumer_no = consumer_no;
    }

    public void run() {
        while (true) {
            market.consumer(consumer_no);

            try {
                Thread.sleep(2000); // Simulate some time for consuming the fruit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class FruitMarketRunnable {
    public static void main(String[] args) {

        Market market = new Market(5);
        int maxFarmers = 4;
        int maxConsumers = 6;
        ArrayList<String> Fruitlist = new ArrayList<>(Arrays.asList("apple","orange","watermelon","grape"));
        Thread farmerThread[] = new Thread[maxFarmers];
        Thread consumerThread[] = new Thread[maxConsumers];
        if(Fruitlist.size() != maxFarmers)
        {
            System.out.printf("Improper Params\n");
            System.exit(0);
        }
        for(int i = 0; i < maxFarmers ; i++)
        {
            farmerThread[i] = new Thread(new Farmer(market,Fruitlist.remove(0), i));
        }
        for(int i = 0; i < maxConsumers ; i++)
        {
            consumerThread[i] = new Thread(new Consumer(market,i));
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


/*
 import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Market {
    private int capacity;
    private ArrayList<String> fruits;
    private Lock lock;
    private Condition notFull;
    private Condition notEmpty;

    public Market(int capacity) {
        this.capacity = capacity;
        fruits = new ArrayList<>();
        lock = new ReentrantLock();
        notFull = lock.newCondition();
        notEmpty = lock.newCondition();
    }

    public boolean isFull() {
        return fruits.size() >= capacity;
    }

    public boolean isEmpty() {
        return fruits.isEmpty();
    }

    public void farmer(String fruit) {
        lock.lock();
        try {
            while (isFull()) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fruits.add(fruit);
            System.out.println("Farmer added " + fruit + " to the market.");
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String consumer() {
        lock.lock();
        try {
            while (isEmpty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String fruit = fruits.remove(0);
            System.out.println("Consumer bought " + fruit + " from the market.");
            notFull.signalAll();
            return fruit;
        } finally {
            lock.unlock();
        }
    }
}

class FarmerThread extends Thread {
    private Market market;
    private String fruit;

    public FarmerThread(Market market, String fruit) {
        this.market = market;
        this.fruit = fruit;
    }

    public void run() {
        market.farmer(fruit);
    }
}

class ConsumerThread extends Thread {
    private Market market;

    public ConsumerThread(Market market) {
        this.market = market;
    }

    public void run() {
        while (true) {
            String fruit = market.consumer();
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

public class FruitMarketProgram {
    public static void main(String[] args) {
        Market market = new Market(5);

        FarmerThread farmerThread1 = new FarmerThread(market, "apple");
        FarmerThread farmerThread2 = new FarmerThread(market, "orange");
        FarmerThread farmerThread3 = new FarmerThread(market, "grape");
        FarmerThread farmerThread4 = new FarmerThread(market, "watermelon");

        ConsumerThread consumerThread1 = new ConsumerThread(market);
        ConsumerThread consumerThread2 = new ConsumerThread(market);

        farmerThread1.start();
        farmerThread2.start();
        farmerThread3.start();
        farmerThread4.start();

        consumerThread1.start();
        consumerThread2.start();
    }
}

 */
/* 
//Working
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

class Market {
    private int capacity;
    private ArrayList<String> fruits;
    private Semaphore PurchaseLock, StockLock;

    public Market(int capacity) {
        this.capacity = capacity;
        this.fruits = new ArrayList<>();
        this.PurchaseLock = new Semaphore(1, isEmpty());
        this.StockLock = new Semaphore(1,isFull());
    }

    public synchronized boolean isFull() {
        return fruits.size() >= capacity;
    }

    public synchronized boolean isEmpty() {
        return fruits.isEmpty();
    }

    public synchronized void farmer(String fruit) {
        try{
            this.StockLock.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        fruits.add(fruit);
        StockLock.release();
    }

    public synchronized String consumer() {
        try{
            this.PurchaseLock.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if (this.fruits.size() == 1)
        {
            try{
                this.StockLock.acquire();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String fruit = fruits.remove(0);
        
        if(this.fruits.size() == 0)
            this.StockLock.release();
        PurchaseLock.release();
        return fruit;
    }
}

class FarmerThread extends Thread {
    private Market market;
    private String fruit;

    public FarmerThread(Market market, String fruit) {
        this.market = market;
        this.fruit = fruit;
    }

    public void run() {
        while(true)
        {
            market.farmer(this.fruit);
            System.out.println("Farmer added " + this.fruit + " to the market.");
            try{
                Thread.sleep(2000);
            }catch(InterruptedException e){
                e.getStackTrace();
            }
        }
    }
}

class ConsumerThread extends Thread {
    private Market market;

    public ConsumerThread(Market market) {
        this.market = market;
    }

    public void run() {
        while (true) {
            String fruit = market.consumer();
            System.out.println("Consumer bought " + fruit + " from the market.");
            // Perform actions with the purchased fruit
            // ...
            
            try {
                Thread.sleep(1000); // Simulate some time for consuming the fruit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }
}

public class FruitMarketProgram {
    public static void main(String[] args) {
        Market market = new Market(5);

        FarmerThread farmerThread1 = new FarmerThread(market, "apple");
        
        FarmerThread farmerThread2 = new FarmerThread(market, "orange");
        FarmerThread farmerThread3 = new FarmerThread(market, "grape");
        FarmerThread farmerThread4 = new FarmerThread(market, "watermelon");

        ConsumerThread consumerThread1 = new ConsumerThread(market);
        ConsumerThread consumerThread2 = new ConsumerThread(market);
        
        //Thread farmerThread1 = new Thread(farmerThread11);
        //Thread farmerThread2 = new Thread(farmerThread21);
        //Thread farmerThread3 = new Thread(farmerThread31);
        //Thread farmerThread4 = new Thread(farmerThread41);
        //Thread consumerThread1 = new Thread(consumerThread11);
        //Thread consumerThread2 = new Thread(consumerThread21);
        
        farmerThread1.start();
        farmerThread2.start();
        farmerThread3.start();
        farmerThread4.start();

        consumerThread1.start();
        consumerThread2.start();
        try{
        farmerThread1.join();
        farmerThread2.join();
        farmerThread3.join();
        farmerThread4.join();

        consumerThread1.join();
        consumerThread2.join();
        }catch(InterruptedException e)
        {
            e.getStackTrace();
        }
        
    }
}
*/
