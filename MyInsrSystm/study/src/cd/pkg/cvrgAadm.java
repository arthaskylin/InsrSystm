package cd.pkg;

import cp.pkg.cpCvrgAdm;

public abstract class cvrgAadm
{

	// cvrg�����࣬����ΪcdCvrgAdm,cpCvrgAdm
	protected String riskCode;// id 8λ
	protected String label;// ����
	protected String clauses;// ��Լ
	protected cvrgLevel cvrgLevle;// ����������־
	public cvrgAadm() {
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