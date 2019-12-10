package math;

import java.util.ArrayList;
import java.util.List;

public class Exmodel {
	
	private double newpop;
	private double oldpop;
	private double birth;
	private double death;
	private int timeperiod;
	private int time = 0;
	
	public int calculate(double oldpop, double birth, double death, int timeperiod)
	{
		double increaseRate = birth - death;
		double power = increaseRate * time;
		newpop = oldpop * Math.exp(power);
		return newpop;
	}
	
	//Calculate for a period of time
	public List longcalculation(int timeperiod)
	{
		//RECURSIVE FUNCTION BASED ON TIME
		
		List a = new ArrayList(timeperiod);
		for (int i=1; i <= timeperiod; i++) {
			calculate(oldpop, birth, death, timeperiod);
			a.add(newpop);
			
		System.out.println(a);
		
		
		
	
		
		
		return a;
	}
	
	public static void Main(String argz)
	{
		Exmodel a = new Exmodel();
		//a.calculate(oldpop, growth, death, densitygrowth, densitydeath);
		
	}

		}
	}
}