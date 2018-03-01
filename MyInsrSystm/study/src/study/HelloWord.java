package study;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cpprsn.Cpprsn;

public class HelloWord
{
	public static void main(String[] args)
	{
		int i = 10;
		while (i < 5) {
			int count = 0;
			count = count + i;
			System.out.println(count);
		}
		Cpprsn prs = new Cpprsn();
		// Class<?> classnew = prs.getClass();
		String className = prs.getClass().getName();
		System.out.println(className);

		try {
			Cpprsn newInst = (Cpprsn) Class.forName(className).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("88888888888888");

		// String className2 = prs.getClass().getName();
		System.out.println("88888888888888");
		// cll oo=new cll();

	}

	public class Cp_cvrg_adm
	{
		private String type;

		public Cp_cvrg_adm list_cvrg_layers() {
			if 
			(type=="initial")
			{}
			return null;
		}

	}
}
