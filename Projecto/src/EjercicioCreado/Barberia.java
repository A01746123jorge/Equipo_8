package EjercicioCreado;
import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class Barberia{

	private final int espacioLimite;
    public int numeroDeClientes;
    private final Barbero barbero;
    private final Semaphore clienteEsperando;
    private final Semaphore barberoDurmiendo;
    private final Semaphore corteClienteHecho;
    private final Semaphore corteBarberoHecho;
    private final Semaphore control;

    public Barberia(int espacioLimite) {
        this.espacioLimite = espacioLimite;
        numeroDeClientes = 0;
        clienteEsperando = new Semaphore(0);
        barberoDurmiendo = new Semaphore(0);
        corteClienteHecho = new Semaphore(0);
        corteBarberoHecho = new Semaphore(0);
        control = new Semaphore(1);
        barbero = new Barbero();
    }
    
    public Void recibirCliente() {

        Cliente cliente = new Cliente();
        cliente.entrar();

        try {
			control.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        
        if (numeroDeClientes == espacioLimite) {
            control.release();
            cliente.salir();
            return null;
        }
        
        numeroDeClientes += 1;
        control.release();

        clienteEsperando.release();

        try {
        	barberoDurmiendo.acquire();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        cliente.corteDeCabello();

        corteClienteHecho.release();
        try {
        	corteBarberoHecho.acquire();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        try {
			control.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        numeroDeClientes -= 1;
        control.release();

        return null;
    }
    
    public Void empezarServicio() {

        try {
        	clienteEsperando.acquire();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        barberoDurmiendo.release();

        barbero.cortarCabello();

        try {
        	corteClienteHecho.acquire();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        corteBarberoHecho.release();
        System.out.println("Corte Listo");
        
        return null;
    }

}