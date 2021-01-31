public class Estudiante {
    public String name = "Estudiante";
    public Estudiante(String text) {
        this.name = text;
    }
    public class Entra implements Runnable {
        public void run() {
            while(Shared.run) {
                try{Thread.sleep(1000);}catch(InterruptedException e){}
                System.out.println(name + " Entra");
            }
        }
    }
    public class Sale implements Runnable {
        public void run() {
            while(Shared.run) {
                try{Thread.sleep(1000);}catch(InterruptedException e){}
                System.out.println(name + " Sale");
            }
        }
    }
}
