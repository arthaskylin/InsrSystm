package cp.pkg;

import java.util.ArrayList;

import cd.pkg.cdCvrgAdm;
import cvrg_formula.formula;

public class cp_cvrg_intrst_fund extends cpCvrgAdm
{
	private int cash_formula_id;// �ֽ��ֵ���㹫ʽid���惦
	private float cashValue;// �ֽ��ֵ������õ�

	public float getCashValue()
	{
		ArrayList<cpCvrgAdm> CvrgLst = new ArrayList<cpCvrgAdm>();
		CvrgLst.add(this);
		formula.calculateFormula(cash_formula_id, CvrgLst);
		return cashValue;
	}

	@Override
	public cpCvrgAdm create_evt(cdCvrgAdm Cdcvrg) // �±�����cvrg����
	{
		// TODO Auto-generated method stub
		
		System.out.println("");
		System.out.println("");
		return null;
	}

}
