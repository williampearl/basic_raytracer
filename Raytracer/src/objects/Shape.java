package objects;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;

public abstract class Shape {
	private Vec3d center;
	public Shape(Vec3d center) {
		this.center = center;
	}
	
	/**
	 * 
	 * @param ray
	 * @return an array with the intersect points
	 */
	public abstract IntersectionInfo intersect(Ray ray);

	public abstract Vec3d getNormal(Vec3d point);
	
	/**
	 * Returns Double.NEGATIVE_INFINITY if a solution for one spot isnt found
	 * @param a
	 * @param b
	 * @param c
	 * @return
	 */
	public static double[] solveQuadratic(double a, double b, double c) {
		double delta = b*b-4*a*c;
		double[] out = new double[2];
		out[0] = Double.POSITIVE_INFINITY;
		out[1] = Double.POSITIVE_INFINITY;
		if(delta < 0) {
		} else if(delta-1<0.0001) {
			out[0] = -b/(2*a);
		} else {
			double sqrt = Math.sqrt(delta);
			out[0] = (-b + sqrt)/(2*a);
			out[1] = (-b - sqrt)/(2*a);
		}
		return out;
	}
	
	public Vec3d getCenter() {
		return center;
	}
	
	public void setCenter(Vec3d newCenter) {
		center = newCenter;
	}
}
