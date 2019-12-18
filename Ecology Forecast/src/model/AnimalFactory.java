package model;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Util.Dataminer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AnimalFactory {
	
	private static AnimalFactory af = null;
	private ObservableList<Animal> animallist = FXCollections.observableArrayList();
	
	private AnimalFactory()
	{
			
	}
	
	public static AnimalFactory getInstance()
	{
		if (af == null)
		{
			af = new AnimalFactory();
		}
		return af;
	}
	
	public ObservableList<Animal> getAnimals()
	{
		animallist.clear();
		try {
			String[][] data = Dataminer.getData();
			for (int i = 1; i<data.length; i++)
			{	
				//System.out.println(data[i][7]);
				Animal ani = new Animal(data[i][0], Integer.parseInt(data[i][1]), Double.parseDouble(data[i][2]), Double.parseDouble(data[i][3]), Double.parseDouble(data[i][4]), Double.parseDouble(data[i][5]), Integer.parseInt(data[i][6]), data[i][7]);
				animallist.add(ani);			
			}
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		return animallist;
	}
	
	public static void main(String args[]) 
	{
		AnimalFactory af = AnimalFactory.getInstance();
		ObservableList<Animal> animallist = af.getAnimals();
		Iterator<Animal> iter = animallist.iterator();
		while (iter.hasNext())
		{
			Animal ani = iter.next();
			System.out.println(ani.getName());
		}
	}

}
