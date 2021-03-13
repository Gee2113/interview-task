package com.lgee.inshurtask.weather.model;

import java.util.List;

import com.lgee.inshurtask.weather.Coordinates;

public final class LocationForcast
{
	private final Coordinates coordinates;
	private final List<Day> forecast;

	public LocationForcast(final Coordinates coordinates, final List<Day> forecast)
	{
		this.coordinates = coordinates;
		this.forecast = forecast;
	}

	public Coordinates getCoordinates()
	{
		return coordinates;
	}

	public List<Day> getForecast()
	{
		return forecast;
	}

}
