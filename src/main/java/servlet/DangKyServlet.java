package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.AuthencationService;
import validate.Validate;

@WebServlet("/register")
public class DangKyServlet extends HttpServlet {
	private final AuthencationService acsv = new AuthencationService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/Singup.jsp").forward(req, resp);
	}

	@Override        
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		session.removeAttribute("thongbao");
		session.removeAttribute("pass1");
		String name = req.getParameter("fullname");
		String user = req.getParameter("username");
		String pass1 = req.getParameter("password1");
		String pass2 = req.getParameter("password2");
		session.setAttribute("pass1",pass1);
		Account ac = new Account();
		ac.setFullname(name);
		ac.setUsername(user);
		ac.setPassword(pass1);
		ac.setRole(1);
        Validate vali = new Validate();
		String trong ="";
		String tbvali = (String) session.getAttribute("thongbao");
		String tb = ("Hãy điền đầy đủ các trường thông tin !");
		if (name.equals(trong)==true || user.equals(trong)==true ||pass1.equals(trong)==true ||pass2.equals(trong)==true){
			req.setAttribute("thongbao", tb);
			req.getRequestDispatcher("/WEB-INF/view/Singup.jsp").forward(req, resp);
		}else {
			if (vali.checkacc(ac ,session)==true){
					acsv.singup(ac);
					req.getRequestDispatcher("/WEB-INF/view/Login.jsp").forward(req, resp);
			}else {
				req.setAttribute("thongbao", tbvali);
				req.getRequestDispatcher("/WEB-INF/view/Singup.jsp").forward(req, resp);
			}
		}

	}
}
