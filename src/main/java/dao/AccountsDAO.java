package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountsDAO {

    private final String JDBC_URL = "jdbc:h2:file:C:/Users/1Java23/Desktop/work/ProfileSheet/database/Users";
    private final String DB_USER  = "sa";
    private final String DB_PASS  = "";

    // ドライバロード
    private void loadDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバが見つかりません", e);
        }
    }

    // ★共通コネクション取得メソッド
    private Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    // ResultSet → Account マッピング
    private Account mapRow(ResultSet rs) throws SQLException {
        return new Account(
            rs.getInt("ID"),
            rs.getString("NAME"),
            rs.getString("PASS"),
            rs.getString("BIRTH"),
            rs.getString("ADDRESS"),
            rs.getString("CONTACT"),
            rs.getString("EDUCATION"),
            rs.getString("WORK_HISTORY"),
            rs.getString("TARGETJOB"),
            rs.getString("CERTIFICATIONS"),
            rs.getString("SELF_PR"),
            rs.getString("HOBBIES"),
            rs.getString("DISABILITY"),
            rs.getString("MEDICAL"),
            rs.getString("PHOTO")
        );
    }

    // 新規登録
    public void save(Account account) {
        String sql = "INSERT INTO USERS "
                   + "(ID, NAME, PASS, BIRTH, ADDRESS, CONTACT,"
                   + " EDUCATION, WORK_HISTORY, TARGETJOB,"
                   + " CERTIFICATIONS, SELF_PR, HOBBIES,"
                   + " DISABILITY, MEDICAL, PHOTO) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, account.getId());
            ps.setString(2, account.getName());
            ps.setString(3, account.getPass());

            // ← 空文字または null のときは DATE カラムに null を入れる
            if (account.getBirth() == null || account.getBirth().isEmpty()) {
                ps.setNull(4, Types.DATE);
            } else {
                ps.setDate(4, java.sql.Date.valueOf(account.getBirth()));
            }

            ps.setString(5, account.getAddress());
            ps.setString(6, account.getContact());
            ps.setString(7, account.getEducation());
            ps.setString(8, account.getWorkHistory());
            ps.setString(9, account.getTargetJob());
            ps.setString(10, account.getCertifications());
            ps.setString(11, account.getSelfPR());
            ps.setString(12, account.getHobbies());
            ps.setString(13, account.getDisability());
            ps.setString(14, account.getMedical());
            ps.setString(15, account.getPhotoBase64());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新
    public void update(Account account) {
        String sql = "UPDATE USERS SET "
                   + "NAME=?, PASS=?, BIRTH=?, ADDRESS=?, CONTACT=?,"
                   + " EDUCATION=?, WORK_HISTORY=?, TARGETJOB=?,"
                   + " CERTIFICATIONS=?, SELF_PR=?, HOBBIES=?,"
                   + " DISABILITY=?, MEDICAL=?, PHOTO=? "
                   + "WHERE ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getName());
            ps.setString(2, account.getPass());

            // ← 空文字または null のときは DATE カラムに null を入れる
            if (account.getBirth() == null || account.getBirth().isEmpty()) {
                ps.setNull(3, Types.DATE);
            } else {
                ps.setDate(3, java.sql.Date.valueOf(account.getBirth()));
            }

            ps.setString(4, account.getAddress());
            ps.setString(5, account.getContact());
            ps.setString(6, account.getEducation());
            ps.setString(7, account.getWorkHistory());
            ps.setString(8, account.getTargetJob());
            ps.setString(9, account.getCertifications());
            ps.setString(10, account.getSelfPR());
            ps.setString(11, account.getHobbies());
            ps.setString(12, account.getDisability());
            ps.setString(13, account.getMedical());
            ps.setString(14, account.getPhotoBase64());
            ps.setInt(15, account.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // IDで取得
    public Account findById(int id) {
        String sql = "SELECT * FROM USERS WHERE ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ログイン認証
    public Account findByLogin(Login login) {
        String sql = "SELECT * FROM USERS WHERE ID=? AND PASS=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, login.getId());
            ps.setString(2, login.getPass());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("認証成功");
                    return mapRow(rs);
                } else {
                    System.out.println("認証失敗");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 全ユーザー取得
    public List<Account> findAllUsers() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT ID, NAME, PHOTO FROM USERS";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("ID"));
                a.setName(rs.getString("NAME"));
                a.setPhotoBase64(rs.getString("PHOTO"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

     //指定IDのユーザーが存在するかチェックする
    public boolean exists(int id) {
        String sql = "SELECT COUNT(*) FROM USERS WHERE ID = ?";
        try (Connection conn = getConnection();
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

    // 削除メソッド
    public void deleteUserById(String id) {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}