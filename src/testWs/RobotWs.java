package testWs;

public class RobotWs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrimaryApiWs miApi= new PrimaryApiWs();
		
		miApi.login();
		
		miApi.connect();
	}

}
