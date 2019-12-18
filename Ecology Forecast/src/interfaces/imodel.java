package interfaces;

import java.util.List;

import model.Animal;

public interface imodel
{
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod, boolean grassmode, boolean predatormode);

}
