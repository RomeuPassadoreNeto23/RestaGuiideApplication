package br.senai.sp.restaguide.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.senai.sp.restaguide.annotation.Privado;
import br.senai.sp.restaguide.annotation.Publico;
import br.senai.sp.restaguide.controller.UsuarioController;
import br.senai.sp.restaguide.rest.UsuarioRestController;

@Component
public class AppInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

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
				String token = null;
				if (metochamado.getMethodAnnotation(Privado.class) != null) {
					try {
						token = request.getHeader("Authorization");
						Algorithm algorithm = Algorithm.HMAC256(UsuarioRestController.SECRET);
						JWTVerifier verifier = JWT.require(algorithm).withIssuer(UsuarioRestController.EMISSOR).build();
						DecodedJWT jwt = verifier.verify(token);
						Map<String, Claim> payload = jwt.getClaims();
						System.out.println(payload.get("nome_usuario"));
						return true;

					} catch (Exception e) {
						if (token == null) {
							response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
						} else {
							response.sendError(HttpStatus.FORBIDDEN.value(), e.getMessage());
						}
						return false;
					}

				}
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
