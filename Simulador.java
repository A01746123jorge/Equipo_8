import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulador {
	private static final int x = 5;

    public static void main(String[] args) {

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
