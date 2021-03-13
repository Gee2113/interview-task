package com.lgee.inshurtask.weather.provider.openweather;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyResult
{
	private long dt;
	private TempResult temp;
	private List<WeatherResult> weather;
	private int humidity;

	public long getDt()
	{
		return dt;
	}

	public TempResult getTemp()
	{
		return temp;
	}

	public void setTemp(final TempResult temp)
	{
		this.temp = temp;
	}

	public List<WeatherResult> getWeather()
	{
		return weather;
	}

	public void setWeather(final List<WeatherResult> weather)
	{
		this.weather = weather;
	}

	public int getHumidity()
	{
		return humidity;
	}

	public void setHumidity(final int humidity)
	{
		this.humidity = humidity;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class TempResult
	{
		private double max;

		public double getMax()
		{
			return max;
		}

		public void setMax(final double max)
		{
			this.max = max;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class WeatherResult
	{
		private String description;

		public String getDescription()
		{
			return description;
		}

		public void setDescription(final String description)
		{
			this.description = description;
		}
	}

}