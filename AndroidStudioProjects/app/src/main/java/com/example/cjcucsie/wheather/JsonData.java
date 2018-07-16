package com.example.cjcucsie.wheather;

import com.google.gson.annotations.SerializedName;

//立一個 Json 物件的類別，這邊可以先宣告好變數以後，透過Android Studio的自動生成完成
public class JsonData {
    @SerializedName("Temp")
    private String temp;
    @SerializedName("Pressure")
    private String pressure;
    @SerializedName("Humidity")
    private String humidity;
    @SerializedName("Temp_min")
    private String temp_min;
    @SerializedName("Temp_max")
    private String temp_max;

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }
}
