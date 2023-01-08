package com.binbinxiu.whh.gateway.constant.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.binbinxiu.whh.gateway.security.model.JwtModel;
import com.binbinxiu.whh.gateway.security.model.JwtParent;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * jwt工具类
 *
 * @author wzd
 */
@Slf4j
public class JwtUtil {

    private static String k;

    static {
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        try {
            yaml.setResources(new ClassPathResource("/cpic/cpicapp/conf/application.yml"));
            Properties properties = yaml.getObject();
            Object obj = properties.get("jwt.key");
            if (obj != null) {
                k = (String) properties.get("jwt.key");
                log.info("key:{}", k);
            } else {
                k = "Cpic2020wzd";
            }
        }catch (Exception e){
            log.error(e.getMessage());
            k = "Cpic2020wzd";
        }

    }

    private JwtUtil() {
    }

    public static final String ISSUER = "www.cpic.com";

    public static final String ID = "id";

    public static final String USER_NAME = "userName";

    /**
     * 由字符串生成加密key
     *
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decodeBase64(k);
        SecretKeySpec key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 创建jwt
     *
     * @param ttlMillis
     * @return
     * @throws Exception
     */
    public static String createJWT(JwtParent jwtModel, long ttlMillis) throws Exception {

        // 指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 生成签名的时候使用的秘钥secret,一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        // 下面就是在为payload添加各种标准声明和私有声明了
        // 这里其实就是new一个JwtBuilder，设置jwt的body
        // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
        // setId 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
        // setIssuedAt(iat): jwt的签发时间
        // setIssuer(issuer)：jwt签发人
        // signWith 设置签名使用的签名算法和签名使用的秘钥
        Map<String,Object> map = MyBeanUtils.objectToMap(jwtModel);

        JWT jwt = JWT.create()
                .addPayloads(map)
                .setIssuedAt(now)
                .setIssuer(ISSUER);
        if(ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            jwt.setExpiresAt(exp);
        }

        return  jwt.sign(JWTSignerUtil.hs256(Base64.decodeBase64(k)));
//
//        JwtBuilder builder = Jwts.builder()
//                .setClaims(map)
//                //.setId(id)
//                .setIssuedAt(now)
//                .setIssuer(ISSUER)
//                .signWith(signatureAlgorithm, key);
//
//        // 设置过期时间
//        if (ttlMillis >= 0) {
//            long expMillis = nowMillis + ttlMillis;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
//        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static JWTPayload parseJWT(String jwtToken) throws SignatureException, ExpiredJwtException {
        JWT jwt = JWTUtil.parseToken(jwtToken);

        // 得到DefaultJwtParser
        // 设置签名的秘钥
        // 设置需要解析的jwt
        return jwt.getPayload();
    }

    /**
     * 检查token
     *
     * @param jwtToken
     * @return
     * @throws SignatureException
     */
    public static JwtParent checkToken(String jwtToken) throws SignatureException, ExpiredJwtException {
        JWTPayload jwtPayload = JwtUtil.parseJWT(jwtToken);
        Integer iat = (Integer) jwtPayload.getClaim("iat");
        JwtParent model = new JwtModel((String) jwtPayload.getClaim("id"), (String) jwtPayload.getClaim("userName"),iat);;
//        if(model == null){
//            model = MyBeanUtils.mapToObject(claims, SSOJwtModel.class);
//        }
//        if(model == null){
//            model = MyBeanUtils.mapToObject(claims, CustomerJwtModel.class);
//        }
//        if(model == null){
//            model = MyBeanUtils.mapToObject(claims, TelJwtModel.class);
//        }
        /*String userId = (String) claims.get(ID);
        String userName = (String) claims.get(USER_NAME);
        long expireTime = claims.getExpiration().getTime();
        JwtModel jwtModel = new JwtModel(userId, userName, expireTime);*/
        return model;
    }

   /**/ public static void main(String[] args) throws Exception {
       JwtParent jwtParent = JwtUtil.checkToken("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ3d3cuY3BpYy5jb20iLCJpZCI6IjY2IiwidXNlck5hbWUiOiI3MTc2IiwiZXhwIjoxNjcxNDM0MjA0LCJpYXQiOjE2NzE0MzI0MDR9.2-pBiI88nwZqLVkoliIU3yn4JNn44NF__TC6ig3zQak");
       long l = jwtParent.getTime() - System.currentTimeMillis();
       System.out.println(l/1000/60);
   }


}
