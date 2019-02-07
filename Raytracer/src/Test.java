import math.Transformation;
import math.Vec3d;

public class Test {
	public static void main(String[] args) {
		Transformation t = new Transformation();
		t.overallScale(3);
		t.translate(0,0,1);
		
		System.out.println(t.apply(new Vec3d(1,1,1)));
	}
}	
