package com.codve;

import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/14 12:58
 */
public class UserProxy<T> implements InvocationHandler {

    private Class<T> mapper;

    private SqlSession sqlSession;

    public UserProxy(Class<T> mapper, SqlSession sqlSession) {
        this.mapper = mapper;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        List<T> list = sqlSession.selectList(mapper.getCanonicalName() + "." + method.getName());
        return list;
    }
}
