package clases;

import java.util.HashMap;
import java.util.Map;

public class GeneradorNumerosAletorios
{
    private int X0;
    private int a;
    private int c;
    private int m;
    private Map<Integer, Float> vecNA;
    private int count;
    private int Xi;

    public GeneradorNumerosAletorios(int X0, int a, int c, int m)
    {
        this.X0 = X0;
        this.a = a;
        this.c = c;
        this.m = m;
        this.vecNA = new HashMap<>();
        this.Xi = X0;
        this.count = 20;
    }

    private float generador()
    {
        float num;

        num = (float) Xi/(m - 1);
        Xi = (( a * Xi) + c) % m;

        return (float)(Math.round(num*10000))/10000;
    }

    public void generar()
    {
        for (int i = 0; i<count; i++)
        {
            vecNA.put(i,generador());
        }

        System.out.println(vecNA.entrySet());
        count++;
    }

    public void siguiente()
    {
        System.out.println("\n");
        float n = generador();
        int tam = vecNA.size();
        vecNA.put(tam,n);
        count++;
        for (Map.Entry m: vecNA.entrySet())
        {
            System.out.println(m);
        }
    }



    public Map<Integer, Float> getVecNA()
    {
        return vecNA;
    }
}
