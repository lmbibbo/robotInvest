package test;

public class Robot {

	public static void main(String[] args) {

		PrimaryAPI miApi = new PrimaryAPI();
		
		miApi.login();
		
		System.out.println(miApi.getSegmentos());
		System.out.println(miApi.getInstrumentos());
		
		miApi.finalize();
	}

}
