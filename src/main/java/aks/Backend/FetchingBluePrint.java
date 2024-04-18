package aks.Backend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FetchingBluePrint {
    public FetchingBluePrint(){

    }
    public String fetchData(String link){
        HttpURLConnection connection;

        // String link = "https://api.jikan.moe/v4/anime/"+id+"/full";
        URI uri = URI.create(link);
        try{
            URL url = uri.toURL();
            connection = (HttpURLConnection)url.openConnection();
            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";

                while((line = br.readLine()) != null){
                    sb.append(line);
                }
                br.close();
                return sb.toString();
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return null;
    }
    public JsonNode fetchNodes(String info){
        JsonNode node = null;
        ObjectMapper om = new ObjectMapper();
        try{
            node = om.readTree(info);
        }catch(Exception e){
            e.printStackTrace();
        }
        return node;
    }
}
