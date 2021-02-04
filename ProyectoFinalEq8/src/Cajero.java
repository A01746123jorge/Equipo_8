
public class Cajero implements Runnable {
    public String name;
    public Cajero(String s) {
        this.name = s;
    }
    public void run() {
        for (int i = 0; i < Global.runs; i++){
            Global.sMesaPedidosO.P();
            System.out.println(name + ": Hay pedido en mesa de pedidos.");
            System.out.println(name + ": Entregando pedido.");
            Global.waitRandom();
            System.out.println(name + ": Pedido entregado.");
            Global.sMesaPedidosD.V();
        }
    }
}
