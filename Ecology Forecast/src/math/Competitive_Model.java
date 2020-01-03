package math;

import java.util.ArrayList;
import java.util.List;

import com.oracle.webservices.internal.api.EnvelopeStyle;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import interfaces.imodel;
import model.Animal;

public class Competitive_Model implements imodel{
	
	private double a11 = 1.0;
	private double a12 = 0.3;
	private double a13 = 0.3;
	private double a21 = 3.3;
	private double a22= 1.0;
	private double a23;
	private double a31= 3.3;
	private double a32;
	private double a33 = 1.0;
	private double k1;
	private double k2;
	private double k3;
	private int n1;
	private int n2;
	private int n3;

	// get the data from cal
	List<Animal> animallist;
	Animal ani;
	int timePeriod;
	boolean grassmode;
	boolean predatormode;

	double [][] matrixdata = {{a11,a12,a13},{a21,a22,a23},{a31,a32,a33}};
	double [][] matrixdata2 = {{k1},{k2},{k3}};
	
	
	public int precalc(List<Animal> animallist, Animal ani, int population,  int timeperiod, boolean grassmode)
	{
		RealMatrix interactionmatrix = MatrixUtils.createRealMatrix(matrixdata);
		RealMatrix capacitymatrix = MatrixUtils.createRealMatrix(matrixdata2);
		int a = 0;
		return a;		
	}
	
	
	@Override
	public List<Integer> calculate(List<Animal> animallist, Animal ani, int timeperiod , boolean grassmode, boolean predatormode) {
		// TODO Auto-generated method stub
		this.animallist = animallist;
		//n1= ( k1 + k3 * a12 * a23 + k2 * a12* a32 - k2 * a21 - k1 * a23 * a32 - k3 * a12 )/ (1+ a12 * a23 * a31 + a13*a21*a32-a21*a12-a23*a32-a13*a31);
		//n2 = (k2+a23*a31*k1+a13*a21*k3-k1*a23-a23*k3-a13*k2*a31)/(1+a12*a23*a31+a13*a21*a31-a21*a12-a23*a32-a13*a31);
		//n3 = (k3+a12*k2*a31+k1*d21*a32-a12*a21*k3+k2*a32+k1*a31)/(1+a12*a23*a31+a13*a21*a32-a21*a12-a23*a32-a13*a31);
		this.ani = ani;
		this.timePeriod = timeperiod;
		this.grassmode = grassmode;
		this.predatormode = predatormode;
		return null;
	}
	
	public boolean calculateK()
	{
		k1 = a11 * n1 + a12 * n2 + a13 * n3;
		k2 = a21 * n1 + a22 * n2 + a23 * n3;
		k3 = a31 * n1 + a32 * n2 + a33 * n3;
		return true;
	}
	public double getinteraction (int row, int column)
	{
		double alphavalue = 0.0;
		
		
//		for(int i=0; i<3;i++)
//		{
//			for(int j=0; j<3; j++)
//			{
//				if(i==row && j== column)
//				{
//					return matrixdata[i][j];
//				}
//			}
//		}
//		
		
		return alphavalue;	
	}
}
