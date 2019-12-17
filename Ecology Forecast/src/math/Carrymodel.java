package math;

import java.util.ArrayList;
import java.util.List;

import model.Animal;

	public class Carrymodel implements imodel{
		
		private double NewCarryingCap;
		private double MeanCarryingCap;
		private double AmplitudeCycle;
		private int timeperiode;
		private double CycleLength;
		private int oldpop;
		private double birth;
		private double death;
		
		
		public int precalc(Animal ani, int timeperiod)
		{
			
			double Equation = 2*Math.PI * timeperiode/CycleLength;
			NewCarryingCap = MeanCarryingCap + AmplitudeCycle * Math.cos(Equation);
			return (int) Math.round(NewCarryingCap);
			
		}

		@Override
		public List<Integer> calculate(Animal ani, int timeperiod) {
			this.oldpop = ani.getNumber();
			this.birth = ani.getGrowthrate();
			this.death = ani.getDeathrate();

			List<Integer> a = new ArrayList<Integer>(timeperiod);
			a.add(oldpop);
			for (int i=1; i <= timeperiod; i++) 
			{
				a.add(precalc(ani, i));			
			}
			return a;
		}
	
}
