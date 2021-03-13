package com.lgee.inshurtask.weather.provider.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherResult
{
	private double lat;
	private double lon;
	private List<DailyResult> daily;

	public double getLat()
	{
		return lat;
	}

	public void setLat(final double lat)
	{
		this.lat = lat;
	}

	public double getLon()
	{
		return lon;
	}

	public void setLon(final double lon)
	{
		this.lon = lon;
	}

	public List<DailyResult> getDaily()
	{
		return daily;
	}

	public void setDaily(final List<DailyResult> daily)
	{
		this.daily = daily;
	}
}
