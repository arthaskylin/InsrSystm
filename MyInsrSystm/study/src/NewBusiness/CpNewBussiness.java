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
	private NewBusinessMessage busninessPara;
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
		ApplicationContext context = new ClassPathXmlApplicationContext("NewBussbean.xml");
		CpNewBussiness NewBuss = (CpNewBussiness) context.getBean("NewBuss");
		verifyParam();
		NewBuss.process_prsn();
		NewBuss.process_coverages();
		NewBuss.process_cntrct();
	}

	private static void verifyParam()
	{
		System.out.println("-------------初步校验输入信息中--------");
	}

	public void process_prsn()
	{// 创建人的实例，赋值给l_owner,l_insur
		Map ownerMessage = busninessPara.getOwnerNode();
		String Ralationg = busninessPara.getRelationshipNode();
		Map insrMessage = busninessPara.getInsuredNode();
		Cpprsn insr = Cpprsn.createPrsn(insrMessage);
		if (Ralationg.equals("1") ) {
			System.out.println("validate");
			this.l_owner = insr;
		} else {
			Cpprsn owner = Cpprsn.createPrsn(ownerMessage);
			this.l_owner = owner;
		}
		this.l_insur = insr;
	}

	public void process_coverages()
	{
		Map cvrgNode = busninessPara.getCoverageSetNode();
		CdCvrgAdm cdCvrg = CdCvrgAdm.create_evt(cvrgNode);
		CpCvrgAdm cpCvrg = CpCvrgAdm.create_evt(cdCvrg, cvrgNode);
		// l_cpCvrg.add(cpCvrg);

	}// 创建cp 保项实例

	public void process_cntrct()
	{// 处理保单
	}

	public void setBusninessPara(NewBusinessMessage busninessPara)
	{
		this.busninessPara = busninessPara;
	}

}
