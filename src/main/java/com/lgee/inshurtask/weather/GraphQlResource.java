package com.lgee.inshurtask.weather;

import java.util.Comparator;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

import com.lgee.inshurtask.weather.model.Day;
import com.lgee.inshurtask.weather.model.LocationForcast;
import com.lgee.inshurtask.weather.provider.WeatherProvider;

@GraphQLApi
public class GraphQlResource
{
	@Inject
	WeatherProvider weatherProvider;

	@Query
	public LocationForcast getForcast(@Source final Coordinates coordinates)
	{
		return weatherProvider.getForecast(coordinates.getLatitude(), coordinates.getLongitude());
	}

	@Query
	public Day getHottestDayForCoordinates(@Source final Coordinates coordinates)
	{
		final var mapping = getForcast(coordinates).getForecast().stream()
				.collect(Collectors.groupingBy(day -> day.getWeather().getMaxTemp()));

		final var max = mapping.keySet().stream().max(Comparator.comparingDouble(v -> v)).get();

		final var days = mapping.get(max);

		if (days.size() == 1)
		{
			return days.get(0);
		}

		return days.stream().min(Comparator.comparing(day -> day.getWeather().getHumidity())).get();

//		return getForcast(coordinates).getForecast().stream()
//				.max(Comparator.comparingDouble(day -> day.getWeather().getMaxTemp()))
//				.orElseThrow(() -> new NotFoundException("Error finding day with max temp"));
	}

}