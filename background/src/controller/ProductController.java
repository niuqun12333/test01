package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import entity.Classes;
import entity.Picture;
import entity.Product;
import service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService proService;

	@RequestMapping("showProduct")
	public ModelAndView showProduct(Product condition) {
		ModelAndView mv = new ModelAndView("Products_List");
		List<Product> list = proService.search(condition);
		List<Classes> classList = proService.searchClass();
		mv.addObject("proList", list);
		mv.addObject("classList", classList);
		return mv;
	}

	@RequestMapping("showPictureAdd")
	public ModelAndView showPictureAdd() {
		ModelAndView mv = new ModelAndView("picture-add");
		List<Classes> classList = proService.searchClass();
		mv.addObject("classList", classList);
		return mv;
	}

	@RequestMapping("upload")
	@ResponseBody
	public String upload(@RequestParam(value = "file") MultipartFile[] myfiles) {
		String pictureName = "";
		String uploadPath = "F://tu";
		try {
			for (MultipartFile myfile : myfiles) {

				// MultipartFile myfile=myfiles[0];

				if (!myfile.isEmpty()) {
					String oldName = myfile.getOriginalFilename();
					UUID uuid = UUID.randomUUID();
					pictureName = uuid.toString() + oldName.substring(oldName.lastIndexOf("."));
					myfile.transferTo(new File(uploadPath + "/" + pictureName));
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pictureName;
	}

	// @RequestMapping(value="addPictures",method = {RequestMethod.POST })
	// @ResponseBody
	// public String addPictures(@RequestBody String[] picNames) {
	// String picIds="";
	// for(int i=0;i<=picNames.length;i++) {
	// int rank=i+1;
	// String picName=picNames[i];
	// Picture picture=new Picture();
	// picture.setPath(picName);
	// picture.setRank(rank);
	// int picId=proService.addPicture(picture);
	// picIds+=picId+",";
	// }
	// picIds=picIds.substring(0, picIds.length()-1);
	// return picIds;
	// }
	//
	// @RequestMapping("addProduct")
	// @ResponseBody
	// public int addProduct(Product product) {
	// int id=proService.addProduct(product);
	// return id;
	// }
	@RequestMapping(value = "addPicPro", method = { RequestMethod.POST })
	@ResponseBody
	public boolean addPicPro(Product product, String picNames) {
		boolean flag = false;
		int id = proService.addProduct(product);
		if (id != 0) {
			flag = true;
		}
		if (picNames != "") {
			String[] pictueNames = picNames.split(",");
			for (int i = 0; i < pictueNames.length; i++) {
				int rank = i + 1;
				String picName = pictueNames[i];
				Picture picture = new Picture();
				picture.setPath(picName);
				picture.setRank(rank);
				picture.setP_id(id);
				int picId = proService.addPicture(picture);
				if (picId != 0) {
					flag = true;
				}
			}
		}
		return flag;
	}

	@RequestMapping("deleteProduct")
	@ResponseBody
	public boolean deleteProduct(String ids) {
		boolean flag = false;
		List<Picture> picList = proService.deleteProduct(ids);
		for (int i = 0; i < picList.size(); i++) {
			Picture picture = picList.get(i);
			String deleteFile = picture.getPath();
			flag = filedelete(deleteFile);
		}
		return flag;
	}

	@RequestMapping("filedelete")
	@ResponseBody
	public boolean filedelete(String picture) {
		boolean flag = false;
		String pat = "F:/tu/";
		File del = new File(pat + picture);
		if (del.exists()) {
			del.delete();
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	@RequestMapping("showProductUpdate")
	public ModelAndView showProductUpdate(Product product) {
		ModelAndView mv = new ModelAndView("picture-update");
		List<Product> list = proService.search(product);
		List<Classes> classList = proService.searchClass();
		mv.addObject("classList", classList);
		mv.addObject("proList", list);
		return mv;
	}

	@RequestMapping("updatePicPro")
	@ResponseBody
	public boolean updatePicPro(Product product, String picNames) {
		boolean flag = proService.updatePicPro(product);
		int id = product.getId();
		if (picNames != "") {
			String[] pictueNames = picNames.split(",");
			for (int i = 0; i < pictueNames.length; i++) {
				String picName = pictueNames[i];
				Picture picture = new Picture();
				picture.setPath(picName);
				picture.setP_id(id);
				int picId = proService.addPicture(picture);
				if (picId != 0) {
					flag = true;
				}
			}
		}
		Picture picture = new Picture();
		picture.setP_id(id);
		List<Picture> picList = proService.searchPicture(picture);
		for (int i = 0; i < picList.size(); i++) {
			Picture pic = picList.get(i);
			int id1 = pic.getId();
			int rank = i + 1;
			Picture upPic = new Picture();
			upPic.setRank(rank);
			upPic.setId(id1);
			flag = proService.updatePicture(upPic);
		}
		return flag;
	}

	@RequestMapping("deletePicture")
	@ResponseBody
	public boolean deletePicture(Picture picture) {
		boolean flag = proService.deletePicture(picture);
		return flag;
	}

	@RequestMapping("showClasses")
	public ModelAndView showClasses() {
		ModelAndView mv = new ModelAndView("Category_Manage");
		List<Classes> classList = proService.searchClass();
		mv.addObject("classList", classList);
		return mv;
	}

	@RequestMapping("showClassPro")
	public ModelAndView showClassPro(Classes cla) {
		ModelAndView mv = new ModelAndView("product-category-add");
		return mv;
	}
	@RequestMapping("showClassProUpdate")
	public ModelAndView showClassProUpdate(Classes cla) {
		ModelAndView mv = new ModelAndView("product-category-update");
		if (cla != null) {
			List<Classes> classList = proService.searchClass(cla);
			mv.addObject("classList", classList);
		}
		return mv;
	}
	@RequestMapping("addClasses")
	public String addClasses(Classes cla) {
		boolean flag = proService.addClasses(cla);
		if(flag) {
			return "redirect:showClassPro.do";
		}else {
			return "redirect:showClassProUpdate.do?id="+cla.getId();
		}
	}
	@RequestMapping("updateClasses")
	public String updateClasses(Classes cla) {
		boolean flag = proService.updateClasses(cla);
		if(flag) {
			return "redirect:showClassPro.do";
		}else {
			return "redirect:showClassProUpdate.do?id="+cla.getId();
		}
		
	}
	@RequestMapping("deleteClasses")
	public String deleteClasses(Classes cla) {
		boolean flag = proService.deleteClasses(cla);
		if(flag) {
			return "redirect:showClassPro.do";
		}else {
			return "redirect:showClassProUpdate.do?id="+cla.getId();
		}
	}
}
