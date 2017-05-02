
public class ContinuousAttribute extends Attribute{
private float max;
private float min;

public ContinuousAttribute(String name, int index, float min, float max){
	super(name,index);
	this.max=max;
	this.min=min;
}

public float getMin(){
	return min;
}

public float getMax(){
	return max;
}


}
