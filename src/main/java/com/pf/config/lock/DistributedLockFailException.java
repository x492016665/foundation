package com.pf.config.lock;

/**
 * @author yk_xing
 */
public class DistributedLockFailException extends RuntimeException {

    public DistributedLockFailException() {
        super("获取锁失败");
    }
}
