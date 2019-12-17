package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;

public class Exmodel implements imodel {
	
	private int oldpop;
	private double birth;
	private double death;
	
	private int precalc(Animal ani, int timeperiod)
	{	
		double increaseRate = birth - death;
		double power = increaseRate * timeperiod;
		double newpop = oldpop * Math.exp(power);
		return (int) Math.round(newpop);
	}
	
	//Calculate for a period of time
	@Override
	public List<Integer> calculate(Animal ani, int timeperiod)
	{		
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