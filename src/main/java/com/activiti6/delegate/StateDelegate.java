package com.activiti6.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class StateDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("em...");
    }
}
