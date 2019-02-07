package scene;

import light.AmbientLight;
import light.DistantLight;
import light.Light;
import light.PointLight;
import math.IntersectionInfo;
import math.Ray;
import math.Ray.Type;
import math.Transformation;
import math.Vec3d;
import objects.WorldObject;

public class Camera {
	private int fov;
	private int width;
	private int height;
	private Color backgroundColor = Color.BLACK;
	private Transformation cameraToWorld = Transformation.identity();
	private Scene scene;
	public Camera(Scene scene) {
		this(90, 200, 200, scene);
	}
	/**
	 * 
	 * @param position
	 * @param orientation
	 * @param fov
	 * @param width
	 * @param height
	 * @param scene
	 */
	public Camera(int fov, int width, int height, Scene scene) {
		this.fov = fov;
		this.width = width;
		this.height = height;
		this.scene = scene;
	}
	public Camera(int fov, int width, int height, Transformation transform, Scene scene) {
		this(fov, width, height,scene);
		cameraToWorld = transform;
	}
	public void setBackgroundColor(Color c) {
		backgroundColor = c;
	}
	public void applyTransformation(Transformation trans) {
		cameraToWorld = cameraToWorld.dot(trans);
	}
	public void setCameraTransformation(Transformation trans) {
		cameraToWorld = trans;
	}
	public Color[][] render() {
		Color[][] out = new Color[height][width];
		long beforeTime = System.currentTimeMillis();
		for(int y=0; y<height; y++) {
			for(int x=0; x<width; x++) {
				Color color = getColor(y,x);
				for(Light l : scene.getLights()) {
					Color lColor = l.getColor();
					double lInt = l.getIntensity();
					if(l instanceof AmbientLight) {
						color = new Color(
								color.getRed()/255*lColor.getRed()*lInt,
								color.getGreen()/255*lColor.getGreen()*lInt,
								color.getBlue()/255*lColor.getBlue()*lInt
								);
					}
				}
				out[y][x] = color;
			}
		}
		System.out.println(System.currentTimeMillis()-beforeTime);
		return out;
	}
	public Color getColor(int row, int col) {
		float imageAspectRatio = 1f*width/height;
		double tan = Math.tan(fov/2*Math.PI/180);
		//calculate the camera space of the pixels
		double Px = (2 * ((col+0.5)/width)-1)*tan * imageAspectRatio;
		double Py = (1 - 2 * ((row+0.5)/height)) * tan;
		Vec3d rayOrigin = new Vec3d(0,0,0);
		Vec3d rayOriginWorld = cameraToWorld.apply(rayOrigin);
		Vec3d rayPWorld = cameraToWorld.apply(new Vec3d(Px, Py, -1));
		Vec3d rayDirection = rayPWorld.sub(rayOriginWorld).normalize();
		Ray cameraRay = new Ray(rayOriginWorld, rayDirection);
		
		IntersectionInfo best = null;
		WorldObject bestObject = null;
		//find closest object
		for(WorldObject s : scene.getObjects()) {
			IntersectionInfo solution = s.intersect(cameraRay);
			if(!solution.foundIntersect()) 
				continue;
			if(best == null) {
				best = solution;
				bestObject = s;
				continue;
			}
			if(solution.getDistance() < best.getDistance()) {
				best = solution;
				bestObject = s;
				continue;
			}
		}
		
		if(best == null) 
			return Color.BLACK;
		//return bestObject.getOverallColor();
		if(scene.getLights().isEmpty())
			return bestObject.getOverallColor();
		Vec3d hitNormal = bestObject.getShape().getNormal(best.getIntersectionPoint());
		Color hitColor = Color.BLACK;
		Vec3d hitPoint = best.getIntersectionPoint();
		WorldObject hitObj = bestObject;
		Vec3d shadowRayBias = hitNormal.mult(-0.0001);
//		//find if point is in shadow
		for(Light light : scene.getLights()) {
			Vec3d lightDirection = light.getLightDirection(hitPoint.add(shadowRayBias));
			Ray shadowRay = new Ray(hitPoint.add(shadowRayBias), lightDirection.mult(-1));
			shadowRay.setRayType(Type.SHADOW);
			IntersectionInfo shadowIntersect = new IntersectionInfo(shadowRay);
			for(WorldObject s : scene.getObjects()) {
				IntersectionInfo current = s.intersect(shadowRay);
				if(current.foundIntersect()) {
					shadowIntersect = current;
					break;
				}
			}
			//did not find a world object that is casting a shadow on the point
			if(!shadowIntersect.foundIntersect()) {
				//light contribution
				Color contrib = Color.BLACK;
				if(light instanceof PointLight) {
					PointLight pLight = (PointLight) light;
					contrib = 
							pLight.lightIntensity(hitPoint).mult(Math.max(0, hitNormal.dot(lightDirection)));
				}
				hitColor = hitColor.add(contrib);
			}
		}
		hitColor = hitColor.mult(hitObj.getOverallColor()).mult(hitObj.getMaterial().getAlbedo());
		
		return hitColor;
	}
}
