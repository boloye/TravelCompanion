package com.example.voyage.api.tools.ia;

import com.example.voyage.api.tools.ia.decition.AbstractDecition;

public class Perceptron {
	private double[] weights;
	private int numberWeights;
	private double step;
	private AbstractDecition decide;
	
	public Perceptron(double[] weigths,double step,AbstractDecition decide){
		this.numberWeights = weigths.length;
		this.weights = weigths;
		this.step = step;
		this.decide = decide;
	}
	
	public double propagate(double[] entry, double threshold){
		double result = 0;
		for(int i  = 0; i < numberWeights;i++)
			result += weights[i]*entry[i] - threshold;
		return decide.result(result);
	}
	
	public void learn(double[] entry,double result, double desired){
		double error = desired - result;
		for(int i = 0; i  < numberWeights; i++)
			weights[i] = weights[i] + step*error*entry[i];
	}
}
