package math;

import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.AnimalFactory;
import interfaces.imodel;

public class Predator_Model {
	private List<Animal> animallist;
	private Animal ani;
	private int timeperiod;
	private boolean grassmode;
	private imodel im;
	private double predgrowth = 0.29;
	private double preddeath = 0.18;
	private int oldpredpop = 100;
	private double newpredpop = 100;
	private double oldanipop;
	private double newanipop;
	private double captureeff = 0.0005;
	
	public int precalc (Animal ani, int timeperiod, boolean grassmode, imodel im) 
	{
		//calculates the population for 1 animal for 1 timestep
		
		if (timeperiod == 0)
		{
			oldanipop = im.precalc(animallist, ani, ani.getNumber(), timeperiod, grassmode);
			System.out.println(oldanipop);
			return (int) (int)Math.round(oldanipop);
			
		}
		else 
		{
			//oldanipop = im.precalc(animallist, ani, (int)Math.round(oldanipop), timeperiod, false);
			double eaten = captureeff * oldanipop * newpredpop;
			oldanipop = im.precalc(animallist,  ani,  (int) Math.round(oldanipop), 1,  grassmode) - eaten;
			newpredpop = getPredPop(oldpredpop,timeperiod);
			
			//System.out.println(newpredpop);
			
			//System.out.println(eaten);
			System.out.println(oldanipop);
		}
				
		//System.out.println(oldanipop);
		return (int)Math.round(oldanipop);
	}
	
	
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod, boolean grassmode, imodel im) {
		// calculates the population for 1 animal for N timesteps, returns a List<Integer>, see Exponential/Logistic model for example
		
		this.animallist = animallist;
		this.ani = ani;
		this.grassmode = grassmode;
		
		List<Integer> a = new ArrayList<Integer>(timeperiod);
		//a.add(ani.getNumber());
		for (int i=0; i<=timeperiod; i++)
		{
			a.add(precalc(ani, i, grassmode, im));
		}
		
		return a;
	}
	
	public int getPredPop(int number, int timeperiod)
	//calculates the population change for predators, assume growth/death rate of predator is known.
	{
		double oldpop =  number;		
		
		for (int i = 0; i<timeperiod; i++)
		{
			oldpop =  (oldpop + predgrowth * oldpop - preddeath * oldpop);
		}
		//System.out.println(oldpop);
		return (int) Math.round(oldpop);
	}
	
	public static void main(String[] argz)
	{
		List<Integer> popoutcome;
		AnimalFactory af = AnimalFactory.getInstance();
		Animal ani = af.getAnimals().get(0);
		List<Animal> anilist = af.getAnimals();
		Logistic_Model im = new Logistic_Model();
		Predator_Model pm = new Predator_Model();
		popoutcome = pm.calculate(anilist, ani, 10, false, im);
		
		//System.out.println(im.precalc(anilist, ani,0,false));
		
		for (int i = 0; i< popoutcome.size(); i++)
		{
			//System.out.println(popoutcome.get(i));
		}
	}
}
