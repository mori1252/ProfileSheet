package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;

@WebServlet("/Profile")
public class ProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountsDAO dao = new AccountsDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) URLパラメータ or セッションからIDを取得
        String idStr = request.getParameter("id");
        HttpSession session = request.getSession();
        int id;
        if (idStr != null) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "不正なIDです");
                return;
            }
            session.setAttribute("profileId", id);
        } else {
            Object o = session.getAttribute("profileId");
            if (o == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "IDが指定されていません");
                return;
            }
            id = (Integer) o;
        }

        // 2) DAO から取得
        Account account = dao.findById(id);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ユーザーが見つかりません");
            return;
        }

        // 3) JSP にセットして表示
        request.setAttribute("user", account);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 1) セッションから ID 取得
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("profileId") == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "セッションが無効です");
            return;
        }
        int id = (Integer) session.getAttribute("profileId");

        // 2) フォームの内容を Account に詰め替え
        Account account = new Account();
        account.setId(id);
        account.setName(request.getParameter("name"));
        account.setPass(request.getParameter("pass"));
        account.setBirth(request.getParameter("birth"));
        account.setAddress(request.getParameter("address"));
        account.setContact(request.getParameter("contact"));
        account.setDisability(request.getParameter("disability"));
        account.setMedical(request.getParameter("medical"));
        account.setSkill(request.getParameter("skill"));
        account.setTargetJob(request.getParameter("targetJob"));

        // 3) INSERT or UPDATE
        if (dao.exists(id)) {
            dao.update(account);
        } else {
            dao.save(account);
        }

        // 4) 再読み込み
        response.sendRedirect(request.getContextPath() + "/Profile");
    }
}
