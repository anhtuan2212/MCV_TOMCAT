package validate;

import model.Account;
import service.AuthencationService;

import javax.servlet.http.HttpSession;

public class Validate {
    private final AuthencationService acsv = new AuthencationService();
    public  boolean checkacc(Account acc , HttpSession session){
        boolean check = true;
        String regex = ""
                + "(?!.* )"
                + "(?=.*\\d)"
                + "(?=.*[A-Z])"
                +"(?=.*[@$!%*?&])"
                + ".{8,16}";
        String pass = (String) session.getAttribute("pass1");
        if (!pass.matches(regex)){
           check =false;
            session.setAttribute("thongbao","Mật khẩu sai định dạng !");
        }
        if (acsv.checkuser(acc.getUsername())==false) {
            session.setAttribute("thongbao", "Tên bị trùng !");
            check = false;
        }
        return check;
    }
}
