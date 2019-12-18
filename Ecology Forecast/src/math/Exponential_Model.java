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
	
	private int precalc(Animal ani, int timeperiod)
	{	
		
		double increaseRate = birth - death;
		double power = increaseRate * timeperiod;
		double teller = CarryingCapacity_Model.getCarrycapacity(ani, animallist);
		double nummer = 1 + ((teller - oldpop)/oldpop)*Math.exp(-power);
		double newpop = teller/nummer;
//		System.out.println("attributes");
//		System.out.println(increaseRate);
//		System.out.println(power);
//		System.out.println(teller);
//		System.out.println(nummer);
		return (int) Math.round(newpop);
	}
	
	//Calculate for a period of time
	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod)
	{		
		this.oldpop = ani.getNumber();
		this.birth = ani.getGrowthrate();
		this.death = ani.getDeathrate();
		this.animallist = animallist;

		List<Integer> a = new ArrayList<Integer>(timeperiod);
		a.add(oldpop);
		for (int i=1; i <= timeperiod; i++) 
		{
			a.add(precalc(ani, i));			
		}
		return a;
	}
	
	public static void main(String[] argz)
	{
		List<Integer> popoutcome;
		AnimalFactory af = AnimalFactory.getInstance();
		Animal ani = af.getAnimals().get(0);
		Exponential_Model lm = new Exponential_Model();
		popoutcome = lm.calculate(af.getAnimals(), ani, 2);
		for (int i = 0; i< popoutcome.size(); i++)
		{
			System.out.println(popoutcome.get(i));
		}
	}
}