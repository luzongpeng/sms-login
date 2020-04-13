package top.lzp.smslogin.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Lu
 * @date 2020/4/13:22:35:14
 * @description
 */
@Component
public class IndexFilter implements Filter {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private HttpSession session;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("拦截!!!"+httpServletRequest.getServletPath());
        //如果没有登录，不允许访问首页
        if(httpServletRequest.getServletPath().equals("/index")){
            //System.out.println("首页");
            if (session.getAttribute("user")!=null){
                //放行
                chain.doFilter(request, response);
                return;
            }else {
                //跳转到登录页面
                request.getRequestDispatcher("/login").forward(request,response);
                return;
            }
        }
        //处理其他请求
        else {
        chain.doFilter(request,response);
        }
    }
}
