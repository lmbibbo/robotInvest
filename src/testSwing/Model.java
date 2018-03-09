package testSwing;

public class Model {	
	
	private int counter;	

	public Model(){
		System.out.println("Model()");
	} //Model()

	public void incrementValue() {
		++counter;
		System.out.println("Model     : counter = " + counter);
	} //incrementValue()
	
	public void decrementValue() {
		--counter;
		System.out.println("Model     : counter = " + counter);
	} //incrementValue()

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
		System.out.println("Model init: counter = " + counter);
	}
}