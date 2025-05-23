package model;

public class LoginLogic {
	private static final int ADMIN_ID    = 999;          // 管理者ID
    private static final String ADMIN_PW = "adminPass";  // 管理者PW

	public boolean execute(Login login) {
		return (login.getId() == ADMIN_ID && (ADMIN_PW.equals(login.getPass())));
	}
}
