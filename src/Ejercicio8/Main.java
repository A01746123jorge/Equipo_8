package Ejercicio8;

public class Main {
    public static void main(String[] args) {
        Thread empacador = new Thread(new Empacador("Empacador"));
        Thread cocinero = new Thread(new Cocinero("Cocinero"));
        Thread despachador = new Thread(new Cocinero("Despachador"));
        empacador.start();
        cocinero.start();
        despachador.start();
        System.out.println("----------Done----------");
    }
}
