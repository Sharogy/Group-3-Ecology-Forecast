package math;

import Util.Settings;

public class Grass_Model {
	
	private double growthrate;
	private double tempvar;
	
	public double getVar() {
		double sum = 0;
		for (int i = 0; i<Settings.tempdata.length; i++)
		{
			sum = Math.pow((Settings.tempdata[i] - Settings.avgtemp),2)+ sum;
		}
		tempvar = Math.pow(sum/Settings.tempdata.length,1);
		return tempvar;	
	}
	
	public double calculate() {
		double power = -0.5*Math.pow((Settings.avgtemp-Settings.optimaltemp)/getVar(),2);
		growthrate = Math.exp(power);
		return growthrate;
	}
	
	public static void main (String[] argz)
	{
		Grass_Model gm = new Grass_Model();
		System.out.println(gm.calculate());
		System.out.println(gm.getVar());
	}
}
