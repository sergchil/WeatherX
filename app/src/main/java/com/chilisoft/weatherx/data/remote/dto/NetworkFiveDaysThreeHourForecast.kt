package com.chilisoft.weatherx.data.remote.dto


import com.google.gson.annotations.SerializedName

data class NetworkFiveDaysThreeHourForecast(
    @SerializedName("city")
    val city: City,
    @SerializedName("cnt")
    val cnt: Int, // 40
    @SerializedName("cod")
    val cod: String, // 200
    @SerializedName("list")
    val list: List<Forecast>,
    @SerializedName("message")
    val message: Int // 0
) {
    data class City(
        @SerializedName("coord")
        val coord: Coord,
        @SerializedName("country")
        val country: String, // AM
        @SerializedName("id")
        val id: Int, // 616052
        @SerializedName("name")
        val name: String, // Yerevan
        @SerializedName("population")
        val population: Int, // 1093485
        @SerializedName("sunrise")
        val sunrise: Int, // 1646105770
        @SerializedName("sunset")
        val sunset: Int, // 1646146372
        @SerializedName("timezone")
        val timezone: Int // 14400
    ) {
        data class Coord(
            @SerializedName("lat")
            val lat: Double, // 40.1811
            @SerializedName("lon")
            val lon: Double // 44.5136
        )
    }

    data class Forecast(
        @SerializedName("clouds")
        val clouds: Clouds,
        @SerializedName("dt")
        val dt: Int, // 1646125200
        @SerializedName("dt_txt")
        val dtTxt: String, // 2022-03-01 09:00:00
        @SerializedName("main")
        val main: Main,
        @SerializedName("pop")
        val pop: Double, // 0
        @SerializedName("rain")
        val rain: Rain,
        @SerializedName("snow")
        val snow: Snow,
        @SerializedName("sys")
        val sys: Sys,
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

        data class Main(
            @SerializedName("feels_like")
            val feelsLike: Double, // 282.24
            @SerializedName("grnd_level")
            val grndLevel: Int, // 905
            @SerializedName("humidity")
            val humidity: Int, // 32
            @SerializedName("pressure")
            val pressure: Int, // 1019
            @SerializedName("sea_level")
            val seaLevel: Int, // 1019
            @SerializedName("temp")
            val temp: Double, // 284.24
            @SerializedName("temp_kf")
            val tempKf: Double, // 1.94
            @SerializedName("temp_max")
            val tempMax: Double, // 284.24
            @SerializedName("temp_min")
            val tempMin: Double // 282.3
        )

        data class Rain(
            @SerializedName("3h")
            val h: Double // 0.14
        )

        data class Snow(
            @SerializedName("3h")
            val h: Double // 0.93
        )

        data class Sys(
            @SerializedName("pod")
            val pod: String // d
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
            val deg: Int, // 58
            @SerializedName("gust")
            val gust: Double, // 1.78
            @SerializedName("speed")
            val speed: Double // 0.58
        )
    }
}