package httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class HttpRequestTest {
    public static void main(String[] args) {
        String urlAddress = "https://query1.finance.yahoo.com/v7/finance/download/%5ERUT?period1=1644192000&period2=1644278400&interval=1d&events=history&includeAdjustedClose=true";
        HttpURLConnection connection = null;

        URL url = null;
        InputStreamReader isR = null;
        BufferedReader bfR = null;
        try {
            url = new URL(urlAddress);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(200);
            connection.setReadTimeout(200);
            connection.connect();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                isR = new InputStreamReader(connection.getInputStream());
                bfR = new BufferedReader(isR);
                String line;
                while ((line = bfR.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.printf("Fail %s", connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bfR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
