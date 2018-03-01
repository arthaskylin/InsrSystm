package cp.pkg;

import java.util.ArrayList;
import java.util.Map;

import cd.pkg.CdCvrgAdm;
import cvrg_formula.Formula;

public class Cp_cvrg_intrst_fund extends CpCvrgAdm
{
	private int cash_formula_id;// �ֽ��ֵ���㹫ʽid���惦
	private float cashValue;// �ֽ��ֵ������õ�

	public Cp_cvrg_intrst_fund() {
		super.setL_decrpt("cp.pkg.Cp_cvrg_intrst_fund");
	}

	public float getCashValue()
	{
		ArrayList<CpCvrgAdm> CvrgLst = new ArrayList<CpCvrgAdm>();
		CvrgLst.add(this);
		Formula.calculateFormula(cash_formula_id, CvrgLst);
		return cashValue;
	}

	public void initialize(CdCvrgAdm cdCvrg, Map cvrgNode)
	{
		String cdName = cdCvrg.getClass().getName();
		this.setL_decrpt(cdName);
		this.riskCode = cdCvrg.getRiskCode();
		// this.cash_formula_id=((Object) cdCvrg).getCash_formula_id();
		
	
	}

}
