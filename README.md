# interview-task project

Build and Run the app in dev mode. this will enable the graphql gui 
```shell script
mvn clean verify
```

#run the app as a standard java jar

```shell script
 cd .\target\quarkus-app\
```

```shell script
java -Dopen-weather-api/mp-rest/url="http://api.openweathermap.org" -jar .\quarkus-run.jar
```

# Using the grapiql gui

go to http://localhost:9090/q/graphql-ui/

# Querying hottest day:

```
{
  hottestDayForCoordinates(coordinates:{latitude:50.824955973889,longitude:-0.13878781625840952}){
    dayOfWeek
    date
    weather {
      description
      maxTemp
    }
  }
}
```
# Query Last 7 Days
```
{
forcast(coordinates:{latitude:50.824955973889,longitude:-0.13878781625840952})
  {
    forecast
    {
      date
      dayOfWeek
      weather{
        maxTemp
        description
      }
    }
    
  }
}
```
