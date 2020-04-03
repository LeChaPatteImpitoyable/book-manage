package com.ying.background.dao;

import com.ying.background.dao.model.ClassInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingsy on 2018/5/28.
 */
@Repository
public class ClassDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_CLASS_INFO_SQL="select * from class_info";

    private final static String GET_CLASS_INFO_SQL = "select * from class_info where class_id = ?";

    public List<ClassInfo> queryAllClassInfo(){
        final List<ClassInfo> classInfos = new ArrayList<ClassInfo>();

        jdbcTemplate.query(QUERY_ALL_CLASS_INFO_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                while (resultSet.next()){
                    ClassInfo classInfo = new ClassInfo();
                    classInfo.setClassId(resultSet.getInt(1));
                    classInfo.setClassName(resultSet.getString(2));
                    classInfos.add(classInfo);
                }
            }
        });
        return classInfos;
    }

    public ClassInfo getBook(int classId){
        final ClassInfo classInfo = new ClassInfo();
        jdbcTemplate.query(GET_CLASS_INFO_SQL, new Object[]{classId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                classInfo.setClassId(resultSet.getInt(1));
                classInfo.setClassName(resultSet.getString(2));
            }

        });
        return classInfo;
    }

}
