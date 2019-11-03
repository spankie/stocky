package spankie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiParam;
import spankie.model.Stock;
import spankie.service.StockService;;

@RestController
@RequestMapping("/stocks")
@Api(tags = "stocks")
public class StockController {

  @Autowired
  private StockService stockService;

  @GetMapping("/list/{username}")
  @ApiOperation(value = "${StockController.listUsersStock}")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Something went wrong"),
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public List<Stock> list(@ApiParam("Username") @RequestParam String username) {
    return stockService.listUsersStock(username);
  }

  @GetMapping("/lookup/{symbol}")
  @ApiOperation(value = "${StockController.lookup}")
  @ApiResponses(value = {
    @ApiResponse(code = 400, message = "Something went wrong"), //
    @ApiResponse(code = 422, message = "Invalid symbol supplied")})
  public Stock lookup(@ApiParam("symbol") @PathVariable String symbol) {
    return stockService.lookup(symbol);
  }
}