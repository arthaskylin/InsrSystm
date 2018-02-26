package cp.pkg;

import java.util.ArrayList;

import cd.pkg.cdCvrgAdm;
import cvrg_formula.formula;

public class cp_cvrg_intrst_fund extends cpCvrgAdm
{
	private int cash_formula_id;// 现金价值计算公式id，存儲
	private float cashValue;// 现金价值，计算得到

	public float getCashValue()
	{
		ArrayList<cpCvrgAdm> CvrgLst = new ArrayList<cpCvrgAdm>();
		CvrgLst.add(this);
		formula.calculateFormula(cash_formula_id, CvrgLst);
		return cashValue;
	}

	@Override
	public cpCvrgAdm create_evt(cdCvrgAdm Cdcvrg) // 新保创建cvrg方法
	{
		// TODO Auto-generated method stub
		
		System.out.println("");
		System.out.println("");
		return null;
	}

}
