package cn.ych.tendering.filter;

import cn.ych.tendering.exception.TenderingEnum;
import cn.ych.tendering.exception.TenderingException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    private StringRedisTemplate stringRedisTemplate;
    private List<String> allowUrl;

    public JwtFilter(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        allowUrl = new ArrayList<>();
        allowUrl.add("/admin/login");
        allowUrl.add("/enterprise/login");
        allowUrl.add("/enterprise/sendCode");
        allowUrl.add("/enterprise/register");
        allowUrl.add("/tendering/.*");
        allowUrl.add("/upload");
        allowUrl.add("/open/.*");
        allowUrl.add("/excellent/.*");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {
        for (String s : allowUrl) {
            if(request.getRequestURI().matches(s)){
                return true;
            }
        }
        String token = request.getHeader("token");
        if (token == null) {
            throw new TenderingException(TenderingEnum.REQUEST_INVALID);
        }
        if (stringRedisTemplate.opsForValue().get(token) == null)
            throw new TenderingException(TenderingEnum.TOKEN_INVALID);
        return true;
    }
}
