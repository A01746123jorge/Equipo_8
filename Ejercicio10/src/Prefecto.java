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
                    System.out.println("0 Estudiandes.");
                    Shared.sBloqueoPrefectoEntrada.V();
                }
                else {
                    if (Shared.numEstudiantes > 50) {
                        System.out.println("Mas de 50 estudiantes.");
                        Shared.sBloqueoPrefectoEntrada.V();
                    }
                    else {
                        Shared.prefectoEsperandoEntrada = true;
                    }
                }
                Shared.sMutex.V();
                Shared.sBloqueoPrefectoEntrada.P();
                System.out.println(name + " Entra");
                Shared.sSalePrefecto.V();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            while (Shared.run) {
                Shared.sSalePrefecto.P();
                Shared.sMutex.P();
                Shared.sMutex.V();
                System.out.println(name + " Sale");
                Shared.sEntraPrefecto.V();
            }
        }
    }
}
