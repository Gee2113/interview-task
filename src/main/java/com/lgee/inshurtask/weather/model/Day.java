package com.lgee.inshurtask.weather.model;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

public final class Day
{
	private String dayOfWeek;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate date;

	private Weather weather;

	public Day(final String dayOfWeek, final LocalDate date, final Weather weather)
	{
		this.dayOfWeek = dayOfWeek;
		this.date = date;
		this.weather = weather;
	}

	public Day()
	{
		// empty for qraphql
	}

	public String getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(final String dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public LocalDate getDate()
	{
		return date;
	}

	public void setDate(final LocalDate date)
	{
		this.date = date;
	}

	public Weather getWeather()
	{
		return weather;
	}

	public void setWeather(final Weather weather)
	{
		this.weather = weather;
	}

}