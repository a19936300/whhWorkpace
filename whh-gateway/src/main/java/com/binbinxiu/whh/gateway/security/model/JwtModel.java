package com.binbinxiu.whh.gateway.security.model;

import lombok.*;


/**
 * @author wzd
 */
@Getter
@Setter
@NoArgsConstructor
public class JwtModel extends JwtParent{

    private String id;

    private String userName;

    @Builder(toBuilder = true)
    public JwtModel(String id, String userName, long time) {
        super(time);
        this.id = id;
        this.userName = userName;
    }
}
