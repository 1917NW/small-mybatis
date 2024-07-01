package org.lxy.mybatis.mapping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lxy.mybatis.session.Configuration;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MappedStatement {

    private Configuration configuration;

    // 接口中对应的方法引用
    private String id;

    // SQL类型
    private SqlCommandType sqlCommandType;

    // 参数类型
    private String  parameterType;

    // 返回类型
    private String resultType;

    // 具体sql
    private String sql;

    // 参数Map
    private Map<Integer, String> parameter;




}
