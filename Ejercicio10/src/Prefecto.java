public class Prefecto {
    public String name = "Prefecto";
    public Prefecto(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            while (Shared.run) {
                Shared.sEntraPrefecto.P();
                Shared.sMutex.P();
                if (Shared.numEstudiantes <= 0) {
                    System.out.println("Hay 0 Estudiandes. Deja entrar a " + name + ".");
                    Shared.sBloqueoPrefectoEntrada.V();
                }
                else {
                    if (Shared.numEstudiantes > 50) {
                        System.out.println("Hay mas de 50 estudiantes. Deja entrar a " + name + ".");
                        Shared.sBloqueoPrefectoEntrada.V();
                    }
                    else {
                        System.out.println(name + " intento entrar, pero fue bloqueado.");
                        Shared.prefectoEsperandoEntrada = true;
                    }
                }
                Shared.sMutex.V();
                Shared.sBloqueoPrefectoEntrada.P();
                System.out.println(name + " Entra.");
                Shared.sSalePrefecto.V();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            while (Shared.run) {
                Shared.sSalePrefecto.P();
                Shared.sMutex.P();
                if (Shared.numEstudiantes <= 0) {
                    System.out.println("Hay 0 Estudiantes. Deja salir a " + name + ".");
                    Shared.sBloqueoPrefectoSalida.V();
                }
                else {
                    System.out.println(name + " intento salir, pero fue bloqueado.");
                    Shared.prefectoEsperandoSalida = true;
                }
                Shared.sMutex.V();
                Shared.sBloqueoPrefectoSalida.P();
                System.out.println(name + " Sale.");
                Shared.sEntraPrefecto.V();
            }
        }
    }
}
