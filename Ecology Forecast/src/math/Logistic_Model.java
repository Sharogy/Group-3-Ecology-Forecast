package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;
import model.AnimalFactory;
import math.CarryingCapacity_Model;

public class Logistic_Model implements imodel{
	
	private double DiscreteGrowthFactor;
	private double FuturePopSize;
	private double Birth;
	private double Death;
	private double oldpop;
	List<Animal> animallist;
	private boolean grassmode;
	private boolean predatormode;
	
	public int precalc(Animal ani)
	{
		//DeathDensity = Death/oldpop;
		//BirthDensity = Birth/oldpop;
		DiscreteGrowthFactor = Birth-Death;
		
		double CarryingCap = CarryingCapacity_Model.getCarrycapacity(ani, animallist, grassmode);
		FuturePopSize = oldpop + DiscreteGrowthFactor * oldpop*((CarryingCap - oldpop) / CarryingCap);
		return (int) Math.round(FuturePopSize);
	}
	

	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod, boolean grassmode, boolean predatormode) 
	{
		this.oldpop = ani.getNumber();
		this.Birth = ani.getGrowthrate();
		this.Death = ani.getDeathrate();	
		this.animallist = animallist;
		this.grassmode = grassmode;
		this.predatormode = predatormode;
		
		List<Integer> a = new ArrayList<Integer>(timeperiod);
		a.add((int)Math.round(this.oldpop));
		for (int i=1; i <= timeperiod; i++) 
		{
			a.add(precalc(ani));	
			this.oldpop = precalc(ani);
		}
		return a;
	}
	
	public static void main(String[] argz)
	{
		List<Integer> popoutcome;
		AnimalFactory af = AnimalFactory.getInstance();
		Animal ani = af.getAnimals().get(0);
		Logistic_Model lm = new Logistic_Model();
		popoutcome = lm.calculate(af.getAnimals(), ani, 10, lm.grassmode, lm.predatormode);
		for (int i = 0; i< popoutcome.size(); i++)
		{
			System.out.println(i);
			System.out.println(popoutcome.get(i));
		}
	}
}
