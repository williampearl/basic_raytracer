package light;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;
import scene.Color;

public class PointLight extends Light{
	private Vec3d position;

	public PointLight(Vec3d position, Color color, double intensity) {
		setColor(color);
		setIntensity(intensity);
		this.position = position;
	}
	
	public Color lightIntensity(Vec3d point) {
		Vec3d lightDir = position.sub(point);
		double r2 = lightDir.dot(lightDir);
		return getColor().mult(getIntensity()/(4*Math.PI*r2));
	}
	public Vec3d getLightDirection(Vec3d point) {
		return position.sub(point).normalize();
	}
	@Override
	public IntersectionInfo intersect(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
