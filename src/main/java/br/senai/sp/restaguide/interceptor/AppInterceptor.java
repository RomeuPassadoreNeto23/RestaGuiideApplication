package br.senai.sp.restaguide.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.senai.sp.restaguide.annotation.Publico;

@Component
public class AppInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// variavel para descobri
		String uri = request.getRequestURI();
		System.out.println(uri);
		if (handler instanceof HandlerMethod) {
			if (uri.equals("/")) {
				return true;
			}
			if (uri.endsWith("/error")) {
				return true;
			}
			HandlerMethod metochamado = (HandlerMethod) handler;
			if (uri.startsWith("/api")) {
				return true;

			} else {

				if (metochamado.getMethodAnnotation(Publico.class) != null) {
					return true;
				}
				if (request.getSession().getAttribute("usuarioLogin") != null) {
					return true;
				} else {
					response.sendRedirect("/");
					return false;
				}
			}
		}
		return true;
	}

}
