package com.lgee.inshurtask.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.lgee.inshurtask.weather.openweather.OpenWeatherMock;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.graphql.client.typesafe.api.GraphQlClientBuilder;

@QuarkusTest
@QuarkusTestResource(OpenWeatherMock.class)
public class GetHottestDayWithSameTempTest
{
	public static final Coordinates HOME = new Coordinates(51.409028916249035, -1.3107228436849618);

	@Test
	void testGetHottestDayWithLowestHumidity() throws Exception
	{
		final var client = GraphQlClientBuilder.newBuilder()
				.endpoint("http://localhost:8081/graphql").build(WeatherGraphQLClient.class);

		final var hottest = client.getHottestDayForCoordinates(HOME);

		assertEquals(LocalDate.parse("2021-03-19"), hottest.getDate(), "Unexpected Date");

		assertEquals(DayOfWeek.FRIDAY.toString(), hottest.getDayOfWeek(), "unexpected day of week");

		assertEquals(10.18, hottest.getWeather().getMaxTemp(), "unexpected max temp");

		assertEquals(1, hottest.getWeather().getHumidity(), "unexpected humiduty");

		assertEquals("light rain", hottest.getWeather().getDescription(),
				"Unexpected weather description");
	}
}
