import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Title extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mouseX, mouseY;
	private Rectangle bounds,bounds1,bounds2;
	private boolean leftClick = false;
	private BufferedImage title, instructions, play,play1,play2;
	private Window window;
	private BufferedImage[] playButton = new BufferedImage[6];
	private Timer timer;
	
	
	public Title(Window window){
		try {
			//เป็นรูปก่อนเข้าเกม
			title = ImageIO.read(Board.class.getResource("/Title1.png"));
			instructions = ImageIO.read(Board.class.getResource("/Arrow3.png"));
			play = ImageIO.read(Board.class.getResource("/Hard.png"));

			play1 = ImageIO.read(Board.class.getResource("/normal.png"));
			play2 = ImageIO.read(Board.class.getResource("/veryhard1.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		timer = new Timer(1000/60, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}	
		});
		timer.start();
		mouseX = 0;
		mouseY = 0;
		//mid กำหนดขนาดของรูปและตำแหน่ง
		playButton[0] = play.getSubimage(0, 0, 85,70 );
		playButton[1] = play.getSubimage(86, 0, 85, 70);
	
		playButton[2] = play1.getSubimage(0, 0, 85, 70);
		playButton[3] = play1.getSubimage(86, 0, 85, 70);
		//กลาง กำหนดขอบเขตการกด
		bounds = new Rectangle(Window.WIDTH/2 - 50, Window.HEIGHT/2 - 70, 100, 80);
		this.window = window;
		bounds1 = new Rectangle(Window.WIDTH/2 - 150, Window.HEIGHT/2 - 70, 100, 80);
		this.window = window;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//คลิกเมาส์ซ้ายที่ปุ่มเพื่อเริ่มเกม
		if(leftClick && bounds1.contains(mouseX, mouseY))
			window.startTetris();
			
		if(leftClick && bounds.contains(mouseX, mouseY))
			window.startTetrisnormal();

		if(leftClick && bounds2.contains(mouseX, mouseY))
			window.startTetris();
			
		g.setColor(Color.BLACK);
		
		g.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
		
		g.drawImage(title, Window.WIDTH/2 - title.getWidth()/2, Window.HEIGHT/2 - title.getHeight()/2 - 200, null);
		g.drawImage(instructions, Window.WIDTH/2 - instructions.getWidth()/2,
				Window.HEIGHT/2 - instructions.getHeight()/2 + 150, null);
		
		if(bounds.contains(mouseX, mouseY))
			g.drawImage(playButton[0], Window.WIDTH/2 - 50, Window.HEIGHT/2 - 90, null);
		else
			g.drawImage(playButton[1], Window.WIDTH/2 - 50, Window.HEIGHT/2 - 90, null);

		if(bounds1.contains(mouseX, mouseY))
			g.drawImage(playButton[2], Window.WIDTH/2 - 150, Window.HEIGHT/2 - 90, null);
		else
			g.drawImage(playButton[3], Window.WIDTH/2 - 150, Window.HEIGHT/2 - 90, null);
		
		/*if(bounds2.contains(mouseX, mouseY))
			g.drawImage(playButton[4], Window.WIDTH/2 + 50, Window.HEIGHT/2 - 90, null);
		else
			g.drawImage(playButton[5], Window.WIDTH/2 + 50, Window.HEIGHT/2 - 90, null);*/
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftClick = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftClick = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}	
}

