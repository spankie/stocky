package spankie.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import spankie.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

  List<Stock> findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

}
