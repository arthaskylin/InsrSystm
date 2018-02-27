package cd.pkg;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cpprsn.Cpprsn;

public class Cd_cvrg_intrst_fund extends CdCvrgAdm
{
	// private float value_per_unit;// 每份的现金价值，计算得到
	private int cash_formula_id;// 现金价值计算公式id，存儲

	public int getCash_formula_id()
	{
		return cash_formula_id;
	}

	public void setCash_formula_id(int cash_formula_id)
	{
		this.cash_formula_id = cash_formula_id;
	}

	@Override

	public void create_to_db()
	{

		ApplicationContext context = new ClassPathXmlApplicationContext("Cd_cvrg_intrst_fund.bean.xml");
		Cd_cvrg_intrst_fund Cvrg = (Cd_cvrg_intrst_fund) context.getBean("New_intrst_fund");
		Cvrg.save_to_db();
		Cvrg.create_to_newBussiness();
		((AbstractApplicationContext) context).close();
	}

	public void save_to_db()
	{
	}

	@Override

	public CdCvrgAdm create_to_newBussiness()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("Cd_cvrg_intrst_fund.bean.xml");
		Cd_cvrg_intrst_fund Cvrg = (Cd_cvrg_intrst_fund) context.getBean("New_intrst_fund");
		// 从数据库中读取cd信息，将信息set到自己身上，
		return Cvrg;
	}

}
