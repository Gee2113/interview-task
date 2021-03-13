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
public class GetHottestDayTest
{
	public static final Coordinates INSHUR = new Coordinates(50.824955973889, -0.13878781625840952);

	@Test
	void testGetHottestDay() throws Exception
	{
		final var client = GraphQlClientBuilder.newBuilder()
				.endpoint("http://localhost:8081/graphql").build(WeatherGraphQLClient.class);

		final var hottest = client.getHottestDayForCoordinates(INSHUR);

		assertEquals(LocalDate.parse("2021-03-13"), hottest.getDate(), "Unexpected Date");

		assertEquals(DayOfWeek.SATURDAY.toString(), hottest.getDayOfWeek(),
				"unexpected day of week");

		assertEquals(10.18, hottest.getWeather().getMaxTemp(), "unexpected max temp");

		assertEquals("moderate rain", hottest.getWeather().getDescription(),
				"Unexpected weather description");
	}
}
