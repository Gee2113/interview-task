package com.lgee.inshurtask.weather.provider.openweather;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "open-weather-api")
public interface OpenWeatherClient
{

	@GET
	@Path("/data/2.5/onecall")
	OpenWeatherResult getWeather(@QueryParam("lat") double latitude,
			@QueryParam("lon") double longitude, @QueryParam("exclude") String exclude,
			@QueryParam("units") String units, @QueryParam("appId") String appId);
}
