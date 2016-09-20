package weekend3.multithreading;

public class ThreadIntro {



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
        { //как только кто то вы
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

