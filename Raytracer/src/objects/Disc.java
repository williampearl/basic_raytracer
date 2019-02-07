package objects;
import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;

public class Disc extends Plane{
	private double radius;
	public Disc(Vec3d center, Vec3d normal, double radius) {
		super(center, normal);
		this.radius = radius;
	}
	public IntersectionInfo intersect(Ray ray) {
		IntersectionInfo plane = super.intersect(ray);
		if(!plane.foundIntersect()) {
			return plane;
		}
		Vec3d p = plane.getIntersectionPoint();
		//System.out.println(p);
		Vec3d v = p.sub(getCenter());
		//System.out.println(v.mag());
		double d2 = v.dot(v);
		//System.out.println(d2);
		if(d2 <= radius*radius) {
			System.out.println("DISC");
			return new IntersectionInfo(ray, this, p);
		}
		return new IntersectionInfo(ray);
	}
	
}
