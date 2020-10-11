package maman15sinaya;

public class Box {
	double height;
	double side;
	
	public Box(double height, double side) {
		super();
		this.height = height;
		this.side = side;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getSide() {
		return side;
	}
	public void setSide(double side) {
		this.side = side;
	}
	
	@Override
	public String toString() {
		return "Box [height=" + height + ", side=" + side + "]";
	}
	
	
}
