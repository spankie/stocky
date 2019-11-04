package spankie.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import spankie.model.Stock;
import spankie.repository.StockRepository;
import spankie.service.StockService;
import spankie.service.UserService;
import spankie.dto.StockResponseDTO;
import spankie.dto.UserResponseDTO;

@RestController
@RequestMapping("/stocks")
@Api(tags = "stocks")
public class StockController {

  @Autowired
  private StockService stockService;

  @Autowired
  private StockRepository stockRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ModelMapper modelMapper;

  // private HttpURLConnection http = new HttpURLConnection();

  @GetMapping("/list")
  @ApiOperation(value = "${StockController.list}")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Something went wrong"),
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public List<Stock> list(HttpServletRequest req) {
    UserResponseDTO me = modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    
    return stockService.listUsersStock(me.getUsername());
  }

  @GetMapping("/lookup/{symbol}")
  @ApiOperation(value = "${StockController.lookup}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 422, message = "Invalid symbol supplied")})
  public Map<String, Object> lookup(@ApiParam("symbol") @PathVariable String symbol) {
    
    return stockService.lookup(symbol);
  }

  @PostMapping("/buy/{symbol}")
  @ApiOperation(value = "${StockController.list}")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Something went wrong"),
      @ApiResponse(code = 422, message = "Invalid parameters supplied")})
  public Stock buy(
    HttpServletRequest req,
    @ApiParam("symbol") @PathVariable String symbol,
    @ApiParam("volume") @RequestParam Integer volume
    ) {
    UserResponseDTO me = modelMapper.map(userService.whoami(req), UserResponseDTO.class);
    Map<String, Object> map = stockService.lookup(symbol);
    Double latesPrice = (Double) map.get("latestPrice");
    // check if the amount user wants to buy is lesser or equal to the latest Volume
    Stock st = new Stock();
    st.setUsername(me.getUsername());
    st.setCompanyName((String) map.get("companyName"));
    st.setLatestPrice(latesPrice);
    st.setLatestSource((String) map.get("latesSource"));
    st.setLatestVolume((Integer) map.get("latestVolume"));
    st.setSymbol(symbol);
    st.setBuyVolume(volume);
    st.setTotalAmount(volume*latesPrice);

    stockRepository.save(st);

    return st;
  }
}