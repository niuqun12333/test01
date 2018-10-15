package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Car;
import entity.CarDetail;
import entity.Product;
import service.ProductService;
import util.Constant;
import util.Pagination;

@Controller
public class ProductController {
	@Autowired
	ProductService proService;

	@RequestMapping("classSearch")
	public ModelAndView classSearch(Product condition, Integer ye, Integer fprice, Integer sprice) {

		int count = proService.searchCount(condition, fprice, sprice);
		if (ye == null) {
			ye = 1;
		}

		Pagination p = new Pagination(ye, count, Constant.EMP_NUM_IN_PAGE, Constant.EMP_NUM_OF_PAGE);
		List<Product> list = proService.search(condition, p.getBegin(), Constant.EMP_NUM_IN_PAGE, fprice, sprice);
		// List<Product> listPic = proService.searchProAndPic(condition, p.getBegin(),
		// Constant.EMP_NUM_IN_PAGE, fprice, sprice);

		ModelAndView mv = new ModelAndView("my-class");
		mv.addObject("list", list);
		// mv.addObject("list", listPic);
		mv.addObject("c", condition);
		mv.addObject("p", p);
		mv.addObject("fprice", fprice);
		mv.addObject("sprice", sprice);
		return mv;
	}

	@RequestMapping("pageSearch")
	public ModelAndView pageSearch(Product condition) {

		Product pro = proService.searchPage(condition);
		// List<Product> listPic = proService.searchProAndPic(condition, p.getBegin(),
		// Constant.EMP_NUM_IN_PAGE, fprice, sprice);

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("pro", pro);
		mv.addObject("pictures", pro.getPictures());
		return mv;
	}
}
