package service;

import java.util.List;

import entity.Classes;
import entity.Picture;
import entity.Product;

public interface ProductService {

	List<Product> search(Product condition);

	int addPicture(Picture picture);

	int addProduct(Product product);

	boolean addPicProId(Product product);

	List<Picture> deleteProduct(String ids);

	boolean updatePicPro(Product product);

	boolean deletePicture(Picture picture);

	List<Picture> searchPicture(Picture picture);

	boolean updatePicture(Picture upPic);

	List<Classes> searchClass();

	List<Classes> searchClass(Classes cla);

	boolean updateClasses(Classes cla);

	boolean addClasses(Classes cla);

	boolean deleteClasses(Classes cla);

}
