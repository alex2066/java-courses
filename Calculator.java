/**
	Class realized calculation
*/
public class Calculator {
	/** Execution result */
//	private int result;
	private double result;
	private boolean useResult = false;

	public void add(int ... params) {
		for (Integer param : params) {
			this.result += param;
		}
	}

	public double sum (double firstArgs, double secondArgs) {
		double newResult = firstArgs + secondArgs;
		if ( Double.isFinite(newResult)) {
			this.result = newResult;
			this.useResult = true;
		} else {
			this.useResult = false;
		}
		return newResult;
	}

	public double subtraction (double firstArgs, double secondArgs) {
		double newResult = firstArgs - secondArgs;
		if ( Double.isFinite(newResult)) {
			this.result = newResult;
			this.useResult = true;
		} else {
			this.useResult = false;
		}
		return newResult;
	}

	public double product (double firstArgs, double secondArgs) {
		double newResult = firstArgs * secondArgs;
		if ( Double.isFinite(newResult)) {
			this.result = newResult;
			this.useResult = true;
		} else {
			this.useResult = false;
		}
		return newResult;
	}

	public double division (double firstArgs, double secondArgs) {
		double newResult = firstArgs / secondArgs;
		if ( Double.isFinite(newResult)) {
			this.useResult = true;
		} else {
			this.useResult = false;
		}
		return newResult;
	}

	public double involution(double firstArgs, double secondArgs) {
		double newResult = Math.pow(firstArgs, secondArgs);
		if ( Double.isFinite(newResult)) {
			this.result = newResult;
			this.useResult = true;
		} else {
			this.useResult = false;
		}
		return newResult;
	}

	public double getResult() {
		return this.result;
	}

	public void cleanResult() {
		this.result = 0.0;
		this.useResult = false;
	}

	public boolean isResult() {
		return this.useResult;
	}
}