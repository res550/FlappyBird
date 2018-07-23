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
	private int count=0;
	private Bird bird;
	private Barrier barrier;
	
	public FlappyBird(){
		bird=new Bird();
		barrier=new Barrier();
		addKeyListener(this);
		setFocusable(true);
		time=new Timer(12,this);
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
			int barrierX=barrier.getLocation()[0];
			int barrierY=barrier.getLocation()[1];
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 800);
			
			bird.paintBird(g);
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
			bird.moveBird();
			boolean topcollision=(new Rectangle(50, bird.getyPos(), 30, 30).intersects
					(new Rectangle(barrier.getLocation()[0], 0, 20, barrier.getLocation()[1]-100)));
			boolean bottomcollision=(new Rectangle(50, bird.getyPos(), 30, 30).intersects
					(new Rectangle(barrier.getLocation()[0], barrier.getLocation()[1]+50, 20, 800-barrier.getLocation()[1]+100)));
			if(bird.getyPos()<=0 || bird.getyPos()>=780 ||topcollision || bottomcollision) {
				play=false;
				if(score>highscore) {
					highscore=score;
				}
				score=0;
				bird.reset();
				barrier=new Barrier();
				count=0;
			}
			
			if(barrier.getLocation()[0]<=-50) {
				barrier=new Barrier();
			}else if(barrier.getLocation()[0]==0){
				score++;
			}
			barrier.move();
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE && play) {
			bird.keyPress();
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
