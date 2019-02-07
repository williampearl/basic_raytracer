package scene;

public class Color {
	private double red;
	private double green;
	private double blue;
	
	public Color(double red, double green, double blue) {
		
		this.red = clamp(red);
		
		this.green = clamp(green);
		
		
		this.blue = clamp(blue);
	}
	public double clamp(double num) {
		if(num > 1)
			return 1;
		if(num < 0)
			return 0;
		return num;
	}
	public Color(int i) {
		i >>= 8;
		red = i & 0xFF;
		i >>= 8;
		green = i & 0xFF;
		i >>= 8;
		blue = i & 0xFF;
	}
	
	public double getRed() {
		return red;
	}
	
	public double getGreen() {
		return green;
	}
	
	public double getBlue() {
		return blue;
	}
	
	public Color mult(double amt) {
		return new Color(red*amt,green*amt,blue*amt);
	}
	public Color mult(Color other) {
		return new Color(red*other.getRed(),green*other.getGreen(),blue*other.getBlue());
	}
	public Color div(double amt) {
		return new Color(red/amt, green/amt, blue/amt);
	}
	public Color add(Color other) {
		return new Color(red + other.getRed(), green + other.getGreen(), blue + other.getBlue());
	}
	public static Color random() {
		return new Color(Math.random(), Math.random(), Math.random());
	}
	public String toString() {
		return String.format("R::%4f G::%4f B::%4f", red,green,blue);
	}
	//some default colors
	public static final Color RED = new Color(1, 0, 0);
	public static final Color GREEN = new Color(0, 1, 0);
	public static final Color BLUE = new Color(0, 0, 1);
	public static final Color BLACK = new Color(0, 0, 0);
	public static final Color WHITE = new Color(1, 1, 1);
	public static final Color GREY = new Color(0.5, 0.5, 0.5);
	
}
