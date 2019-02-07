package objects;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;

public class Plane extends Shape{

	private Vec3d normal;
	public Plane(Vec3d center, Vec3d normal) {
		super(center);
		this.normal = normal;
	}

	//DONT COMPARE THE CENTER OF THE PLANE, USE THE INTERSECTION POINT AND THE RAY ORIGIN.
	@Override
	public IntersectionInfo intersect(Ray ray) {
		double denom = normal.dot(ray.getDir());
		if(denom >= 1e-6) {
			Vec3d p0l0 = getCenter().sub(ray.getOrigin());
			double t = -p0l0.dot(normal)/denom;
			if(t < 0) {
				return new IntersectionInfo(ray);
			}
			//System.out.println(ray.getPoint(t));
			return new IntersectionInfo(ray, this, ray.getPoint(t));
		}
		return new IntersectionInfo(ray);
	}

	@Override
	public Vec3d getNormal(Vec3d point) {
		double dist1 = getCenter().sub(point).mag();
		if(getCenter().add(normal.mult(1)).sub(point).mag() < dist1)
			return normal;
		else
			return normal.mult(-1);
	}	
	
}
