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
        // --- 1) ID取得 ---
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

        // --- 2) DAO から取得 ---
        Account account = dao.findById(id);
        if (account == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "ユーザーが見つかりません");
            return;
        }

        // --- 3) リクエスト属性にセットして JSP へ ---
        request.setAttribute("account", account);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profile.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        // --- 1) セッションから ID 取得 ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("profileId") == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "セッションが無効です");
            return;
        }
        int id = (Integer) session.getAttribute("profileId");

        // --- 2) フォームパラメータを Account に詰め替え ---
        Account account = new Account();
        account.setId(id);
        account.setName(request.getParameter("name"));
        // パスワードは画面に出していない場合は削除、または必要ならフォームに追加してください
//        account.setPass(request.getParameter("pass"));
        account.setBirth(request.getParameter("birth"));
        account.setAddress(request.getParameter("address"));
        account.setContact(request.getParameter("contact"));
        account.setEducation(request.getParameter("education"));
        account.setWorkHistory(request.getParameter("workHistory"));
        account.setTargetJob(request.getParameter("targetJob"));
        account.setCertifications(request.getParameter("certifications"));
        account.setSelfPR(request.getParameter("selfPR"));
        account.setHobbies(request.getParameter("hobbies"));
        account.setDisability(request.getParameter("disability"));
        account.setMedical(request.getParameter("medical"));
        String photoBase64 = request.getParameter("photoBase64");
        if (photoBase64 == null || photoBase64.isEmpty()) {
            Account old = dao.findById(id);
            if (old != null) {
                photoBase64 = old.getPhotoBase64();
            }
        }
        account.setPhotoBase64(photoBase64);

        // --- 3) INSERT or UPDATE ---
        if (dao.exists(id)) {
            dao.update(account);
        } else {
            dao.save(account);
        }

        // --- 4) リダイレクトして GET を再実行 ---
        response.sendRedirect(request.getContextPath() + "/Profile");
    }
}
