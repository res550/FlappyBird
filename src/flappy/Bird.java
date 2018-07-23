package flappy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird{
	private BufferedImage up;
	private BufferedImage down;
	private int yPos=400;
	private int ySpeed=0;
	
	public Bird(){
		try {
			up = ImageIO.read(new File("rsz_up.png"));
			down = ImageIO.read(new File("rsz_1straight.png"));

		} catch(IOException e) {
			System.out.println("ERROR: " + e);
		}
	}
	public void paintBird(Graphics g) {
		if(ySpeed<0) {
			g.drawImage(up, 50, yPos, null);
		}
		else {
			g.drawImage(down, 50, yPos, null);
		}
	}
	public void moveBird() {
		ySpeed++;
		yPos+=ySpeed;
	}
	public int getyPos() {
		return yPos;
	}
	public void reset() {
		yPos=400;
		ySpeed=0;
	}
	public void keyPress() {
		ySpeed=(-15);
	}
}
