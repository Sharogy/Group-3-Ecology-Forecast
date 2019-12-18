package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;

public class Stochastic_Model implements imodel{
	
	private int oldpop;
	private double birth;
	private double death;
	
	public int precalc(Animal ani, int timeperiod)
	{
		double increaseRate = birth - death;
		double power = increaseRate * timeperiod;
		double variancePopSize = (oldpop*oldpop*Math.exp(power*2)*(Math.exp(power*2)-1));
		return (int) Math.round(Math.pow(variancePopSize, 0.5));
	}
	

	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod) 
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

