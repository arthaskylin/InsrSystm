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
		while (true) {
			synchronized (prsnlst) {

				while (prsnlst.size() >= 0 & prsnlst.size() <= 20) {
					Cpprsn prsn = new Cpprsn();
					Random ran = new Random();
					int id = ran.nextInt();
					String strId = String.valueOf(id);
					prsn.setIdntfr(strId);
					prsnlst.addLast(prsn);
					System.out.println("add persin id=" + strId);
				}
				System.out.println("reader wait. prsnlst size=" + prsnlst.size());
				try {
					notifyAll();
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}

		}
	}

	public void run()
	{
		readFromDb();
	}
}
