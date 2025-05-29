package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Account;
import model.Login;

public class AccountsDAO {

    private final String JDBC_URL = "jdbc:h2:file:C:/Users/1Java23/Desktop/work/profile/database";
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

    // 共通コネクション取得メソッド
    private Connection getConnection() throws SQLException {
        loadDriver();
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
    }

    // ResultSet → Account マッピング
    private Account mapRow(ResultSet rs) throws SQLException {
        return new Account(
            rs.getInt("ID"),
            rs.getString("NAME"),
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
                   + "(NAME, BIRTH, ADDRESS, CONTACT,"
                   + " EDUCATION, WORK_HISTORY, TARGETJOB,"
                   + " CERTIFICATIONS, SELF_PR, HOBBIES,"
                   + " DISABILITY, MEDICAL, PHOTO) "
                   + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, account.getName());

            if (account.getBirth() == null || account.getBirth().isEmpty()) {
                ps.setNull(2, Types.DATE);
            } else {
                ps.setDate(2, java.sql.Date.valueOf(account.getBirth()));
            }

            ps.setString(3, account.getAddress());
            ps.setString(4, account.getContact());
            ps.setString(5, account.getEducation());
            ps.setString(6, account.getWorkHistory());
            ps.setString(7, account.getTargetJob());
            ps.setString(8, account.getCertifications());
            ps.setString(9, account.getSelfPR());
            ps.setString(10, account.getHobbies());
            ps.setString(11, account.getDisability());
            ps.setString(12, account.getMedical());
            ps.setString(13, account.getPhotoBase64());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
            	if (rs.next()) {
            		account.setId(rs.getInt(1));
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新
    public void update(Account account) {
        String sql = "UPDATE USERS SET "
                   + "NAME=?, BIRTH=?, ADDRESS=?, CONTACT=?,"
                   + " EDUCATION=?, WORK_HISTORY=?, TARGETJOB=?,"
                   + " CERTIFICATIONS=?, SELF_PR=?, HOBBIES=?,"
                   + " DISABILITY=?, MEDICAL=?, PHOTO=? "
                   + "WHERE ID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getName());

            if (account.getBirth() == null || account.getBirth().isEmpty()) {
                ps.setNull(2, Types.DATE);
            } else {
                ps.setDate(2, java.sql.Date.valueOf(account.getBirth()));
            }

            ps.setString(3, account.getAddress());
            ps.setString(4, account.getContact());
            ps.setString(5, account.getEducation());
            ps.setString(6, account.getWorkHistory());
            ps.setString(7, account.getTargetJob());
            ps.setString(8, account.getCertifications());
            ps.setString(9, account.getSelfPR());
            ps.setString(10, account.getHobbies());
            ps.setString(11, account.getDisability());
            ps.setString(12, account.getMedical());
            ps.setString(13, account.getPhotoBase64());
            ps.setInt(14, account.getId());

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