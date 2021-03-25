package clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que generara numeros peudaaleatorios por el metodo
 *  Linea o Mixto y el metodo Multiplicativo
 */
public class GeneradorNumerosAletorios
{

    //Constantes enteros y positivos
    private int X0; // semilla
    private int a; // contante multiplicativa
    private int c;  //contante aditiva -- en el metodo Multiplicativo vale 0, no se usa
    private int m;  // modulo

    // Lista de NumerosAleatorios
    private ObservableList<NumeroAleatorio> vecNA;

    // Cantidad de numeros a generar
    private int cant;

    private int Xi; //
    public GeneradorNumerosAletorios() {
    }

    public GeneradorNumerosAletorios(int X0, int a, int c, int m)
    {
        this.X0 = X0;
        this.a = a;
        this.c = c;
        this.m = m;
        this.Xi = X0;
        this.cant = 20;
    }

    /**
     * Genera una cantidad "n" de numero pseudoaleatorio
     * por defecto la cantidad inicial de numeros a genererar es de 20
     */
    private void generarNumero(int newCant)
    {
        if (cant!= newCant)
            cant= newCant;

        float num;
        NumeroAleatorio nuevo;

        for (int i = vecNA.size(); i< newCant; i++)
        {
            num = (float) Xi/m;
            Xi = (( a * Xi) + c) % m;
            nuevo = new NumeroAleatorio(i, (float)(Math.round(num*10000))/10000);
            vecNA.add(nuevo);
        }
    }

    /**
     * Genera por un numero fijo de numeros
     */
    public void generar()
    {
        vecNA = FXCollections.observableArrayList();
        generarNumero(cant);
    }

    /**
     *
     * @param cant cantidad de numeros a generar
     */
    public void generar(int cant)
    {
        vecNA = FXCollections.observableArrayList();
        generarNumero(cant);
    }

    /** Genera un solo numero aleatorio
     *
     */
    public void siguiente()
    {
        generarNumero(cant + 1);
    }

    /** Retorna una lista Observable XD
     * de NumerosAleatorio ordenados como fueron generados
     * @return el vecNA
     */
    public ObservableList<NumeroAleatorio> getVecNA() {
        return vecNA;
    }

    public float[] getNumeros()
    {
        float[] vec = new float[cant];
        int i = 0;
        for (NumeroAleatorio n: vecNA)
        {
            vec[i] = n.getNumRand();
            i++;
        }
        return vec;
    }
}
