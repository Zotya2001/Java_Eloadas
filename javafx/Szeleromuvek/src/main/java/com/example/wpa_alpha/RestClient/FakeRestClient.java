package com.example.wpa_alpha.RestClient;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class FakeRestClient {
    public static String getGetResponseValue() {
        return getResponseValue;
    }

    private static String getResponseValue;

    private String putResponseValue;
    private String deleteResponseValue;
    static HttpsURLConnection connection;
    static String token = "4f2812201c0b9e952081afaa7af1da08f6ab6f336c66b76fbdffeb1d3b82be56";

    public static void GET(String id) throws IOException {
        System.out.println("\nGET...");
        String url = "https://gorest.co.in/public/v1/users";
        if(id!=null) {
            url = url + "/" + id;
            URL usersURL = new URL(url);
            connection = (HttpsURLConnection) usersURL.openConnection();
            connection.setRequestMethod("GET");
        }
        if(id!=null)
            connection.setRequestProperty("Authorization", "Bearer " + token);
        request3(HttpsURLConnection.HTTP_OK);

    }

    static void request3(int code) throws IOException {
        int statusCode = connection.getResponseCode();
        System.out.println("Status code: " + statusCode);
        if(statusCode == code){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer jsonResponseData = new StringBuffer();
            String readLine = null;
            while ((readLine = in.readLine()) != null){
                jsonResponseData.append(readLine);
            }
            in.close();
            getResponseValue = new String("A kérés válasza: " + jsonResponseData.toString());
        }else{
            System.out.println("Hiba!!!");
            getResponseValue = new String("A kérés válasza: Hiba a kért erőforrás nem található!");
        }
        connection.disconnect();
    }

    public static void POST(String name, String gender, String email, String status) throws IOException{
        System.out.println("\nPOST...");
        URL postUrl = new URL("https://gorest.co.in/public/v1/users");
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("POST");
        request1();
        String params = "{\"name\":\""+name+"\", \"gender\":\""+gender+"\", \"email\":\""+email+"\", \"status\":\""+status+"\"}";
        request2(params);
        request3(HttpsURLConnection.HTTP_CREATED);
    }

    public static void PUT(String ID, String name, String gender, String email, String status) throws IOException {
        System.out.println("\nPUT...");
        String url = "https://gorest.co.in/public/v1/users"+"/"+ID;
        URL postUrl = new URL(url);  // Url for making PUT request
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("PUT");            // Set PUT as request method
        request1();
        String params = "{\"name\":\""+name+"\", \"gender\":\""+gender+"\", \"email\":\""+email+"\", \"status\":\""+status+"\"}";   // Adding Body payload for POST request
        request2(params);
        request3(HttpsURLConnection.HTTP_OK);
    }

    public static void DELETE(String ID) throws IOException {
        System.out.println("\nDELETE...");
        String url = "https://gorest.co.in/public/v1/users"+"/"+ID;
        URL postUrl = new URL(url);  // Url for making PUT request
        connection = (HttpsURLConnection) postUrl.openConnection();
        connection.setRequestMethod("DELETE");            // Set DELETE as request method
        request1();
        request3(HttpsURLConnection.HTTP_NO_CONTENT);
    }



    static void request1(){
        // Setting Header Parameters
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);
        connection.setUseCaches(false);
        connection.setDoOutput(true);
    }

    static void request2(String params) throws IOException {
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
        wr.write(params);
        wr.close();
        connection.connect();
    }


}
