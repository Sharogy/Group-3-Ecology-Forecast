package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;

public class Logmodel implements imodel{
	
	private double DiscreteGrowthFactor;
	private double FuturePopSize;
	private double Birth;
	private double Death;
	private double DeathDensity;
	private double BirthDensity;
	private double NewPopSize;
	private double oldpop;
	
	public int precalc(Animal ani, int timeperiod)
	{
		DeathDensity = Death/oldpop;
		BirthDensity = Birth/oldpop;
		int CarryingCap = (int) (Math.round(Birth - Death) / (BirthDensity+DeathDensity));
		FuturePopSize = oldpop + DiscreteGrowthFactor * oldpop*(1-(NewPopSize/CarryingCap));
		return (int) Math.round(FuturePopSize);
	}
	

	public List<Integer> calculate(Animal ani, int timeperiod) 
	{
		this.oldpop = ani.getNumber();
		this.DiscreteGrowthFactor = ani.getNumber();
		this.Birth = ani.getGrowthrate();
		this.Death = ani.getDeathrate();		

		List<Integer> a = new ArrayList<Integer>(timeperiod);
		a.add((int) FuturePopSize);
		for (int i=1; i <= timeperiod; i++) 
		{
			a.add(precalc(ani, i));			
		}
		return a;
	}
}
