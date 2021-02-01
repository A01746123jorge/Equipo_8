public class Main {
    public static void main(String[] args) throws Exception {

        int n = Shared.N;

        Prefecto prefecto = new Prefecto(Shared.nombrePrefecto);
        Thread tPrefectoEntra = new Thread(prefecto.new Entra());
        Thread tPrefectoSale = new Thread(prefecto.new Sale());
        Estudiante[] estudiantes = new Estudiante[n];
        Thread[] tEstudianteEntra = new Thread[n];
        Thread[] tEstudianteSale = new Thread[n];

        for (int i = 0; i < n; i++) {
            estudiantes[i] = new Estudiante("Estudiante (Thread-" + (i + 1) + ")");
            tEstudianteEntra[i] = new Thread(estudiantes[i].new Entra());
            tEstudianteSale[i] = new Thread(estudiantes[i].new Sale());
            tEstudianteEntra[i].start();
            tEstudianteSale[i].start();
        }
        tPrefectoEntra.start();
        tPrefectoSale.start();

        
        /*try {Thread.sleep(1000);}
        catch (InterruptedException e){
            System.out.println("Sleep interrupted");
        }
        finally {
            Shared.run = false;
        }*/
        System.out.println("----------Done----------");
    }
}
