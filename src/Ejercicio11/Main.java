package Ejercicio11;
import Common.MySemaphore;
public class Main {
    public static MySemaphore sWomanEntering = new MySemaphore(1);
    public static MySemaphore sManEntering = new MySemaphore(1);
    public static MySemaphore sMutex = new MySemaphore(1);
    public static MySemaphore sMenMutex = new MySemaphore(1);
    public static MySemaphore sWomenMutex = new MySemaphore(1);
    public static MySemaphore sWomanWait = new MySemaphore(0);
    public static MySemaphore sManWait = new MySemaphore(0);
    public static MySemaphore sWomanIn = new MySemaphore(0);
    public static MySemaphore sManIn = new MySemaphore(0);
    public static int MEN = 0;
    public static int WOMEN = 0;
    public static int N = 100;
    public static boolean womanWaits = false;
    public static boolean manWaits = false;
    public static void WomanEnter() {
        sWomanEntering.P();
            int count;
            boolean waiting;
            sMutex.P();
                waiting = MEN > 0;
                womanWaits = waiting;
                if (!waiting) {
                    WOMEN++;
                }
                count = WOMEN;
            sMutex.V();
            if (waiting) {
                sWomanWait.P();
            }
            System.out.println("Woman entered. Total: " + count);
            sWomanIn.V();
        sWomanEntering.V();
    }
    public static void WomanExit() {
        int count;
        boolean free;
        sWomanIn.P();
        sMutex.P();
            count = --WOMEN;
            free = count <= 0 && manWaits;
            if (free) {
                MEN++;
                manWaits = false;
            }
        sMutex.V();
        System.out.println("Woman exits. Total: " + count);
        if (free) {
            System.out.println(count + " Women. Freeing wating man.");
            sManWait.V();
        }
    }
    public static void ManEnter() {
        System.out.println("Test3");
    }
    public static void ManExit() {
        System.out.println("Test4");
    }
    public static void waitRandom() throws RuntimeException {
        try {Thread.sleep((long)(Math.random()*1000));}
        catch (InterruptedException e) {throw new RuntimeException();}
    }
    public static void main() throws Exception {
        main(new String[0]);
    }
    public static void main(String[] args) throws Exception {
        Thread t1[] = new Thread[N];
        Thread t2[] = new Thread[N];
        Thread t3[] = new Thread[N];
        Thread t4[] = new Thread[N];

        for (int i = N - 1; i >= 0; i--) {
            t1[i] = new Thread(){public void run(){WomanEnter();}};
            t2[i] = new Thread(){public void run(){WomanExit();}};
            t3[i] = new Thread(){public void run(){ManEnter();}};
            t4[i] = new Thread(){public void run(){ManExit();}};
        }
        
        for (int i = N - 1; i >= 0; i--) {
            t1[i].start();
            t2[i].start();
            t3[i].start();
            t4[i].start();
        }

        System.out.println("----------Done----------");
    }
}
