public class MySemaphore {
    private int n = 0;
    public MySemaphore(int num) {
        this.n = num;
    }
    public synchronized void P() throws InterruptedException {
        while (this.n <= 0) {wait();}
        this.n--;
    }
    public synchronized void V() {
        this.n++;
        this.notify();
    }
}