151
https://raw.githubusercontent.com/fordes123/Subtitles-View/master/src/main/java/org/fordes/subview/test/TestThread.java
package org.fordes.subview.test;

public class TestThread extends Thread
{
	public void run()
	{
		System.out.println(this.getName() + "子线程开始");
		try
		{
			
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(this.getName() + "子线程结束");
	}
}