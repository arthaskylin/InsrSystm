package cd.pkg;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import cvrg_formula.Formula;
import header.Cd_product;

public abstract class CdCvrgAdm extends CvrgAadm
{// ��Ʒ��������Ϊ����ı���
	private Cd_product l_product;
	private productStatus cvrgSale;// ��Ʒ����״̬
	private java.util.Date starDate; // ��������

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

	public abstract void create_to_db();// ��Ʒ����ʱ�����洢�����ݿ���

	public abstract CdCvrgAdm create_to_newBussiness();// �±�ʱ���������newbussness

}
