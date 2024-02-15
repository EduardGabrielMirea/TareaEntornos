package com.company;
import java.util.Scanner;

/**
 * La clase Main contiene un programa que utiliza la criba de Eratóstenes para generar y imprimir números primos hasta un número proporcionado por el usuario.
 */
public class Main {

    /**
     * El método main es el punto de entrada del programa, que solicita al usuario un número para la criba de Eratóstenes, genera y muestra los números primos hasta ese número.
     * @param args Argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Eratóstenes:");

        // Pedimos un dato al usuario en forma de 1 número entero, que usaremos para la medida de nuestro vector.
        int medidaVector = teclado.nextInt();
        int vector[] = new int[medidaVector];

        // Nos genera el vector con 100 posiciones empezando desde 0[0] a 99[100] y lo imprime en líneas de 10 números.
        System.out.println("\nVector inicial hasta :" + medidaVector);
        leerDatos(vector);

        // Reescribimos nuestro vector con el resultado que nos ha dado el método generarPrimos.
        vector = generarPrimos(medidaVector);
        System.out.println("\n");

        // Imprime el vector que está lleno de números primos, de forma en que en cada línea solo haya 10 números por línea, junto con un tabulador para separarlos.
        System.out.println("\nVector de primos hasta:" + medidaVector);
        imprimirLosPrimos(vector);
    }

    /**
     * El método leerDatos genera el vector con 100 posiciones empezando desde 0[0] a 99[100] y lo imprime en líneas de 10 números.
     * @param vector El vector que se generará y mostrará.
     */
    public static void leerDatos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(i + 1 + "\t");
        }
    }

    /**
     * El método generarPrimos utiliza la criba de Eratóstenes para generar un vector de números primos hasta el número proporcionado.
     * @param medidaVector El número hasta el cual se generan los números primos.
     * @return Un vector que contiene los números primos hasta la medidaVector.
     */
    public static int[] generarPrimos(int medidaVector) {
        int i, j;

        // El if nos obliga a meter una medidaVector por Scanner que sea 2 o mayor que 2. En otro caso, el programa solo nos devuelve un vector vacío.
        if (medidaVector >= 2) {
            int dimension = medidaVector + 1; // Tamaño del array
            // Pasamos cada posición del vector a no es primo.
            boolean[] esPrimo = new boolean[dimension];

            // Inicializar el array
            inicializar(dimension, esPrimo);

            // Eliminar el 0 y el 1, que no son primos
            eliminar(esPrimo);

            // Criba para eliminar los múltiplos de i y ponerlos en falso
            multiplosDeI(dimension, esPrimo);

            // ¿Cuántos primos hay?
            int cuenta = getCuenta(dimension, esPrimo);

            // Rellenar el vector de números primos
            int[] primos = getInts(cuenta, dimension, esPrimo);

            return primos;

        } else { // medidaVector < 2
            return new int[0]; // Vector vacío
        }
    }

    /**
     * El método inicializar inicializa un array de booleanos a true.
     * @param dimension Tamaño del array.
     * @param esPrimo Array de booleanos a inicializar.
     */
    public static void inicializar(int dimension, boolean[] esPrimo) {
        int i;
        for (i = 0; i < dimension; i++)
            esPrimo[i] = true;
    }

    /**
     * El método eliminar establece a falso las posiciones 0 y 1 del array de booleanos, ya que no son primos.
     * @param esPrimo Array de booleanos a modificar.
     */
    public static void eliminar(boolean[] esPrimo) {
        esPrimo[0] = esPrimo[1] = false;
    }

    /**
     * El método multiplosDeI elimina los múltiplos de i recorriendo el vector de 2 en 2 posiciones y pasándolos a falso.
     * @param dimension Tamaño del array.
     * @param esPrimo Array de booleanos que representa si un número es primo o no.
     */
    public static void multiplosDeI(int dimension, boolean[] esPrimo) {
        int j;
        int i;
        for (i = 2; i < Math.sqrt(dimension) + 1; i++) {
            if (esPrimo[i]) {
                // Cojemos el vector y lo recorremos en j += i, que significa de 2 en 2, ya que igualamos j+ a i donde i=2. Pasamos las posiciones a falso. ej: 10 = falso, 12 = falso, etc.
                for (j = 2 * i; j < dimension; j += i)
                    esPrimo[j] = false;
            }
        }
    }

    /**
     * El método getCuenta cuenta cuántos números primos hay en el array de booleanos.
     * @param dimension Tamaño del array.
     * @param esPrimo Array de booleanos que representa si un número es primo o no.
     * @return La cantidad de números primos en el array.
     */
    public static int getCuenta(int dimension, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i = 0; i < dimension; i++) {
            if (esPrimo[i])
                cuenta++;
        }
        return cuenta;
    }

    /**
     * El método getInts rellena un vector con los números primos encontrados en el array de booleanos.
     * @param cuenta La cantidad de números primos.
     * @param dimension Tamaño del array.
     * @param esPrimo Array de booleanos que representa si un número es primo o no.
     * @return Un vector que contiene los números primos.
     */
    public static int[] getInts(int cuenta, int dimension, boolean[] esPrimo) {
        int j;
        int i;
        int[] primos = new int[cuenta];
        for (i = 0, j = 0; i < dimension; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }

    /**
     * El método imprimirLosPrimos imprime el vector que está lleno de números primos, de forma en que en cada línea solo haya 10 números por línea, junto con un tabulador para separarlos.
     * @param vector El vector de números primos a imprimir.
     */
    public static void imprimirLosPrimos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(vector[i] + "\t");
        }
    }
}
