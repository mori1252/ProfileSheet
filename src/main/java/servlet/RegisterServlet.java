package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
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
        // GET リクエスト時は新規登録フォーム（register.jsp）を表示
        request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
               .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // パラメータ取得: ID とパスワードは不要
        String name = request.getParameter("name");
        String birth = request.getParameter("birth");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        String education = request.getParameter("education");
        String workHistory = request.getParameter("workHistory");
        String targetJob = request.getParameter("targetJob");
        String certifications = request.getParameter("certifications");
        String selfPR = request.getParameter("selfPR");
        String hobbies = request.getParameter("hobbies");
        String disability = request.getParameter("disability");
        String medical = request.getParameter("medical");
        String photoBase64 = ""; // 今は空文字固定

        // Account オブジェクトにセット（IDはDAO側で自動採番）
        Account account = new Account();
        account.setName(name);
        account.setPass("");            // パスワード不要なら空文字
        account.setBirth(birth);
        account.setAddress(address);
        account.setContact(contact);
        account.setEducation(education);
        account.setWorkHistory(workHistory);
        account.setTargetJob(targetJob);
        account.setCertifications(certifications);
        account.setSelfPR(selfPR);
        account.setHobbies(hobbies);
        account.setDisability(disability);
        account.setMedical(medical);
        account.setPhotoBase64(photoBase64);

        // 登録／更新
        dao.save(account);
        // 登録処理後
        request.setAttribute("registerSuccess", true);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
        rd.forward(request, response);
        // 登録完了後、一覧画面へリダイレクト
        response.sendRedirect(request.getContextPath() + "/LoginServlet");
    }
}