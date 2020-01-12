package math;

import java.util.ArrayList;
import java.util.List;

import com.oracle.webservices.internal.api.EnvelopeStyle;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import interfaces.imodel;
import model.Animal;
import model.AnimalFactory;

public class Competitive_Model implements imodel{
	
	private double a11;
	private double a12;
	private double a13;
	private double a21;
	private double a22;
	private double a23;
	private double a31;
	private double a32;
	private double a33;
	private double k1;
	private double k2;
	private int ne1;
	private int ne2;
	private double newanipop;
	private double newanipop2;
	private double newanipop3;
	private double oldanipop;
	private double oldanipop2;
	private double oldanipop3;
	// get the data from cal
	List<Animal> animallist;
	Animal ani;
	int timePeriod;
	boolean grassmode;
	boolean predatormode;
	
	public int precalc(List<Animal> animallist, Animal ani, int population,  int timeperiod, boolean grassmode)
	{
		return 0;
	}
	
	public List<Integer> precalc(List<Animal> animallist, Animal ani, List<Integer> population,  int timeperiod, boolean grassmode)
	{
		List<Integer> animalpop = new ArrayList<Integer>(animallist.size());
		
		oldanipop = population.get(1);
		oldanipop2 = population.get(2);
		oldanipop3 = population.get(0);
		
		k1 = CarryingCapacity_Model.getCarrycapacity(animallist.get(1), animallist, grassmode);
		k2 = CarryingCapacity_Model.getCarrycapacity(animallist.get(2), animallist, grassmode);
				
		ne1 = 200;
		ne2 = 850;
		
		a12 = (k1-ne1)/ne2;
		a21 = (k2-ne2)/ne1;
		
		double factor1 = (animallist.get(1).getGrowthrate()-animallist.get(1).getDeathrate())/k1;
		newanipop = oldanipop + factor1*oldanipop*(k1-oldanipop-oldanipop2*a12);
		
		double factor2 = (animallist.get(2).getGrowthrate()-animallist.get(2).getDeathrate())/k2;
		newanipop2 = oldanipop2 + factor2*oldanipop2*(k2-oldanipop*a21-oldanipop2);
		
		k1 = CarryingCapacity_Model.getCarrycapacity(animallist.get(1), animallist, grassmode) + CarryingCapacity_Model.getCarrycapacity(animallist.get(2), animallist, grassmode);
		k2 = CarryingCapacity_Model.getCarrycapacity(animallist.get(0), animallist, grassmode);
		//System.out.println(k2);
		
		ne1 = 200+850;
		ne2 = 2250;
		
		a12 = (k1-ne1)/ne2;
		a21 = (k2-ne2)/ne1;
		
		double oldanipoptemp = oldanipop + oldanipop2;
		
		double factor3 = (animallist.get(0).getGrowthrate() - animallist.get(0).getDeathrate())/k2;
		newanipop3 = oldanipop3 + factor3*oldanipop3*(k2-oldanipoptemp*a21-oldanipop3);
		
		animalpop.add((int) Math.round(newanipop3));
		animalpop.add((int) Math.round(newanipop));
		animalpop.add((int) Math.round(newanipop2));
		
		return animalpop;
	}
	
	
	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod , boolean grassmode, boolean predatormode) {
		this.animallist = animallist;
		this.ani = ani;
		this.timePeriod = timeperiod;
		this.grassmode = grassmode;
		this.predatormode = predatormode;
		List<Integer> animalpop = new ArrayList<Integer>();
		if (ani.getType().equalsIgnoreCase("Primary"))
		{
			int primarycount = 0;
			List<Animal> primarylist = new ArrayList<Animal>();
			for (int i = 0; i<animallist.size(); i++)
			{
				if (animallist.get(i).getType().equalsIgnoreCase("Primary"))
				{
					primarycount = primarycount+1;
					primarylist.add(animallist.get(i));
					//System.out.println(primarylist.get(i).getName());
				}
			}
			List<Integer> startingpop = new ArrayList<Integer>();
			startingpop.add(primarylist.get(0).getNumber());
			startingpop.add(primarylist.get(1).getNumber());
			startingpop.add(primarylist.get(2).getNumber());
			
			if (ani.getName().equalsIgnoreCase("Heck Cattle"))
			{
				animalpop.add(startingpop.get(1));
				for (int j = 0; j<timeperiod; j++)
				{
					List<Integer> nextpop = precalc(primarylist, ani, startingpop, 1, grassmode);
					animalpop.add(nextpop.get(1));
					startingpop = nextpop;
				}
			}
			if (ani.getName().equalsIgnoreCase("Konik Horse"))
			{
				animalpop.add(startingpop.get(2));
				for (int j = 0; j<timeperiod; j++)
				{
					List<Integer> nextpop = precalc(primarylist, ani, startingpop, 1, grassmode);
					animalpop.add(nextpop.get(2));
					startingpop = nextpop;
				}
			}
			if (ani.getName().equalsIgnoreCase("Red Deer"))
			{
				animalpop.add(startingpop.get(0));
				for (int j = 0; j<timeperiod; j++)
				{
					List<Integer> nextpop = precalc(primarylist, ani, startingpop, 1, grassmode);
					animalpop.add(nextpop.get(0));
					startingpop = nextpop;
				}
			}
			
			//System.out.println(animalpop);
			
		}
		else
		{
			Exponential_Model em = new Exponential_Model();
			animalpop = em.calculate(animallist, ani, timeperiod, grassmode, predatormode);
			//System.out.println(ani.getName());
		}
		
		
		
		return animalpop;
		
		
	}
	

	public static void main(String[] argz)
	{
		List<Integer> popoutcome;
		int popoutcome2;
		AnimalFactory af = AnimalFactory.getInstance();
		Animal ani = af.getAnimals().get(3);
		List<Animal> anilist = af.getAnimals();
		Competitive_Model cm = new Competitive_Model();
		//popoutcome2 = cm.precalc(anilist, ani, 1694, 1, false);
		popoutcome = cm.calculate(af.getAnimals(), ani, 5, false, false);
		//System.out.println(popoutcome.size());
		for (int i = 0; i< popoutcome.size(); i++)
		{
			//System.out.println(popoutcome.get(i));
		}
		//System.out.println(popoutcome);
	}
}
