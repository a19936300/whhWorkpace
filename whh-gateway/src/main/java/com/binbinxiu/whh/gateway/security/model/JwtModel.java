package com.binbinxiu.whh.gateway.security.model;

import lombok.Getter;
import lombok.Setter;


/**
 * @author wzd
 */
@Getter
@Setter
public class JwtModel extends JwtParent{

    private String id;

    private String userName;

    public JwtModel(){}

    public JwtModel(String id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public JwtModel(String id, String userName, long time) {
        this.id = id;
        this.userName = userName;
        this.time = time;
    }
}
