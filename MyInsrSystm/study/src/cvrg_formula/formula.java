package cvrg_formula;

import java.util.ArrayList;

public abstract class formula
{
	private int formula_id;

	public static void calculateFormula(int formula_id, ArrayList<?> objlist)
	{
		formula formulaObj = findFormula(formula_id);
		formulaObj.calculate( objlist);
	}

	private static formula findFormula(int formula_id2)
	{
		// TODO Auto-generated method stub
		//���� formula_id �����ݿ⣬����ʵ��
		return null;
	}

	abstract  void calculate(ArrayList<?> objlist );

}
