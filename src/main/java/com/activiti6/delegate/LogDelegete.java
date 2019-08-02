package com.activiti6.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author wgm
 * @since 2019/7/27 21:49
 */
public class LogDelegete implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println(delegateExecution.getId() + ": 运行中...");
        System.out.println(delegateExecution.getVariable("iData"));
    }
}
