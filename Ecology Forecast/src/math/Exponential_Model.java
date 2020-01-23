package math;

import java.util.ArrayList;
import java.util.List;

import interfaces.imodel;
import model.Animal;
import model.AnimalFactory;

public class Exponential_Model implements imodel {
	
	private int oldpop;
	private double birth;
	private double death;
	private List<Animal> animallist;
	private boolean grassmode;
	private boolean predatormode;
	
	public int precalc(List<Animal> animallist, Animal ani, int population, int timeperiod, boolean grassmode)
	{	
		this.animallist = animallist;
		this.oldpop = population;
		this.birth = ani.getBirthrate();
		this.death = ani.getDeathrate();
		this.grassmode = grassmode;
		double increaseRate = birth - death;
		double power = increaseRate * timeperiod;
		double teller = CarryingCapacity_Model.getCarrycapacity(ani, animallist, grassmode);
		double nummer = 1 + ((teller - oldpop)/oldpop)*Math.exp(-power);
		double newpop = teller/nummer;
		return (int) Math.round(newpop);
	}
	
	//Calculate for a period of time
	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod , boolean grassmode, boolean predatormode)
	{		

		List<Integer> a = new ArrayList<Integer>(timeperiod);
		for (int i=0; i <= timeperiod; i++) 
		{
			a.add(precalc(animallist, ani, ani.getNumber(), i, grassmode));			
		}
		return a;
	}
	
	public static void main(String[] argz)
	{
		List<Integer> popoutcome;
		//int popoutcome2;
		AnimalFactory af = AnimalFactory.getInstance();
		Animal ani = af.getAnimals().get(0);
		List<Animal> anilist = af.getAnimals();
		Exponential_Model lm = new Exponential_Model();
		//popoutcome2 = lm.precalc(anilist, ani, 1694, 1, false);
		popoutcome = lm.calculate(af.getAnimals(), ani, 10, false, false);
		//System.out.println(popoutcome.size());
		for (int i = 0; i< popoutcome.size(); i++)
		{
			System.out.println(popoutcome.get(i));
		}
		//System.out.println(popoutcome2);
	}
}