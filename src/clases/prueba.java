package clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class prueba
{

    //Constantes enteros y positivos para la generacion de los numeros
    private int X0; // semilla
    private int a; // contante multiplicativa
    private int c;  //contante aditiva -- en el metodo Multiplicativo vale 0, no se usa
    private int m;  // modulo


    // necesario para calcular Chi-Cuadrado
    private int cantIntervalos;
    private int muestra;
    private float[] vecNum;
    private Intervalo[] intervalos;

    // Lista de NumerosAleatorios
    private ObservableList<randomNum> vecNA;

    // Cantidad de numeros a generar
    private int cant;
    private int Xi;


    private float media;
    private float var;


    /**
     * Metodo que permite calcular la media
     */
    private void calcMedia()
    {
        int sum = 0;
        for (float num: vecNum)
        {
            sum += num;
        }
        media = (float) sum/muestra;
    }

    /**
     * Metodo que calcula la Varianza
     */
    private void calcVarianza()
    {
        int sum = 0;
        var = 0;
        for (int i = 0; i <muestra; i++)
        {
            sum +=  Math.pow((vecNum[i]-media),2);
        }
        var = (float) sum/muestra;
    }


    public void calcIntervalos()
    {
        intervalos = new Intervalo[cantIntervalos];
        float tam = (float) cantIntervalos /muestra;
        float inf = 0;
        float sup = tam;
        for (int i = 0; i < cantIntervalos; i++)
        {
            intervalos[i] = new Intervalo(i,inf,(float)Math.round(sup*10)/10);
            inf = (float) Math.round((sup + 0.0001)*10000)/10000;
            sup += tam;

        }
    }


    private void f_Esperada()
    {
        int fE = muestra/cantIntervalos;
        for (Intervalo i: intervalos)
        {
            i.setF_Esp(fE);
        }
    }

    private void f_Observada()
    {
        for (float n: vecNum)
        {
            for (Intervalo i: intervalos)
            {
                System.out.println("Inf: " + i.getInferior()+
                        " Sup: " + i.getSuperior()+
                        " N°: " + n);

                if (n>= i.getInferior() && n<= i.getSuperior())
                {
                    System.out.println("aceptado");
                    i.contar();
                    break;
                }
            }
        }
    }

    public void calcularChi()
    {
        calcIntervalos();
        f_Observada();
        f_Esperada();
        float sum = 0;
        int fO;
        int fE;

        for (Intervalo n: intervalos)
        {
            fO = n.getF_Obs();
            fE = n.getF_Esp();
            float v = (float) (Math.pow((fO - fE), 2) / fE);
            System.out.println("fObse:" +fO+ " fEsp: " + fE+" nuevo: " + sum + "+"+ v + "= " + ((sum+v)) );
            sum += v;
            n.setChi((float)Math.round(sum*1000)/1000);
        }
    }

    public void mostrar()
    {
        for (Intervalo i: intervalos)
        {
            System.out.println(i.toString());
        }
    }


    /**
     * Genera una cantidad "n" de numero pseudoaleatorio
     * por defecto la cantidad inicial de numeros a genererar es de 20
     */
    private void generarNumero(int newCant)
    {
        if (cant!= newCant)
            cant = newCant;

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

    public static class Intervalo
    {
        private int num;
        private float inferior;
        private float superior;
        private int f_Obs;
        private int f_Esp;
        private float chi;

        public Intervalo(int num, float inferior, float superior)
        {
            this.num = num;
            this.inferior = inferior;
            this.superior = superior;
            this.f_Obs = 0;
            this.f_Esp = 0;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public float getInferior() {
            return inferior;
        }

        public void setInferior(float inferior) {
            this.inferior = inferior;
        }

        public float getSuperior() {
            return superior;
        }

        public void setSuperior(float superior) {
            this.superior = superior;
        }

        public int getF_Obs() {
            return f_Obs;
        }

        public void setF_Obs(int f_Obs) {
            this.f_Obs = f_Obs;
        }

        public int getF_Esp() {
            return f_Esp;
        }

        public void setF_Esp(int f_Esp) {
            this.f_Esp = f_Esp;
        }

        public float getChi() {
            return chi;
        }

        public void setChi(float chi) {
            this.chi = chi;
        }

        public void contar()
        {
            f_Obs++;
        }

        @Override
        public String toString() {
            return "Intervalo{" +
                    "num=" + num +
                    ", inferior=" + inferior +
                    ", superior=" + superior +
                    ", f_Obs=" + f_Obs +
                    ", f_Esp=" + f_Esp +
                    ", chi=" + chi +
                    '}';
        }
    }
    public  static class randomNum
    {
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
