package dao;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisSqlSession {// mybatis 与 sql的对话，相当于connection

	public static SqlSession sqlSession = null;
	public static SqlSessionFactory sqlSessionFactory = null;
	static {

		String resource = "sqlMapConfig.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

	}

	public static SqlSession getSqlSession() {
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
