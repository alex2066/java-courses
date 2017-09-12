//import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/** Class for launching calculator. Supports inputing from user. */
public class InteractRunner {
/*
	public static void main(String[] arg) {
		Scanner reader = new Scanner(System.in);
		try {
			Calculator calc = new Calculator();
			String exit = "no";
			while ( ! exit.equals("yes")) {
				System.out.println("Enter first arg : ");
				String first = reader.next();
				System.out.println("Enter second arg : ");
				String second = reader.next();
				calc.add(Integer.valueOf(first), Integer.valueOf(second));
				System.out.println("Result : " + calc.getResult());
				calc.cleanResult();
				System.out.println("Exit : yes/no ");
				exit = reader.next();
			}
		} finally {
			reader.close();
		}
	}
*/
	private double firstArgs;
	private double secondArgs;
	private String operation = "+";
	private boolean exit = false;
//	private boolean useResult = false;

//	public Scanner reader = new Scanner(System.in);
	private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private final Calculator calc = new Calculator();

//	public void close() {
//		reader.close();
//	}

	/** логика последовательности вычислений */
	public void go() {
		try {
			while( ! this.exit) {
				if(calc.isResult()) {
					this.firstArgs = calc.getResult();
					enterOperation();
					if ( ! calc.isResult()) {
						continue;
					}
					enterSecondArgs();
				} else {
					enterFirstArgs();
					enterOperation();
					enterSecondArgs();
				}
				if (this.operation.equals("exit")) {
					confirmExit();
				} else if ( ! this.exit) {
					doOperation(this.firstArgs, this.secondArgs, this.operation);
				}
			}
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				reader.close();
			} catch(IOException e) {
				System.err.println(e);
			}
		}
	}

	public void doOperation(double firstArgs, double secondArgs, String operation) {
		double result;
		switch (operation)
		{
		case "+":
		    	result = calc.sum(firstArgs, secondArgs);
			printResult(result);
		break;
		case "-":
		    	result = calc.subtraction(firstArgs, secondArgs);
			printResult(result);
		break;
		case "*":
		    	result = calc.product(firstArgs, secondArgs);
			printResult(result);
		break;
		case "/":
		    	result = calc.division(firstArgs, secondArgs);
			printResult(result);
		break;
		case "^":
		    	result = calc.involution(firstArgs, secondArgs);
			printResult(result);
		break;
		default:
			System.out.println("Unknown operation : " + operation);
			printResult();
		break;
		}
	}

	public void confirmClean() throws IOException {
		while (true) {
			System.out.print("Clean results? (yes/no) : ");
//			String exit = reader.next();
			String exit = reader.readLine();
			if ("yes".equals(exit)) {
				calc.cleanResult();
				break;
			} else if ("no".equals(exit)) {
				printResult();
				break;
			}
		}
	}

	public void enterFirstArgs() throws IOException {
		System.out.print("Enter first args : ");
		while (true) {
			try {
//				this.firstArgs = Double.valueOf(reader.next());
				this.firstArgs = Double.valueOf(reader.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Please enter the number : ");
			}
		}
	}

	public void enterSecondArgs() throws IOException {
		if (this.exit) { return; }
		System.out.print("Enter second args : ");
		while (true) {
			try {
//				this.secondArgs = Double.valueOf(reader.next());
				this.secondArgs = Double.valueOf(reader.readLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Please enter the number : ");
			}
		}
	}

	public void confirmExit() throws IOException {
		while (true) {
			System.out.print("Exit? (yes/no) : ");
//			String exit = reader.next();
			String answer = reader.readLine();
			if ("yes".equals(answer)) {
				this.exit = true;
				break;
			} else if ("no".equals(answer)) {
				if (calc.isResult()) {
					printResult();
				} else {
					System.out.println("First arg : " + this.firstArgs);
				}
				break;
			}
		}
	}

	public void enterOperation() throws IOException {
		String[] operations;
		while ( ! this.exit) {
			if (calc.isResult()) {
				System.out.print("Enter operation +-*/^, clean or exit [" + this.operation + "] : ");
				operations = new String[]{"+", "-", "*", "/", "^", "clean", "exit"};
			} else {
				System.out.print("Enter operation +-*/^ or exit [" + this.operation + "] : ");
				operations = new String[]{"+", "-", "*", "/", "^", "exit"};
			}
	//		String newOperation = reader.next();
			String newOperation = reader.readLine();
			if (newOperation.length() > 0) {
				boolean correctOperation = false;
				for (String op : operations) {
					if (op.equals(newOperation)) {
						correctOperation = true;
						break;
					}
				}
				if ( ! correctOperation) continue;
				if ("clean".equals(newOperation)) {
					confirmClean();
					if ( ! calc.isResult()) {
						break;
					}
				} else if ("exit".equals(newOperation)) {
					confirmExit();
				} else {
					this.operation = newOperation;
					break;
				}
			} else {
				break;
			}
		}
	}

	public void printResult() {
		if (calc.isResult()) {
			printResult(calc.getResult());
		}
	}

	public void printResult(double result) {
		System.out.printf("Result : %.2f\n", result);
	}

}