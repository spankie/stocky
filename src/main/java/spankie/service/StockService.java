package spankie.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
// import java.io.DataOutputStream;
// import java.io.IOException;
import java.io.InputStreamReader;
import org.springframework.http.HttpStatus;
// import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spankie.repository.StockRepository;
import spankie.model.Stock;
import spankie.exception.CustomException;
// import spankie.dto.StockResponseDTO;

@Service
public class StockService {
  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private UrlService urlService;

  public List<Stock> listUsersStock(String username) {
    List<Stock> stocks = stockRepository.findByUsername(username);
    if (stocks == null) {
      throw new CustomException("There are not stocks for this user", HttpStatus.NOT_FOUND);
    }
    return stocks;
  }

  public Map<String, Object> lookup(String symbol) {
    String url = "https://cloud-sse.iexapis.com/stable/stock/"+symbol+"/quote?token=sk_e6e5f9391b6549a5b1e1129d8ff04026";
    return urlService.GET(url);
  }
}