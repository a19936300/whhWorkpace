package com.binbinxiu.whh.gateway.controller;

import cn.hutool.json.JSONUtil;
import com.binbinxiu.whh.gateway.constant.ResponseVO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author wzd
 */
@Slf4j
@RestController
public class PublicKeyController {
//
//    @Autowired
//    private SysConfigFeign sysConfigFeign;

    String publicRsa = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu6B1jGsK7oG7Rwl+9roxPygYTz20DB9xzN6oZWD8ZaW9Z13dlCUyGeW2XaoMuS7+NQRwUTFruCl3dTH05YRFrBwhLr74lvFLNDtiT45aCli0a/qOT2QF8dqteGHcTghmGqmtakUAiN8smP4WidtqDFpPm/wTn859iDC53mUePWtJ8k863Gj4L5dPi5Lyg+iZayETAohHtK08AiBsPUza8dASfk8FdoYz8czRdyZNbTbzNCjr8jGbmYPx9dS510Y3uR7kIKTD1L8TxkuOlR+ZEShQVw/+pnOz2UeObz06UcUJy3mfnMCNHuqVyrZ7DG3PuNLPXqlrqQHPOP0GcTHLbQIDAQAB";

    public static final String privateRsa = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7oHWMawrugbtHCX72ujE/KBhPPbQMH3HM3qhlYPxlpb1nXd2UJTIZ5bZdqgy5Lv41BHBRMWu4KXd1MfTlhEWsHCEuvviW8Us0O2JPjloKWLRr+o5PZAXx2q14YdxOCGYaqa1qRQCI3yyY/haJ22oMWk+b/BOfzn2IMLneZR49a0nyTzrcaPgvl0+LkvKD6JlrIRMCiEe0rTwCIGw9TNrx0BJ+TwV2hjPxzNF3Jk1tNvM0KOvyMZuZg/H11LnXRje5HuQgpMPUvxPGS46VH5kRKFBXD/6mc7PZR45vPTpRxQnLeZ+cwI0e6pXKtnsMbc+40s9eqWupAc84/QZxMcttAgMBAAECggEBAJtu7nKJKuEzMAZFY5zZ2npLqPfcUg/I7OmfezmhAvbiOuoq5KkZt0uk9VLa0PeIaqdZKgLZQjeqgn/gBtUsottV4ldrdVz2QCrhFyx47TatDap2QvaaEiKt2g9kYrFyW8Oy1Sq4Rx0cZUHJHLrPvibFmjXKuiclVPFCDHikpEyT7AoZvZ87Fe68Y5RdcVaM+e3rKosifytp+LbIEWgkZIzrhTH2nlCsfcgAe2KT6M8HgUkRuXP6paMvNzb4QBIvlx6QDAM4AYt++Bea4m0N7RxB9gQ3nQq/+LL7YBYV3ZE7jE+Pz5hiWUJWo56Gksu20LwMhW1ExSTJ/l8eje5Y0DECgYEA8p8fMJMu0Ovr3M8ll58IgU0PyzRg6buOdxAgDybXc0xVe1AHPMhmRBHlQ38SRfE6+WPkKk3ep9kuDZ0DJ5X57vpj/M3w0VoCrg9eAfSxM/ZbMlK+idDC82duq2vCVfyYH+mvSap6ZKzS7xY7MuT62cYI9Rd08QhlPPTLydqpboMCgYEAxfkGGD0TzbQu75xqT3beBGm2tTnOrALFiZYWlLoDMsqw9O6DKC03MysS3LymkCMXTcx7cPv9Ryot5TkdqTdcHwiEpbWNnjrVIEGeFBYkEo8LuZc8zDTbrpHHSzuBvXbpl20PKdRjnG/6/fz0NlHXR7dQXLZS0KBGs4gptMjlu08CgYAdtI3FDMA2pCwEpPgdUENiUWUVpBwZgfU3bqn+MOtAGqB8sJeyQXcD7oz7bpt2JMrrredJpq8ULesNj6PLPPmU4ONNGInbmAGr/gWsh6EKk6cmyiq6BO1Zj9hk9JSeooadoabshgrmOKK60yX8XTy5XR3LpEuVF0oN5Iru8+66sQKBgFAEdCMt5Kg7ENGGXi3aNKiB9fhP8inyQNd4k43vlUVdSaLmGJsPk4UZ/ti7Y3NUy6GFactj/p5Z/QzuRf/UQWbFsZ+svx3aV7Re8T7bKB9cM/gCme9L2hM4Ft5gofLrckTjLsLQdqaPWFp6eFbQQ3xYKqIei2CJt6nu+jR5EunfAoGAVmdalqDQMybHDtrDJXdeVn580DpV62aFszsc6a0CeHu8y47xLmFhx0gOv/sid3upkH1Ykgq6CGgTSstVky+B8zW1W1pKQKruDHBH5IG2fZxXxBhQn+f4aqYvY7hGzru2TwipVg06QuzZIGdJ6MeKCN7Az90c/IYrIcshMRw9s8U=";

    public static List<String> encryptFields = new ArrayList<>();

    public void init() {
        String value = "";//sysConfigFeign.getValue("base", "encryptFields");
        log.info("加密字段：{}", value);
        putEncryptFields(value);
    }

    private synchronized void putEncryptFields(String value) {
        if (StringUtils.isNotBlank(value) && JSONUtil.isTypeJSON(value)) {
            encryptFields =JSONUtil.toList(value, String.class);
        }
    }

    @Setter
    @Getter
    public static class EncryptConfig implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 公钥
         */
        private String publicKey;

        /**
         * 加密的字段
         */
        private List<String> encryptFields;

        public EncryptConfig(String publicKey, List<String> encryptFields) {
            this.publicKey = publicKey;
            this.encryptFields = encryptFields;
        }
    }

    /**
     * 获取公钥
     *
     * @return
     */
    @PostMapping("getPublicKey")
    public ResponseEntity<ResponseVO> getPublicKey() {
        this.init();
        EncryptConfig config;
        if (encryptFields.isEmpty()) {
            config = new EncryptConfig("", encryptFields);
        } else {
            config = new EncryptConfig(publicRsa, encryptFields);
        }
        return ResponseEntity.ok(ResponseVO.successInstance(config));
    }
}
