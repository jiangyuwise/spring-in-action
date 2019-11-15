package com.codve.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/14 20:24
 */
public class Generator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<>();
        boolean overWrite = true;
        InputStream in = Generator.class.getResourceAsStream("/generator/generatorConfig.xml");
        ConfigurationParser parser = new ConfigurationParser(warnings);
        Configuration config = parser.parseConfiguration(in);
        in.close();
        DefaultShellCallback callback = new DefaultShellCallback(overWrite);

        MyBatisGenerator generator = new MyBatisGenerator(config, callback, warnings);
        generator.generate(null);

        for (String warning : warnings) {
            System.out.println(warning);
        }
    }
}
