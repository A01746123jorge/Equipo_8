public class Shared {
    public static boolean run = true;
    public static MySemaphore sMutex = new MySemaphore(1);

    public static boolean prefectoEsperandoEntrada = false;
    public static boolean prefectoEsperandoSalida = false;
    public static boolean prefectoAdentro = false;
    public static MySemaphore sBloqueoPrefectoEntrada = new MySemaphore(0);
    public static MySemaphore sBloqueoPrefectoSalida = new MySemaphore(0);
    public static MySemaphore sEntraPrefecto = new MySemaphore(1);
    public static MySemaphore sSalePrefecto = new MySemaphore(0);

    public static int numEstudiantes = 0;

}
