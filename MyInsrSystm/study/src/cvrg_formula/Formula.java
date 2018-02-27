package cvrg_formula;

import java.util.ArrayList;

public abstract class Formula
{
	private int formula_id;

	public int getFormula_id()
	{
		return formula_id;
	}

	public void setFormula_id(int formula_id)
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
		// ���� formula_id �����ݿ⣬����ʵ��
		return null;
	}

	abstract void calculate(ArrayList<?> objlist);

}