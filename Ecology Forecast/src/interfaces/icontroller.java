package interfaces;

import javafx.collections.ObservableList;
import model.Animal;

public interface icontroller
{
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im, boolean grassmode, boolean predatormode, int packcount, boolean competitive);
	public void cleardata();
}
