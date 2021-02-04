import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Ejercicio10.Estudiante;
import Ejercicio10.Global;
import Ejercicio10.Prefecto;
//import Ejercicio10.Estudiante.Sale;
//import Ejercicio10.Prefecto.Entra;

public class Main {
	private static final int x = 5;
    /*public static void main() {
        main(new String[0]);
    }*/
    public static void main(String[] args) throws IOException {
    	char choice;

        do {
        	System.out.println("Menu:");
    	    System.out.println("1 : Ejercicio 8");
    	    System.out.println("2 : Ejercicio 10");
    	    System.out.println("3 : Problema del Barbero");
    	    System.out.println("Ejecuta uno: ");
          choice = (char) System.in.read();
        } while( choice < '1' || choice > '3');

        System.out.println("\n");
     
        switch(choice) {
        case '1':
	    	  ejercicio8();
	        break;
	      case '2':
	    	  ejercicio10();
	        break;
	      case '3':
	        barberoProblema();
	        break;
        }
    	  
   }
    public static void ejercicio8() {
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
    
    public static void ejercicio10() {
    	int n = Global.N;

        Prefecto prefecto = new Prefecto(Global.nombrePrefecto);
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
    
    public static void barberoProblema() {
    	ExecutorService simulacion = Executors.newWorkStealingPool();
        Barberia barberia = new Barberia(4);


        Callable<Void> barberoTrabajar = barberia::empezarServicio;
        Callable<Void> clienteComprar = barberia::recibirCliente;

        List <Future<Void>> barberoLista = new ArrayList<>();
        List <Future<Void>> clienteLista = new ArrayList<>();


        for (int i=0; i<x; i++) {
            Future <Void> barberFuture = simulacion.submit(barberoTrabajar);
            barberoLista.add(barberFuture);

            Future <Void> customerFuture = simulacion.submit(clienteComprar);
            clienteLista.add(customerFuture);
        }

        barberoLista.forEach(futuro -> {
            try {
                futuro.get();
            } catch (ExecutionException e) {
                ((Throwable) e).printStackTrace();
            } catch (InterruptedException e) {
				e.printStackTrace();
			}
        });

        clienteLista.forEach(futuro -> {
            try {
                futuro.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
				e.printStackTrace();
			}
        });
    }
}