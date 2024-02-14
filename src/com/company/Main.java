package com.company;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");

        int dato = teclado.nextInt();
        int vector[] = new int[dato];

        System.out.println("\nVector inicial hasta :"+dato);
        leerDatos(vector);

        vector=generarPrimos(dato);
        System.out.println("\nVector de primos hasta:"+dato);

        buscarPrimos(vector);
    }

    public static void leerDatos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(i+1+"\t");
        }
    }

    public static void buscarPrimos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(vector[i]+"\t");
        }
    }

    // Inicializar el array
    public static void inicializar(int dimension, boolean[] esPrimo) {
        int i;
        for (i=0; i< dimension; i++)
            esPrimo[i] = true;
    }

    // Eliminar el 0 y el 1, que no son primos
    public static void eliminar(boolean[] esPrimo) {
        esPrimo[0] = esPrimo[1] = false;
    }

    private static int getCuenta(int dimension, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i=0; i< dimension; i++) {
            if (esPrimo[i])
                cuenta++;
        }
        return cuenta;
    }


    public static void multiplosDeI(int dimension, boolean[] esPrimo) {
        int j;
        int i;
        for (i=2; i<Math.sqrt(dimension)+1; i++) {
            if (esPrimo[i]) {
                // Eliminar los múltiplos de i
                for (j=2*i; j< dimension; j+=i)
                    esPrimo[j] = false;
            }
        }
    }

    private static int[] getInts(int cuenta, int dimension, boolean[] esPrimo) {
        int j;
        int i;
        int[] primos = new int[cuenta];
        for (i=0, j=0; i< dimension; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }

    // Generar números primos de 1 a dato
    public static int[] generarPrimos (int dato){
        int i,j;
        if (dato >= 2) {
            int dimension = dato + 1; // Tamaño del array
            boolean[] esPrimo = new boolean[dimension];

            // Inicializar el array
            inicializar(dimension, esPrimo);

            // Eliminar el 0 y el 1, que no son primos
            eliminar(esPrimo);

            //  Criba para elimiinar los multiplos de i y ponerlos en false
            multiplosDeI(dimension, esPrimo);

            // ¿Cuántos primos hay?
            int cuenta = getCuenta(dimension, esPrimo);

            // Rellenar el vector de números primos
            int[] primos = getInts(cuenta, dimension, esPrimo);

            return primos;

        } else { // dato < 2
            return new int[0];
    // Vector vacío
        }
    }
}