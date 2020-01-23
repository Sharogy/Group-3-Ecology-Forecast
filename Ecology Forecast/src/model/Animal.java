package model;

import interfaces.ianimal;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal implements ianimal {
	
	private StringProperty name;
	private IntegerProperty total;
	private DoubleProperty birthrate;
	private DoubleProperty deathrate;
	private DoubleProperty consumptionrate;
	private IntegerProperty avgweight;
	private StringProperty type;
	private StringProperty notes;
	private IntegerProperty carrying;
	private DoubleProperty preylikelihood;
	
	
	public Animal()
	{
		this(null,0,0,0,0,0,0,null, 0.0, null);
	}
	
	public Animal(String name, int total, double growth, double death, double consumption, int avgweight, int carrying, String type, Double preylikelihood, String notes)
	{
		this.name = new SimpleStringProperty(name);
		this.total = new SimpleIntegerProperty(total);
		this.birthrate = new SimpleDoubleProperty(growth);
		this.deathrate = new SimpleDoubleProperty(death);
		this.consumptionrate = new SimpleDoubleProperty(consumption);
		this.avgweight = new SimpleIntegerProperty(avgweight);
		this.notes = new SimpleStringProperty(notes);		
		this.carrying = new SimpleIntegerProperty(carrying);
		this.type = new SimpleStringProperty(type);
		this.preylikelihood = new SimpleDoubleProperty(preylikelihood);
		
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

	public double getBirthrate() {
		return birthrate.get();
	}

	public void setBirthrate(double birthrate) {
		this.birthrate.set(birthrate);
	}
	
	public DoubleProperty birthProperty()
	{
		return birthrate;
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
	
	public int getAvgweight() {
		return this.avgweight.get();
	}
	
	public void setAvgweight (int avgweight) {
		this.avgweight.set(avgweight);
	}
	
	public IntegerProperty avgweightProperty()
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
	public double getPreylikelihood() {
		return preylikelihood.get();
	}
	public void setPreylikelihood (double preylikelihood) {
		this.preylikelihood.set(preylikelihood);
	}
	public DoubleProperty PreylikelihoodProperty()
	{
		return preylikelihood;
	}	
}
