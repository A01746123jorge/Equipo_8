package Ejercicio8;

public class Empacador implements Runnable {
    public String name;
    public Empacador(String s) {
        this.name = s;
    }
    public void run() {
        for (int i = 0; i < Global.runs; i++){
            Global.sMesaOrdenesO.P();
            System.out.println(name + ": Hay orden en mesa de ordenes.");
            Global.sMesaHamburguesaO.P();
            System.out.println(name + ": Hay hamburguesa en mesa de hamburguesas.");
            Global.sMesaPedidosD.P();
            System.out.println(name + ": Hay espacio en mesa de pedidos.");
            System.out.println(name + ": Empacando pedido.");
            Global.waitRandom();
            System.out.println(name + ": Pedido empacado.");
            Global.sMesaOrdenesD.V();
            Global.sMesaHamburguesaD.V();
            Global.sMesaPedidosO.V();
        }
    }
}
