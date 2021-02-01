public class Estudiante {
    public String name = "Estudiante";
    private boolean esperandoE = false;
    public Estudiante(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.studentruns; i++) {
                Shared.waitRandom();
                //System.out.println(name + " empieza intento de entrada.");
                Shared.sEntraEstudiante.P();
                Shared.sControlEstudiantes.P();
                Shared.sMutex.P();
                if (!Shared.prefectoAdentro) {
                    System.out.println(Shared.nombrePrefecto + " no esta adentro. Deja entrar a " + name);
                    Shared.numEstudiantes++;
                    Shared.estudianteEsperandoEntrada = false;
                    esperandoE = false;
                }
                else {
                    System.out.println(name + " intento entrar, pero fue bloqueado.");
                    Shared.estudianteEsperandoEntrada = true;
                    esperandoE = true;
                }
                Shared.sMutex.V();
                if (Shared.estudianteEsperandoEntrada) {
                    Shared.sBloqueoEstudianteEntrada.P();
                }
                esperandoE = false;
                System.out.println(name + " Entra.");
                //Shared.waitRandom();
                Shared.sMutex.P();
                if (Shared.numEstudiantes > Shared.studentLimit && Shared.prefectoEsperandoEntrada) {
                    System.out.println("Hay mas de " + Shared.studentLimit + " estudiantes. Deja entrar a " + Shared.nombrePrefecto + ".");
                    Shared.prefectoAdentro = true;
                    Shared.prefectoEsperandoEntrada = false;
                    Shared.sBloqueoPrefectoEntrada.V();
                }
                Shared.sMutex.V();
                Shared.sSaleEstudiante.V();
                Shared.sControlEstudiantes.V();
                Shared.waitRandom();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.studentruns; i++) {
                Shared.waitRandom();
                //System.out.println(name + " empieza intento de salida.");
                //Shared.sControlEstudiantesSalida.P();
                Shared.sSaleEstudiante.P();
                //Shared.sMutex.P();
                //Shared.sMutex.V();
                System.out.println(name + " Sale.");
                //Shared.waitRandom();
                Shared.sMutex.P();
                Shared.numEstudiantes--;
                if (Shared.numEstudiantes <= 0) {
                    if (Shared.prefectoEsperandoEntrada) {
                        System.out.println("Hay 0 estudiantes. Deja entrar a " + Shared.nombrePrefecto + ".");
                        Shared.prefectoAdentro = true;
                        Shared.prefectoEsperandoEntrada = false;
                        Shared.sBloqueoPrefectoEntrada.V();
                    }
                    if (Shared.prefectoEsperandoSalida) {
                        System.out.println("Hay 0 estudiantes. Deja salir a " + Shared.nombrePrefecto + ".");
                        Shared.prefectoAdentro = false;
                        Shared.prefectoEsperandoSalida = false;
                        Shared.sBloqueoPrefectoSalida.V();
                    }
                }
                Shared.sMutex.V();
                Shared.sEntraEstudiante.V();
                //Shared.sControlEstudiantesSalida.V();
                Shared.waitRandom();
            }
        }
    }
}
