package spankie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import spankie.repository.StockRepository;
import spankie.model.Stock;
import spankie.exception.CustomException;

@Service
public class StockService {
  @Autowired
  private StockRepository stockRepository;

  public List<Stock> listUsersStock(String username) {
    List<Stock> stocks = stockRepository.findByUsername(username);
    if (stocks == null) {
      throw new CustomException("There are not stocks for this user", HttpStatus.NOT_FOUND);
    }
    return stocks;
  }

  public Stock lookup(String symbol) {

    Stock stock = new Stock();
    // search the api with the key: 
    return stock;

  }
}