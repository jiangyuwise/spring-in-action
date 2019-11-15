package com.codve;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/14 16:07
 */
public class DataUtil {

    private SqlSession sqlSession;

    private List<String> scripts = new ArrayList<>();

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public void addScript(String script) {
        scripts.add(script);
    }

    public void init() throws IOException {
        Connection conn = sqlSession.getConnection();
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setAutoCommit(true);
        runner.setFullLineDelimiter(false);
        runner.setDelimiter(";");
        runner.setLogWriter(null);
        for (String script : scripts) {
            InputStream in = Resources.getResourceAsStream(script);
            runner.runScript(new InputStreamReader(in));
        }
    }
}
