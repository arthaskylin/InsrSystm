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
	private List<Map> cdCvrg_lst;
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
		ApplicationContext context = new ClassPathXmlApplicationContext("NewBuss.bean.xml");
		CpNewBussiness NewBuss = (CpNewBussiness) context.getBean("NewBuss");
		NewBuss.process_prsn();
		NewBuss.process_coverages();
		NewBuss.process_cntrct();
	}

	public void process_prsn()
	{// �����˵�ʵ������ֵ��l_owner,l_insur

		this.l_owner = Cpprsn.createPrsn("NewOwner");
		this.l_insur = Cpprsn.createPrsn("NewInsr");

	}

	public void process_Cdcvrg()
	{

		// ����cd���봴��cdʵ������ֵ��l_CdCvrg_lst

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

	}// ����cp ����ʵ��

	public void process_cntrct()
	{// ������
	}

	public void setCdCvrg_lst(List<Map> cdCvrg_lst)
	{
		this.cdCvrg_lst = cdCvrg_lst;
	}

}
