package net.yxchen.poorpoolblog.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import net.yxchen.poorpoolblog.annotation.UserLoginToken;
import net.yxchen.poorpoolblog.bean.User;
import net.yxchen.poorpoolblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        Method method = ((HandlerMethod) handler).getMethod();
        if ("options".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        boolean flag = true;
        do {
            if (method.isAnnotationPresent(UserLoginToken.class) && method.getAnnotation(UserLoginToken.class).required()) {
                if (token == null) {
                    flag = false;
                    break;
                }
                token = token.substring(7);
                String userId = JWT.decode(token).getAudience().get(0);
                User user = userService.getUserByUserId(Long.parseLong(userId));
                if (user == null) {
                    flag = false;
                    break;
                }
                JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getUserPassword())).build();
                try {
                    jwtVerifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new RuntimeException("401");
                }
            }
        } while (false);
        if (!flag) {
            response.reset();
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write("{\n" +
                    "  \"code\": 200,\n" +
                    "  \"message\": \"权限不够\",\n" +
                    "  \"data\": {}\n" +
                    "}\n");
            pw.flush();
            pw.close();
            return false;
        } else {
            return true;
        }
    }
}
