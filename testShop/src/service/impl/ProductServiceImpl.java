package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDao;
import entity.Product;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao proDao;

	@Override
	public int searchCount(Product condition, Integer fprice, Integer sprice) {
		int count = proDao.searchCount(condition, fprice, sprice);
		return count;
	}

	@Override
	public List<Product> search(Product condition, int begin, int size, Integer fprice, Integer sprice) {
		List<Product> list = proDao.search(condition, begin, size, fprice, sprice);
		return list;
	}

	@Override
	public Product searchPage(Product condition) {
		Product pro = proDao.searchPage(condition);
		return pro;
	}

	

}
