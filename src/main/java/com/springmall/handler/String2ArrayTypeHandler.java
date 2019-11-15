package com.springmall.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.web.servlet.HandlerMapping;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Cats-Fish
 * @version 1.0
 * @date 2019/11/15 16:11
 */
@MappedTypes(String[].class)
public class String2ArrayTypeHandler implements TypeHandler<String[]> {

    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,parseArray2String(strings));
    }

    /**
     * 输入映射 数组内容转为字符串存到数据库中
     * @param strings
     * @return
     */
    public String parseArray2String(String[] strings) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String s = objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        return parseString2Array(resultSet.getString(s));
    }

    /**
     * 输出映射   将字符数据从数据出去变为数组
     * @param string
     * @return
     */
    public String[] parseString2Array(String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        String[] strings = new String[0];
        try {
            strings = objectMapper.readValue(string, String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return strings;
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        return parseString2Array(resultSet.getString(i));
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        return parseString2Array(callableStatement.getString(i));
    }

}
