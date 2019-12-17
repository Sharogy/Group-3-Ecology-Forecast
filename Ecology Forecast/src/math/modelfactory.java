package math;

import interfaces.imodel;

public class modelfactory {
	
	public imodel getModel(String modeltype)
	{
		if (modeltype == "null")
		{
			return null;
		}
		if (modeltype.equalsIgnoreCase("Exponential Model"))
		{
			return new Exponential_Model();	
		}
		if (modeltype.equalsIgnoreCase("Stochastic Model"))
		{
			return new Stochastic_Model();
		}
		if (modeltype.equalsIgnoreCase("Competitive Model"))
		{
			return new Competitive_Model();
		}
		if (modeltype.equalsIgnoreCase("Predator Model"))
		{
			return new Competitive_Model();
		}
		if (modeltype.equalsIgnoreCase("Logistic Model"))
		{
			return new Logistic_Model();
		}
		return null;
	}
}
