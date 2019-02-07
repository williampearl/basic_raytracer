package light;

import math.IntersectionInfo;
import math.Ray;
import scene.Color;

public class AmbientLight extends Light{
	public AmbientLight() {
		setColor(Color.WHITE);
		setIntensity(1);
	}
	public IntersectionInfo intersect(Ray ray) {
		IntersectionInfo inter = new IntersectionInfo(ray);
		inter.setDistance(0);
		return inter;
	}
}
