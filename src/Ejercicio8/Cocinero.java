package Ejercicio8;

public class Cocinero implements Runnable {
    public String name;
    public Cocinero(String s) {
        this.name = s;
    }
    public void run() {
        for (int i = 0; i < Global.runs; i++){
            Global.sMesaHamburguesaD.P();
            System.out.println(name + ": Hay espacio en mesa de hamburguesas.");
            System.out.println(name + ": Haciendo hamburguesa.");
            Global.waitRandom();
            System.out.println(name + ": Hamburguesa hecha.");
            Global.sMesaHamburguesaO.V();
        }
    }
}
