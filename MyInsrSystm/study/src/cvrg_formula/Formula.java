package cvrg_formula;

import java.util.ArrayList;

public abstract class Formula
{
	private String formula_id;

	public String getFormula_id()
	{
		return formula_id;
	}

	public void setFormula_id(String formula_id)
	{
		this.formula_id = formula_id;
	}

	public static void calculateFormula(int formula_id, ArrayList<?> objlist)
	{
		Formula formulaObj = findFormula(formula_id);
		formulaObj.calculate(objlist);
	}

	private static Formula findFormula(int formula_id2)
	{
		// TODO Auto-generated method stub
		// 跟据 formula_id 查数据库，返回实例
		return null;
	}

	abstract void calculate(ArrayList<?> objlist);

}
