package Logic;

import Logic.Consumer;
import Logic.Meson;
import Logic.Producer;
import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

public class Restaurant {

    //  SEMAPHORES
    private Semaphore seMutex, smMutex, seProd, smProd, seCon, smCon, sdMutex, sdProd, sdCon;

    //  MESONS
    private Meson eMes, mMes, dMes;

    //  PRODUCERS
    private Producer[] eProd, mProd, dProd;

    //  WAITERS
    private Consumer[] waiters;

    //  WORKERS COUNTERS
    private int eProdCounter, mProdCounter, dProdCounter, wCounter;

    //  FOOD COUNTERS
    private static int eCount, mCount, dCount;

    //  POINTERS
    public static int ePointer, mPointer, dPointer, eCosPointer, mCosPointer, dCosPointer;

    public Restaurant(int eProdLimit, int mProdLimit, int dProdLimit, int waitersLimit, int eProdInit, int mProdInit, int dProdInit, int waiterInit, int eMesLimit, int mMesLimit, int dMesLimit) {

        this.eMes = new Meson(eMesLimit);
        this.mMes = new Meson(mMesLimit);
        this.dMes = new Meson(dMesLimit);

        this.seProd = new Semaphore(eMesLimit);
        this.smProd = new Semaphore(mMesLimit);
        this.sdProd = new Semaphore(dMesLimit);

        this.seMutex = new Semaphore(1);
        this.smMutex = new Semaphore(1);
        this.sdMutex = new Semaphore(1);

        this.seCon = new Semaphore(0);
        this.smCon = new Semaphore(0);
        this.sdCon = new Semaphore(0);

        this.eProd = new Producer[eProdLimit];
        this.mProd = new Producer[mProdLimit];
        this.dProd = new Producer[dProdLimit];
        this.waiters = new Consumer[waitersLimit];

        Restaurant.ePointer = 0;
        Restaurant.mPointer = 0;
        Restaurant.dPointer = 0;

        Restaurant.eCosPointer = 0;
        Restaurant.mCosPointer = 0;
        Restaurant.dCosPointer = 0;

        Restaurant.eCount = 0;
        Restaurant.mCount = 0;
        Restaurant.dCount = 0;

        for (int i = 0; i < eProdInit; i++) {
            this.hireEntryChef();
        }

        for (int i = 0; i < mProdInit; i++) {
            this.hireMainChef();
        }

        for (int i = 0; i < dProdInit; i++) {
            this.hireDessertChef();
        }

        for (int i = 0; i < waiterInit; i++) {
            this.hireWaiter();
        }
    }

    public boolean hireEntryChef() {
        if (this.eProdCounter == this.eProd.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.eProd.length; i++) {
                if (this.eProd[i] == null) {
                    this.eProd[i] = new Producer(eMes, seMutex, seProd, seCon, 1000, 1);
                    this.eProd[i].start();
                    this.eProdCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireMainChef() {
        if (this.mProdCounter == this.mProd.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.mProd.length; i++) {
                if (this.mProd[i] == null) {
                    this.mProd[i] = new Producer(mMes, smMutex, smProd, smCon, 1200, 2);
                    this.mProd[i].start();
                    this.mProdCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireDessertChef() {
        if (this.dProdCounter == this.dProd.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Chef!");
            return false;
        } else {
            for (int i = 0; i < this.dProd.length; i++) {
                if (this.dProd[i] == null) {
                    this.dProd[i] = new Producer(dMes, sdMutex, sdProd, sdCon, 1100, 1);
                    this.dProd[i].start();
                    this.dProdCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean hireWaiter() {
        if (this.wCounter == this.waiters.length) {
            JOptionPane.showMessageDialog(null, "Can't hire any more Waiter!");
            return false;
        } else {
            for (int i = 0; i < this.dProd.length; i++) {
                if (this.waiters[i] == null) {
                    this.waiters[i] = new Consumer(eMes, mMes, dMes, seMutex, seProd, seCon, smMutex, smProd, smCon, sdMutex, sdProd, sdCon, 2000);
                    this.waiters[i].start();
                    this.wCounter++;

                    return true;
                }
            }
        }

        return false;
    }

    public boolean fireEntryChef() {
        if (this.eProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.eProd.length; i >= 0; i--) {
                if (this.eProd[i] != null) {
                    this.eProd[i].Fire();
                    this.eProd[i] = null;
                    this.eProdCounter--;
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean fireMainChef() {
        if (this.mProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.mProd.length; i >= 0; i--) {
                if (this.mProd[i] != null) {
                    this.mProd[i].Fire();
                    this.mProd[i] = null;
                    this.mProdCounter--;
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean fireDesChef() {
        if (this.dProdCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.dProd.length; i >= 0; i--) {
                if (this.dProd[i] != null) {
                    this.dProd[i].Fire();
                    this.dProd[i] = null;
                    this.dProdCounter--;
                    
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean fireWaiter() {
        if (this.wCounter < 0) {
            JOptionPane.showMessageDialog(null, "There's no any more chef!");
            return false;
        } else {
            for (int i = this.waiters.length; i >= 0; i--) {
                if (this.waiters[i] != null) {
                    this.waiters[i].Fire();
                    this.waiters[i] = null;
                    this.wCounter--;
                    
                    return true;
                }
            }
        }
        
        return false;
    }

    public static void addEntryCount() {
        Restaurant.eCount++;
    }

    public static void addMainCount() {
        Restaurant.mCount++;
    }

    public static void addDesCount() {
        Restaurant.dCount++;
    }

    public static void subEntryCount() {
        Restaurant.eCount--;
    }

    public static void subMainCount() {
        Restaurant.mCount--;
    }

    public static void subDesCount() {
        Restaurant.dCount--;
    }

    public int getEntryCount() {
        return Restaurant.eCount;
    }

    public int getMainCount() {
        return Restaurant.mCount;
    }

    public int getDesCount() {
        return Restaurant.dCount;
    }
}
