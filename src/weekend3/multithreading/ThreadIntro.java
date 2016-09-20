package weekend3.multithreading;

public class ThreadIntro {

    public static void main(String[] args) {
        //get current thread
        Thread main = Thread.currentThread();
        //Thread.sleep(1000); // == Thread.currentThread.sleep(1000);

        System.out.println(main.getName());

        MyThread myThread = new MyThread("simple thread");
//Daemon поток - слабый поток, который зависит от мейна
//Не демон поток - основная программа умерла, а ты можешь продолжать жить. Демон - умираешь вместе с главным потоком
        myThread.setDaemon(true);
        myThread.start();

        for (int i = 0; i < 10; i++) {
            System.out.printf("main working %d\n", i);
            try {
                main.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



class MyThread extends Thread
{
    private String threadDescription;

    public MyThread(String threadDescription) {
        this.threadDescription = threadDescription;
    }

    @Override
    public void run() {

        while (!isInterrupted()) // пока не отправлен сигнал что этот поток не нужен - работает
        { //как только кто то вызовет на этом потоке interrupt метод - вызовется эксепшн и в кэче можно довыполнить что то или просто продолжить свою работу.
            //и чтобы окончательно закрыть поток - надо самому на себе вызвать метод interrupt
            System.out.printf("My work %s, %s, %s\n", getId(), getName(), getState());
            try {
                sleep(1000); //не доступиться к ресурсам пока спишь
            } catch (InterruptedException e) { //когда спал - и кто то вызывает interrupt метод на этот поток, надо завершить работу, выкидывается эксепшни и будит поток
                e.printStackTrace();
                interrupt(); //самостоятельная остановка после выполнения необходимых действий или
            }
        }

    }
}

