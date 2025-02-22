package org.lxy.mybatis.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Resources {

    private static  ClassLoader[] getClassLoaders(){
        return new ClassLoader[]{
                ClassLoader.getSystemClassLoader(),
                Thread.currentThread().getContextClassLoader()
        };
    }

    private static InputStream getResourceAsStream(String resource) throws  IOException{
        ClassLoader[] classLoaders = getClassLoaders();
        for(ClassLoader classLoader : classLoaders){
            InputStream inputStream = classLoader.getResourceAsStream(resource);
            if(null != inputStream){
                return inputStream;
            }
        }
        throw new IOException("Could not find resource " + resource);

    }

    public static Reader getResourceAsReader(String resource) throws IOException{
        return new InputStreamReader(getResourceAsStream(resource));
    }
}
