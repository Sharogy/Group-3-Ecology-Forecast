package math;

import java.util.List;

import model.Animal;
import interfaces.imodel;

public class Predator_Model {
	private List<Animal> animallist;
	private Animal ani;
	private int timeperiod;
	private boolean grassmode;
	private imodel im;
	private double predgrowth;
	private double preddeath;
	private int oldpredpop;
	private int newpredpop;
	
	public int precalc (Animal ani, int timeperiod, boolean grassmode, imodel im) 
	{
		//calculates the population for 1 animal for 1 timestep
		
		return 0;		
	}
	
	
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod, boolean grassmode, imodel im) {
		// calculates the population for 1 animal for N timesteps, returns a List<Integer>, see Exponential/Logistic model for example
		
		this.animallist = animallist;
		this.ani = ani;
		this.timeperiod = timeperiod;
		this.grassmode = grassmode;
		this.im = im;
		
		return null;
	}
	
	public int getPredPop(int oldpop)
	//calculates the population change for predators, assume growth/death rate of predator is known.
	{
		this.oldpredpop = oldpop;
		//formula here
		newpredpop = 0;
		
		return newpredpop;
	}

}
