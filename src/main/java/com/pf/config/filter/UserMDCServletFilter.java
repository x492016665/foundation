package com.pf.config.filter;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.pf.pojo.dto.session.WebSessionUser;
import com.pf.service.SessionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * @author yk_xing
 */
@Slf4j
public class UserMDCServletFilter extends OncePerRequestFilter {

    public static final String USER_ID = "session.uid";
    public static final String REQUEST_ID = "req.id";
    public static final String REAL_IP = "ip";

    private SessionService sessionService;

    public UserMDCServletFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        insertIntoMDC(request);
        try {
            filterChain.doFilter(request, response);
        } finally {
            clearMDC();
        }
    }

    void insertIntoMDC(HttpServletRequest request) {
        HttpSession session = request.getSession();
        WebSessionUser user = sessionService.getSessionUser(session, WebSessionUser.class);
        if(user == null) {
            MDC.put(USER_ID, "noLogin");
        } else {
            MDC.put(USER_ID, Objects.toString(user.getAccountId()));
        }
        MDC.put(REQUEST_ID, getRequestId(request));
        MDC.put(REAL_IP, getRealIp(request));
    }

    private String getRealIp(HttpServletRequest request) {
        String realIp = request.getHeader("X-Real-IP");
        if(StrUtil.isEmpty(realIp)) {
            return request.getRemoteAddr();
        }
        return realIp;
    }

    private String getRequestId(HttpServletRequest request) {
        String requestId = request.getHeader(REQUEST_ID);
        if (StrUtil.isEmpty(requestId)) {
            requestId = UUID.randomUUID().toString(true);
        }
        return requestId;
    }

    void clearMDC() {
        MDC.remove(USER_ID);
        MDC.remove(REQUEST_ID);
    }
}
