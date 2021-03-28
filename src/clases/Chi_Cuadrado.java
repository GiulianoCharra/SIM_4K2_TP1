package clases;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Chi_Cuadrado
{
    private int cantIntervalos;
    private int muestra;
    private float[] vecNum;
    private float media;
    private float var;
    private ObservableList<Intervalo> intervalos;

    public Chi_Cuadrado(int muestra, int cantIntervalos, String metodo)
    {
        this.cantIntervalos = cantIntervalos;
        this.muestra = muestra;
        this.vecNum = new float[muestra];
        switch (metodo)
        {
            case "Sistema":

                // Metodo Random del Sistema
                for (int i = 0; i < muestra; i++)
                {
                    vecNum[i] = (float)Math.round(Math.random()*10000)/10000;
                    System.out.println(vecNum[i]);
                }
                System.out.println(metodo + 1);
                break;
            case "Lineal":
                // Metodo Random  Lineal o Mixto
                System.out.println(metodo + 2);
                break;
            case "Multiplicativo":
                // Metodo Random Multiplicativo
                System.out.println(metodo+ 3 );
                break;
        }
        intervalos = FXCollections.observableArrayList();
        calcularChi();
    }

    private void calcMedia()
    {
        int sum = 0;
        for (float num: vecNum)
        {
            sum += num;
        }
        media = (float) sum/muestra;
    }

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
       float tam = (float) 1 /cantIntervalos;
       float inf = 0;
       float sup = tam;
        for (int i = 1; i <= cantIntervalos; i++)
        {
            intervalos.add(new Intervalo(i, inf,(float)Math.round(sup*100)/100));
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

    public ObservableList<Intervalo> getIntervalos()
    {
        return intervalos;
    }

    public static class Intervalo
    {
        private int numIt;
        private float inferior;
        private float superior;
        private int f_Obs;
        private int f_Esp;
        private float chi;

        public Intervalo(int num, float inferior, float superior)
        {
            this.numIt = num;
            this.inferior = inferior;
            this.superior = superior;
            this.f_Obs = 0;
            this.f_Esp = 0;
        }

        public int getNumIt() {
            return numIt;
        }

        public void setNumIt(int numIt) {
            this.numIt = numIt;
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
                    "num=" + numIt +
                    ", inferior=" + inferior +
                    ", superior=" + superior +
                    ", f_Obs=" + f_Obs +
                    ", f_Esp=" + f_Esp +
                    ", chi=" + chi +
                    '}';
        }
    }
}
