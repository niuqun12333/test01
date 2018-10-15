package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Area;
import entity.CarDetail;
import entity.City;
import entity.Location;
import entity.Order;
import entity.OrderDetail;
import entity.Province;
import entity.User;
import service.OrderService;
import util.GenerateOrderNo;
import util.ShowTime;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping("showOrder")
	public ModelAndView showOrder(String ids, int allNum, int allPrice,HttpSession session) {
		List<CarDetail> list = orderService.searchAdd(ids);
		List<Province> proList = orderService.searchProvince();
		User user=(User) session.getAttribute("userShop");
		int u_id=user.getId();
		List<Location> locList = orderService.searchLocation(u_id);
		ModelAndView mv = new ModelAndView("my-add");
		mv.addObject("list", list);
		mv.addObject("locList", locList);
		mv.addObject("proList", proList);
		mv.addObject("allNum", allNum);
		mv.addObject("allPrice", allPrice);
		mv.addObject("proIds", ids);
		return mv;
	}

	@RequestMapping("searchCity")
	@ResponseBody
	public List<City> searchCity(Province prov) {
		List<City> list = orderService.searchCity(prov);
		return list;

	}
	@RequestMapping("searchArea")
	@ResponseBody
	public List<Area> searchArea(City city) {
		List<Area> list = orderService.searchArea(city);
		return list;

	}
	@RequestMapping("addLocation")
	@ResponseBody
	public boolean addLocation(Location location,HttpSession session) {
		User user=(User) session.getAttribute("userShop");
		int u_id=user.getId();
		location.setU_id(u_id);
		boolean flag = orderService.addLocation(location);
		return flag;
	}
	
	@RequestMapping("showOrderDetail")
	public ModelAndView showOrderDetail(HttpSession session) {
		ModelAndView mv = new ModelAndView("my-dingdan");
		User user=(User) session.getAttribute("userShop");
		int u_id=user.getId();
		List<Order> list= orderService.searchOrderDetail(u_id);
		mv.addObject("list", list);
		return mv;
	}
	@RequestMapping("addOrder")
	public String addOrder(HttpSession session,Order order,int loc_id,String cdIds) {
		User user=(User) session.getAttribute("userShop");
		int u_id=user.getId();
		Location location = new Location();
		String time = ShowTime.Time();
		String orderNum = GenerateOrderNo.generate();
		order.setOrderNum(orderNum);
		order.setTime(time);
		order.setU_id(u_id);
		location.setId(loc_id);
		order.setLocation(location);
		boolean flag= orderService.addOrder(order,cdIds);
		return "redirect:showOrderDetail.do";
	}
	@RequestMapping("deleteOrder")
	public String deleteOrder(Order order) {
	 	boolean flag = orderService.deleteOrder(order);
		return "redirect:showOrderDetail.do";
		
	}
	@RequestMapping("deleteLocation")
	@ResponseBody
	public boolean deleteLocation(Location location) {
		boolean flag = orderService.deleteLocation(location);
		return flag;
	}

}
