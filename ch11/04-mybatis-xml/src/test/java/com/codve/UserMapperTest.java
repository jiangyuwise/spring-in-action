package com.codve;

import com.codve.mapper.UserMapper;
import com.codve.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author admin
 * @date 2019/11/13 19:08
 */
class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    private SqlSession sqlSession;

    private UserMapper userMapper;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static DataUtil dataUtil;

    @BeforeAll
    static void setUpAll() throws IOException {
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        dataUtil = new DataUtil();
        dataUtil.addScript("data.sql");
    }

    @BeforeEach
    void setUp() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
        dataUtil.setSqlSession(sqlSession);
        dataUtil.init();
    }

    @AfterEach
    public void teardown() {
        sqlSession.close();
    }

    @Test
    public void insertTest() {
        User user = new User("Robot", new Date().getTime());
        userMapper.insert(user);
    }

    @Test
    public void findByIdTest() {
        User user = userMapper.findById(1L);
        assertNotNull(user);
    }

    @Test
    public void deleteTest() {
        int result = userMapper.delete(1L);
        assertEquals(1, result);
    }

    @Test
    public void findByParamsTest() throws ParseException {
        Date date = dateFormat.parse("1995-1-1");
        String name = "%" + "j" + "%";
        List<User> userList = userMapper.findByParams(name, date.getTime());
        assertTrue(userList.size() > 0);
        userList.forEach(e -> System.out.println(e.toString()));
    }

    /**
     * 动态 SQL, 使用 if 拼接
     */
    @Test
    public void selectIf1() {
        List<User> userList = userMapper.selectIf(null, null);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void selectIf2() {
        List<User> userList = userMapper.selectIf("j", null);
        assertTrue(userList.size() > 0);
    }

    @Test
    public void selectIf3() throws ParseException {
        Date date = dateFormat.parse("1996-1-1");
        List<User> userList = userMapper.selectIf("j", date.getTime());
        assertTrue(userList.size() > 0);
    }

    @Test
    public void selectIf4() throws ParseException {
        Date date = dateFormat.parse("2001-1-1");
        List<User> userList = userMapper.selectIf("j", date.getTime());
        assertEquals(0, userList.size());
    }

    @Test
    public void updateIf1() {
        User user = userMapper.findById(1L);
        user.setName("Helen");
        int result = userMapper.updateIf(user);
        assertEquals(1, result);
    }

    // insert into `user` ( ) values ( )
    @Test
    public void insertIf1() {
        User user = new User();
        userMapper.insertIf(user);
    }

    // insert into `user` ( `user_name` ) values ( ? )
    @Test
    public void insertIf2() {
        User user = new User();
        user.setName("Hank");
        userMapper.insertIf(user);
    }

    // insert into `user` ( `user_name` , `user_birthday` ) values ( ? , ? )
    @Test
    public void insertIf3() {
        User user = new User("James", System.currentTimeMillis());
        userMapper.insertIf(user);
        assertTrue(user.getId() > 0);
    }

    // select * from `user` where 1 = 1 and 1 = 2
    @Test
    public void selectChoose1() {
        List<User> userList = userMapper.selectChoose(null, null, null);
        assertEquals(0, userList.size());
    }

    // select * from `user` where 1 = 1 and `user_id` = ?
    @Test
    public void selectChoose2() {
        List<User> userList = userMapper.selectChoose(1L, null, null);
        assertEquals(1, userList.size());
    }

    // select * from `user` where 1 = 1 and `user_name` like concat('%', ?, '%')
    @Test
    public void selectChoose3() {
        List<User> userList = userMapper.selectChoose(null, "a", null);
        assertTrue(userList.size() > 0);
    }

    // select * from `user` where 1 = 1 and `user_birthday` >= ?
    @Test
    public void selectChoose4() throws ParseException {
        Date date = dateFormat.parse("1996-1-1");
        List<User> userList = userMapper.selectChoose(null, null, date.getTime());
        assertTrue(userList.size() > 0);
    }

    // select * from `user`
    @Test
    public void selectWhere1() {
        List<User> userList = userMapper.selectWhere(new User());
        assertTrue(userList.size() > 0);
    }

    // select * from `user` WHERE `user_name` like concat('%', ?, '%')
    @Test
    public void selectWhere2() {
        List<User> userList = userMapper.selectWhere(new User("J", null));
        assertTrue(userList.size() > 0);
    }

    @Test
    public void selectWhere3() throws ParseException {
        Date date = dateFormat.parse("1995-8-7");
        List<User> userList = userMapper.selectWhere(new User(null, date.getTime()));
        assertTrue(userList.size() > 0);
    }

    // update `user` SET `user_id` = ? where `user_id` = ?
    @Test
    public void updateSet1() {
        User user = new User();
        user.setId(1L);
        int result = userMapper.updateSet(user);
        assertEquals(1, result);
    }

    // update `user` SET `user_name` = ?, `user_id` = ? where `user_id` = ?
    @Test
    public void updateSet2() {
        User user = new User();
        user.setId(1L);
        user.setName("Alice");
        int result = userMapper.updateSet(user);
        assertEquals(1, result);
    }

    @Test
    public void updateSet3() {
        User user = new User("Alice", System.currentTimeMillis());
        user.setId(1L);
        int result = userMapper.updateSet(user);
        assertEquals(1, result);
    }

    @Test
    public void selectForeach() {
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<User> userList = userMapper.selectForeach(ids);
        assertTrue(userList.size() > 0);
    }

    // insert into `user` (`user_name`, `user_birthday`) values (?, ?) , (?, ?)
    @Test
    public void insertForeach() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Cathy", System.currentTimeMillis()));
        userList.add(new User("Angelica", System.currentTimeMillis()));
        assertEquals(2, userMapper.insertForeach(userList));
    }

    // 批量插入并批量返回主键, 仅支持 mysql
    @Test
    public void insertForeach2() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Cathy", System.currentTimeMillis()));
        userList.add(new User("Angelica", System.currentTimeMillis()));

        int count = userMapper.insertForeach2(userList);
        assertEquals(2, count);

        boolean result = userList.stream().allMatch(e -> e.getId() > 0);
        assertTrue(result);
    }

    @Test
    public void updateForeach() {
        Map<String, Object> params = new HashMap<>();
        params.put("user_id", 1L);
        params.put("user_name", "哈哈哈");
        params.put("user_birthday", System.currentTimeMillis());

        int result = userMapper.updateForeach(params);
        assertEquals(1, result);
    }

}