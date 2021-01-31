public class Main {
    public static void main(String[] args) throws Exception {
        Prefecto prefecto = new Prefecto("Prefecto");
        Thread tPrefectoEntra = new Thread(prefecto.new Entra());
        Thread tPrefectoSale = new Thread(prefecto.new Sale());

        int n = 0;
        if (args.length >= 0) {
            System.out.println("No args, n = 100");
            n = 100;
        }
        else {
            n = Integer.parseInt(args[0]);
        }
        Estudiante[] estudiantes = new Estudiante[n];
        Thread[] tEstudianteEntra = new Thread[n];
        Thread[] tEstudianteSale = new Thread[n];

        for (int i = 0; i < n; i++) {
            estudiantes[i] = new Estudiante("Estudiante #" + (i + 1));
            tEstudianteEntra[i] = new Thread(estudiantes[i].new Entra());
            tEstudianteSale[i] = new Thread(estudiantes[i].new Sale());
        }
        
        tPrefectoSale.start();
        tPrefectoEntra.start();
        try {Thread.sleep(1000);}
        catch (InterruptedException e){
            System.out.println("Sleep interrupted");
        }
        finally {
            Shared.run = false;
        }
        System.out.println("Done");
    }
}
