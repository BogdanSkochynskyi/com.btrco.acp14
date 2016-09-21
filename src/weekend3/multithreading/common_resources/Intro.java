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

//� ������� ���������� ���� ��� �����, ������ � ������� ������� ������ ������� ������� ��� � ���������� (L1-L2-L3)
//� L1 ������� ��� � L2 � L3..
//� � �������� ��������� ��� ������� �����������
//���� ������ ���� ���� � ��������� �� ��������. ������� ��������� ����� ��� � ��� � ��� ���������� ��������. 
//������ ���������� ���������� � ����� �� ����� ����� �������� ���������� ��������� � ����� ������ ���� ==> ������ ���������� ���������
//�������� ���� ��� = 0. � ���� ��� ������ ������������ ����� ��� �� ����������, �� ��� ��� ����� 0 � ���� 
// ��� ������ ������ �������� +1, �� ��� � ������ 1, �.� �������� 1. ������� ������ ���������� ���������� � ���� �������
//volatile ���� ����� ���� ������ ������, �� ������� ���� ��������� ������� �� ���� ���������� ������� �� ��������.
//��� ��������� ����� � ����������, � ������ � ���������� ����� � ���� �����������. 
// �� ������ �������� ������������ �� ��� �� � ��� �� ������ ��������
//���� ���� ����� �������� ���� ���������� ������� ���, � ������ ����� ������ �������� ��� ���������� �� ���� ����� ���������� ������ �����, ��
//���� ������ ���������� �� volatile �� ������ ����� ������ �� �� ������ ����, � ������ ������ �� ���� � ����� ����. 
//�������� ���������.



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

//synchronized - �������� � ��������
//monitor - ������ ������� ����� ������������ ���������� ������ � ������ �������
//������� � ���� ������ ������� ����� ��������� ������ 1 �����.
//(2 ������ ����� ���������)����� 1 ����� �������� � inc - �� ����������� ������� � ��������� ����� 
// ��� ������� �������� � ��� ��������� � ������ ���������
//������ ����� ������� �� ������, ��������� ������ ������� �������� ����� ��������� ������� � ����� 
// ��� ������ ��������, ��������� ���� � ����.
//� ���� ����� �� ���� ������� ��������������� �� ����������� ����������� ������. ��� �� ���� ������� �� ���� �����
//Blocked SET - ���� �������� ������, ���� ��� ����� ������, �� ������� �������� ������ �������


class Container {
    private volatile int count;

	private Object lock = new Object();
	
	//������ ������� ��� ���������� ������ �� ���� ������ �� ����� ��� ��� �� this
	//�� this ����� ������������� ������ ��� � ���� ������ ����� ���� �������� ���� ����������
	// � ��� ��� �� ����� ����� �� ���. � ��� ������� �� � ����� �������, ������ ��� � ����� ��� �� ����� ��������
	//synchronize ��������� ��� ������
	// ������ ���� ���, ������� �� ������ ������ ����� �� ����������������
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
		synchronized (this){ // � ���� ����� ������ ���, �� � ���� ������� ������. ��������� � ������ ���, ��� ��������� � ��������� Blocked
			count--;
		}
    }

    public int getCount() {
        return count;
    }
}
