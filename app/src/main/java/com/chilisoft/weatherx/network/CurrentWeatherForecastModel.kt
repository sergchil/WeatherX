package com.chilisoft.weatherx.network


import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecastModel(
    @SerializedName("base")
    val base: String, // stations
    @SerializedName("clouds")
    val clouds: Clouds,
    @SerializedName("cod")
    val cod: Int, // 200
    @SerializedName("coord")
    val coord: Coord,
    @SerializedName("dt")
    val dt: Int, // 1646125954
    @SerializedName("id")
    val id: Int, // 616052
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val name: String, // Yerevan
    @SerializedName("sys")
    val sys: Sys,
    @SerializedName("timezone")
    val timezone: Int, // 14400
    @SerializedName("visibility")
    val visibility: Int, // 10000
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("wind")
    val wind: Wind
) {
    data class Clouds(
        @SerializedName("all")
        val all: Int // 99
    )

    data class Coord(
        @SerializedName("lat")
        val lat: Double, // 40.1811
        @SerializedName("lon")
        val lon: Double // 44.5136
    )

    data class Main(
        @SerializedName("feels_like")
        val feelsLike: Double, // 282.24
        @SerializedName("humidity")
        val humidity: Int, // 32
        @SerializedName("pressure")
        val pressure: Int, // 1019
        @SerializedName("temp")
        val temp: Double, // 284.24
        @SerializedName("temp_max")
        val tempMax: Double, // 284.24
        @SerializedName("temp_min")
        val tempMin: Double // 282.83
    )

    data class Sys(
        @SerializedName("country")
        val country: String, // AM
        @SerializedName("id")
        val id: Int, // 8851
        @SerializedName("sunrise")
        val sunrise: Int, // 1646105770
        @SerializedName("sunset")
        val sunset: Int, // 1646146372
        @SerializedName("type")
        val type: Int // 1
    )

    data class Weather(
        @SerializedName("description")
        val description: String, // overcast clouds
        @SerializedName("icon")
        val icon: String, // 04d
        @SerializedName("id")
        val id: Int, // 804
        @SerializedName("main")
        val main: String // Clouds
    )

    data class Wind(
        @SerializedName("deg")
        val deg: Int, // 180
        @SerializedName("speed")
        val speed: Double // 1.54
    )
}