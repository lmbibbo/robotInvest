package com.luisma.connection;

public class robotito {

	public static void main(String[] args) {
		PrimaryAPI miApi = new PrimaryAPI();

		miApi.login();

		System.out.println(miApi.getSegmentos());
		System.out.println(miApi.getInstrumentos());
		System.out.println(miApi.getInstrumentosDetail("WTIMar18","ROFX"));

		miApi.finalize();

	}

}
