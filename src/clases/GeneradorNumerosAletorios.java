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
    // Cantidad inical de numeros a generar
    private int count;

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
        this.count = 20;
    }

    /**
     * Genera un numero pseudoaleatorio
     *
     * @return un numero float con 4 digitos decimale
     */
    private float generador()
    {
        float num;

        num = (float) Xi/(m - 1);
        Xi = (( a * Xi) + c) % m;

        return (float)(Math.round(num*10000))/10000;
    }

    /**
     *
     */
    public void generar()
    {
        vecNA = FXCollections.observableArrayList();
        for (int i = 0; i<count; i++)
        {
            vecNA.add(new NumeroAleatorio(i, generador()));
        }
        count++;
    }

    /**
     *
     */
    public void siguiente()
    {
        float n = generador();
        int tam = vecNA.size();
        vecNA.add(new NumeroAleatorio(tam,n));
        count++;
    }

    /** Retorna una lista Observable XD
     * de NumerosAleatorio ordenados como fueron generados
     * @return el vecNA
     */
    public ObservableList<NumeroAleatorio> getVecNA() {
        return vecNA;
    }
}
