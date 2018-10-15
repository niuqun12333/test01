package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entity.Classes;
import entity.Picture;
import entity.Product;

public interface ProductDao {

	List<Product> search(@Param("pro") Product condition);

	int addPicture(Picture picture);

	int addProduct(Product product);

	int addPicProId(Product product);

	int deleteProduct(@Param("ids") String ids);

	int deletePicForPro(@Param("ids") String ids);

	int updatePicPro(Product product);

	int deletePicture(Picture picture);

	List<Picture> searchPicture(Picture picture);

	int updatePicture(Picture picture);

	List<Picture> serachDeletePicture(@Param("ids") String ids);

	List<Classes> searchClass();

	List<Classes> searchAClass(Classes cla);

	int updateClasses(Classes cla);

	int addClasses(Classes cla);

	int deleteClasses(Classes cla);

}
