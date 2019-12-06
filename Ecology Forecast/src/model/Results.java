package model;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Results {
	private StringProperty animalname;
	private IntegerProperty startingpop;
	private List<Integer> results;
	private static List<Integer> list = null;

	public Results()
	{
		this(null,0,list);
	}
	
	public Results(String animalname, int startingpop, List<Integer> results)
	{
		this.animalname = new SimpleStringProperty(animalname);
		this.startingpop = new SimpleIntegerProperty(startingpop);
		this.results = results;	
	}
	
	public String getanimalname() {
		return animalname.get();
	}

	public void setanimalname(String name) {
		this.animalname.set(name);
	}
	
	public StringProperty nameProperty()
	{
		return animalname;
	}

	public int getstartingpop() {
		return startingpop.get();
	}

	public void setstartingpop(int number) {
		this.startingpop.set(number);
	}
	
	public IntegerProperty totalProperty()
	{
		return startingpop;
	}

	public List<Integer> getresults() {
		return this.results;
	}

	public void setresults(List<Integer> results) {
		this.results = results;
	}
}
