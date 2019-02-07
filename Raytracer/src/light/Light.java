package light;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;
import scene.Color;

public abstract class Light {
	private Color color;
	private double intensity;
	public abstract IntersectionInfo intersect(Ray ray);
	public Color getColor() {
		return color;
	}
	public void setColor(Color newColor) {
		color = newColor;
	}
	public double getIntensity() {
		return intensity;
	}
	public void setIntensity(double newIntensity) {
		intensity = newIntensity;
	}
	public abstract Vec3d getLightDirection(Vec3d point);
	public abstract Color lightIntensity(Vec3d point);
}
	
