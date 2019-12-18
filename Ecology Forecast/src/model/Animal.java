package model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {
	
	private StringProperty name;
	private IntegerProperty total;
	private DoubleProperty growthrate;
	private DoubleProperty deathrate;
	private DoubleProperty consumptionrate;
	private DoubleProperty avgweight;
	private StringProperty notes;
	
	
	public Animal()
	{
		this(null,0,0,0,0,0,null);
	}
	
	public Animal(String name, int total, double growth, double death, double consumption, double avgweight, String notes)
	{
		this.name = new SimpleStringProperty(name);
		this.total = new SimpleIntegerProperty(total);
		this.growthrate = new SimpleDoubleProperty(growth);
		this.deathrate = new SimpleDoubleProperty(death);
		this.consumptionrate = new SimpleDoubleProperty(consumption);
		this.avgweight = new SimpleDoubleProperty(avgweight);
		this.notes = new SimpleStringProperty(notes);		
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}
	
	public StringProperty nameProperty()
	{
		return name;
	}

	public int getNumber() {
		return total.get();
	}

	public void setNumber(int number) {
		this.total.set(number);
	}
	
	public IntegerProperty totalProperty()
	{
		return total;
	}

	public double getGrowthrate() {
		return growthrate.get();
	}

	public void setGrowthrate(double growthrate) {
		this.growthrate.set(growthrate);
	}
	
	public DoubleProperty growthProperty()
	{
		return growthrate;
	}

	public double getDeathrate() {
		return deathrate.get();
	}

	public void setDeathrate(double deathrate) {
		this.deathrate.set(deathrate);
	}
	
	public DoubleProperty deathProperty()
	{
		return deathrate;
	}

	public double getConsumptionrate() {
		return consumptionrate.get();
	}

	public void setConsumptionrate(double consumptionrate) {
		this.consumptionrate.set(consumptionrate);
	}
	
	public DoubleProperty consumptionProperty()
	{
		return consumptionrate;
	}
	
	public double getAvgweight() {
		return avgweight.get();
	}
	
	public void setAvgweight (int avgweight) {
		this.avgweight.set(avgweight);
	}
	
	public DoubleProperty avgweightProperty()
	{
		return avgweight;
	}
	
	public String getNotes() {
		return notes.get();
	}
	public void setNotes (String notes) {
		this.notes.set(notes);
	}
	public StringProperty notesProperty()
	{
		return notes;
	}

}
