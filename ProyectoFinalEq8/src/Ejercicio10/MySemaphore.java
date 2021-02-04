package Ejercicio10;
public class MySemaphore {
    private int n;
    public MySemaphore(int num) {
        this.n = num;
    }
    public synchronized void P() throws RuntimeException {
        try {
            while (this.n <= 0) {wait();}
            this.n--;
        }
        catch(InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Thread Interrupted");
        }
    }
    public synchronized void V() {
        this.n++;
        this.notify();
    }
}