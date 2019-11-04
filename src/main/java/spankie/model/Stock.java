package spankie.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  private String username; // username of user buying stock

  private String symbol; // company stock trading symbol

  private String companyName; // Company Full name

  private Double latestPrice; // Price of stock

  private Integer latestVolume; // Volume of available stock

  private String latestSource; // "Close" or "Open"

  private Integer buyVolume; // volume of stock purchased.

  private Double totalAmount; // total price of bought stock: buyVolume * latestPrice


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public Double getLatestPrice() {
    return latestPrice;
  }

  public void setLatestPrice(Double latestPrice) {
    this.latestPrice = latestPrice;
  }

  public Integer getLatestVolume() {
    return latestVolume;
  }

  public void setLatestVolume(Integer latestVolume) {
    this.latestVolume = latestVolume;
  }

  public String getLatestSource() {
    return latestSource;
  }

  public void setLatestSource(String latestSource) {
    this.latestSource = latestSource;
  }

  public Integer getBuyVolume() {
    return buyVolume;
  }

  public void setBuyVolume(Integer buyVolume) {
    this.buyVolume = buyVolume;
  }

  public Double getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Double totalAmount) {
    this.totalAmount = totalAmount;
  }

  
}