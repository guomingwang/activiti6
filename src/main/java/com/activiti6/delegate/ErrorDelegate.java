package com.activiti6.delegate;

import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @author wgm
 * @since 2019/7/27 23:36
 */
public class ErrorDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        throw new BpmnError("errorName");
    }
}
