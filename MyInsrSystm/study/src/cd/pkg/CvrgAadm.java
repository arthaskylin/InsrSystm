package cd.pkg;

import cp.pkg.CpCvrgAdm;

public abstract class CvrgAadm
{

	// cvrg顶层类，子类为cdCvrgAdm,cpCvrgAdm
	protected String riskCode;// id 8位
	protected String label;// 名称
	protected String clauses;// 特约
	protected cvrgLevel cvrgLevle;// 险种主附标志
	public CvrgAadm() {
	}
	public String getRiskCode()
	{
		return riskCode;
	}
	public void setRiskCode(String riskCode)
	{
		this.riskCode = riskCode;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	public String getClauses()
	{
		return clauses;
	}
	public void setClauses(String clauses)
	{
		this.clauses = clauses;
	}
	public cvrgLevel getCvrgLevle()
	{
		return cvrgLevle;
	}
	public void setCvrgLevle(cvrgLevel cvrgLevle)
	{
		this.cvrgLevle = cvrgLevle;
	}
	
}