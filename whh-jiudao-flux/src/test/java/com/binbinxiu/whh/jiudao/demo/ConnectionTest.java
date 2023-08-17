package com.binbinxiu.whh.jiudao.demo;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.h2.H2ConnectionOption;
import io.r2dbc.spi.ConnectionFactoryOptions;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {
        ConnectionFactoryOptions.builder().option(H2ConnectionOption.DB_CLOSE_DELAY,"1").build();
    }
}
