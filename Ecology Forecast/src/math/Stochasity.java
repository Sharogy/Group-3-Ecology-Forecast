package math;

import java.util.ArrayList;
import java.util.List;

public class Stochasity {
	
	private double variancePopSize;
	private double oldpop;
	private double birth;
	private double death;
	private int time = 0;
	private int timeperiod;
	private double increaseRate;
	
	public int calculate(double oldpop, double birth, double death, int timeperiod)
	{
		double increaseRate = birth - death;
		double power = increaseRate * time;
		variancePopSize = (oldpop*(birth+death)*Math.exp(power)*(Math.exp(power)-time))/increaseRate;
		return VariancePopSize;
	}
	
	public List VarianceCalculation(int timeperiod)
	{
		//RECURSIVE FUNCTION BASED ON TIME
		
		List b = new ArrayList(timeperiod);
		for (int i=1; i <= timeperiod; i++) {
			calculate(oldpop, birth, death, timeperiod);
			b.add(variancePopSize);
			
		System.out.println(b);
		return List b;
		}
		
	}
}

