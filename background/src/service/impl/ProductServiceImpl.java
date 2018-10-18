package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ProductDao;
import entity.Classes;
import entity.MClass;
import entity.Picture;
import entity.Product;
import service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDao proDao;

	@Override
	public List<Product> search(Product condition) {
		List<Product> list = proDao.search(condition);
		return list;
	}

	@Override
	public int addPicture(Picture picture) {
		int rs = proDao.addPicture(picture);
		int picId = picture.getId();
		return picId;
	}

	@Override
	public int addProduct(Product product) {
		int rs = proDao.addProduct(product);
		int id = product.getId();
		return id;
	}

	@Override
	public boolean addPicProId(Product product) {
		int rs = proDao.addPicProId(product);
		return rs > 0;
	}

	@Override
	public List<Picture> deleteProduct(String ids) {
		List<Picture> list = proDao.serachDeletePicture(ids);
		int rs = proDao.deletePicForPro(ids);
		rs = proDao.deleteProduct(ids);
		return list;
	}

	@Override
	public boolean updatePicPro(Product product) {
		int rs = proDao.updatePicPro(product);
		return rs > 0;
	}

	@Override
	public boolean deletePicture(Picture picture) {
		int rs = proDao.deletePicture(picture);
		return rs > 0;
	}

	@Override
	public List<Picture> searchPicture(Picture picture) {
		List<Picture> list = proDao.searchPicture(picture);
		return list;
	}

	@Override
	public boolean updatePicture(Picture picture) {
		int rs = proDao.updatePicture(picture);
		return rs > 0;
	}

	@Override
	public List<Classes> searchClass() {
		List<Classes> list = proDao.searchClass();
		return list;
	}

	@Override
	public List<Classes> searchClass(Classes cla) {
		List<Classes> list = proDao.searchAClass(cla);
		return list;
	}

	@Override
	public boolean updateClasses(Classes cla) {
		int rs = proDao.updateClasses(cla);
		return rs>0;
	}

	@Override
	public int addClasses(Classes cla) {
		int rs = proDao.addClasses(cla);
		int c_id = cla.getId();
		return c_id;
	}

	@Override
	public boolean deleteClasses(Classes cla) {
		int rs = proDao.deleteMClassByClass(cla);
		rs = proDao.deleteClasses(cla);
		return rs>0;
	}

	@Override
	public List<MClass> searchMClass(MClass mc) {
		List<MClass> list = proDao.searchMClass(mc);
		return list;
	}

	@Override
	public boolean addMClass(MClass mclass) {
		int rs = proDao.addMClass(mclass);
		return rs>0;
	}

	@Override
	public boolean deleteMClass(MClass mc) {
		int rs = proDao.deleteMClass(mc);
		return rs>0;
	}

	@Override
	public boolean updateMClass(MClass mc) {
		int rs = proDao.updateMClass(mc);
		return rs>0;
	}

}
