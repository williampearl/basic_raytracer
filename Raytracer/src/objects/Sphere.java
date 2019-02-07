package objects;

import java.util.ArrayList;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;
import scene.Color;

public class Sphere extends Shape{
	private double radius;
	public Sphere(Vec3d center, double radius) {
		super(center);
		this.radius = radius;
	}
	
	public Vec3d getNormal(Vec3d p) {
		return getCenter().sub(p).normalize();
	}
	@Override
	public IntersectionInfo intersect(Ray ray) {
		Vec3d m = ray.getOrigin().sub(getCenter());
		double b = m.dot(ray.getDir());
		double c = m.dot(m) - radius * radius;
		if(c > 0.0  && b > 0.0)
			return new IntersectionInfo(ray);
		double disc = b*b - c;
		if(disc < 0)
			return new IntersectionInfo(ray);
		double t = -b - Math.sqrt(disc);
		if(t  < 0.0)
			t = 0;
		return new IntersectionInfo(ray, this, ray.getPoint(t));
	}

	
}
