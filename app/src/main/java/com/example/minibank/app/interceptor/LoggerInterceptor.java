package com.example.minibank.app.interceptor;

import com.example.minibank.commons.utils.DateUtils;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
@Component
public class LoggerInterceptor extends HandlerInterceptorAdapter {

	private static final String YYYY_MMM_DD_HH_MM_SS = "yyyy MMM dd HH:mm:ss";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

		final String startTime = DateUtils.convertDateToString()
				.apply(new Date(System.currentTimeMillis()), YYYY_MMM_DD_HH_MM_SS);

		final String ip = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
		final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;

		request.setAttribute("startTime", startTime);

		log.info("# HTTP Interceptor {}", startTime);
		log.info("# {} request received...", request.getMethod());

		if (!StringUtils.isEmpty(ipAddr)) {
			log.info("# IP: {}", ipAddr);
		}

		log.info("# URI: {}", request.getRequestURI());

		log.info("# Params: {}\n", getParameters(request));

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

		if (request.getAttribute("startTime") != null) {

			final String startTime = request.getAttribute("startTime").toString();

			final String ip = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
			final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;

			long executeTime = System.currentTimeMillis() - DateUtils.convertStringDateToLong()
					.apply(startTime, YYYY_MMM_DD_HH_MM_SS);

			log.info("# HTTP Interceptor {}", startTime);
			log.info("# {} response sent...", request.getMethod());

			if (!StringUtils.isEmpty(ipAddr)) {
				log.info("# IP: {}", ipAddr);
			}

			log.info("# URI: {}", request.getRequestURI());
			log.info("# Total Execution Time: {} ms\n", executeTime);

		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

	private String getRemoteAddr(final HttpServletRequest request) {
		final String ipFromHeader = request.getHeader(HttpHeaders.X_FORWARDED_FOR);
		if (ipFromHeader != null && ipFromHeader.length() > 0) {
			return ipFromHeader;
		}
		return request.getRemoteAddr();
	}

	private String getParameters(final HttpServletRequest request) {
		final StringBuilder posted = new StringBuilder();
		final Enumeration<?> e = request.getParameterNames();
		if (e != null)
			while (e.hasMoreElements()) {
				if (posted.length() > 1)
					posted.append(", ");
				final String curr = (String) e.nextElement();
				posted.append(curr).append("=");
				if (curr.contains("password") || curr.contains("userId") || curr.contains("sessionId")) {
					posted.append("n/a");
				} else {
					posted.append(request.getParameter(curr));
				}
			}

		return posted.toString();
	}
}
