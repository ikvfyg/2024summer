package cn.edu.seu.demo.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static String signKey = "seusrtp";
    private static Long expire = 43200000L;//12h

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */
    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey.repeat(100))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){

//        if (jwt != null && jwt.startsWith("Bearer ")) {
//            jwt = jwt.substring(7); // "Bearer "有7个字符
//        }


        return Jwts.parser()
                .setSigningKey(signKey.repeat(100))
                .build()
                .parseClaimsJws(jwt)
                .getBody();

//        } catch (ExpiredJwtException e) {
//            throw new UserNotLoggedInException("登录已过期");
//        } catch (SignatureException e) {
//            throw new UserNotLoggedInException("签名验证失败");
//        } catch (Exception e) {
//            throw new UserNotLoggedInException("登录校验失败");
//        }
    }
}
