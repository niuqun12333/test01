package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import entity.Order;
import service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@RequestMapping("showTransaction")
	public ModelAndView showTransaction() {
		ModelAndView mv = new ModelAndView("transaction");
		return mv;
	}
	@RequestMapping("showOrderform")
	public ModelAndView showOrderform(Order order) {
		ModelAndView mv = new ModelAndView("Orderform");
		List<Order> orderList = orderService.searchOrder(order);
		mv.addObject("orderList", orderList);
		return mv;
	}
	@RequestMapping("showOrderDetail")
	public ModelAndView showOrderDetail(Order order) {
		ModelAndView mv = new ModelAndView("order_detailed");
		List<Order> orderList = orderService.searchOrder(order);
		mv.addObject("orderList", orderList);
		return mv;
	}
	@RequestMapping("updateCondition")
	@ResponseBody
	public boolean updateCondition(Order order) {
		boolean flag = orderService.updateCondition(order);
		return flag;
	}
	@RequestMapping("deleteOrder")
	@ResponseBody
	public boolean deleteOrder(Order order) {
		boolean flag = orderService.deleteOrder(order);
		return flag;
	}
}
