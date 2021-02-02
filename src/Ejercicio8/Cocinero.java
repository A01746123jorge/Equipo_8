package Ejercicio8;

public class Cocinero implements Runnable {
    public String name;
    public Cocinero(String s) {
        this.name = s;
    }
    public void run() {
        System.out.println(name + ": TODO");
    }
}
