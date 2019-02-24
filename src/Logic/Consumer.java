package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Consumer extends Thread {

    private final Meson mMain, mDessert, mEntry;
    private final Semaphore sMainMutex;
    private final Semaphore sEntryMutex;
    private final Semaphore sDessertMutex;
    private final Semaphore sMainConsumer;
    private final Semaphore sEntryConsumer;
    private final Semaphore sDessertConsumer;
    private final Semaphore sMainChef;
    private final Semaphore sEntryChef;
    private final Semaphore sDessertChef;
    private final int time;
    private boolean hire;

    //****** WAITER *********
    public Consumer(Meson mMain, Meson mEntry, Meson mDessert, Semaphore sMainMutex, Semaphore sEntryMutex, Semaphore sDessertMutex, Semaphore sMainConsumer, Semaphore sEntryConsumer, Semaphore sDessertConsumer, Semaphore sMainChef, Semaphore sEntryChef, Semaphore sDessertChef, int time) {
        this.mMain = mMain;
        this.mEntry = mEntry;
        this.mDessert = mDessert;
        this.sMainMutex = sMainMutex;
        this.sEntryMutex = sEntryMutex;
        this.sDessertMutex = sDessertMutex;
        this.sMainConsumer = sMainConsumer;
        this.sEntryConsumer = sEntryConsumer;
        this.sDessertConsumer = sDessertConsumer;
        this.sMainChef = sMainChef;
        this.sEntryChef = sEntryChef;
        this.sDessertChef = sDessertChef;
        this.time = time;
        this.hire = true;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("1 Acquire Consumer");
                this.sEntryConsumer.acquire(3);
                //  CS ENTRIES
                this.sEntryMutex.acquire();
                System.out.println("Entry Mutex Acquire Consumer");
                for (int i = 0; i < 3; i++) {
                    this.mEntry.cook(Restaurant.entryMesPointer, 0);
                    Restaurant.entryMesPointer = (Restaurant.entryMesPointer + 1) % this.mEntry.getSize();
                }
                Restaurant.substractEntry();    // # OF ENTRIES - 3
                this.sEntryMutex.release();
                
                System.out.println("2 Acquire Consumer");
                this.sMainConsumer.acquire(2); 
                //  CS MAIN DISHES
                this.sMainMutex.acquire();
                System.out.println("Main Mutex Acquire Consumer");
                for (int i = 0; i < 2; i++) {
                    this.mMain.cook(Restaurant.mainMesPointer, 0);
                    Restaurant.mainMesPointer = (Restaurant.mainMesPointer + 1) % this.mMain.getSize();
                }
                Restaurant.substractMain();     //  # OF MAIN DISHES -2
                this.sMainMutex.release();
                this.sDessertConsumer.acquire();
                System.out.println("3 Acquire Consumer");

                

                //  CS DESSERTS
                this.sDessertMutex.acquire();
                System.out.println("Dessert Mutex Acquire Consumer");
                this.mDessert.cook(Restaurant.dessertMesPointer, 0);
                Restaurant.dessertMesPointer = (Restaurant.dessertMesPointer + 1) % this.mDessert.getSize();
                Restaurant.substractDessert();  //  # OF DESSERTS - 1
                this.sDessertMutex.release();

                //  RELEASING CHEFS
                this.sEntryChef.release(3);
                this.sMainChef.release(2);
                this.sDessertChef.release();

                //  SERVING THE FOOD
                this.Serve();
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Serve() {

        //Thread.sleep(this.time);
        System.out.println("Food served");
    }

    public void Fire() {
        this.hire = false;
        System.out.println("Waiter Fired");
    }
}
