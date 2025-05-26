package test;

import dao.AccountsDAO;
import model.Account;
import model.Login;

public class AccountsDAOTest {
	public static void main(String[] args) {
//		testFindByLoginOK();
		testFindByLoginNG();
	}
//	public static void testFindByLoginOK() {
//		Login login = new Login(1, "1234");
//		AccountsDAO dao = new AccountsDAO();
//		Account result = dao.findByLogin(login);
//		
//		if (result != null && result.getId().equals(1) && result.getPass().equals("1234") ) {
//			System.out.println("testFindByLoginOK:成功しました");
//		} else {
//			System.out.println("testFindByLoginOK:失敗しました");
//		}
//	}
	
	public static void testFindByLoginNG() {
		Login login = new Login(1, "12345");
		AccountsDAO dao = new AccountsDAO();
		Account result = dao.findByLogin(login);
		if (result == null) {
			System.out.println("testFindByLoginNG:成功しました");
		} else {
			System.out.println("testFindByLoginNG:失敗しました");
		}
	}
}
