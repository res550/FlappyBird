package flappy;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame back=new JFrame();
		FlappyBird game=new FlappyBird();
		back.setSize(800, 800);
		back.setVisible(true);
		back.setResizable(false);
		back.setTitle("Flappy Bird");
		back.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		back.add(game);
	}

}
