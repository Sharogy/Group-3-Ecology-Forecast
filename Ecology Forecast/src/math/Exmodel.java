package math;

import java.util.ArrayList;
import java.util.List;

public class Exmodel {
	
	private double newpop;
	private double oldpop;
	private double birth;
	private double death;
	private int timeperiode;
	
	public void calculate(int oldpop, double birth, double death, int timeperiode)
	{
		
		double r = birth - death;
		int time = 1;
		List calculation = new ArrayList(timeperiode);
		while (time <= timeperiode) {
			double power = r * time;
			newpop = oldpop * Math.exp(power);
			calculation.add(newpop);
			time++;
			System.out.println(calculation);

// comments for the show
		}
	}
}