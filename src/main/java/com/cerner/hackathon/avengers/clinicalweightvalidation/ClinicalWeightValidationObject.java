package com.cerner.hackathon.avengers.clinicalweightvalidation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClinicalWeightValidationObject
{
	private double weight;
	private String weightUnit;
		
	public double getWeight()
	{
		return weight;
	}
	
	public String getWeightUnit()
	{
		return weightUnit;
	}
	public void setWeight(double weight)
	{
		 this.weight = weight;
	}
	public void setWeightUnit(String weightUnit)
	{
		this.weightUnit = weightUnit;
	}
}