package com.lgee.inshurtask.weather.model;

public final class Weather
{
	private double maxTemp;
	private int humidity;
	private String description;

	public Weather(final double maxTemp, final int humdity, final String description)
	{
		this.maxTemp = maxTemp;
		this.humidity = humdity;
		this.description = description;
	}

	public Weather()
	{
		// empty for graphql
	}

	public double getMaxTemp()
	{
		return maxTemp;
	}

	public void setMaxTemp(final int maxTemp)
	{
		this.maxTemp = maxTemp;
	}

	public int getHumidity()
	{
		return humidity;
	}

	public void setHumidity(final int humidity)
	{
		this.humidity = humidity;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(final String description)
	{
		this.description = description;
	}

}
