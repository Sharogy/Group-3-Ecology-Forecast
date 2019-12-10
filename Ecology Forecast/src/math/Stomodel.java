package math;

import java.util.ArrayList;
import java.util.List;

import model.Animal;

public class Stomodel implements imodel{
	
	private int oldpop;
	private double birth;
	private double death;
	
	public int precalc(Animal ani, int timeperiod)
	{
		double increaseRate = birth - death;
		double power = increaseRate * timeperiod;
		double variancePopSize = (oldpop*(birth+death)*Math.exp(power)*(Math.exp(power)-timeperiod))/increaseRate;
		return (int) Math.round(variancePopSize);
	}
	

	@Override
	public List<Integer> calculate(Animal ani, int timeperiod) {
		this.oldpop = ani.getNumber();
		this.birth = ani.getGrowthrate();
		this.death = ani.getDeathrate();

		List<Integer> a = new ArrayList<Integer>(timeperiod);
		a.add(oldpop);
		for (int i=1; i <= timeperiod; i++) 
		{
			a.add(precalc(ani, i));			
		}
		return a;
	}
}

