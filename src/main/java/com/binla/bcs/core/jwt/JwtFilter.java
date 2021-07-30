package com.binla.bcs.core.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.binla.bcs.domain.constants.SecurityConstant;
import com.binla.bcs.service.IAuthService;
import com.binla.bcs.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    private IAuthService authService;
    public JwtFilter(IAuthService authService){
        this.authService = authService;
    }
    /**
     * 登录认证
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginAttempt(request, response))
            return executeLogin(request, response);
        return false;
    }

    /**
     * 判断用户是否是登入,检测headers里是否包含token字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(SecurityConstant.AUTH_HEADER);
        return token != null;
    }

    /**
     * 执行登录
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        boolean result = false;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String token = httpServletRequest.getHeader(SecurityConstant.AUTH_HEADER);
        try {
            JwtToken jwtToken = new JwtToken(token);
            getSubject(request, response).login(jwtToken);
            result = true;
        } catch (Exception e) {
            Throwable throwable = e.getCause();
            if (throwable instanceof TokenExpiredException){
                try{
                    String userCode = JwtUtil.getUserCode(token);
                    token = authService.authLogin(userCode);
                    result = true;
                }catch (Exception ignored){}
            }
        }
        //将token装入返回消息中的header里
        httpServletResponse.setHeader("Access-Control-Expose-Headers", SecurityConstant.AUTH_HEADER);
        httpServletResponse.setHeader(SecurityConstant.AUTH_HEADER, token);
        return result;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
