package batch;

import java.util.LinkedList;
import java.util.Random;

import cpprsn.Cpprsn;

public class prsnReadeBatch extends Thread
{
	private LinkedList<Cpprsn> prsnlst = new LinkedList<Cpprsn>();

	public prsnReadeBatch(LinkedList<Cpprsn> prsn) {
		prsnlst = prsn;
	}

	public void readFromDb()
	{
		synchronized (prsnlst) {
			while (true) {
				while (prsnlst.size() >= 20) {
					System.out.println("reader wait. prsnlst size=" + prsnlst.size());
					try {
						prsnlst.notifyAll();
						prsnlst.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Cpprsn prsn = new Cpprsn();
				Random ran = new Random();
				int id = ran.nextInt();
				String strId = String.valueOf(id);
				prsn.setIdntfr(strId);
				prsnlst.addLast(prsn);
				System.out.println("add persin id=" + strId);
			}
		}
	}

	public void run()
	{
		readFromDb();
	}
}
