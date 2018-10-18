package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Car;
import entity.CarDetail;
import entity.Product;
import entity.User;
import service.CarService;

@Controller
public class CarController {
	@Autowired
	CarService carService;

	@RequestMapping("searchCar")
	public ModelAndView searchCar(HttpSession session, User condition) {
		User userSession=(User) session.getAttribute("userShop");
		int id=userSession.getId();
		condition.setId(id);
		User user = carService.searchCar(condition);
		ModelAndView mv = new ModelAndView("my-car");
		if (user != null) {
			mv.addObject("user", user);
			mv.addObject("car", user.getCar());
			mv.addObject("cds", user.getCar().getCds());
		}
		return mv;
	}

	@RequestMapping("addCar")
	@ResponseBody
	public boolean addCar(HttpSession session, Product pro, int num) {
		User userSession=(User) session.getAttribute("userShop");
		int id=userSession.getId();
		int c_id = carService.searchCarId(id);
		boolean flag = carService.addCar(pro, id, num, c_id);
		return flag;


	}

	@RequestMapping("deleteCar")
	public String deleteCar(CarDetail cd) {
		boolean flag = carService.deleteCar(cd);
		return "redirect:searchCar.do";
	}
	
	@RequestMapping("deleteCarDetails")
	public String deleteCarDetails(String cdIds) {
		boolean flag = carService.deleteCarDetails(cdIds);
		return "redirect:searchCar.do";
	}

	@RequestMapping("updateCar")
	@ResponseBody
	public boolean updateCar(CarDetail cd, int num) {
		boolean flag = carService.updateCarDetail(cd, num);
		return flag;
	}

	@RequestMapping("updateCarAll")
	@ResponseBody
	public boolean updateCarAll(Car car) {
		boolean flag = carService.updateCarAll(car);
		return flag;
	}
}
