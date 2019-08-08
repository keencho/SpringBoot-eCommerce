package iducs.springboot.board.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminHomeException extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		

		if ("/admin/404".equals(request.getRequestURI())) {
			return true;
		}
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/admin/404");
			return false;
		}

		if (session.getAttribute("userRank").equals("1")) {
			response.sendRedirect("/admin/404");
			return false;
		}

		return true;
	}

}
