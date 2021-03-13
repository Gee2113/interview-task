package com.lgee.inshurtask.weather.openweather;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.lgee.inshurtask.weather.GetHottestDayTest;
import com.lgee.inshurtask.weather.GetHottestDayWithSameTempTest;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class OpenWeatherMock implements QuarkusTestResourceLifecycleManager
{
	private static WireMockServer wireMockServer;

	private String jsonFile;

	@Override
	public Map<String, String> start()
	{
		if (wireMockServer == null)
		{
			wireMockServer = new WireMockServer();

		}
		if (!wireMockServer.isRunning())
		{
			wireMockServer.start();
		}

		try
		{
			stubFor(get(urlMatching("/data/2.5/onecall?.*"))
					.withQueryParam("lat",
							equalTo(String.valueOf(GetHottestDayTest.INSHUR.getLatitude())))
					.withQueryParam("lon",
							equalTo(String.valueOf(GetHottestDayTest.INSHUR.getLongitude())))
					.willReturn(aResponse().withHeader("Content-Type", "application/json")
							.withBody(readExampleFile("example.json"))));

			stubFor(get(urlMatching("/data/2.5/onecall?.*"))
					.withQueryParam("lat",
							equalTo(String
									.valueOf(GetHottestDayWithSameTempTest.HOME.getLatitude())))
					.withQueryParam("lon",
							equalTo(String
									.valueOf(GetHottestDayWithSameTempTest.HOME.getLongitude())))
					.willReturn(aResponse().withHeader("Content-Type", "application/json")
							.withBody(readExampleFile("exampleWithSameTemp.json"))));
		}
		catch (final IOException e)
		{
			throw new RuntimeException(e);
		}

		return Collections.singletonMap("open-weather-api/mp-rest/url", wireMockServer.baseUrl());
	}

	@Override
	public void init(final Map<String, String> initArgs)
	{
		jsonFile = initArgs.get("jsonFile");
		QuarkusTestResourceLifecycleManager.super.init(initArgs);
	}

	@Override
	public void stop()
	{
		if (null != wireMockServer)
		{
			wireMockServer.stop();
		}
	}

	private String readExampleFile(final String jsonFile) throws IOException
	{
		return new String(OpenWeatherMock.class.getResourceAsStream(jsonFile).readAllBytes());
	}
}
