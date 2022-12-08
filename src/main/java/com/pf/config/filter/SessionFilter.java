package com.pf.config.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import com.alibaba.fastjson.JSON;
import com.pf.config.property.LoginProperty;
import com.pf.dao.enums.AccountStatus;
import com.pf.pojo.dto.session.MiniSessionUser;
import com.pf.pojo.dto.session.WebSessionUser;
import com.pf.service.SessionService;
import com.pf.utils.request.RequestContextHelper;
import com.pf.utils.result.StatusCode;
import com.pf.utils.result.WebMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author yk_xing
 */
@Slf4j
public class SessionFilter implements Filter {

    private String sessionExpire = JSON.toJSONString(WebMessage.fail(StatusCode.SYSTEM_SESSION_EXPIRE));

    private SessionFilterContext sessionFilterContext;

    private String[] AJAX_HEADERS = new String[]{"application/json", "application/x-www-form-urlencoded"};

    public SessionFilter(SessionFilterContext sessionFilterContext) {
        this.sessionFilterContext = sessionFilterContext;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        //获取忽略路径
        boolean ignorePath = sessionFilterContext.isIgnorePath(uri);
        if (ignorePath) {
            chain.doFilter(req, rep);
            return;
        }
        if(RequestContextHelper.isMp()) {
            chain.doFilter(req, rep);
            return ;
        }
        HttpServletResponse response = (HttpServletResponse) rep;
        //判断是web,还是mini
        if (RequestContextHelper.isWeb()) {
            doWebFilter(request, response, chain);
            return;
        }
        if (RequestContextHelper.isMini()) {
            doMiniFilter(request, response, chain);
            return;
        }
        throw new ServletException(StatusCode.SYSTEM_NAMESPACE_ERROR.getMsg());
    }

    private void doMiniFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        SessionService sessionService = sessionFilterContext.getSessionService();
        MiniSessionUser user = sessionService.getSessionUser(session, MiniSessionUser.class);
        if (user == null || !isAccountEnable(user)) {
            response.setHeader(Header.CONTENT_TYPE.getValue(),
                    "application/json;charset=utf-8");
            response.getWriter().write(sessionExpire);
            return;
        }
        RequestContextHelper.setSessionUser(user);
        chain.doFilter(request, response);
    }

    private void doWebFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        SessionService sessionService = sessionFilterContext.getSessionService();
        WebSessionUser user = sessionService.getSessionUser(session, WebSessionUser.class);
        if (user == null || !isAccountEnable(user)) {
            //判断是否是ajax请求
            if (isAjax(request)) {
                response.setHeader(Header.CONTENT_TYPE.getValue(),
                        "application/json;charset=utf-8");
                response.getWriter().write(sessionExpire);
            } else {
                log.info("会话失效跳转:" + uri);
                LoginProperty login = sessionFilterContext.getSystemConfig().getLogin();
                //重定向到登录页(需要在static文件夹下建立此html文件)
                response.sendRedirect(login.getIndex());
            }
            return;
        }
        RequestContextHelper.setSessionUser(user);
        chain.doFilter(request, response);
    }

    private boolean isAccountEnable(WebSessionUser sessionUser) {
        return AccountStatus.getByCode(sessionUser.getAccountStatus()) == AccountStatus.ENABLE;
    }

    private boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader(Header.CONTENT_TYPE.getValue());
        if (StrUtil.isEmpty(requestType)) {
            return false;
        }
        return Arrays.stream(AJAX_HEADERS).anyMatch((x) -> requestType.indexOf(x) > -1);
    }
}
