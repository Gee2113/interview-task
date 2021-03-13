package com.lgee.inshurtask.weather.provider;

import com.lgee.inshurtask.weather.model.LocationForcast;

public interface WeatherProvider
{
	LocationForcast getForecast(double latitude, double longitude);
}
