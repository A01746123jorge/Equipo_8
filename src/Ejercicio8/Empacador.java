package Ejercicio8;

public class Empacador implements Runnable {
    public String name;
    public Empacador(String s) {
        this.name = s;
    }
    public void run() {
        System.out.println(name + ": TODO");
    }
}
