package org.lxy.mybatis.builder;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.lxy.mybatis.session.Configuration;

public abstract class BaseBuilder {
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    public Configuration getConfiguration(){
        return configuration;
    }
}
