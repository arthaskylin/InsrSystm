package batch;

import java.util.LinkedList;

import cpprsn.Cpprsn;

public class PrsnProcessbatch extends Thread
{
	private LinkedList<Cpprsn> prsnlst = new LinkedList<Cpprsn>();

	public PrsnProcessbatch(LinkedList<Cpprsn> prsn) {
		prsnlst = prsn;
	}

	public void processLst()
	{
		while (true) {
			synchronized (prsnlst) {
				while (prsnlst.size() <= 0) {
					System.out.println("process wait. prsnlst size=" + prsnlst.size());
					try {
						prsnlst.notify();
						prsnlst.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Cpprsn Prsn = prsnlst.getFirst();
				String ID = Prsn.getIdntfr();
				System.out.println("process persn. id =" + ID);
				prsnlst.remove(0);
			}
		}
	}

	public void run()
	{
		processLst();
	}
}
