package com.example.cjcucsie.wheather;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class Convert implements JsonDeserializer {
    @Override
    public Weather deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject main = jsonObject.getAsJsonObject("main"); //取得main裡面的東西

        //main裡面的物件
        Double temp = main.get("temp").getAsDouble();
        Double pressure = main.get("pressure").getAsDouble();
        Double humidity = main.get("humidity").getAsDouble();
        Double temp_min = main.get("temp_min").getAsDouble();
        Double temp_max = main.get("temp_max").getAsDouble();

        //回傳
        return new Weather(temp.toString()
        ,pressure.toString(),humidity.toString(), temp_max.toString(),temp_min.toString());
    }
}
