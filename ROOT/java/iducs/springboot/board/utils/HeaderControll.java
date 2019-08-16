package iducs.springboot.board.utils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import iducs.springboot.board.domain.Category;
import iducs.springboot.board.domain.Division;
import iducs.springboot.board.service.CategoryService;
import iducs.springboot.board.service.DivisionService;

public class HeaderControll extends HandlerInterceptorAdapter {
	@Autowired
	DivisionService divisionService;
	@Autowired
	CategoryService categoryService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		List<Category> category = categoryService.getCategory();
		request.setAttribute("header", category);
		List<Division> division = divisionService.getDivision();
		request.setAttribute("header2", division);
		return true;
	}
}
