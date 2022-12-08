package com.pf.config.filter;

import com.pf.utils.request.RequestContextHelper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author yk_xing
 */
@Slf4j
public class GlobalServletFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        RequestContextHelper.setRootPath(uri);
        chain.doFilter(req, rep);
    }
}
