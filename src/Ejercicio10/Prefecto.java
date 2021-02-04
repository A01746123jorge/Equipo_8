package Ejercicio10;

public class Prefecto {
    public String name = "Prefecto";
    private boolean esperandoE = false;
    private boolean esperandoS = false;
    public Prefecto(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            for (int i = 0; i < Global.prefectruns; i++) {
                Global.waitRandom();
                //System.out.println(name + " empieza intento de entrada.");
                Global.sEntraPrefecto.P();
                Global.sMutex.P();
                if (Global.numEstudiantes <= 0) {
                    System.out.println("Hay 0 estudiandes. Deja entrar a " + name + ".");
                    Global.prefectoAdentro = true;
                    Global.prefectoEsperandoEntrada = false;
                    esperandoE = false;
                }
                else {
                    if (Global.numEstudiantes > Global.studentLimit) {
                        System.out.println("Hay mas de " + Global.studentLimit + " estudiantes. Deja entrar a " + name + ".");
                        Global.prefectoAdentro = true;
                        Global.prefectoEsperandoEntrada = false;
                        esperandoE = false;
                    }
                    else {
                        System.out.println(name + " intento entrar, pero fue bloqueado.");
                        Global.prefectoEsperandoEntrada = true;
                        esperandoE = true;
                    }
                }
                Global.sMutex.V();
                if (esperandoE) {
                    Global.sBloqueoPrefectoEntrada.P();
                }
                esperandoE = false;
                System.out.println(name + " Entra.");
                //Shared.waitRandom();
                Global.sSalePrefecto.V();
                Global.waitRandom();
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            for (int i = 0; i < Global.prefectruns; i++) {
                Global.waitRandom();
                //System.out.println(name + " empieza intento salida.");
                Global.sSalePrefecto.P();
                Global.sMutex.P();
                if (Global.numEstudiantes <= 0) {
                    System.out.println("Hay 0 estudiantes. Deja salir a " + name + ".");
                    Global.prefectoEsperandoSalida = false;
                    esperandoS = false;
                }
                else {
                    System.out.println(name + " intento salir, pero fue bloqueado.");
                    Global.prefectoEsperandoSalida = true;
                    esperandoS = true;
                }
                Global.sMutex.V();
                if (esperandoS){
                    Global.sBloqueoPrefectoSalida.P();
                }
                esperandoS = false;
                System.out.println(name + " Sale.");
                //Shared.waitRandom();
                Global.sMutex.P();
                Global.prefectoAdentro = false;
                if (Global.estudianteEsperandoEntrada) {
                    System.out.println(name + " salio. Deja entrar a un estudiante.");
                    Global.estudianteEsperandoEntrada = false;
                    Global.numEstudiantes++;
                    Global.sBloqueoEstudianteEntrada.V();
                }
                Global.sMutex.V();
                Global.sEntraPrefecto.V();
                Global.waitRandom();
            }
        }
    }
}
