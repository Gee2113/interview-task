package com.lgee.inshurtask.weather.provider.openweather;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.lgee.inshurtask.weather.Coordinates;
import com.lgee.inshurtask.weather.model.Day;
import com.lgee.inshurtask.weather.model.LocationForcast;
import com.lgee.inshurtask.weather.model.Weather;
import com.lgee.inshurtask.weather.provider.WeatherProvider;
import com.lgee.inshurtask.weather.provider.openweather.DailyResult.WeatherResult;

/**
 * OpenWetaher implementation of {@link WeatherProvider}
 * 
 * @author lukeg
 *
 */
@ApplicationScoped
public class OpenWeatherProvider implements WeatherProvider
{

	private final static String UNIT = "metric";
	private final static String EXCLUDES = "minutely";

	@Inject
	@ConfigProperty(name = "open-weather-api.key")
	String apiKey;

	@Inject
	@RestClient
	OpenWeatherClient client;

	@Override
	public LocationForcast getForecast(final double latitude, final double longitude)
	{
		return mapModel(client.getWeather(latitude, longitude, EXCLUDES, UNIT, apiKey));

	}

	private LocationForcast mapModel(final OpenWeatherResult openWeatherResult)
	{
		return new LocationForcast(
				new Coordinates(openWeatherResult.getLat(), openWeatherResult.getLon()),
				openWeatherResult.getDaily().stream().map(this::buildDayFromResult)
						.sorted(Comparator.comparing(Day::getDate)).collect(Collectors.toList()));
	}

	private Day buildDayFromResult(final DailyResult dailyResult)
	{
		final var date = LocalDate
				.from(Instant.ofEpochSecond(dailyResult.getDt()).atZone(ZoneOffset.UTC));

		return new Day(date.getDayOfWeek().toString(), date,
				new Weather(dailyResult.getTemp().getMax(), dailyResult.getHumidity(),
						dailyResult.getWeather().stream().map(WeatherResult::getDescription)
								.collect(Collectors.joining(", "))));
	}

}
