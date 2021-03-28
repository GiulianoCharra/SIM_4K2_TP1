package interfaz;


import clases.Chi_Cuadrado;
import clases.logicaTP1;

public class mainPrueba {

    public static void main(String[] args)
    {
        logicaTP1 lista = new logicaTP1(6,13,7,8);
        lista.generar();
        lista.siguiente();

        Chi_Cuadrado chi = new Chi_Cuadrado(10,100,"Sistema");
        chi.calcularChi();
        chi.mostrar();
    }
}
