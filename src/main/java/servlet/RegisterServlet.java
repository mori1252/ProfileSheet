package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.AccountsDAO;
import model.Account;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountsDAO dao = new AccountsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 登録フォーム表示
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");

        // Account オブジェクト生成（他フィールドは空文字で初期化）
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        account.setPass(pass);
        account.setBirth("");
        account.setAddress("");
        account.setContact("");
        account.setEducation("");
        account.setWorkHistory("");
        account.setTargetJob("");
        account.setCertifications("");
        account.setSelfPR("");
        account.setHobbies("");
        account.setDisability("");
        account.setMedical("");
        account.setPhotoBase64("");

        // 登録
        dao.save(account);

        // 登録完了後にログイン画面へリダイレクト
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }
}
