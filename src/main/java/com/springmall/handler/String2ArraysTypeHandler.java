package com.springmall.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String[].class)
public class String2ArraysTypeHandler implements TypeHandler<String[]> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, String[] strings, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i,Array2String(strings));
    }

    @Override
    public String[] getResult(ResultSet resultSet, String s) throws SQLException {
        String string = resultSet.getString(s);
        return String2Array(string);
    }

    @Override
    public String[] getResult(ResultSet resultSet, int i) throws SQLException {
        String string = resultSet.getString(i);
        return String2Array(string);
    }

    @Override
    public String[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        String string = callableStatement.getString(i);
        return String2Array(string);
    }

    private String Array2String(String[] strings){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(strings);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
    private String[] String2Array(String string){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(string, String[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
