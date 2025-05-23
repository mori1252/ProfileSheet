package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountsDAO {
    private final String JDBC_URL = "jdbc:h2:C:/Users/1Java23/Desktop/work/ProfileSheet/database/Users";
    private final String DB_USER  = "sa";
    private final String DB_PASS  = "";

    // ── 1. 全ユーザー取得 ───────────────────────────────
    public List<Account> findAllUsers() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<Account> list = new ArrayList<>();
        String sql = "SELECT ID, NAME FROM USERS";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql);
             ResultSet rs = pStmt.executeQuery()) {

            while (rs.next()) {
                int id   = rs.getInt("ID");
                String name = rs.getString("NAME");
                list.add(new Account(id, name, null, null, null, null, null, null, null, null));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ── 2. ログイン認証用にID＋パスワードで取得 ────────────────────
    public Account findByLogin(Login login) {
        Account account = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        String sql = "SELECT ID, NAME, PASS, BIRTH, ADDRESS, CONTACT, DISABILITY, MEDICAL, SKILL, TARGETJOB "
                   + "FROM USERS WHERE ID = ? AND PASS = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1,  login.getId());
            pStmt.setString(2,  login.getPass());

            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    int id          = rs.getInt("ID");
                    String name     = rs.getString("NAME");
                    String pass     = rs.getString("PASS");
                    String birth    = rs.getString("BIRTH");
                    String address  = rs.getString("ADDRESS");
                    String contact  = rs.getString("CONTACT");
                    String disability = rs.getString("DISABILITY");
                    String medical  = rs.getString("MEDICAL");
                    String skill    = rs.getString("SKILL");
                    String targetJob = rs.getString("TARGETJOB");

                    account = new Account(
                        id, name, pass, birth, address, contact,
                        disability, medical, skill, targetJob
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    // ── 3. IDで取得 ───────────────────────────────────
    public Account findById(int id) {
        Account account = null;
        String sql = "SELECT * FROM USERS WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, id);
            try (ResultSet rs = pStmt.executeQuery()) {
                if (rs.next()) {
                    String name     = rs.getString("NAME");
                    String pass     = rs.getString("PASS");
                    String birth    = rs.getString("BIRTH");
                    String address  = rs.getString("ADDRESS");
                    String contact  = rs.getString("CONTACT");
                    String disability = rs.getString("DISABILITY");
                    String medical  = rs.getString("MEDICAL");
                    String skill    = rs.getString("SKILL");
                    String targetJob = rs.getString("TARGETJOB");

                    account = new Account(
                        id, name, pass, birth, address, contact,
                        disability, medical, skill, targetJob
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    // ── 4. ID 存在チェック ───────────────────────────────
    public boolean exists(int id) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ── 5. 新規登録 ──────────────────────────────────
    public void save(Account account) {
        String sql = "INSERT INTO USERS "
                   + "(ID, NAME, PASS, BIRTH, ADDRESS, CONTACT, DISABILITY, MEDICAL, SKILL, TARGETJOB) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, account.getId());
            ps.setString(2, account.getName());
            ps.setString(3, account.getPass());
            ps.setString(4, account.getBirth());
            ps.setString(5, account.getAddress());
            ps.setString(6, account.getContact());
            ps.setString(7, account.getDisability());
            ps.setString(8, account.getMedical());
            ps.setString(9, account.getSkill());
            ps.setString(10, account.getTargetJob());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ── 6. 更新 ────────────────────────────────────
    public void update(Account account) {
        String sql = "UPDATE USERS SET "
                   + "NAME=?, PASS=?, BIRTH=?, ADDRESS=?, CONTACT=?, "
                   + "DISABILITY=?, MEDICAL=?, SKILL=?, TARGETJOB=? "
                   + "WHERE ID = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getName());
            ps.setString(2, account.getPass());
            ps.setString(3, account.getBirth());
            ps.setString(4, account.getAddress());
            ps.setString(5, account.getContact());
            ps.setString(6, account.getDisability());
            ps.setString(7, account.getMedical());
            ps.setString(8, account.getSkill());
            ps.setString(9, account.getTargetJob());
            ps.setInt(10, account.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
