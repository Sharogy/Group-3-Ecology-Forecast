package math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Animal;
import model.AnimalFactory;
import math.Grass_Model;
import Util.Settings;

	public class CarryingCapacity_Model {
		
		private static Map<String, Double> carryingcapacity = new HashMap<String, Double>();
		
		public static Map<String, Double> calculate(List<Animal> animallist) {
			if (carryingcapacity.size() == 0)
			{
				for (Animal ani: animallist)
				{
					String name = ani.getName();
					double foodcapacityperanimal;
					if (ani.getName().equalsIgnoreCase("Konik Horse"))
					{
						foodcapacityperanimal = 2*grasscapacity()/(animallist.size()+1);
					}
					else
					{
						foodcapacityperanimal = grasscapacity()/(animallist.size()+1);
					}
					double carryingcapacityperanimal = foodcapacityperanimal / ani.getAvgweight() / ani.getConsumptionrate() / 0.015 / 300;	
					carryingcapacity.put(name, carryingcapacityperanimal);
				}
			}			
			return carryingcapacity;
		}
		
		public static double getCarrycapacity(Animal ani, List<Animal> animallist, boolean grassmode) {
			if (grassmode)
			{
				calculate(animallist);
				return carryingcapacity.get(ani.getName());	
			}
			else
			{
				return ani.getCarrying();
			}
		}
		
		public static double grasscapacity() {
			Grass_Model gm = new Grass_Model();
			double growthrate = gm.calculate();
			double grasscapacity = growthrate * Settings.area * Settings.maxyield * 12;
			return grasscapacity;
		}
				
		public static double animalweight(List<Animal> animallist) {
			double weight = 0;
			for (Animal ani: animallist)
			{
				weight = ani.getAvgweight()+weight;
			}
			return weight;
		}
	
		public static void main (String[] argz)
		{
			CarryingCapacity_Model cm = new CarryingCapacity_Model();
			System.out.println(cm.grasscapacity());
			AnimalFactory af = AnimalFactory.getInstance();
			List<Animal> animallist = af.getAnimals();
			CarryingCapacity_Model.calculate(animallist);
			for (int i = 0; i<carryingcapacity.size(); i++)
			{
				System.out.println(carryingcapacity.get(animallist.get(i).getName()));
			}
		}
}
