package com.lgee.inshurtask.weather;

import com.lgee.inshurtask.weather.model.Day;
import com.lgee.inshurtask.weather.model.LocationForcast;

import io.smallrye.graphql.client.typesafe.api.GraphQlClientApi;

@GraphQlClientApi
public interface WeatherGraphQLClient
{
	LocationForcast getForcast(Coordinates coordinates);

	Day getHottestDayForCoordinates(Coordinates coordinates);
}
