package math;

import java.util.ArrayList;
import java.util.List;

import model.Animal;
import model.AnimalFactory;
import interfaces.imodel;

public class Predator_Model {
	private List<Animal> animallist;
	private int timeperiod;
	private boolean grassmode;
	private imodel im;
	private int packcount;
	private int oldpredpop;
	private double predcarryingcapacity = 48.9*55/100;
	private double newpredpop;
	private double predbirth = 0.29;
	private double preddeath = 0.18;
	private double consumption = 7/2.2046;
	private double primarymeatratio = 0.70;
	
	private static List<Integer> predpopulation;
		
	public List<List<Integer>> calculate(List<Animal> animallist, int timeperiod, boolean grassmode, imodel im, int wolfcount, boolean competitive) {
		// calculates the population for 1 animal for N timesteps, returns a List<Integer>, see Exponential/Logistic model for example
		
		this.animallist = animallist;
		this.grassmode = grassmode;
		this.oldpredpop = wolfcount;
		this.newpredpop = wolfcount;
		double requirement = consumption * newpredpop * 365 * primarymeatratio;	
		int primarycount = 0;
		double primaryanimalcount = 0;
		predpopulation = new ArrayList<Integer>(timeperiod);
		predpopulation.add((int) Math.round(newpredpop));
		
		List<List<Integer>> allanimalpop = new ArrayList<List<Integer>>(animallist.size());
		for (int i = 0; i<animallist.size(); i++)
		{
			List<Integer> animalpop = new ArrayList<Integer>(timeperiod);
			animalpop.add(animallist.get(i).getNumber());
			if (animallist.get(i).getType().equals("Primary"))
			{
				primarycount = primarycount +1;
				primaryanimalcount = animallist.get(i).getNumber()*animallist.get(i).getPreylikelihood() + primaryanimalcount;
				allanimalpop.add(animalpop);
			}		
		}
		
		if (competitive)
		{
			List<Animal> primarylist = new ArrayList<Animal>();
			for (int i = 0; i<animallist.size(); i++)
			{
				if (animallist.get(i).getType().equalsIgnoreCase("Primary"))
				{
					primarylist.add(animallist.get(i));
					//System.out.println(primarylist.get(i).getName());
				}
			}
			
			Competitive_Model cm = new Competitive_Model();
			for (int i = 0; i < timeperiod; i++)
			{
				for (int j = 0; j<primarycount; j++)
				{
					List<Integer> currentanimalcount = allanimalpop.get(j);
					//System.out.println("current animal");
					//System.out.println(currentanimalcount);
					
					List<Integer> temppop = new ArrayList<Integer>();
					for (int k = 0; k<3; k++)
					{
						//System.out.println(allanimalpop.get(k).get(0));
						temppop.add(allanimalpop.get(k).get(i));
					}
					//System.out.println(temppop);
					int time = i+1;
					Animal currentanimal = primarylist.get(j);
					//System.out.println(currentanimal.getName());
					double currentanimalmeatweight = currentanimal.getAvgweight()*2/3;
					double eaten = requirement*temppop.get(j)*currentanimal.getPreylikelihood()/primaryanimalcount/currentanimalmeatweight;
					//System.out.println(temppop.get(j));
					//System.out.println(currentanimal.getPreylikelihood());
					double nextanipop = cm.precalc(animallist, currentanimal, temppop, timeperiod, grassmode).get(j)-eaten;

					//double nextanipop = im.precalc(animallist, currentanimal, (int) Math.round(currentanimalcount.get(i)), 1, grassmode)-eaten;
					allanimalpop.get(j).add((int) Math.round(nextanipop));
				}
				newpredpop = getPredPop(newpredpop, i+1);
				requirement = consumption * newpredpop * 365 * primarymeatratio;	
				predpopulation.add((int) Math.round(newpredpop));
				primaryanimalcount = 0;
				for (int j = 0; j<primarycount; j++)
				{
					primaryanimalcount = allanimalpop.get(j).get(i)*animallist.get(j).getPreylikelihood()+primaryanimalcount;
				}
			}
		}
		if (!competitive)
		{
			for (int i = 0; i < timeperiod; i++)
			{
				for (int j = 0; j<primarycount; j++)
				{
					List<Integer> currentanimalcount = allanimalpop.get(j);
					//System.out.println("current animal");
					//System.out.println(currentanimalcount);
					int time = i+1;
					Animal currentanimal = animallist.get(j);
					double currentanimalmeatweight = currentanimal.getAvgweight()*2/3;
					double eaten = requirement*currentanimalcount.get(i)*currentanimal.getPreylikelihood()/primaryanimalcount/currentanimalmeatweight;
					
					double nextanipop = im.precalc(animallist, currentanimal, (int) Math.round(currentanimalcount.get(i)), 1, grassmode)-eaten;
					allanimalpop.get(j).add((int) Math.round(nextanipop));
				}
				newpredpop = getPredPop(newpredpop, i+1);
				requirement = consumption * newpredpop * 365 * primarymeatratio;	
				predpopulation.add((int) Math.round(newpredpop));
				primaryanimalcount = 0;
				for (int j = 0; j<primarycount; j++)
				{
					primaryanimalcount = allanimalpop.get(j).get(i)*animallist.get(j).getPreylikelihood()+primaryanimalcount;
				}
			}
		}
		
		for (int i = 0; i<animallist.size(); i++)
		{
			List<Integer> secondaryanimalpop = new ArrayList<Integer>(timeperiod);
			if (!animallist.get(i).getType().equals("Primary"))
			{
				Exponential_Model em = new Exponential_Model();
				secondaryanimalpop = em.calculate(animallist, animallist.get(i), timeperiod, grassmode, false);
				allanimalpop.add(secondaryanimalpop);
			}		
		}			
		return allanimalpop;
	}
	
	public int getPredPop(double number, int timeperiod)
	//calculates the population change for predators, assume growth/death rate of predator is known.
	{
		double oldpop =  number;		
		
		for (int i = 0; i<timeperiod; i++)
		{
			oldpop =  oldpop + (predbirth-preddeath) * oldpop * (1 - oldpop/predcarryingcapacity);
		}
		//System.out.println(oldpop);
		return (int) Math.round(oldpop);
	}
	
	public List<Integer> getPredPopulation()
	{
		return predpopulation;
	}
	
	public static void main(String[] argz)
	{
		List<List<Integer>> popoutcome;
		List<Integer>popoutcome2;
		AnimalFactory af = AnimalFactory.getInstance();
		List<Animal> anilist = af.getAnimals();
		Exponential_Model im = new Exponential_Model();
		Predator_Model pm = new Predator_Model();
		//popoutcome2 = im.calculate(anilist, anilist.get(0), 10, false, false);
		popoutcome = pm.calculate(anilist, 3, true, im, 10, true);
		List<Integer> pred = pm.getPredPopulation();
		
		//System.out.println(popoutcome.size());
		
		for (int i = 0; i< popoutcome.size(); i++)
		{
			List<Integer> anipop = popoutcome.get(i);
			System.out.println(popoutcome.get(i));
		}
		System.out.println(pred);
	}
}
