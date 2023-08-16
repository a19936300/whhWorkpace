package com.binbinxiu.whh.jiudao.data.classic;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2FileStorageExample {
    public static void main(String[] args) {
        try {
            // 连接到H2数据库（文件模式），存储到指定路径
            String dbUrl = "jdbc:h2:D:\\WorkSpace\\springworkspace\\whhWorkpace\\whh-jiudao-flux\\jiudaodb\\file;DB_CLOSE_DELAY=-1";
            Connection connection = DriverManager.getConnection(dbUrl, "root", "root");

            // 创建表格
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(255))";
            statement.executeUpdate(createTableSQL);

            // 插入数据
            String insertDataSQL = "INSERT INTO users (id, name) VALUES (1, 'Alice'), (2, 'Bob')";
            statement.executeUpdate(insertDataSQL);

            // 关闭连接
            statement.close();
            connection.close();

            System.out.println("操作成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testR2dbcTest(){
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                .option(ConnectionFactoryOptions.DRIVER,"h2")
                .option(ConnectionFactoryOptions.PROTOCOL, "file")  // file, mem
                .option(ConnectionFactoryOptions.HOST, "r2dbc:h2:file:///d:/WorkSpace/pringworkspace/whhWorkpace/whh-jiudao-flux/jiudaodb/file")
                .option(ConnectionFactoryOptions.USER, "root")
                .option(ConnectionFactoryOptions.PASSWORD, "root")
                //.option(ConnectionFactoryOptions.DATABASE, "…")
                .build();
        ConnectionFactory connectionFactory = ConnectionFactories.get(options);

        Publisher<? extends io.r2dbc.spi.Connection> publisher = connectionFactory.create();
        Mono<? extends io.r2dbc.spi.Connection> from = Mono.from(publisher);
    }
}
