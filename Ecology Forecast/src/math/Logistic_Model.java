package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;
import model.AnimalFactory;

public class Logistic_Model implements imodel{
	
	private double DiscreteGrowthFactor;
	private double FuturePopSize;
	private double Birth;
	private double Death;
	private double DeathDensity;
	private double BirthDensity;
	private double oldpop;
	
	public int precalc(Animal ani)
	{
		DeathDensity = Death/oldpop;
		BirthDensity = Birth/oldpop;
		DiscreteGrowthFactor = Birth-Death;
		int CarryingCap = (int) ((Birth - Death) / (BirthDensity+DeathDensity));
		FuturePopSize = oldpop + DiscreteGrowthFactor * oldpop*(1-(oldpop/CarryingCap));
		System.out.println(CarryingCap);
		return (int) Math.round(FuturePopSize);
	}
	

	public List<Integer> calculate(Animal ani, int timeperiod) 
	{
		this.oldpop = ani.getNumber();
		this.Birth = ani.getGrowthrate();
		this.Death = ani.getDeathrate();				
		List<Integer> a = new ArrayList<Integer>(timeperiod);
		a.add((int)Math.round(this.oldpop));
		for (int i=1; i <= timeperiod; i++) 
		{
			a.add(precalc(ani));	
			this.oldpop = precalc(ani);
		}
		return a;
	}
	
//	public static void main(String[] argz)
//	{
//		List<Integer> popoutcome;
//		AnimalFactory af = AnimalFactory.getInstance();
//		Animal ani = af.getAnimals().get(0);
//		Logistic_Model lm = new Logistic_Model();
//		popoutcome = lm.calculate(ani, 10);
//		for (int i = 0; i< popoutcome.size(); i++)
//		{
//			System.out.println(i);
//			//System.out.println(popoutcome.get(i));
//		}
//	}
}
