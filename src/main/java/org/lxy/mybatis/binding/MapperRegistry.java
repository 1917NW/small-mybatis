package org.lxy.mybatis.binding;

import cn.hutool.core.lang.ClassScanner;
import org.lxy.mybatis.session.Configuration;
import org.lxy.mybatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapperRegistry {

    public MapperRegistry(Configuration configuration) {
    }

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();


    public <T> T getMapper(Class<T> type, SqlSession sqlSession){
        final MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory<T>) knownMappers.get(type);
        if(mapperProxyFactory == null) {
            throw new RuntimeException("Type: " + type + " is not known to the MapperRegistry");
        }
        try{
            return mapperProxyFactory.newInstance(sqlSession);
        }catch (Exception e){
            throw new RuntimeException("Error getting mapper instance. Cause: "+ e, e);
        }
    }

    public boolean hasMapper(Class<?> mapperInterface){
        return knownMappers.containsKey(mapperInterface);
    }

    public <T> void addMapper(Class<T> mapperInterface){
        //
        if(mapperInterface.isInterface()){
            if(hasMapper(mapperInterface)){
                throw new RuntimeException("Type: "+ mapperInterface + " is already known to the MapperRegistry");
            }

            // 注册到代理工厂中
            knownMappers.put(mapperInterface, new MapperProxyFactory<>(mapperInterface));
        }
    }


    public void addMappers(String packageName){
       Set<Class<?>> mapperSet = ClassScanner.scanPackage(packageName);
       for(Class<?> mapperClass : mapperSet){
           addMapper(mapperClass);
       }
    }
}
