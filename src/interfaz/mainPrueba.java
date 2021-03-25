package interfaz;


import clases.Chi_Cuadrado;
import clases.GeneradorNumerosAletorios;
import clases.NumeroAleatorio;

import java.util.ArrayList;

public class mainPrueba {

    public static void main(String[] args)
    {
        GeneradorNumerosAletorios lista = new GeneradorNumerosAletorios(6,13,7,8);
        lista.generar();
        lista.siguiente();

        Chi_Cuadrado chi = new Chi_Cuadrado(10,100,1);
        chi.calcularChi();
        chi.mostrar();
    }
}
