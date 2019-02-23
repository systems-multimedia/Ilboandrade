package Logic;

import Logic.Consumer;
import Logic.Meson;
import Logic.Producer;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

public class Restaurant {

    //  SEMAPHORES
    private Semaphore smMutex, sdMutex, seMutex;
    private Semaphore sMainConsumer, sEntryConsumer, sDessertConsumer;
    private Semaphore sMainChef, sEntryChef, sDessertChef;

    //  PRODUCERS AND CONSUMERS
    //  Food
    private Meson mainMes, entryMes, dessertMes;

    //  Producers
    private Producer[] mainChef, dessertChef, entryChef;

    //  Consumers
    private Consumer[] waiters;

    //  Producers' and Consumers' counters
    private int mainChefCounter, entryChefCounter, dessertChefCounter, waitersCounter;

    //  Producers' and Consumer's pointers
    public static int mainChefPointer, entryChefPointer, dessertChefPointer, mainMesPointer, entryMesPointer, dessertMesPointer;

    //  Counter for each kind of food
    private static int mainDishCounter, entryDishCounter, dessertDishCounter;

    //  Time of work - How much time should be a day
    private int workHours;

    private int hoursForClosing;

    public Restaurant(int workHours, int hoursForClosing, int mainChefLimit, int dessertChefLimit, int entryChefLimit, int waitersLimit, int mainMesLimit, int entryMesLimit, int dessertMesLimit, int initMainChef, int initEntryChef, int initDessertChef, int initWaiters) {

        //  INITIALIZING HOURS
        this.workHours = workHours;
        this.hoursForClosing = hoursForClosing;

        //  INITIALIZING MESONS
        this.mainMes = new Meson(mainMesLimit);
        this.entryMes = new Meson(entryMesLimit);
        this.dessertMes = new Meson(dessertMesLimit);

        //  INITIALIZING COUNTERS AND POINTERS
        Restaurant.mainDishCounter = 0;
        Restaurant.entryDishCounter = 0;
        Restaurant.dessertDishCounter = 0;

        Restaurant.mainChefPointer = 0;
        Restaurant.entryChefPointer = 0;
        Restaurant.dessertChefPointer = 0;

        Restaurant.mainMesPointer = 0;
        Restaurant.entryMesPointer = 0;
        Restaurant.dessertMesPointer = 0;

        //  INITIALIZING SEMAPHORES
        this.sMainChef = new Semaphore(mainChefLimit);
        this.sEntryChef = new Semaphore(entryChefLimit);
        this.sDessertChef = new Semaphore(dessertChefLimit);

        this.smMutex = new Semaphore(1);
        this.seMutex = new Semaphore(1);
        this.sdMutex = new Semaphore(1);

        this.sMainConsumer = new Semaphore(0);
        this.sEntryConsumer = new Semaphore(0);
        this.sDessertConsumer = new Semaphore(0);

        this.mainMes = new Meson(mainChefLimit);
        this.entryMes = new Meson(entryChefLimit);
        this.dessertMes = new Meson(dessertChefLimit);

        this.mainChef = new Producer[mainChefLimit];
        this.dessertChef = new Producer[dessertChefLimit];
        this.entryChef = new Producer[entryChefLimit];
        this.waiters = new Consumer[waitersLimit];

        //  HIRING INITIAL PRODUCERS
        for (int i = 0; i < initMainChef; i++) {
            this.hireMainChef();
        }

        for (int i = 0; i < initEntryChef; i++) {
            this.hireEntryChef();
        }

        for (int i = 0; i < initDessertChef; i++) {
            this.hireDessertChef();
        }

        //  HIRING INITIAL WAITERS
        for (int i = 0; i < initWaiters; i++) {
            this.hireWaiter();
        }

    }

    public int getHour() {
        return (this.workHours * 1000) / 8;
    }

    //  Timer View Remember me
    public boolean hireMainChef() {
        if (this.mainChefCounter == this.mainChef.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.mainChef.length; i++) {
                if (this.mainChef[i] == null) {
                    this.mainChef[i] = new Producer(this.mainMes, this.smMutex, this.sMainChef, this.sMainConsumer, this.getHour() / 3, 1);
                    this.mainChef[i].start();
                    this.mainChefCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireEntryChef() {
        if (this.entryChefCounter == this.entryChef.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.entryChef.length; i++) {
                if (this.entryChef[i] == null) {
                    this.entryChef[i] = new Producer(this.entryMes, this.seMutex, this.sEntryChef, this.sEntryConsumer, this.getHour() / 4, 2);
                    this.entryChef[i].start();
                    this.entryChefCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireDessertChef() {
        if (this.dessertChefCounter == this.dessertChef.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.dessertChef.length; i++) {
                if (this.dessertChef[i] == null) {
                    this.dessertChef[i] = new Producer(this.dessertMes, this.sdMutex, this.sDessertChef, this.sDessertConsumer, (this.getHour() * 30) / 10, 3);
                    this.dessertChef[i].start();
                    this.dessertChefCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireWaiter() {
        if (this.waitersCounter == this.waiters.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Waiter!");
            return false;
        } else {
            for (int i = 0; i < this.waiters.length; i++) {
                if (this.waiters[i] == null) {
                    this.waiters[i] = new Consumer(this.mainMes, this.entryMes, this.dessertMes, this.smMutex, this.seMutex, this.sdMutex, this.sMainChef, this.sEntryChef, this.sDessertChef, this.sMainConsumer, this.sEntryConsumer, this.sDessertConsumer, this.getHour());
                    this.waiters[i].start();
                    this.waitersCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireMainChef() {
        if (this.mainChefCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more Chef!");
            return false;
        } else {
            for (int i = this.mainChef.length - 1; i >= 0; i--) {
                if (this.mainChef[i] != null) {
                    this.mainChef[i].Fire();
                    this.mainChef[i] = null;
                    this.mainChefCounter--;

                    return true;
                }
            }
        }
        System.out.println("Main Chef Fired");

        return false;
    }

    public boolean fireEntryChef() {
        if (this.entryChefCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more Chef!");
            return false;
        } else {
            for (int i = this.entryChef.length - 1; i >= 0; i--) {
                if (this.entryChef[i] != null) {
                    this.entryChef[i].Fire();
                    this.entryChef[i] = null;
                    this.entryChefCounter--;

                    return true;
                }
            }
        }

        System.out.println("Entry Chef Fired");

        return false;
    }

    public boolean fireDessertChef() {
        if (this.dessertChefCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more Chef!");
            return false;
        } else {
            for (int i = this.dessertChef.length - 1; i >= 0; i--) {
                if (this.dessertChef[i] != null) {
                    this.dessertChef[i].Fire();
                    this.dessertChef[i] = null;
                    this.dessertChefCounter--;
                    
                    return true;
                }
            }
        }

        System.out.println("Dessert Chef Fired");
        return false;
    }

    public boolean fireWaiter() {
        if (this.waitersCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more Waiter!");
            return false;
        } else {
            for (int i = this.waiters.length - 1; i >= 0; i--) {
                if (this.waiters[i] != null) {
                    this.waiters[i].Fire();
                    this.waiters[i] = null;
                    this.waitersCounter--;

                    return true;
                }
            }
        }

        return false;
    }

    public static void addMain() {
        Restaurant.mainDishCounter++;
    }

    public static void addEntry() {
        Restaurant.entryDishCounter++;
    }

    public static void addDessert() {
        Restaurant.dessertDishCounter++;
    }

    public static void substractMain() {
        Restaurant.mainDishCounter -= 2;
    }

    public static void substractEntry() {
        Restaurant.entryDishCounter -= 3;
    }

    public static void substractDessert() {
        Restaurant.dessertDishCounter--;
    }

    public int getMainCount() {
        return Restaurant.mainDishCounter;
    }

    public int getEntryCount() {
        return Restaurant.entryDishCounter;
    }

    public int getDessertCount() {
        return Restaurant.dessertDishCounter;
    }

    public int getMainChefCount() {
        return this.mainChefCounter;
    }

    public int getEntryChefCount() {
        return this.entryChefCounter;
    }

    public int getDessertChefCount() {
        return this.dessertChefCounter;
    }
}
