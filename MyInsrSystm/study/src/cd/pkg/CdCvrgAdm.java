package cd.pkg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import cvrg_formula.Formula;
import header.Cd_product;

public abstract class CdCvrgAdm extends CvrgAadm
{// 产品级别，子类为具体的保项
	private Cd_product l_product;
	private productStatus cvrgSale;// 产品售卖状态
	private java.util.Date starDate; // 开售日期

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

	public static CdCvrgAdm createCdcvrgForNewBuss(String riskCode)
	{
		CdCvrgAdm CdCvrg = findCdcvrg(riskCode);
		CdCvrg.create_to_newBussiness();
		return CdCvrg;
	}

	private static CdCvrgAdm findCdcvrg(String riskCode)
	{
		return null;
	}

	public abstract void create_to_db();// 产品定义时创建存储到数据库中

	public abstract CdCvrgAdm create_to_newBussiness();// 新保时创建对象给newbussness

}
