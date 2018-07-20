package com.zt.study.jdbc.transction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 事务(数据库提供)
 * 1、原子性（atomicity） :组成事务处理的语句形成了一个逻辑单元，不能只执行其中的一部分。
 * 2、一致性（consistency）:在事务处理之星前后，数据库是一致的（数据库数据完整性约束）。
 * 3、隔离性（isolcation）：一个事务的处理对另一个事务处理的影响
 * 4、持续性（durability）：事务处理的效果能够被永久保存下来
 *
 * connection.setAutoCommit(false);//打开事务----true : 自动提交
 * connection.commit();//提交事务
 * connection.rollback();//回滚事务
 */
public class Transaction {
    public static void main(String[] args) throws Exception{

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
    }
}
