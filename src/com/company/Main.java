package com.company;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        Scanner teclado=new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");

        //Pedimos un dato al usuario en forma de 1 numero entero, que usaremos para la medida de nuestro vector.
        int medidaVector = teclado.nextInt();
        int vector[] = new int[medidaVector];

        //Nos genera el vector con 100 posiciones empezando desde 0[0] a 99[100] y lo imprime en lineas de 10 numeros.
        System.out.println("\nVector inicial hasta :"+medidaVector);
        leerDatos(vector);

        //Reecribimos nuestro vector con el resultado que nos a dado el metodo generarPrimos.
        vector = generarPrimos(medidaVector);
        System.out.println("\n");

        // Imprime el vector que esta lleno de numeros primos, de forma en que en cada linea solo haya 10 numeros por linea, junto con un tabluador para separarlos.
        System.out.println("\nVector de primos hasta:"+medidaVector);
        imprimirLosPrimos(vector);
    }

    //Nos genera el vector con 100 posiciones empezando desde 0[0] a 99[100] y lo imprime en lineas de 10 numeros.
    public static void leerDatos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(i+1+"\t");
        }
    }

    public static int[] generarPrimos (int medidaVector){
        int i,j;

        //El if nos obliga a meter una medidaVector por Scanner que sea 2 o mayor que 2. En otro caso el programa solo nos devol.
        if (medidaVector >= 2) {
            int dimension = medidaVector + 1; // Tamaño del array
            //Pasamos cada posicion del vector a no es primo.
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

        } else { // medidaVector < 2
            return new int[0];
            // Vector vacío
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
    // Eliminar los múltiplos de 2 recoriendo el vector de 2 en 2 posiciones y pasandolos a false usando el bucle for j.
    public static void multiplosDeI(int dimension, boolean[] esPrimo) {
        int j;
        int i;
        for (i=2; i<Math.sqrt(dimension)+1; i++) {
            if (esPrimo[i]) {
                //Cojemos el vector y lo recorremos en j+=i que quiere decir de 2 en 2 ya que igualamos j+ a i donde i=2. Pasamos las posiciones a false. ej: 10 = false, 12= false, etc.
                for (j=2*i; j< dimension; j+=i)
                    esPrimo[j] = false;
            }
        }
    }

    // ¿Cuántos primos hay?
    public static int getCuenta(int dimension, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i=0; i< dimension; i++) {
            if (esPrimo[i])
                cuenta++;
        }
        return cuenta;
    }

    // Rellenar el vector de números primos
    public static int[] getInts(int cuenta, int dimension, boolean[] esPrimo) {
        int j;
        int i;
        int[] primos = new int[cuenta];
        for (i=0, j=0; i< dimension; i++) {
            if (esPrimo[i])
                primos[j++] = i;
        }
        return primos;
    }

    // Imprime el vector que esta lleno de numeros primos, de forma en que en cada linea solo haya 10 numeros por linea, junto con un tabluador para separarlos.
    public static void imprimirLosPrimos(int[] vector) {
        for (int i = 0; i < vector.length; i++) {
            if (i%10==0) System.out.println();
            System.out.print(vector[i]+"\t");
        }
    }
}