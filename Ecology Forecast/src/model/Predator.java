package model;

import Util.Settings;
import interfaces.ianimal;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Predator implements ianimal{

	private StringProperty name;
	private IntegerProperty total;
	private DoubleProperty birthrate;
	private DoubleProperty deathrate;
	private DoubleProperty consumptionrate;
	private IntegerProperty avgweight;
	
	public Predator()
	{
		
		this.name = new SimpleStringProperty("Gray Wolves");
		this.total = new SimpleIntegerProperty(Settings.predatornumber);
		this.birthrate = new SimpleDoubleProperty(Settings.predatorbirth);
		this.deathrate = new SimpleDoubleProperty(Settings.predatordeath);
		this.consumptionrate = new SimpleDoubleProperty(Settings.predatorconsumption);
		this.avgweight = new SimpleIntegerProperty(Settings.predatoravgweight);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name.get();
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		this.name.set(name);
	}

	@Override
	public StringProperty nameProperty() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getNumber() {
		// TODO Auto-generated method stub
		return this.total.get();
	}

	@Override
	public void setNumber(int number) {
		// TODO Auto-generated method stub
		this.total.set(number);
	}

	@Override
	public IntegerProperty totalProperty() {
		// TODO Auto-generated method stub
		return this.total;
	}

	@Override
	public double getBirthrate() {
		// TODO Auto-generated method stub
		return this.birthrate.get();
	}

	@Override
	public void setBirthrate(double birthrate) {
		// TODO Auto-generated method stub
		this.birthrate.set(birthrate);
		
	}

	@Override
	public DoubleProperty birthProperty() {
		// TODO Auto-generated method stub
		return this.birthrate;
	}

	@Override
	public double getDeathrate() {
		// TODO Auto-generated method stub
		return this.deathrate.get();
	}

	@Override
	public void setDeathrate(double deathrate) {
		// TODO Auto-generated method stub
		this.deathrate.set(deathrate);
	}

	@Override
	public DoubleProperty deathProperty() {
		// TODO Auto-generated method stub
		return this.deathrate;
	}

	@Override
	public double getConsumptionrate() {
		// TODO Auto-generated method stub
		return this.consumptionrate.get();
	}

	@Override
	public void setConsumptionrate(double consumptionrate) {
		// TODO Auto-generated method stub
		this.consumptionrate.set(consumptionrate);
	}

	@Override
	public DoubleProperty consumptionProperty() {
		// TODO Auto-generated method stub
		return this.consumptionrate;
	}

	@Override
	public int getAvgweight() {
		// TODO Auto-generated method stub
		return this.avgweight.get();
	}

	@Override
	public void setAvgweight(int avgweight) {
		// TODO Auto-generated method stub
		this.avgweight.set(avgweight);
	}

	@Override
	public IntegerProperty avgweightProperty() {
		// TODO Auto-generated method stub
		return this.avgweight;
	}
	

	public static void main(String[] argz)
	{
		Predator pm = new Predator();
		System.out.println(pm.getName());
	}
}
