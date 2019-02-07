import java.io.PrintStream;

public class Log {
	public static PrintStream printTo = System.out;
	public static boolean logInput;
	public static void switchPrintStream(PrintStream to) {
		if(to == null) {
			System.out.println("Entered a null PrintStream");
			return;
		}
		printTo.flush();
		printTo.close();
		printTo = to;
	}
	public static void print(String in) {
		printTo.println(in);
		printTo.flush();
	}
}
