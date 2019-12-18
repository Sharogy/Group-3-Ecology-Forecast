package math;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import interfaces.imodel;
import model.Animal;

public class Competitive_Model implements imodel{
	
	private double a11;
	private double a12;
	private double a13;
	private double a21;
	private double a22;
	private double a23;
	private double a31;
	private double a32;
	private double a33;
	private int k1;
	private int k2;
	private int k3;

	
	public int precalc(Animal ani, int timeperiod)
	{
		double [][] matrixdata = {{a11,a12,a13},{a21,a22,a23},{a31,a32,a33}};
		double [][] matrixdata2 = {{k1},{k2},{k3}};
		RealMatrix interactionmatrix = MatrixUtils.createRealMatrix(matrixdata);
		RealMatrix capacitymatrix = MatrixUtils.createRealMatrix(matrixdata2);
		int a = 0;
		return a;		
	}
	
	
	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod , boolean grassmode, boolean predatormode) {
		// TODO Auto-generated method stub
		List<List<?>> a = new ArrayList();	
		return null;
	}
	
	public double getinteraction (int row, int column)
	{
		double alphavalue = 0;
		
		
		return alphavalue;
		
	}

}
