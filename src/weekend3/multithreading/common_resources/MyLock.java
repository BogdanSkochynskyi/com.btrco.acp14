package weekend3.multithreading.common_resources;

public class MyLock
{
	
	private volatile boolean isLocked;
	public void lock()
	{
		while (isLocked){} //spin lock нагружает процессор
		isLocked = true;
	}
	
	public void unlock()
	{
		isLocked = false;
	}
	
	
}
