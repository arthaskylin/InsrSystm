package cd.pkg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cvrg_formula.Formula;
import header.Cd_product;

public abstract class CdCvrgAdm extends CvrgAadm
{// 产品级别，子类为具体的保项
	private Cd_product l_product;
	private productStatus cvrgSale;// 产品售卖状态
	private java.util.Date starDate; // 开售日期
	private String l_cpCvrgName;

	public void openCvrg(String stardate)
	{
		this.cvrgSale = productStatus.validated;
		SimpleDateFormat formatter = new SimpleDateFormat(stardate);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(stardate, pos);
		this.starDate = strtodate;
	}

	public CdCvrgAdm() {
	}

	public static CdCvrgAdm create_evt(Map cvrgNode)
	{
		String riskCode = (String) cvrgNode.get(cvrgNode);
		CdCvrgAdm CdCvrg = findCdcvrg(riskCode);
		CdCvrg.create_to_newBussiness();
		return CdCvrg;
	}

	public static CdCvrgAdm create_evt()
	{
		return null;
	}

	private static CdCvrgAdm findCdcvrg(String riskCode)
	{
		// TODO 按照riskCode 查询数据库，返回cdCvrg
		if (riskCode == "44440101") {
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext("Cd_cvrg_intrst_fund.bean.xml");
			Cd_cvrg_intrst_fund Cvrg = (Cd_cvrg_intrst_fund) context.getBean("New_intrst_fund");
			// Cvrg.save_to_db();
			// Cvrg.create_to_newBussiness();
			return Cvrg;
		}
		return null;
	}

	public abstract void create_to_db();// 产品定义时创建存储到数据库中

	public abstract CdCvrgAdm create_to_newBussiness();// 新保时创建对象给newbussness

	public String getL_cpCvrgName()
	{
		return l_cpCvrgName;
	}

	public void setL_cpCvrgName(String l_cpCvrgName)
	{
		this.l_cpCvrgName = l_cpCvrgName;
	}

}
