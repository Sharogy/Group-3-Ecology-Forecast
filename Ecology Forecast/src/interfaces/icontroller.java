package interfaces;

import javafx.collections.ObservableList;
import model.Animal;

public interface icontroller
{
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im);
	public void cleardata();
}
