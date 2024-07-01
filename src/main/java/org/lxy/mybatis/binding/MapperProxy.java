package org.lxy.mybatis.binding;

import org.lxy.mybatis.session.SqlSession;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;

    private SqlSession sqlSession;

    private final Class<T> mapperInterface;


    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 如果是调用的是从Object中继承得到方法，则通过代理对象调用该方法
        if(Object.class.equals(method.getDeclaringClass())){
            return method.invoke(this, args);
        }else {
            return sqlSession.selectOne(method.getName(), args);
        }
    }
}

