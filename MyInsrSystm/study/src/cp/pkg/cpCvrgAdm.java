package cp.pkg;

import java.util.Calendar;
import cd.pkg.cdCvrgAdm;
import cd.pkg.cvrgAadm;
import cd.pkg.eum.cvrgEnum.CvrgStatus;
import cd.pkg.eum.cvrgEnum.CvrgStatusReason;
import cd.pkg.eum.cvrgEnum.Suspend;
import cd.pkg.eum.cvrgEnum.SuspendReason;
import cd.pkg.eum.cvrgEnum.bllngFreq;
import cd.pkg.eum.cvrgEnum.timeUnit;
import cpprsn.cpprsn;
import header.cp_contract;

public abstract class cpCvrgAdm extends cvrgAadm
{
	// ������������Ϊ���屣��
	private CvrgStatus status;
	private CvrgStatusReason statusReason;
	private Suspend suspend;
	private SuspendReason suspendReason;
	private cpprsn l_insur; // ������
	private int issue_age;// ǩ��ʱ��ͣ��洢
	private cp_contract l_cntrctr;// ����
	private int ppp_option;// prem Payment period �ɷ��ڼ� (egg.3��֮�ڽ���)
	private bllngFreq bllngFreq; // ��billing��ȡ
	private int bp_option;// �����ڼ� (egg.��10�꣩
	private timeUnit bp_nuit;// �����ڼ䵥λ
	private Calendar prem_expiry_date;// �ɷ�����
	private Calendar end_date;// ������ֹʱ��
	private cp_contract l_cntrct;
	public abstract cpCvrgAdm create_evt(cdCvrgAdm Cdcvrg);
	// ����cd_cvrg����cp_cvrg

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

}
