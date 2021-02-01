public class Shared {
    public static int studentruns = 2;
    public static int prefectruns = 10;
    public static int N = 100;
    public static int studentLimit = 5;
    public static MySemaphore sMutex = new MySemaphore(1);

    public static boolean prefectoEsperandoEntrada = false;
    public static boolean prefectoEsperandoSalida = false;
    public static boolean prefectoAdentro = false;
    public static String nombrePrefecto = "Prefecto";
    public static MySemaphore sBloqueoPrefectoEntrada = new MySemaphore(0);
    public static MySemaphore sBloqueoPrefectoSalida = new MySemaphore(0);
    public static MySemaphore sEntraPrefecto = new MySemaphore(1);
    public static MySemaphore sSalePrefecto = new MySemaphore(0);

    public static int numEstudiantes = 0;
    public static boolean estudianteEsperandoEntrada = false;
    public static MySemaphore sControlEstudiantes = new MySemaphore(1);
    public static MySemaphore sControlEstudiantesSalida = new MySemaphore(1);
    public static MySemaphore sBloqueoEstudianteEntrada = new MySemaphore(0);
    public static MySemaphore sEntraEstudiante = new MySemaphore(N);
    public static MySemaphore sSaleEstudiante = new MySemaphore(0);

    public static void waitRandom() throws RuntimeException{
        try {
            Thread.sleep((long)(Math.random() * 1000));
        }
        catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}
