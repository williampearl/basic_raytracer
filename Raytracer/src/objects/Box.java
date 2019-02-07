package objects;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;

public class Box extends Shape{
	
	private Vec3d point1;
	private Vec3d point2;
	
	/**
	 * 
	 * @param corner one corner of the box
	 * @param sideLength side length of the box
	 */
	public Box(Vec3d corner, double sideLength) {
		super(corner);
		point1 = corner;
		point2 = corner.add(new Vec3d(sideLength, sideLength, sideLength));
	}

	@Override
	public IntersectionInfo intersect(Ray ray) {
		double B0x = point1.getX();
		double B1x = point2.getX();
		double B0y = point1.getY();
		double B1y = point2.getY();
		double B0z = point1.getZ();
		double B1z = point2.getZ();
		double Ox = ray.getOrigin().getX();
		double Oy = ray.getOrigin().getY();
		double Oz = ray.getOrigin().getZ();
		double Dx = ray.getDir().getX();
		double Dy = ray.getDir().getY();
		double Dz = ray.getDir().getZ();

		double t0x = (B0x - Ox) / Dx;
		double t0y = (B0y - Oy) / Dy;
		double tmin = (t0x>t0y)?t0x:t0y;
		double t1x = (B1x - Ox) / Dx;
		double t1y = (B1y - Oy) / Dy;
		double tmax = (t1x<t1y)?t1x:t1y;
		double t0z = (B0z - Oz) / Dz;
		double t1z = (B1z - Oz) / Dz;
		
		return new IntersectionInfo(ray);
		
	}

	@Override
	public Vec3d getNormal(Vec3d point) {
		// TODO Auto-generated method stub
		return null;
	}

}
