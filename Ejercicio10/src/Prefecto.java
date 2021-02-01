public class Prefecto {
    public String name = "Prefecto";
    public Prefecto(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.prefectruns; i++) {
                System.out.println(name + " empieza intento de entrada.");
                Shared.sEntraPrefecto.P();
                Shared.sMutex.P();
                if (Shared.numEstudiantes <= 0) {
                    System.out.println("Hay 0 Estudiandes. Deja entrar a " + name + ".");
                    Shared.prefectoAdentro = true;
                    Shared.sBloqueoPrefectoEntrada.V();
                }
                else {
                    if (Shared.numEstudiantes > Shared.studentLimit) {
                        System.out.println("Hay mas de " + Shared.studentLimit + " estudiantes. Deja entrar a " + name + ".");
                        Shared.prefectoAdentro = true;
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
                Shared.waitRandom();
                Shared.sSalePrefecto.V();
                Shared.waitRandom();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.prefectruns; i++) {
                System.out.println(name + " empieza intento salida.");
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
                Shared.waitRandom();
                Shared.sMutex.P();
                Shared.prefectoAdentro = false;
                if (Shared.estudianteEsperandoEntrada) {
                    System.out.println(name + " salio. Deja entrar a un estudiante.");
                    Shared.estudianteEsperandoEntrada = false;
                    Shared.numEstudiantes++;
                    Shared.sBloqueoEstudianteEntrada.V();
                }
                Shared.sMutex.V();
                Shared.sEntraPrefecto.V();
                Shared.waitRandom();
            }
        }
    }
}
