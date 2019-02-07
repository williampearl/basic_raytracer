package scene;

import java.util.ArrayList;

import light.Light;
import objects.WorldObject;

public class Scene {
	private Camera camera;
	private ArrayList<Light> lights;
	private ArrayList<WorldObject> objects;
	public Scene() {
		System.out.println("a");
		camera = new Camera(this);
		lights = new ArrayList<>();
		objects = new ArrayList<>();
	}
	public Scene(Camera cam, ArrayList<Light> lights, ArrayList<WorldObject> objects) {
		camera = cam;
		this.lights = lights;
		this.objects = objects;
	}
	public Color[][] render() {
		
		return camera.render();
	}
	public ArrayList<Light> getLights() {
		return lights;
	}
	public ArrayList<WorldObject> getObjects() {
		return objects;
	}
	public void setLights(ArrayList<Light> newLights) {
		lights = newLights;
	}
	public void setObjects(ArrayList<WorldObject> newObjects) {
		objects = newObjects;
	}
	public void addLight(Light newLight) {
		if(newLight == null)
			return;
		lights.add(newLight);
	}
	public void addWorldObject(WorldObject newObject) {
		if(newObject == null)
			return;
		objects.add(newObject);
	}
	public Camera getCamera() {
		return camera;
	}
	public void setCamera(Camera camera) {
		if(camera == null)
			return;
		this.camera = camera;
	}
}
