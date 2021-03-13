package com.lgee.inshurtask.weather;

public class Coordinates
{
	private double latitude;
	private double longitude;

	public Coordinates(final double latitude, final double longitude)
	{
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Coordinates()
	{
		// deafult for gql querying
	}

	public double getLatitude()
	{
		return latitude;
	}

	public void setLatitude(final double latitude)
	{
		this.latitude = latitude;
	}

	public double getLongitude()
	{
		return longitude;
	}

	public void setLongitude(final double longitude)
	{
		this.longitude = longitude;
	}

}
