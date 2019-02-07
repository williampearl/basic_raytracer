package objects;

import scene.Color;

public class Material {
	private double albedo;
	private Color color;
	public Material(Color color, double albedo) {
		this.color = color;
		setAlbedo(albedo);
	}
	public void setAlbedo(double newAlbedo) {
		if(newAlbedo < 0)
			newAlbedo = 0;
		if(newAlbedo > 1)
			newAlbedo = 1;
		albedo = newAlbedo;
	}
	public void setColor(Color newColor) {
		color = newColor;
	}
	public double getAlbedo() {
		return albedo;
	}
	public Color getColor() {
		return color;
	}
	public static Material common() {
		return new Material(Color.random(), 0.18);
	}
	public static Material common(Color newColor) {
		return new Material(newColor, 0.18);
	}
}
