package math;

public class Vec3d {
	private double x;
	private double y;
	private double z;
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3d add(Vec3d other) {
		return new Vec3d(x + other.getX(), y + other.getY(), z + other.getZ());
	}
	
	public Vec3d sub(Vec3d other) {
		return new Vec3d(x - other.getX(), y - other.getY(), z - other.getZ());
	}
	
	public double mag() {
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public double dot(Vec3d b) {
		return x*b.getX() + y*b.getY() + z*b.getZ();
	}
	
	public Vec3d cross(Vec3d other) {
		return new Vec3d(
				y*other.getZ() - z*other.getY(),
				z*other.getX() - x*other.getZ(),
				x*other.getY() - y*other.getX()
				);
				
	}
	
	public Vec3d mult(double scalar) {
		return new Vec3d(
				x*scalar,
				y*scalar,
				z*scalar
				);
				
	}
	
	public Vec3d normalize() {
		double mag = mag();
		return new Vec3d (
				x/mag,
				y/mag,
				z/mag
				);
	}
	
	public boolean equals(Vec3d other) {
		return Math.abs(x-other.getX())<0.000001 &&
				Math.abs(y-other.getY())<0.000001 &&
				Math.abs(z-other.getZ())<0.000001;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}
	
	public Vec3d copy() {
		return new Vec3d(x,y,z);
	}
	
	public String toString() {
		return String.format("x::%.2f y::%.2f z::%.2f", x,y,z);
	}
}
