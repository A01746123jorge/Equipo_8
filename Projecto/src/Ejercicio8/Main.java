package Ejercicio8;

public class Main {
    public static void main() {
        main(new String[0]);
    }
    public static void main(String[] args) {
        Thread empacador = new Thread(new Empacador("Empacador"));
        Thread cocinero = new Thread(new Cocinero("Cocinero"));
        Thread despachador = new Thread(new Despachador("Despachador"));
        Thread cajero = new Thread(new Cajero("Cajero"));
        empacador.start();
        cocinero.start();
        despachador.start();
        cajero.start();
        System.out.println("----------Done----------");
    }
}
