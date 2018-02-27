package cp.pkg;

import java.util.ArrayList;

import cd.pkg.CdCvrgAdm;
import cvrg_formula.Formula;


public class Cp_cvrg_intrst_fund extends CpCvrgAdm
{
	private int cash_formula_id;// 现金价值计算公式id，存儲
	private float cashValue;// 现金价值，计算得到

	public float getCashValue()
	{
		ArrayList<CpCvrgAdm> CvrgLst = new ArrayList<CpCvrgAdm>();
		CvrgLst.add(this);
		Formula.calculateFormula(cash_formula_id, CvrgLst);
		return cashValue;
	}

	

}
