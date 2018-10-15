package service;

import java.util.List;

import entity.Car;
import entity.CarDetail;
import entity.Product;

public interface ProductService {

	int searchCount(Product condition, Integer fprice, Integer sprice);

	List<Product> search(Product condition, int begin, int size, Integer fprice, Integer sprice);

	Product searchPage(Product condition);

	



}
