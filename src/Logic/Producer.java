package Logic;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Producer extends Thread {

    private Meson meson;
    private Semaphore sMutex, sChef, sWaiter;
    private int time, type;
    private boolean hired;

    //      ***** CHEFS ******
    public Producer(Meson meson, Semaphore sMutex, Semaphore sChef, Semaphore sWaiter, int time, int type) {
        this.hired = true;
        this.meson = meson;
        this.sMutex = sMutex;
        this.sChef = sChef;
        this.sWaiter = sWaiter;
        this.time = time;
        this.type = type;
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.sChef.acquire();
                //Thread.sleep(this.time);
                System.out.println("1 Acquire Producer");
                this.sMutex.acquire();
                System.out.println("Mutex Acquire Producer");
                switch (this.type) {
                    case 1:
                        this.meson.cook(Restaurant.mainChefPointer, 1);
                        Restaurant.mainChefPointer = (Restaurant.mainChefPointer + 1) % this.meson.getSize();
                        Restaurant.addMain();
                        break;
                    case 2:
                        this.meson.cook(Restaurant.entryChefPointer, 2);
                        Restaurant.entryChefPointer = (Restaurant.entryChefPointer + 1) % this.meson.getSize();
                        Restaurant.addEntry();
                        break;
                    case 3:
                        this.meson.cook(Restaurant.dessertChefPointer, 3);
                        Restaurant.dessertChefPointer = (Restaurant.dessertChefPointer + 1) % this.meson.getSize();
                        Restaurant.addDessert();
                        break;
                }
                this.sWaiter.release();
                System.out.println("1 Release Producer");
                this.sMutex.release();
                System.out.println("2 Release Producer");
            } catch (InterruptedException ex) {
                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void Fire() {
        this.hired = false;
    }
}
