package Logic;

import java.util.concurrent.Semaphore;

public class Meson {

    private int[] meson;
    private int size;

    public Meson(int size) {
        this.size = size;
        this.meson = new int[size];
    }

    /*public void cook(int data) throws InterruptedException {
        sSpace.acquire();
        sMutex.acquire();
        meson[i] = data;
        System.out.println("cook: " + meson[i]);
        i = (i + 1) % size;
        sMutex.release();
        sDish.release();
    }

    public int serve() throws InterruptedException {
        sDish.acquire();
        sMutex.acquire();
        int temp = j;
        j = (j + 1) % size;
        meson[temp] = 0;
        sMutex.release();
        sSpace.release();
        return meson[temp];
    }*/
    public int getSize() {
        return this.size;
    }

    public void cook(int i, int data) {
        meson[i] = data;
        System.out.println("Cooked " + data);
    }

}
