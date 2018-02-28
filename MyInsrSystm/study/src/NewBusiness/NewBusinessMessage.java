package NewBusiness;

import java.util.List;
import java.util.Map;

public class NewBusinessMessage
{
	private Map<String, String> baseInfoNode;
	private Map<String, String> ownerNode;
	private String relationshipNode;// 投保人和被保人的关系代码
	private Map<String, String> insuredNode;
	private Map<String, String> coverageSetNode;

	public Map<String, String> getBaseInfoNode()
	{
		return baseInfoNode;
	}

	public void setBaseInfoNode(Map<String, String> baseInfoNode)
	{
		this.baseInfoNode = baseInfoNode;
	}

	public Map<String, String> getOwnerNode()
	{
		return ownerNode;
	}

	public void setOwnerNode(Map<String, String> ownerNode)
	{
		this.ownerNode = ownerNode;
	}

	public String getRelationshipNode()
	{
		return relationshipNode;
	}

	public void setRelationshipNode(String relationshipNode)
	{
		this.relationshipNode = relationshipNode;
	}

	public Map<String, String> getInsuredNode()
	{
		return insuredNode;
	}

	public void setInsuredNode(Map<String, String> insuredNode)
	{
		this.insuredNode = insuredNode;
	}

	public Map<String, String> getCoverageSetNode()
	{
		return coverageSetNode;
	}

	public void setCoverageSetNode(Map<String, String> coverageSetNode)
	{
		this.coverageSetNode = coverageSetNode;
	}

}
