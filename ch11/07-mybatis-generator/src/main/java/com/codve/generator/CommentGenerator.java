package com.codve.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

/**
 * @author admin
 * @date 2019/11/15 14:47
 */
public class CommentGenerator extends DefaultCommentGenerator {

    private boolean suppressAllComments;

    private boolean addRemarkComments;

    @Override
    public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        suppressAllComments = isTrue(properties.getProperty(
                PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS
        ));
        addRemarkComments = isTrue(properties.getProperty(
                PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS
        ));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        field.addJavaDocLine("/**");
        // 获取数据库字段的备注信息
        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        // 输出原始数据库字段名
        field.addJavaDocLine(" * " + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(" */");
    }
}
