package cd.pkg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import header.cd_product;

public class cdCvrgAdm extends cvrgAadm
{// 产品级别，子类为具体的保项
	private cd_product l_product;
	private ProductStatus cvrgSale;// 产品售卖状态
	private java.util.Date starDate; // 开售日期
    
	public void openCvrg(String stardate)
	{
		this.cvrgSale = ProductStatus.validated;
		SimpleDateFormat formatter = new SimpleDateFormat(stardate);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(stardate, pos);
		this.starDate = strtodate;
	}
}
