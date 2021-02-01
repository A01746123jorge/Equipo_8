public class Estudiante {
    public String name = "Estudiante";
    public Estudiante(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.studentruns; i++) {
                System.out.println(name + " empieza intento de entrada.");
                Shared.sEntraEstudiante.P();
                Shared.sControlEstudiantes.V();
                Shared.sMutex.P();
                if (!Shared.prefectoAdentro) {
                    System.out.println(Shared.nombrePrefecto + " no esta adentro. Deja entrar a " + name);
                    Shared.numEstudiantes++;
                    Shared.sBloqueoEstudianteEntrada.V();
                }
                else {
                    System.out.println(name + " intento entrar, pero fue bloqueado.");
                    Shared.estudianteEsperandoEntrada = true;
                }
                Shared.sMutex.V();
                Shared.sBloqueoEstudianteEntrada.P();
                System.out.println(name + " Entra.");
                Shared.waitRandom();
                Shared.sMutex.P();
                if (Shared.numEstudiantes > Shared.studentLimit && Shared.prefectoEsperandoEntrada) {
                    System.out.println("Hay mas de " + Shared.studentLimit + " estudiantes. Deja entrar a " + Shared.nombrePrefecto + ".");
                    Shared.prefectoAdentro = true;
                    Shared.sBloqueoPrefectoEntrada.V();
                }
                Shared.sMutex.V();Shared.waitRandom();
                Shared.sSaleEstudiante.V();
                Shared.sControlEstudiantes.V();
                Shared.waitRandom();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            for (int i = 0; i < Shared.studentruns; i++) {
                System.out.println(name + " empieza intento de salida.");
                Shared.sControlEstudiantesSalida.P();
                Shared.sSaleEstudiante.P();
                //Shared.sMutex.P();
                //Shared.sMutex.V();
                System.out.println(name + " Sale.");
                Shared.waitRandom();
                Shared.sMutex.P();
                Shared.numEstudiantes--;
                if (Shared.numEstudiantes <= 0) {
                    if (Shared.prefectoEsperandoEntrada) {
                        System.out.println("Hay 0 estudiantes. Deja entrar a " + Shared.nombrePrefecto + ".");
                        Shared.prefectoEsperandoEntrada = false;
                        Shared.prefectoAdentro = true;
                        Shared.sBloqueoPrefectoEntrada.V();
                    }
                    else {
                        if (Shared.prefectoEsperandoSalida) {
                            System.out.println("Hay 0 estudiantes. Deja salir a " + Shared.nombrePrefecto + ".");
                            Shared.prefectoEsperandoSalida = false;
                            Shared.prefectoAdentro = false;
                            Shared.sBloqueoPrefectoSalida.V();
                        }
                    }
                }
                Shared.sMutex.V();
                Shared.sEntraEstudiante.V();
                Shared.sControlEstudiantesSalida.V();
                Shared.waitRandom();
            }
        }
    }
}
