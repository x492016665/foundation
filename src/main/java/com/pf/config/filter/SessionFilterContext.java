package com.pf.config.filter;

import com.pf.config.SystemConfig;
import com.pf.service.SessionService;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yk_xing
 */
@Data
public class SessionFilterContext {

    private SessionService sessionService;

    private Set<String> ignorePaths = new HashSet<>();

    private SystemConfig systemConfig;

    public SessionFilterContext(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void addIgnorePath(String path) {
        this.ignorePaths.add(path);
    }

    public boolean isIgnorePath(String pathInfo) {
        return ignorePaths.contains(pathInfo);
    }
}
