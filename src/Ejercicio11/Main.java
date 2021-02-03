package Ejercicio11;
import Common.MySemaphore;
public class Main {
    public static MySemaphore sWomanEnter = new MySemaphore(1);
    public static MySemaphore sManEnter = new MySemaphore(1);
    public static MySemaphore sMutex = new MySemaphore(1);
    public static MySemaphore sMenMutex = new MySemaphore(1);
    public static MySemaphore sWomenMutex = new MySemaphore(1);
    public static MySemaphore sWomanWait = new MySemaphore(0);
    public static MySemaphore sManWait = new MySemaphore(0);
    public static int MEN = 0;
    public static int WOMEN = 0;
    public static int N = 100;
    public static void WomanEnter() {
        sWomanEnter.P();
            int count;
            boolean noMen;
            sMutex.P();
                noMen = MEN <= 0;
                if (noMen) {
                    sWomanWait.V();
                    WOMEN++;
                }
                count = WOMEN;
            sMutex.V();
            sWomanWait.P();
            System.out.println("Woman entered. Total: " + count);
        sWomanEnter.V();
    }
    public static void WomanExit() {
        System.out.println("Test2");
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
