package net.yxchen.poorpoolblog.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import net.yxchen.poorpoolblog.bean.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    //根据 userId 和 userPassword 获得 token
    public String getToken(User user) {
        Date start = new Date();
        long futureTime = System.currentTimeMillis() + 24 * 60 * 60 * 1000;
        Date end = new Date(futureTime);
        return JWT.create().withAudience(user.getUserId().toString())
                .withIssuedAt(start)
                .withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getUserPassword()));
    }
}
