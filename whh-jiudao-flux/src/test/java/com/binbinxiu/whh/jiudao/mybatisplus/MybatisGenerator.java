package com.binbinxiu.whh.jiudao.mybatisplus;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.LikeTable;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MybatisGenerator extends BaseGeneratorTest {
    /**
     * 数据源配置
     */
//    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
//            .Builder("jdbc:mysql://192.168.31.48:3306/yepro?serverTimezone=Asia/Shanghai&characterEncoding=UTF-8", "builder", "builder@123")
//            .schema("yepro")
//            .build();

    private static final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:sqlite://C:/Users/binbin/OneDrive/1项目project/商业小程序学习/jiudou.db", "", "")
            .schema("main")
            .build();

    @Test
    public void testJdbc(){

        try {
            Connection conn =
                    DATA_SOURCE_CONFIG.getConn();
            PreparedStatement preparedStatement = conn.prepareStatement("select * from sqlite_master");
            ResultSet resultSet = preparedStatement.executeQuery();
            String string = resultSet.getString(1);
            System.out.println(string);
            resultSet.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSimple() {
        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder()
                .outputDir("F:\\A-wangbinbin\\workSpace\\whhWorkpace\\whh-jiudao\\src\\main\\java")
                .author("binbin")
                .dateType(DateType.TIME_PACK)
                .fileOverride()
                .commentDate("yyyy-MM-dd")
                .build();
        //包配置
        PackageConfig packageConfig = new PackageConfig.Builder()
                .parent("com.binbinxiu.whh.jiudao")
                .build();
        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .likeTable(new LikeTable("t_book"))
                .addTablePrefix("t_")
                //entity策略
                .entityBuilder()
                .superClass("com.binbinxiu.whh.jiudao.config.BaseEntity")
                .enableLombok()
               // .enableFileOverride()
        //service策略
                .serviceBuilder()
                .formatServiceImplFileName("%sServiceImpl")
               // .enableFileOverride()
                .build();

        generator.global(globalConfig);
        generator.packageInfo(packageConfig);
        generator.strategy(strategyConfig);
        generator.execute();
    }


}
