package weekend3.multithreading.common_resources;

public class Intro {


    public static void main(String[] args) {
        Container c = new Container();

            Thread thread1 = new Thread(new IncThread(c, 1000000));
            thread1.start();
            Thread thread2 =  new Thread(new DecrThread(c, 1000000));
            thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(c.getCount());

    }

}

class IncThread implements Runnable
{

    private Container container;
    private int operationCounter;

    public IncThread(Container container, int operationCounter) {
        this.container = container;
        this.operationCounter = operationCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < operationCounter; i++) {
            container.inc();
        }

    }
}
class DecrThread implements Runnable
{
    private Container container;
    private int operationCounter;

    public DecrThread(Container container, int operationCounter) {
        this.container = container;
        this.operationCounter = operationCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < operationCounter; i++) {
            container.decr();
        }
    }
}


class Container {
    private int count;

    public void inc()
    {
        count++;
    }

    public void decr()
    {
        count--;
    }

    public int getCount() {
        return count;
    }
}
