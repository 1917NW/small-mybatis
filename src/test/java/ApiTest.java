import dao.IUserDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.lxy.mybatis.binding.MapperProxyFactory;
import org.lxy.mybatis.binding.MapperRegistry;
import org.lxy.mybatis.session.SqlSession;
import org.lxy.mybatis.session.SqlSessionFactory;
import org.lxy.mybatis.session.defaults.DefaultSqlSessionFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiTest {


    @Test
    public void test_MapperProxyFactory02() {
        // 1. 注册 Mapper
        MapperRegistry registry = new MapperRegistry();
        registry.addMappers("dao");

        // 2. 从 SqlSession 工厂获取 Session
        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(registry);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 3. 获取映射器对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        // 4. 测试验证
        String res = userDao.queryUserName("10001");
        log.info("测试结果：{}", res);
    }


}
