package flappy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class FlappyBird extends JPanel implements KeyListener,ActionListener{
	Timer time;
	private boolean play=false;
	private int score=0;
	private int highscore=0;
	private int yPos=400;
	private int ySpeed= 0;
	private int count=0;
	private int barrierY=400;
	private int barrierX=750;
	private BufferedImage up;
	private BufferedImage down;
	
	public FlappyBird(){
		try {
			up = ImageIO.read(new File("rsz_up.png"));
			down = ImageIO.read(new File("rsz_1straight.png"));

		} catch(IOException e) {
			System.out.println("ERROR: " + e);
		}
		addKeyListener(this);
		setFocusable(true);
		time=new Timer(15,this);
		time.start();
	}
	
	public void paint(Graphics g) {
		if(!play) {
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 800);
			g.setColor(Color.white);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g.drawString("Press Enter to Start", 250, 300);
			g.drawString("Press Space Bar to Jump", 200, 350);
			g.drawString("HighScore: "+Integer.toString(highscore), 250,400);
		}
		else {
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 800);
			
			if(ySpeed<0) {
				g.drawImage(up, 50, yPos, null);
			}
			else {
				g.drawImage(down, 50, yPos, null);
			}
			g.setColor(Color.GREEN);
			g.fillRect(barrierX, 0, 50, barrierY-100);
			g.fillRect(barrierX, barrierY+50, 50, 800-barrierY+100);
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
			g.drawString("Score : " + Integer.toString(score), 375, 50);
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//time.start();
		if(play) {
			ySpeed++;
			yPos+=ySpeed;
			boolean topcollision=(new Rectangle(50, yPos, 30, 30).intersects
					(new Rectangle(barrierX, 0, 20, barrierY-100)));
			boolean bottomcollision=(new Rectangle(50, yPos, 30, 30).intersects
					(new Rectangle(barrierX, barrierY+50, 20, 800-barrierY+100)));
			if(yPos<=0 || yPos>=780 ||topcollision || bottomcollision) {
				play=false;
				if(score>highscore) {
					highscore=score;
				}
				score=0;
				yPos=400;
				ySpeed=0;
				barrierX=650;
				barrierY=450;
				count=0;
			}
			
			if(count==80) {
				score++;
				barrierY=(int) (Math.random()*600)+100;
				barrierX=780;
				count=0;
			}
			count++;
			barrierX-=10;
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE && play) {
			ySpeed= (-15);
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			play=!play;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
