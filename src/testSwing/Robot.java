package testSwing;

import com.luisma.connection.PrimaryAPI;

public class Robot {

	public static void main(String[] args) {

		PrimaryAPI miApi = new PrimaryAPI();
		
		miApi.login();
		
		System.out.println(miApi.getSegmentos());
		System.out.println(miApi.getInstrumentos());
		System.out.println(miApi.getInstrumentosDetail("WTIMar18","ROFX"));

		miApi.finalize();
	}

}
