package spankie.dto;

import io.swagger.annotations.ApiModelProperty;

public class StockResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;
  @ApiModelProperty(position = 1)
  private String username;
  @ApiModelProperty(position = 2)
  private String symbol;
  @ApiModelProperty(position = 3)
  private String companyName; // Company Full name
  @ApiModelProperty(position = 4)
  private Double latestPrice; // Price of stock
  @ApiModelProperty(position = 5)
  private Integer latestVolume; // Volume of available stock
  @ApiModelProperty(position = 6)
  private String latestSource; // "Close" or "Opend"

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


}
