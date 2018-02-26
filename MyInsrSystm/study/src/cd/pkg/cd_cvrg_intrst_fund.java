package cd.pkg;

public class cd_cvrg_intrst_fund extends cdCvrgAdm
{
	//private float value_per_unit;// 每份的现金价值，计算得到
	private int cash_formula_id;// 现金价值计算公式id，存儲

	public int getCash_formula_id()
	{
		return cash_formula_id;
	}
	public void setCash_formula_id(int cash_formula_id)
	{
		this.cash_formula_id = cash_formula_id;
	}
  
}
