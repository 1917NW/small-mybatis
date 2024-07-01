package org.lxy.mybatis.session.defaults;

import org.lxy.mybatis.binding.MapperRegistry;
import org.lxy.mybatis.session.SqlSession;
import org.lxy.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private  final  MapperRegistry mapperRegistry;
    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
    }


    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
