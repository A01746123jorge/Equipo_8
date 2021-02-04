package EjercicioCreado;

public class Cliente{
	public void entrar() {
		System.out.println("Entrando a Barberia: " + Thread.currentThread().getName());
	}
	public void corteDeCabello() {
		System.out.println("Me estan cortando el cabello:" + Thread.currentThread().getName());
	}
	public void salir() {
		System.out.println("Saliendo de la tienda: " + Thread.currentThread().getName());
	}
	
}