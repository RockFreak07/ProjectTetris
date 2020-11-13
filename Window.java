import javax.swing.JFrame;

public class Window{
	//413
	public static final int WIDTH = 445, HEIGHT = 629;
	
	private Board board;
	private Title title;
	private JFrame window;
	
	private Normal normal;
	
	public Window(){
		
		window = new JFrame("Tetris");
		window.setSize(WIDTH, HEIGHT);//กำหนดขนาดของเฟรม
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//กดปุ่มexitเพื่อจบการทำงาน
		window.setLocationRelativeTo(null);
		window.setResizable(false);	
		//เรียกคลาสboardกับtitle
		board = new Board();
		title = new Title(this);
		
		window.addKeyListener(normal);
		window.addKeyListener(board);
		window.addMouseMotionListener(title);
		window.addMouseListener(title);
		
		window.add(title);

		window.setVisible(true);
	}
	//เริ่มเกม
	public void startTetris(){
		window.remove(title);
		window.addMouseMotionListener(board);
		window.addMouseListener(board);
		window.add(board);
		board.startGame();
		window.revalidate();
	}
	public void startTetrisnormal(){
		window.remove(title);
		window.addMouseMotionListener(normal);
		window.addMouseListener(normal);
		window.add(normal);
		normal.startGame();
		window.revalidate();
	}
	
	public static void main(String[] args) {
		new Window();
	}

}
