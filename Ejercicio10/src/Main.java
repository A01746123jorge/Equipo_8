public class Main {
    public static void main(String[] args) throws Exception {
        Prefecto prefecto = new Prefecto(Shared.nombrePrefecto);
        Thread tPrefectoEntra = new Thread(prefecto.new Entra());
        Thread tPrefectoSale = new Thread(prefecto.new Sale());
        int n = Shared.N;

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
        for (int i = 0; i < n; i++) {
            tEstudianteEntra[i].start();
            tEstudianteSale[i].start();
        }
        try {Thread.sleep(10);}
        catch (InterruptedException e){
            System.out.println("Sleep interrupted");
        }
        finally {
            Shared.run = false;
        }
        System.out.println("Done");
    }
}
