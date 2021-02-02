package Ejercicio10;

public class Estudiante {
    public String name = "Estudiante";
    private boolean esperandoE = false;
    public Estudiante(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            for (int i = 0; i < Global.studentruns; i++) {
                Global.waitRandom();
                //System.out.println(name + " empieza intento de entrada.");
                Global.sEntraEstudiante.P();
                Global.sControlEstudiantes.P();
                Global.sMutex.P();
                if (!Global.prefectoAdentro) {
                    System.out.println(Global.nombrePrefecto + " no esta adentro. Deja entrar a " + name);
                    Global.numEstudiantes++;
                    Global.estudianteEsperandoEntrada = false;
                    esperandoE = false;
                }
                else {
                    System.out.println(name + " intento entrar, pero fue bloqueado.");
                    Global.estudianteEsperandoEntrada = true;
                    esperandoE = true;
                }
                Global.sMutex.V();
                if (esperandoE) {
                    Global.sBloqueoEstudianteEntrada.P();
                }
                esperandoE = false;
                System.out.println(name + " Entra.");
                //Shared.waitRandom();
                Global.sMutex.P();
                if (Global.numEstudiantes > Global.studentLimit && Global.prefectoEsperandoEntrada) {
                    System.out.println("Hay mas de " + Global.studentLimit + " estudiantes. Deja entrar a " + Global.nombrePrefecto + ".");
                    Global.prefectoAdentro = true;
                    Global.prefectoEsperandoEntrada = false;
                    Global.sBloqueoPrefectoEntrada.V();
                }
                Global.sMutex.V();
                Global.sSaleEstudiante.V();
                Global.sControlEstudiantes.V();
                Global.waitRandom();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            for (int i = 0; i < Global.studentruns; i++) {
                Global.waitRandom();
                //System.out.println(name + " empieza intento de salida.");
                //Shared.sControlEstudiantesSalida.P();
                Global.sSaleEstudiante.P();
                //Shared.sMutex.P();
                //Shared.sMutex.V();
                System.out.println(name + " Sale.");
                //Shared.waitRandom();
                Global.sMutex.P();
                Global.numEstudiantes--;
                if (Global.numEstudiantes <= 0) {
                    if (Global.prefectoEsperandoEntrada) {
                        System.out.println("Hay 0 estudiantes. Deja entrar a " + Global.nombrePrefecto + ".");
                        Global.prefectoAdentro = true;
                        Global.prefectoEsperandoEntrada = false;
                        Global.sBloqueoPrefectoEntrada.V();
                    }
                    if (Global.prefectoEsperandoSalida) {
                        System.out.println("Hay 0 estudiantes. Deja salir a " + Global.nombrePrefecto + ".");
                        Global.prefectoAdentro = false;
                        Global.prefectoEsperandoSalida = false;
                        Global.sBloqueoPrefectoSalida.V();
                    }
                }
                Global.sMutex.V();
                Global.sEntraEstudiante.V();
                //Shared.sControlEstudiantesSalida.V();
                Global.waitRandom();
            }
        }
    }
}
