package com.zt.study.jdbc;

import java.sql.*;

/**
 * jdbc 测试
 */
public class Jdbc {
    public static void main(String[] args) throws Exception{
        testJdbc();
    }
    static void testJdbc() throws Exception{
        try {
            String url = "jdbc:mysql://localhost:3306/choice-scm?useSSL=false&&allowMultiQueries=true" +
                    "&&characterEncoding=UTF-8&&serverTimezone=GMT%2B8\",\"root\",\"zhutong960607";
            //1、注册驱动
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            //Class.forName("com.mysql.jdbc.Driver"); //将类装载到虚拟机中来   也可以用来装载驱动类
            //2、建立连接
            Connection connection = DriverManager.getConnection(url);
            //3、创建语句
            Statement statement = connection.createStatement();
            //4、执行语句
            ResultSet resultSet = statement.executeQuery("select * from scm_plan_order");
            //5、处理结果
            while (resultSet.next()){
                System.out.println(resultSet.getObject(1));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
