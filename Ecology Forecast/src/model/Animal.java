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
	private StringProperty type;
	private StringProperty notes;
	private IntegerProperty carrying;
	private IntegerProperty preylikelihood;
	
	
	public Animal()
	{
		this(null,0,0,0,0,0,0,null, null, null);
	}
	
	public Animal(String name, int total, double growth, double death, double consumption, double avgweight, int carrying, String type, String preylikelihood, String notes)
	{
		this.name = new SimpleStringProperty(name);
		this.total = new SimpleIntegerProperty(total);
		this.growthrate = new SimpleDoubleProperty(growth);
		this.deathrate = new SimpleDoubleProperty(death);
		this.consumptionrate = new SimpleDoubleProperty(consumption);
		this.avgweight = new SimpleDoubleProperty(avgweight);
		this.notes = new SimpleStringProperty(notes);		
		this.carrying = new SimpleIntegerProperty(carrying);
		this.type = new SimpleStringProperty(type);
		if (preylikelihood == null)
		{
			this.preylikelihood = new SimpleIntegerProperty(0);
		}
		else
		{
			this.preylikelihood = new SimpleIntegerProperty(Integer.valueOf(preylikelihood));
		}
		
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
	public int getCarrying() {
		return carrying.get();
	}
	public void setCarrying (int carrying) {
		this.carrying.set(carrying);
	}
	public IntegerProperty carryingProperty()
	{
		return carrying;
	}
	
	public String getType() {
		return type.get();
	}
	
	public void setType(String type)
	{
		this.type.set(type);
	}
	public StringProperty typeProperty()
	{
		return type;
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
	public int getPreylikelihood() {
		return preylikelihood.get();
	}
	public void setPreylikelihood (int preylikelihood) {
		this.preylikelihood.set(preylikelihood);
	}
	public IntegerProperty PreylikelihoodProperty()
	{
		return preylikelihood;
	}
	
}
