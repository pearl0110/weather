package com.example.cjcucsie.wheather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.Manifest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import static android.Manifest.permission.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.text);
        sendBtn = (Button)findViewById(R.id.send_request);

        final OkHttpClient client = new OkHttpClient();
        final ExecutorService service = Executors.newSingleThreadExecutor();

        sendBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        HttpUrl.Builder builder = HttpUrl.parse("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").newBuilder();

                        Request request = new Request.Builder()
                                //.header("Authorization","your token")
                                .url(builder.toString())
                                .build();
                        /*
                        try{
                            final Response response = client.newCall(request).execute();
                            final String resStr = response.body().string();
                            System.out.println("res "+resStr);
                            JSONObject json = new JSONObject(resStr);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(resStr);
                                }
                            });
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        catch (JSONException e){

                        }
                        */

                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call,final IOException e) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(e.getMessage());
                                    }
                                });
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                final String resStr = response.body().string();
                                //final List<JsonData> jsonData = new Gson().fromJson(resStr,new TypeToken<List<JsonData>>(){}.getType());

                                //Builder()==>為創建具體產品的具體建造者提供接口
                                //registerTypeAdapter==>分别是write()和read()方法,讀取Convert()的東西到Weather
                                //create到gson
                                Gson gson= new GsonBuilder()
                                        .registerTypeAdapter(Weather.class,new Convert())
                                        .create();

                                //gson裡面的東西丟到resStr，並用formJson轉到Weather
                                final Weather weather=gson.fromJson(resStr,Weather.class);

                                runOnUiThread(new Runnable() {
                                    @Override //將設定好的weather東西印出
                                    public void run() {
                                        StringBuffer sb = new StringBuffer();

                                        sb.append("temp:");
                                        sb.append(weather.temp);
                                        sb.append("\n");
                                        sb.append("pressure:");
                                        sb.append(weather.pressure);
                                        sb.append("\n");
                                        sb.append("humidity:");
                                        sb.append(weather.humidity);
                                        sb.append("\n");
                                        sb.append("temp_min:");
                                        sb.append(weather.temp_min);
                                        sb.append("\n");
                                        sb.append("temp_max:");
                                        sb.append(weather.temp_max);
                                        sb.append("\n");

                                        textView.setText(sb.toString());
                                    }
                                });
                            }
                        });

                    }
                });
            }
        });
    }




}
