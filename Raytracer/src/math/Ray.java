package math;

public class Ray {
	private Vec3d origin;
	private Vec3d dir;
	private Type rayType;
	
	
	/**
	 * 
	 * @param origin origin of the ray
	 * @param direction direction the ray faces, this should be normalized before passing it in.
	 */
	public Ray(Vec3d origin, Vec3d direction) {
		this.origin = origin;
		dir = direction; //this should be normalized
		rayType = Type.UNSPECIFIED;
	}
	
	/**
	 * 
	 * @param origin origin of the ray
	 * @param direction direction the ray faces, this should be normalized before passing it in.
	 * @param rayType will set the ray to the given type
	 */
	public Ray(Vec3d origin, Vec3d direction, Type rayType) {
		this(origin, direction);
		this.rayType = rayType;
	}
	
	/**
	 * Sets the ray type
	 * @param t new ray type
	 */
	public void setRayType(Type t) {
		rayType = t;
	}
	
	/**
	 * 
	 * @return the origin of the ray
	 */
	public Vec3d getOrigin() {
		return origin;
	}
	
	/**
	 * 
	 * @return the direction of the ray
	 */
	public Vec3d getDir() {
		return dir;
	}
	
	/**
	 * 
	 * @return the ray type
	 */
	public Type getRayType() {
		return rayType;
	}
	
	/**
	 * Finds what point the ray is pointing to given value C.
	 * The calculation is origin + direction*c.
	 * @param c the number that multiplies the direction
	 * @return the corresponding point
	 */
	public Vec3d getPoint(double c) {
		return origin.add(dir.mult(c));
	}
	
	
	
	
	public enum Type {
		UNSPECIFIED(-1),
		CAMERA(0),
		SHADOW(1);
		private int x;
		private Type(int i) {
			x = i;
		}
		public int getTypeNumber() {
			return x;
		}
	}
}
