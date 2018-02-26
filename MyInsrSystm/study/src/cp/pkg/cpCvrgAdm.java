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
	// 保单级别，子类为具体保项
	private CvrgStatus status;
	private CvrgStatusReason statusReason;
	private Suspend suspend;
	private SuspendReason suspendReason;
	private cpprsn l_insur; // 被保人
	private int issue_age;// 签发时年纪，存储
	private cp_contract l_cntrctr;// 保单
	private int ppp_option;// prem Payment period 缴费期间 (egg.3年之内交完)
	private bllngFreq bllngFreq; // 从billing上取
	private int bp_option;// 保险期间 (egg.保10年）
	private timeUnit bp_nuit;// 保险期间单位
	private Calendar prem_expiry_date;// 缴费终期
	private Calendar end_date;// 保单终止时间
	private cp_contract l_cntrct;
	public abstract cpCvrgAdm create_evt(cdCvrgAdm Cdcvrg);
	// 根据cd_cvrg创建cp_cvrg

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
