package bi.lunch.component.middleware;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.log4j.*;

@Component
public class LogRequestMiddleware extends OncePerRequestFilter {
	private static final Logger log = Logger.getLogger(LogRequestMiddleware.class.getName());

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		log.info(
				String.format("Request from IP: %s To Endpoint: %s", request.getRemoteAddr(), request.getRequestURL()));
		filterChain.doFilter(request, response);
	}

}
