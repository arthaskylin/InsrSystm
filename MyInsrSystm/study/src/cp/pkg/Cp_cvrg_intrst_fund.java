package cp.pkg;

import java.util.ArrayList;

import cd.pkg.CdCvrgAdm;
import cvrg_formula.Formula;


public class Cp_cvrg_intrst_fund extends CpCvrgAdm
{
	private int cash_formula_id;// �ֽ��ֵ���㹫ʽid���惦
	private float cashValue;// �ֽ��ֵ������õ�

	public float getCashValue()
	{
		ArrayList<CpCvrgAdm> CvrgLst = new ArrayList<CpCvrgAdm>();
		CvrgLst.add(this);
		Formula.calculateFormula(cash_formula_id, CvrgLst);
		return cashValue;
	}

	

}
