public class Once {
    /* VARIABLES GLOBALES */
    public static MySemaphore sMutex = new MySemaphore(1); // Semaforo de exclusion mutua (equivalente a 'init(sMutex,1);')
    public static MySemaphore sWomanEntering = new MySemaphore(1); //Semaforo de control de entrada de mujer
    public static MySemaphore sManEntering = new MySemaphore(1); //Semaforo de control de entrada de mujer
    public static MySemaphore sWomanWait = new MySemaphore(0); //Semaforo que bloquea la entrada mujer
    public static MySemaphore sManWait = new MySemaphore(0); //Semaforo que bloquea la entrada mujer
    public static MySemaphore sWomanIn = new MySemaphore(0); //Semaforo que habilita la salida de mujer
    public static MySemaphore sManIn = new MySemaphore(0); //Semaforo que habilita la salida de hombre
    public static int MEN = 0; // cantidad de hombres adentro
    public static int WOMEN = 0; // cantidad de mujeres adentro
    public static int N = 100; // n, numero de threads a ejecutar
    public static boolean womanWaits = false; // indica que una mujer esta bloqueada
    public static boolean manWaits = false; // indica que un hombre esta bloqueado

    /* Funcion de Entrada de Mujer */
    public static void WomanEnter() {
        sWomanEntering.P(); // Solo una mujer puede entrar a la vez
            int count; // Para uso en el mensaje imprimido
            boolean waiting; // Para uso afuera de la seccion critica
            sMutex.P(); //Empieza seccion critica (equivalente a 'P(sMutex);')
                waiting = MEN > 0;
                womanWaits = waiting;
                count = WOMEN + 1;
                if (!waiting) {
                    WOMEN++;
                }
            sMutex.V(); //Termina seccion critica (equivalente a  'V(sMutex);')
            if (waiting) { // Si hay hombres adentro, espera
                sWomanWait.P();
            }
            System.out.println("Woman entered. Total: " + count);
            sWomanIn.V(); // Habilita salida
        sWomanEntering.V();
    }

    /*Funcion de Salida de Mujer*/
    public static void WomanExit() {
        int count; // Para uso en el mensaje
        boolean free;
        sWomanIn.P();
        sMutex.P(); // Empieza seccion critica
            count = --WOMEN;
            free = count <= 0 && manWaits;
            if (free) {
                MEN++;
                manWaits = false;
            }
        sMutex.V(); // Termina seccion critica
        System.out.println("Woman exits. Total: " + count);
        if (free) { // Si es la ultima mujer, puede entrar a un hombre esperando
            System.out.println(count + " Women. Freeing wating man.");
            sManWait.V();
        }
    }

    /*Funcion de Entrada de Hombre (similar a Entrada de Mujer)*/
    public static void ManEnter() {
    sManEntering.P();
        int count;
        boolean waiting;
        sMutex.P();
            waiting = WOMEN > 0;
            manWaits = waiting;
            count = MEN + 1;
            if (!waiting) {
                MEN++;
            }
        sMutex.V();
        if (waiting) {
            sManWait.P();
        }
        System.out.println("Man entered. Total: " + count);
        sManIn.V();
    sManEntering.V();
    }

    /*Funcion de Salida de Hombre (similar a la Salida de Mujer)*/
    public static void ManExit() {
        int count;
        boolean free;
        sManIn.P();
        sMutex.P();
            count = --MEN;
            free = count <= 0 && womanWaits;
            if (free) {
                WOMEN++;
                womanWaits = false;
            }
        sMutex.V();
        System.out.println("Man exits. Total: " + count);
        if (free) {
            System.out.println(count + " Men. Freeing wating woman.");
            sWomanWait.V();
        }
    }
    public static void main() throws Exception {
        main(new String[0]);
    }

    //              //
    // FUNCION MAIN //
    //              //
    public static void main(String[] args) throws Exception {
        Thread t1[] = new Thread[N]; //
        Thread t2[] = new Thread[N]; //
        Thread t3[] = new Thread[N]; //
        Thread t4[] = new Thread[N]; // Arrays de threads

        //Llenando los arrays de threads con las funciones hechas
        for (int i = N - 1; i >= 0; i--) {
            t1[i] = new Thread(){public void run(){WomanEnter();}};
            t2[i] = new Thread(){public void run(){WomanExit();}};
            t3[i] = new Thread(){public void run(){ManEnter();}};
            t4[i] = new Thread(){public void run(){ManExit();}};
        }
        
        //Empezando los threads de entrada
        for (int i = N - 1; i >= 0; i--) {
            t1[i].start();
            t3[i].start();
        }

        //Empezando los threads de salida
        for (int i = N - 1; i >= 0; i--) {
            t2[i].start();
            t4[i].start();
        }

        System.out.println("----------Done----------");
    }
}
