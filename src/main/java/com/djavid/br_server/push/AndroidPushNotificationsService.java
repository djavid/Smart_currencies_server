package com.djavid.br_server.push;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AndroidPushNotificationsService {

    private static final String FIREBASE_SERVER_KEY = "AAAAqEKW79M:APA91bFBAZiWX1Z_ojXnw5dNa5Spzx-vaJbA-fI5r7IWqsuht" +
            "8tOUVFKzFMii4r5m7o73i2AS1WniIFJkisNXLIrE-lqhxIukqSARz91Nn9OO5g5nHJtpGmfqX7sMheHbUnEYYDdrQal";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(String device_id, String title, String desc) throws Exception {

        URL url = new URL(FIREBASE_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "key=" + FIREBASE_SERVER_KEY);
        conn.setRequestProperty("Content-Type", "application/json");

        JSONObject notification = new JSONObject();
        notification.put("title", title);
        notification.put("body", desc);
        notification.put("sound", "default");

        JSONObject body = new JSONObject();
        body.put("to", device_id);
        body.put("priority", "high");
        //body.put("notification", notification);
        //body.put("data", data);
        body.put("data", notification);

        OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream());
        os.write(body.toString());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return CompletableFuture.completedFuture(response.toString());
    }

}
