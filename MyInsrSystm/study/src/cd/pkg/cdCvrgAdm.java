package cd.pkg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import header.cd_product;

public class cdCvrgAdm extends cvrgAadm
{// ��Ʒ��������Ϊ����ı���
	private cd_product l_product;
	private ProductStatus cvrgSale;// ��Ʒ����״̬
	private java.util.Date starDate; // ��������
    
	public void openCvrg(String stardate)
	{
		this.cvrgSale = ProductStatus.validated;
		SimpleDateFormat formatter = new SimpleDateFormat(stardate);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(stardate, pos);
		this.starDate = strtodate;
	}
}
