package cn.ych.tendering.filter;

import cn.ych.tendering.exception.TenderingEnum;
import cn.ych.tendering.exception.TenderingException;
import cn.ych.tendering.utils.JwtUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    private StringRedisTemplate stringRedisTemplate;
    private Set<String> allowUrl;

    public JwtFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        allowUrl = new HashSet<>();
        allowUrl.add("/admin/login");
        allowUrl.add("/enterprise/login");
        allowUrl.add("/enterprise/sendCode");
        allowUrl.add("/enterprise/register");
        allowUrl.add("/upload");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        if (allowUrl.contains(request.getRequestURI()))
            return true;
        String token = request.getHeader("token");
        if (token == null) {
            throw new TenderingException(TenderingEnum.REQUEST_INVALID);
        }
        if (stringRedisTemplate.opsForValue().get(token) == null)
            throw new TenderingException(TenderingEnum.TOKEN_INVALID);
//        if (roles == null) {
//            throw new EryaException(EryaEnum.TOKEN_INVALID);
//        }
//        if (!roles.equals("user") && !roles.equals("all")) {
//            throw new EryaException(EryaEnum.PERMISSION_REFUSED);
//        }
        return true;
    }
}