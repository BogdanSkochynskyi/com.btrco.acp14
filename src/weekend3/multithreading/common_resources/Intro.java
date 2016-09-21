package weekend3.multithreading.common_resources;

public class Intro {


    public static void main(String[] args) {
        Container c = new Container();

		long start = System.currentTimeMillis();
		
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
		
		long end = System.currentTimeMillis();
	
		System.out.println("Time");
		System.out.println(end - start);
		System.out.println("Counter");
		System.out.println(c.getCount());

    }

}

//У каждого процессора есть кэш линии, память к которым имеется доступ намного быстрее чем к оперативке (L1-L2-L3)
//К L1 быстрее чем к L2 и L3..
//А к регистру процессор еще быстрее доступается
//Этот пример выше даже в оперативу не грузится. Поэтому процессор берет инт в кэш и там производит операции. 
//Другие процессоры аналогично и никто не знает какие операции производит процессор в своем личном кэше ==> разные результаты постоянно
//например есть инт = 0. И если два потока одновременно берут его на выполнение, то они оба берут 0 и если 
// оба потока делают операцию +1, то оба и вернут 1, т.е теряется 1. Поэтому разные результаты получаются в этом примере
//volatile если будет проц менять данные, то сообщит всем остальным потокам об этих изменениях который он произвел.
//Это изменение уйдет в оперативку, и доступ к изменениям будет у всех процессоров. 
// Но другие процессы переписывают ее так же и это не решает проблему
//Если один поток работает пока переменная буллиан тру, а второй поток должен поменять эту переменную на фолс чтобы остановить первый поток, то
//если булиан переменная не volatile то первый поток читает ее из своего кэша, а второй меняет ее тоже в своем кэше. 
//Волатайл замедляет.



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

//synchronized - крепится к монитору
//monitor - объект который будет обеспечивать правильный доступ к общему ресурсу
//монитор в один момент времени может захватить только 1 поток.
//(2 потока юзают инкремент)Когда 1 поток приходит в inc - он захватывает монитор а остальные видят 
// что монитор захвачен и они переходят в блокед состояние
//первый поток выходит из метода, остальные потоки начинаю ломиться чтобы захватить монитор и опять 
// кто первый захватил, остальные ушли в блок.
//В этом месте по сути умирает многопоточность но сохраняется целостность данных. Это по сути семафор на один поток
//Blocked SET - сюда попадают потоки, если они хотят ресурс, но монитор захвачен другим потоком


class Container {
    private volatile int count;

	private Object lock = new Object();
	
	//второй монитор для совместных данных не есть хорошо но лучше так чем по this
	//по this плохо блокироваться потому что в мейн потоке можно тоже добавить блок синхронайз
	// и уже что то может пойти не так. А тут монитор не в общем доступе, только тут и никто его не может изменить
	//synchronize замедляет еще больше
	// методы типа гет, которые не меняют данные можно не синхронизировать
/*    public synchronized void inc()
    {
		synchronized (monitor)
		{
			count++;
		}
    }*/
	
	public synchronized void inc() // monitor this
    {
        count++;
    }

    public void decr()
    {
		synchronized (this){ // в этот метод зайдут все, но в блок попадет первый. остальные в блокед сет, они находятся в состоянии Blocked
			count--;
		}
    }

    public int getCount() {
        return count;
    }
}
