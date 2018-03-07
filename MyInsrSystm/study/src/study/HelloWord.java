package study;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.Map.Entry;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import batch.PrsnProcessbatch;
import batch.prsnReadeBatch;
import cpprsn.Cpprsn;
import dao.pkg.BaseDao;
import dao.pkg.SysBaseDao;

public class HelloWord
{
	public static void main(String[] args)
	{

		LinkedList<Cpprsn> prsnlst = new LinkedList<Cpprsn>();

		Thread read = new prsnReadeBatch(prsnlst);
		Thread process = new PrsnProcessbatch(prsnlst);
		read.start();
		process.start();

		/*
		 * Cpprsn prsn = new Cpprsn(); prsn.setName("kylin"); prsn.setIdntfr("500235");
		 * prsn.setSex("m");
		 * 
		 * // @SuppressWarnings("unchecked")
		 * 
		 * PrsnDao prsnDao = (PrsnDao) new SysBaseDao<Cpprsn>(); prsnDao.save(prsn);
		 * 
		 * //// //.save(prsn);
		 */ /*
			 * Map<String, String> maps = new HashMap<String, String>(); maps.put("k111",
			 * "111a"); maps.put("k222", "222b"); maps.put("k333", "333c"); maps.put("k44",
			 * "444d"); maps.put("k555", "555e"); maps.put("k666", "666f");
			 * 
			 * int siz=maps.size(); System.out.println("Map集合大小为："+siz);
			 */
		/*
		 * for(String str : maps.keySet()){ System.out.println("****************");
		 * System.out.println(str + ":" + maps.get(str)); }
		 */

		// System.out.println("--------------");
		//
		// for(Entry<String, String> str : maps.entrySet()){
		// System.out.println(str.getKey());
		// System.out.println(str.getValue());
		// //+ " " + str.getKey() + ":" + str.getValue());
		// }

	}

}
/*
 * private JdbcTemplate t;
 * 
 * int i = 10; while (i < 5) { int count = 0; count = count + i;
 * System.out.println(count); }
 * 
 * ApplicationContext context = new
 * ClassPathXmlApplicationContext("create_prsn.xml"); Cpprsn user = (Cpprsn)
 * context.getBean("NewOwner"); Cpprsn prs = new Cpprsn(); // Class<?> classnew
 * = prs.getClass(); String className = prs.getClass().getName();
 * System.out.println(className);
 * 
 * try { Cpprsn newInst = (Cpprsn) Class.forName(className).newInstance(); }
 * catch (InstantiationException | IllegalAccessException |
 * ClassNotFoundException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } System.out.println("88888888888888");
 * 
 * // String className2 = prs.getClass().getName();
 * System.out.println("88888888888888"); // cll oo=new cll();
 * 
 * }
 * 
 * public class Cp_cvrg_adm { private String type;
 * 
 * public Cp_cvrg_adm list_cvrg_layers() { if (type=="initial") {} return null;
 * }
 * 
 * } }
 */