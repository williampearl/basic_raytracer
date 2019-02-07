package math;

public class Transformation {
	private double[][] transformation = {
			{1, 0, 0, 0},
			{0, 1, 0, 0},
			{0, 0, 1, 0},
			{0, 0, 0, 1}};
	
	/**
	 * Creates a transformation that uses the identity
	 */
	public Transformation() {
		
	}
	
	
	/**
	 * Sets the transformation matrix to the given array
	 * @param preset what to set to
	 */
	public Transformation(double[][] preset) {
		transformation = preset;
	}
	
	/**
	 * Applies the transformation to a copy of the vector
	 * @return
	 */
	public Vec3d apply(Vec3d vector) {
		double x = vector.getX();
		double y = vector.getY();
		double z = vector.getZ();
		double w = x*transformation[3][0] + y*transformation[3][1] + z*transformation[3][2] + transformation[3][3];
		return new Vec3d(
				(x*transformation[0][0] + y*transformation[0][1] + z*transformation[0][2] + transformation[0][3])/w,
				(x*transformation[1][0] + y*transformation[1][1] + z*transformation[1][2] + transformation[1][3])/w,
				(x*transformation[2][0] + y*transformation[2][1] + z*transformation[2][2] + transformation[2][3])/w				
				);
	}
	
	/**
	 * Translates
	 * @param x amount in the x axis
	 * @param y amount in the y axis
	 * @param z amount in the z axis
	 */
	public void translate(double x, double y, double z) {
		double[][] t1 = Transformation.identity().getTransformationMatrix();
		t1[0][3] = x;
		t1[1][3] = y;
		t1[2][3] = z;
		Transformation after = new Transformation(t1);
		transformation = dot(after).getTransformationMatrix();
	}
	
	/**
	 * Overall scale
	 * @param amt amount to scale up by
	 */
	public void overallScale(double amt) {
		double[][] t1 = Transformation.identity().getTransformationMatrix();
		t1[3][3] = amt;
		Transformation after = new Transformation(t1);
		transformation = dot(after).getTransformationMatrix();
	}
	
	/**
	 * Returns a transformation that is the this transformation . t
	 * Does not affect either transformations.
	 * @param t other transformation
	 * @return the corresponding transformation
	 */
	public Transformation dot(Transformation t) {
		double[][] t1 = transformation;
		double[][] t2 = t.getTransformationMatrix();
		
		double[][] out = new double[4][4];
		for(int r=0; r<4; r++) {
			for(int c=0; c<4; c++) {
				double hold = 0;
				for(int k=0; k<4; k++) {
					hold += t1[r][k]*t2[k][c];
				}
				out[r][c] = hold;
			}
		}
		return new Transformation(out);
	}
	
	/**
	 * Rotates the transformation by theta RADIANS around the Z axis
	 * @param theta amount of RADIANS to rotate around the Z axis
	 */
	public void rotateAboutZAxis(double theta) {
		double[][] t1 = Transformation.identity().getTransformationMatrix();
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		t1[0][0] = cos;
		t1[0][1] = sin;
		t1[1][0] = -sin;
		t1[1][1] = cos;
		Transformation after = new Transformation(t1);
		transformation = dot(after).getTransformationMatrix();
	}
	
	/**
	 * Rotates the transformation by theta RADIANS around the Y axis
	 * @param theta amount of RADIANS to rotate around the Z axis
	 */
	public void rotateAboutYAxis(double theta) {
		double[][] t1 = Transformation.identity().getTransformationMatrix();
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		t1[0][0] = cos;
		t1[0][2] = sin;
		t1[2][0] = -sin;
		t1[2][2] = cos;
		Transformation after = new Transformation(t1);
		transformation = dot(after).getTransformationMatrix();
	}
	/**
	 * 
	 * @return the transformation matrix
	 */
	public double[][] getTransformationMatrix() {
		return transformation;
	}
	
	/**
	 * 
	 * @return a new Transformation using the identity matrix
	 */
	public static Transformation identity() {
		return new Transformation();
	}
	
	public String toString() {
		String out = "";
		for(double[] d1 : transformation) {
			for(double d2 : d1) {
				out += String.format("%7.2f", d2);
			}
			out+="\n";
		}
		return out;
	}
	
}
