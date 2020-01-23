package interfaces;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public interface ianimal {
	public String getName();
	public void setName(String name);
	public StringProperty nameProperty();

	public int getNumber();
	public void setNumber(int number);
	public IntegerProperty totalProperty();
	
	public double getBirthrate();
	public void setBirthrate(double birthrate);	
	public DoubleProperty birthProperty();

	public double getDeathrate();
	public void setDeathrate(double deathrate);
	public DoubleProperty deathProperty();

	public double getConsumptionrate();
	public void setConsumptionrate(double consumptionrate);
	public DoubleProperty consumptionProperty();
	
	public int getAvgweight();	
	public void setAvgweight (int avgweight);
	public IntegerProperty avgweightProperty();

}
