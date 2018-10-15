package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Product;

public interface ProductDao {

	int searchCount(@Param("pro") Product condition, @Param("fprice") Integer fprice, @Param("sprice") Integer sprice);

	List<Product> search(@Param("pro") Product condition, @Param("begin") int begin, @Param("size") int size,
			@Param("fprice") Integer fprice, @Param("sprice") Integer sprice);

	Product searchPage(@Param("pro") Product condition);

}
