package NewBusiness;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cd.pkg.CdCvrgAdm;
import cp.pkg.CpCvrgAdm;
import cpprsn.Cpprsn;
import header.Cp_contract;

public class CpNewBussiness
{
	// private Map<String, String> cdCvrg_lst;// 传cd cvrg代码 ,2xn,每一行为cd实例和份数
	private List<Map> cdCvrg_lst;
	private ArrayList<CpCvrgAdm> l_cpCvrg;// 产生的cp保项
	private Cp_contract l_cntrct;// 创建成功的保单
	private Cpprsn l_owner;
	private Cpprsn l_insur;
	private Calendar process_date;// 处理时间
	private ArrayList<String> message;// 记录处理过程中产生的信息
	private int error_number;// 记录处理过程中产生的错误数量

	public CpNewBussiness() {
	}

	public static void newBussStart()
	{ // 静态方法，入口
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("NewBuss.bean.xml");
		CpNewBussiness NewBuss = (CpNewBussiness) context.getBean("NewBuss");
		NewBuss.process_prsn();
		NewBuss.process_coverages();
		NewBuss.process_cntrct();
	}

	public void process_prsn()
	{// 创建人的实例，赋值给l_owner,l_insur

		this.l_owner = Cpprsn.createPrsn("NewOwner");
		this.l_insur = Cpprsn.createPrsn("NewInsr");

	}

	public void process_Cdcvrg()
	{

		// 根据cd代码创建cd实例，赋值给l_CdCvrg_lst

	}

	public void process_coverages()
	{

		Iterator iter = cdCvrg_lst.iterator();
		while (iter.hasNext()) {
			Map map = (Map) iter.next();
			String riskCode = (String) map.get("riskCode");
			int unit = (int) map.get("unit");
			CdCvrgAdm CdCvrg = CdCvrgAdm.createCdcvrgForNewBuss(riskCode);
			CpCvrgAdm cpCvrg = CpCvrgAdm.create_evt(CdCvrg);
			l_cpCvrg.add(cpCvrg);

		}

	}// 创建cp 保项实例

	public void process_cntrct()
	{// 处理保单
	}

	public void setCdCvrg_lst(List<Map> cdCvrg_lst)
	{
		this.cdCvrg_lst = cdCvrg_lst;
	}

}
