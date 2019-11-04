package spankie.service;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;
import spankie.exception.CustomException;

@Service
public class UrlService {

  public static final String USER_AGENT = "Mozilla/5.0";

  public Map<String, Object> GET(String url) {
    URL obj;
    HttpURLConnection con;
    StringBuffer response;
    try {
      obj = new URL(url);
      con = (HttpURLConnection) obj.openConnection();
      // optional default is GET
      con.setRequestMethod("GET");

      //add request header
      con.setRequestProperty("User-Agent", USER_AGENT);

      int responseCode = con.getResponseCode();
      System.out.println("\nSending 'GET' request to URL : " + url);
      System.out.println("Response Code : " + responseCode);

      BufferedReader in = new BufferedReader(
              new InputStreamReader(con.getInputStream()));
      String inputLine;
      response = new StringBuffer();

      while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
      }
      in.close();

      ObjectMapper mapper = new ObjectMapper();
      return mapper.readValue(response.toString(), Map.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new CustomException("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}