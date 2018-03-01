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
	// 子类为具体保项
	private String l_decrpt;
	private CvrgStatus status;
	private CvrgStatusReason statusReason;
	private Suspend suspend;
	private SuspendReason suspendReason;
	private Cpprsn l_insur; // 被保人
	private int issue_age;// 签发时年纪，存储
	private Cp_contract l_cntrctr;// 保单
	private int ppp_option;// prem Payment period 缴费期间 (egg.3年之内交完)
	private bllngFreq bllngFreq; // 从billing上取
	private int bp_option;// 保险期间 (egg.保10年）
	private timeUnit bp_nuit;// 保险期间单位
	private Calendar prem_expiry_date;// 缴费终期
	private Calendar end_date;// 保单终止时间

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

	// 根据cd_cvrg创建cp_cvrg
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
