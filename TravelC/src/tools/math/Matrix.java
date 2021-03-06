package tools.math;

import java.util.Random;

import tools.math.random.RandomDouble;

public class Matrix {
	public final int sizeX, sizeY;
	protected double matrix[][];
	
	
	
	public Matrix(int sizeX, int sizeY) {
		this(sizeX, sizeY, 0);
	}

	public Matrix(double[][] matrix) {
		this.sizeX = matrix.length;
		this.sizeY = matrix[0].length;
		this.matrix = matrix;
	}

	public Matrix(int sizeX, int sizeY, double init) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.matrix = new double[sizeX][sizeY];
		for (int x = 0; x < sizeX; x++)
			for (int y = 0; y < sizeY; y++)
				matrix[x][y] = init;
	}

	public Matrix(int sizeX, int sizeY, Random rand,double max, double min) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.matrix = new double[sizeX][sizeY];
		for (int x = 0; x < sizeX; x++)
			for (int y = 0; y < sizeY; y++)
				matrix[x][y] = RandomDouble.generate(max, min);
	}

	
	public Matrix(int size) {
		this(size, size, 0);
	}

	public Matrix(int size, double init) {
		this(size, size, init);
	}
	
	public static Matrix diagonal(int sizeX, int sizeY, double value) {
		Matrix m = new Matrix(sizeX, sizeY, 0.0);
		for (int x = 0; x < sizeX; x++)
			for (int y = 0; y < sizeY; y++)
				if (x == y)
					m.matrix[x][y] = value;
		return m;
	}

	public static Matrix diagonal(int size, double value) {
		Matrix m = new Matrix(size, 0.0);
		for (int x = 0; x < size; x++)
			m.matrix[x][x] = value;
		return m;
	}

	public static boolean isSquare(Matrix m1) {
		return m1.sizeX == m1.sizeY;
	}

	public static boolean sameSize(Matrix m1, Matrix m2) {
		return m1.sizeX == m2.sizeX && m1.sizeY == m2.sizeY;
	}

	public static Matrix add(Matrix m1, Matrix m2) {
		if (!sameSize(m1, m2))
			return null;
		Matrix res = new Matrix(m1.sizeX, m1.sizeY);
		for (int x = 0; x < m1.sizeX; x++)
			for (int y = 0; y < m1.sizeY; y++)
				res.matrix[x][y] = m1.matrix[x][y] + m2.matrix[x][y];
		return res;
	}

	public static boolean possibleMult(Matrix m1, Matrix m2) {
		return m1.sizeX == m2.sizeY;
	}

	public static Matrix mult(Matrix m1, Matrix m2) {
		if (!possibleMult(m1, m2))
			return null;
		Matrix res = new Matrix(m2.sizeX, m1.sizeY);
		for (int x = 0; x < res.sizeX; x++)
			for (int y = 0; y < res.sizeY; y++) {
				res.matrix[x][y] = 0;
				for (int k = 0; k < m1.sizeX; k++)
					res.matrix[x][y] += m1.matrix[k][y] * m2.matrix[x][k];
			}
		return res;
	}

	public static Matrix mult(Matrix m,double k){
		for (int x = 0; x < m.sizeX; x++)
			for (int y = 0; y < m.sizeY; y++) {  
				m.matrix[x][y] = k*m.matrix[x][y];
			}
		return m;
	}
	
	public static Matrix trans(Matrix m) {
		Matrix res = new Matrix(m.sizeY, m.sizeX);
		for (int x = 0; x < m.sizeX; x++)
			for (int y = 0; y < m.sizeY; y++)
				res.matrix[y][x] = m.matrix[x][y];

		return res;
	}

	public static Matrix ones(int sizeX,int sizeY){
		Matrix m = new Matrix(sizeX,sizeY);
		for(int x =0; x < sizeX; x ++)
			for(int y =0; y < sizeY; y ++)
				m.matrix[x][y] = 1;
		return m;
	}
	
	public static Matrix zeros(int sizeX,int sizeY){
		Matrix m = new Matrix(sizeX,sizeY);
		for(int x =0; x < sizeX; x ++)
			for(int y =0; y < sizeY; y ++)
				m.matrix[x][y] = 0;
		return m;
	}
	
	public static Matrix kmatrix(int sizeX,int sizeY,double k){
		Matrix m = new Matrix(sizeX,sizeY);
		for(int x =0; x < sizeX; x ++)
			for(int y =0; y < sizeY; y ++)
				m.matrix[x][y] = k;
		return m;
	}
	
	@Override
	public String toString() {
		String elt = "";
		for (int y = 0; y < sizeY; y++) {
			elt += "|";
			for (int x = 0; x < sizeX; x++)
				elt += matrix[x][y] + "|";
			elt += "\n";
		}

		return elt;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public double[][] getMatrix() {
		return matrix;
	}

	public class ColumnVector extends Matrix{
		public ColumnVector(double[] vector){
			super(new double[1][vector.length]);
		}
	}
	
	public class RowVector extends Matrix{
		public RowVector(double[] vector){
			super(new double[vector.length][1]);
			}
	}
	
}
