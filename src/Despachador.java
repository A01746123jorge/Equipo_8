public class Despachador implements Runnable {
    public String name;
    public Despachador(String s) {
        this.name = s;
    }
    public void run() {
        for (int i = 0; i < Global.runs; i++){
            Global.sMesaOrdenesD.P();
            System.out.println(name + ": Hay espacio en mesa de ordenes.");
            System.out.println(name + ": Tomando orden.");
            Global.waitRandom();
            System.out.println(name + ": Orden tomada.");
            Global.sMesaOrdenesO.V();
        }
    }
}
