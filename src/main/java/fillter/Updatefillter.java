package fillter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Account;
import org.hibernate.Session;

@WebFilter("/update")
public class Updatefillter implements Filter  {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        // Lấy Role của Người đăng nhập
        HttpSession session = req.getSession();
        Integer rl = (Integer) session.getAttribute("role");
        if (rl==null) { // Đúng code cho đi tiếp
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append("<h1 style=\"text-align: center; color: red;\">Bạn cần <a href=\"/PH14249/login\">đăng nhập</a> để truy cập trang web này !</h1>");
        } else if (rl==1) {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().append("<h1 style=\"text-align: center; color: red;\">Bạn cần <a href=\"/PH14249/login\">đăng nhập</a> với quyển quản trị để truy cập trang web này !</h1>");
        } else { // Không đúng cho về

            chain.doFilter(request, response); // đi tiếp
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
