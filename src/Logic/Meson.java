package Logic;

import java.util.concurrent.Semaphore;

public class Meson {

    private int[] meson;
    private int size;

    public Meson(int size) {
        this.size = size;
        this.meson = new int[size];
    }

    public int getSize() {
        return this.size;
    }

    public void cook(int i, int data) {
        meson[i] = data;
        System.out.println("______________________");
        System.out.println("Cooked " + data);
        System.out.println("----------------------");
    }

}
