package org.lxy.mybatis.builder.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.lxy.mybatis.builder.BaseBuilder;
import org.lxy.mybatis.io.Resources;
import org.lxy.mybatis.session.Configuration;
import org.xml.sax.InputSource;


import java.io.Reader;
import java.util.List;

public class XMLConfigBuilder extends BaseBuilder {
    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 调用父类构造方法
        super(new Configuration());
        
        // 2. dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try{
            Document document = saxReader.read(new InputSource(reader));
            Element rootElement = document.getRootElement();
        }catch (DocumentException e){
            e.printStackTrace();
        }
    }

    private void mapperElement(Element mappers) throws Exception{
        List<Element> mapperList = mappers.elements("mapper");
        for(Element e: mapperList){
            String resource = e.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(new InputSource(reader));
            Element root = document.getRootElement();

            // 获取命名空间
            String namespace = root.attributeValue("namespace");

            // SELECT
            handleSelect(root.elements("select"));

            // TODO: UPDATE

            // TODO: DELETE

            // TODO: INSERT

        }
    }

    private void handleSelect(List<Element> selectNodes){
        for(Element node: selectNodes){
            String id = node.attributeValue("id");

        }
    }

    public Configuration parse() {
        try {
            // 解析映射器
            mapperElement(root.element("mappers"));
        } catch (Exception e) {
            throw new RuntimeException("Error parsing SQL Mapper Configuration. Cause: " + e, e);
        }
        return configuration;
    }
}
