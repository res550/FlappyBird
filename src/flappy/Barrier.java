package flappy;

public class Barrier {
	private int barrierY;
	private int barrierX;
	private int barrierSpeed=5;

	public Barrier() {
		barrierY=(int) (Math.random()*600)+100;
		barrierX=800;
	}
	public int[] getLocation() {
		int[] pos={barrierX,barrierY};
		return pos;
	}
	public void move() {
		barrierX-=barrierSpeed;
	}
}
