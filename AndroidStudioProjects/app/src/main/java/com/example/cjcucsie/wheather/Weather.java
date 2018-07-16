package com.example.cjcucsie.wheather;

public class Weather {
    String temp;
    String pressure;
    String humidity;
    String temp_min;
    String temp_max;

    //把值傳給Wheather
    Weather(String temp,String pressure,String humidity,String temp_max,String temp_min){
        this.temp=temp;
        this.pressure=pressure;
        this.humidity=humidity;
        this.temp_max=temp_max;
        this.temp_min=temp_min;
    }
}
