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
	// private Map<String, String> cdCvrg_lst;// ��cd cvrg���� ,2xn,ÿһ��Ϊcdʵ���ͷ���
	private NewBusinessMessage busninessPara;
	private ArrayList<CpCvrgAdm> l_cpCvrg;// ������cp����
	private Cp_contract l_cntrct;// �����ɹ��ı���
	private Cpprsn l_owner;
	private Cpprsn l_insur;
	private Calendar process_date;// ����ʱ��
	private ArrayList<String> message;// ��¼��������в�������Ϣ
	private int error_number;// ��¼��������в����Ĵ�������

	public CpNewBussiness() {
	}

	public static void newBussStart()
	{ // ��̬���������
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
		System.out.println("-------------����У��������Ϣ��--------");
	}

	public void process_prsn()
	{// �����˵�ʵ������ֵ��l_owner,l_insur
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

	}// ����cp ����ʵ��

	public void process_cntrct()
	{// ������
	}

	public void setBusninessPara(NewBusinessMessage busninessPara)
	{
		this.busninessPara = busninessPara;
	}

}
