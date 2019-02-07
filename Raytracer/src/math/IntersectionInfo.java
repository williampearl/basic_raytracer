
package math;

import objects.Shape;

public class IntersectionInfo implements Comparable<IntersectionInfo>{
	private Vec3d point;
	private Ray originalRay;
	private Shape intersectedShape;
	private boolean foundIntersect = false;
	private double distance = Double.NEGATIVE_INFINITY;
	/**
	 * Creates an intersection info and assumes that there was no intersection found.
	 * @param original original ray
	 */
	public IntersectionInfo(Ray original) {
		originalRay = original;
	}
	
	/**
	 * 
	 * @param original original ray
	 * @param intersectedShape shape that the ray intersects
	 * @param intersectionPoint the point that the ray intersects with intersectedShape
	 */
	public IntersectionInfo(Ray original, Shape intersectedShape, Vec3d intersectionPoint) {
		originalRay = original;
		this.intersectedShape = intersectedShape;
		point = intersectionPoint;
		distance = original.getOrigin().sub(intersectionPoint).mag();
		foundIntersect = true;
	}
	
	/**
	 * WARNING: This will be null if no intersect was found. Check foundIntersect() if there was any intersect.
	 * @return the point of intersection
	 */
	public Vec3d getIntersectionPoint() {
		return point;
	}
	
	/**
	 * WARNING: This will be null if no intersect was found. Check foundIntersect() if there was any intersect.
	 * @return the shape that is intersected
	 */
	public Shape getIntersectedShape() {
		return intersectedShape;
	}
	
	/**
	 * This will be Double.NEGATIVE_INFINITY if there was no intersection.
	 * Check if there was an intersection before using this method with foundIntersect()
	 * If distance is not set with setDistance(double) then it will calculate the distance 
	 * using the rays origin point and the point of intersection.
	 * @return the distance between the rays origin point and the point of intersection
	 */
	public double getDistance() {
		if(!foundIntersect) {
			return distance;
		}
		//calculate the distance if it was not given properly
		if(distance == Double.NEGATIVE_INFINITY) {
			distance = point.sub(originalRay.getOrigin()).mag();
		}
		return distance;
	}
	
	/**
	 * 
	 * @return the original ray
	 */
	public Ray getOriginalRay() {
		return originalRay;
	}

	/**
	 * 
	 * @return if there was an intersect 
	 */
	public boolean foundIntersect() {
		return foundIntersect;
	}
	
	/**
	 * Sets the distance to newDistance
	 * @param newDistance
	 */
	public void setDistance(double newDistance) {
		distance = newDistance;
	}
	
	public void setIntersectedShape(Shape shape) {
		intersectedShape = shape;
		foundIntersect = true;
	}
	
	public void setPoint(Vec3d point) {
		this.point = point;
		foundIntersect = true;
	}
	@Override
	/**
	 * Sorts the intersections based on their distance.
	 * The smaller distance goes first.
	 * @param otherIntersect
	 * @return
	 */
	public int compareTo(IntersectionInfo otherIntersect) {
		return (int)Double.compare(distance, otherIntersect.getDistance());
	}
	

}
