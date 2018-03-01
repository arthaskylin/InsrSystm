package cp.pkg;

import java.util.Calendar;
import java.util.Map;

import cd.pkg.CdCvrgAdm;
import cd.pkg.CvrgAadm;

import cd.pkg.eum.cvrgEnum.CvrgStatus;
import cd.pkg.eum.cvrgEnum.CvrgStatusReason;
import cd.pkg.eum.cvrgEnum.Suspend;
import cd.pkg.eum.cvrgEnum.SuspendReason;
import cd.pkg.eum.cvrgEnum.bllngFreq;
import cd.pkg.eum.cvrgEnum.timeUnit;
import cpprsn.Cpprsn;
import header.Cp_contract;

public class CpCvrgAdm extends CvrgAadm
{
	// ����Ϊ���屣��
	private String l_decrpt;
	private CvrgStatus status;
	private CvrgStatusReason statusReason;
	private Suspend suspend;
	private SuspendReason suspendReason;
	private Cpprsn l_insur; // ������
	private int issue_age;// ǩ��ʱ��ͣ��洢
	private Cp_contract l_cntrctr;// ����
	private int ppp_option;// prem Payment period �ɷ��ڼ� (egg.3��֮�ڽ���)
	private bllngFreq bllngFreq; // ��billing��ȡ
	private int bp_option;// �����ڼ� (egg.��10�꣩
	private timeUnit bp_nuit;// �����ڼ䵥λ
	private Calendar prem_expiry_date;// �ɷ�����
	private Calendar end_date;// ������ֹʱ��

	public static CpCvrgAdm create_evt(CdCvrgAdm cdCvrg, Map cvrgNode)
	{
		// String riskCode = (String) cvrgNode.get(cvrgNode);
		String cvrlClassName = cdCvrg.getL_cpCvrgName();
		try {
			CpCvrgAdm cpCvrg = (CpCvrgAdm) Class.forName(cvrlClassName).newInstance();
			cpCvrg.initialize(cdCvrg, cvrgNode);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	// ����cd_cvrg����cp_cvrg
	public void initialize(CdCvrgAdm cdCvrg, Map cvrgNode)
	{
		
	};

	public void cvrg_chng_status(CvrgStatus status, CvrgStatusReason Reason)
	{
		setStatus(status);
		setStatusReason(Reason);
	}

	public CvrgStatus getStatus()
	{
		return status;
	}

	public void setStatus(CvrgStatus status)
	{
		this.status = status;
	}

	public CvrgStatusReason getStatusReason()
	{
		return statusReason;
	}

	public void setStatusReason(CvrgStatusReason statusReason)
	{
		this.statusReason = statusReason;
	}

	public String getL_decrpt()
	{
		return l_decrpt;
	}

	public void setL_decrpt(String l_decrpt)
	{
		this.l_decrpt = l_decrpt;
	}

}
