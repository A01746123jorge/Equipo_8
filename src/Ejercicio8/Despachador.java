package Ejercicio8;

public class Despachador implements Runnable {
    public String name;
    public Despachador(String s) {
        this.name = s;
    }
    public void run() {
        System.out.println(name + ": TODO");
    }
}
