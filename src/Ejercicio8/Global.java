package Ejercicio8;
import Common.MySemaphore;

public class Global {
    public static int N = 10;
    public static int M = 20;
    public static MySemaphore sMutex = new MySemaphore(0);

    public static MySemaphore sMesaOrdenesD = new MySemaphore(N);
    public static MySemaphore sMesaOrdenesO = new MySemaphore(0);
    public static MySemaphore sMesaHamburguesaD = new MySemaphore(M);
    public static MySemaphore sMesaHamburguesaO = new MySemaphore(0);
    public static MySemaphore sMesaPedidosD = new MySemaphore(M);
    public static MySemaphore sMesaPedidosO = new MySemaphore(0);
}
