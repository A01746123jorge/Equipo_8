package Launcher;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        char option = '\0';
        String line;
        while (option != 'd' && option != 'D') {
            System.out.println("-----Elije un ejercicio-----");
            System.out.println("A. Ejercicio8  B. Ejercicio10   C. Barberia  D. Salir");
            System.out.print("Opcion: ");
            line = scanner.nextLine();
            if (line.length()>0) {
                option = line.charAt(0);
            }
            else {
                option = '\0';
            }
            System.out.println("Tu eleccion: " + option);
            if (option == 'a' || option == 'A')
                Ejercicio8.Main.main(null);
            if (option == 'b' || option == 'B')
                Ejercicio10.Main.main(null);
            if (option == 'c' || option == 'C')
                EjercicioCreado.Main.main(null);
            
        }
        scanner.close();
        System.out.println("----Terminando el programa----");
    }
}