package objects;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;
import scene.Color;

public class WorldObject {
	private Shape shape;
	private Material material;
	public WorldObject(Shape shape) {
		this.shape = shape;
		material = Material.common();
	}
	public WorldObject(Shape shape, Material newMaterial) {
		this.shape = shape;
		material = newMaterial;
	}
	public void setMaterial(Material newMaterial) {
		material = newMaterial;
	}
	public Material getMaterial() {
		return material;
	}
	public void setColor(Color color) {
		material.setColor(color);
	}
	public IntersectionInfo intersect(Ray ray) {
		return shape.intersect(ray);
	}
	public void translate(Vec3d amt) {
		shape.setCenter(shape.getCenter().add(amt));
	}
	public Shape getShape() {
		return shape;
	}
	public Color getOverallColor() {
		return material.getColor();
	}
	/**
	 * Gets the color of the object at the given point
	 * @param ray
	 * @param c
	 * @return
	 */
	public Color getColor(Ray ray, double c) {
		return getOverallColor();
	 }
}
