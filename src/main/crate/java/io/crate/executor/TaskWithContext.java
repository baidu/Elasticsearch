package io.crate.executor;

import io.crate.analyze.ParameterContext;

public abstract class TaskWithContext implements Task {

    private ParameterContext paramContext;
    
    @Override
    public ParameterContext getParamContext() {
        return paramContext;
    }
    
    @Override
    public void setParamContext(ParameterContext parameterContext) {
        this.paramContext = parameterContext;
    }
}
