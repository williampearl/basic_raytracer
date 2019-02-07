package light;

import math.IntersectionInfo;
import math.Ray;
import math.Vec3d;
import scene.Color;

public class DistantLight extends Light {
	private Vec3d direction;
	public DistantLight(Vec3d direction, Color color) {
		this.direction = direction.normalize();
		setColor(color);
	}
	public Vec3d getDirection() {
		return direction;
	}
	public void setDirection(Vec3d newDirection) {
		direction = newDirection;
	}
	public IntersectionInfo intersect(Ray ray) {
		return new IntersectionInfo(ray);
	}
	@Override
	public Vec3d getLightDirection(Vec3d point) {
		return direction;
	}
	
}
