package interfaces;

import java.util.List;

import model.Animal;

public interface imodel
{
	int precalc(List<Animal> animallist, Animal ani, int population, int timeperiod, boolean grassmode);
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod, boolean grassmode, boolean predatormode);

}
