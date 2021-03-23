package interfaz;


import clases.GeneradorNumerosAletorios;

public class mainPrueba {

    public static void main(String[] args)
    {
        GeneradorNumerosAletorios lista = new GeneradorNumerosAletorios(6,13,7,8);
        lista.generar();
        lista.siguiente();
    }
}
