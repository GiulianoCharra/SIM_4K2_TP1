package clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Clase que generara numeros peudaaleatorios por el metodo
 *  Linea o Mixto y el metodo Multiplicativo
 */
public class logicaTP1
{

    //Constantes enteros y positivos
    private int X0; // semilla
    private int a; // contante multiplicativa
    private int c;  //contante aditiva -- en el metodo Multiplicativo vale 0, no se usa
    private int m;  // modulo

    // Lista de NumerosAleatorios
    private ObservableList<randomNum> vecNA;

    // Cantidad de numeros a generar
    private int cant;

    private int Xi; //
    public logicaTP1() {
    }

    public logicaTP1(int X0, int a, int c, int m)
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
        randomNum nuevo;

        for (int i = vecNA.size(); i< newCant; i++)
        {
            num = (float) Xi/m;
            Xi = (( a * Xi) + c) % m;
            nuevo = new randomNum(i, (float)(Math.round(num*10000))/10000);
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
    public ObservableList<randomNum> getVecNA() {
        return vecNA;
    }

    public float[] getNumeros()
    {
        float[] vec = new float[cant];
        int i = 0;
        for (randomNum n: vecNA)
        {
            vec[i] = n.getNumRand();
            i++;
        }
        return vec;
    }

    public  static class randomNum {
        private int iteracion;
        private float numRand;

        public randomNum()
        {
        }

        public randomNum(int iteracion, float numRand)
        {
            this.iteracion = iteracion;
            this.numRand = numRand;
        }

        public int getIteracion()
        {
            return iteracion;
        }

        public void setIteracion(int iteracion) {
            this.iteracion = iteracion;
        }

        public float getNumRand() {
            return numRand;
        }

        public void setNumRand(float numRand) {
            this.numRand = numRand;
        }

    }

}
