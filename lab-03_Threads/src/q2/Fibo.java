import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.lang.*;
class FibNumbers
{
    private volatile List<Integer> fibResult;

    public FibNumbers()
    {
        this.fibResult = new ArrayList<Integer>();
        this.fibResult.add(0);
        this.fibResult.add(1);
    }

    public List<Integer> getValues() {
        return fibResult;
    }
    public void add(int next) {
        this.fibResult.add(next);
    }

}
class Fibo implements Runnable
{
    private FibNumbers fibValues;
    private int size;
    public Fibo(FibNumbers values, int size) {

        this.fibValues = values;
        this.size = size;
    }


    public void run() {


        for (int i = 2; i <= this.size - 1 ; i++)
        {
            int next = this.fibValues.getValues().get(i-2) + this.fibValues.getValues().get(i-1);
            this.fibValues.add(next);
        }


    }
}
class Driver
{
    public static void print(int length, FibNumbers fib)
    {
        String fila = "";
        for (int i = 0; i <= length - 1; i++)
        {
            fila = fila + fib.getValues().get(i) + ' ';
        }
        System.out.println("Fibonnaci:  ");
        System.out.println(fila);

    }

    public static void main(String[] args) {
        FibNumbers fib = new FibNumbers();
        int size = -1;
        System.out.println("Insira a quantidade de valores desejados: ");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
            size = Integer.parseInt( reader.readLine());

        } catch (Exception e) {
            System.out.println("Valor inserido é invalido.");
        }
        if(size == 0)
        {
            String fila = "";
            for (int i = 0; i <= size - 1; i++)
            {
                fila = fila + fib.getValues().get(i) + ' ';
            }
            System.out.println("Fibonnaci:  ");
            System.out.println(fila);
        }
        else if (size == 1)
        {
            String fila = "";
            for (int i = 0; i <= size - 1; i++)
            {
                fila = fila + fib.getValues().get(i) + ' ';
            }
            System.out.println("Fibonnaci:  ");
            System.out.println(fila);
        }

        if(size >= 2)
        {

            Thread worker = new Thread(new Fibo(fib, size));
            worker.start();
            try {
                worker.join();
            } catch (InterruptedException ie) { }
            String fila = "";
            for (int i = 0; i <= size - 1; i++)
            {
                fila = fila + fib.getValues().get(i) + ' ';
            }
            System.out.println("Fibonnaci:  ");
            System.out.println(fila);
        }
    }
}