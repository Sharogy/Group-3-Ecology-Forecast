package math;

import interfaces.imodel;

public class modelfactory {
	
	public imodel getModel(String modeltype)
	{
		if (modeltype == "null")
		{
			return null;
		}
		if (modeltype == "Exponential Model")
		{
			return new Exmodel();	
		}
		if (modeltype == "Stochastic Model")
		{
			return new Stomodel();
		}
		return null;
	}
}
