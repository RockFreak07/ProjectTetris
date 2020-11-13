import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Shape1 {
	
	private int color;	
	private int x, y; 	
	private long time, lastTime;	
	private int normal = 800, fast = 70;	
	private int delay;	
	private BufferedImage block;	
	private int[][] coords;	
	private int[][] reference;	
	private int deltaX;	
	private Normal normal1;	
	private boolean collision = false, moveX = false;
	
	public Shape1(int[][] coords, BufferedImage block, Normal normal1, int color){
		this.coords = coords;
		this.block = block;
		this.normal1 = normal1;
		this.color = color;
		deltaX = 0;
		x = 4;
		y = 0;
		delay = normal;
		time = 0;
		lastTime = System.currentTimeMillis();
		reference = new int[coords.length][coords[0].length];
		
		System.arraycopy(coords, 0, reference, 0, coords.length);
		
	}
	
	public void update(){
		moveX = true;
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(collision)
		{
			for(int row = 0; row < coords.length; row ++)
			{
				for(int col = 0; col < coords[0].length; col ++)
				{
					if(coords[row][col] != 0)
						normal1.getNormal()[y + row][x + col] = color;
				}
			}
			checkLine();
			normal1.addScore();
			normal1.setCurrentShape();
		}
		
		if(!(x + deltaX + coords[0].length > 10) && !(x + deltaX < 0))
		{
			
			for(int row = 0; row < coords.length; row++)
			{
				for(int col = 0; col < coords[row].length; col ++)
				{
					if(coords[row][col] != 0)
					{
						if(normal1.getNormal()[y + row][x + deltaX + col] != 0)
						{
							moveX = false;
						}
						
					}
				}
			}
			
			if(moveX)
				x += deltaX;
			
		}
		
		if(!(y + 1 + coords.length > 20))
		{
			
			for(int row = 0; row < coords.length; row++)
			{
				for(int col = 0; col < coords[row].length; col ++)
				{
					if(coords[row][col] != 0)
					{
						
						if(normal1.getNormal()[y + 1 + row][x +  col] != 0)
						{
							collision = true;
						}
					}
				}
			}
			if(time > delay)
				{
					y++;
					time = 0;
				}
		}else{
			collision = true;
		}
		
		deltaX = 0;
	}
	
	public void render(Graphics g){
		
		for(int row = 0; row < coords.length; row ++)
		{
			for(int col = 0; col < coords[0].length; col ++)
			{
				if(coords[row][col] != 0)
				{
					g.drawImage(block, col*30 + x*30, row*30 + y*30, null);	
				}
			}		
		}
		
		for(int row = 0; row < reference.length; row ++)
		{
			for(int col = 0; col < reference[0].length; col ++)
			{
				if(reference[row][col] != 0)
				{
					g.drawImage(block, col*30 + 320, row*30 + 160, null);	
				}	
				
			}
				
		}

	}
	
	private void checkLine(){
		int size = normal1.getNormal().length - 1;
		
		for(int i = normal1.getNormal().length - 1; i > 0; i--)
		{
			int count = 0;
			for(int j = 0; j < normal1.getNormal()[0].length; j++)
			{
				if(normal1.getNormal()[i][j] != 0)
					count++;
				
                    normal1.getNormal()[size][j] = normal1.getNormal()[i][j];
			}
			if(count < normal1.getNormal()[0].length)
				size --;
		}
	}
	
	public void rotateShape()
	{
		
		int[][] rotatedShape = null;
		
		rotatedShape = transposeMatrix(coords);
		
		rotatedShape = reverseRows(rotatedShape);
		
		if((x + rotatedShape[0].length > 10) || (y + rotatedShape.length > 20))
		{
			return;
		}
		
		for(int row = 0; row < rotatedShape.length; row++)
		{
			for(int col = 0; col < rotatedShape[row].length; col ++)
			{
				if(rotatedShape[row][col] != 0)
				{
					if(normal1.getNormal()[y + row][x + col] != 0)
					{
						return;
					}
				}
			}
		}
		coords = rotatedShape;
	}

	
    private int[][] transposeMatrix(int[][] matrix){
        int[][] temp = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                temp[j][i] = matrix[i][j];
        return temp;
    }


	
	private int[][] reverseRows(int[][] matrix){
		
		int middle = matrix.length/2;
		
		
		for(int i = 0; i < middle; i++)
		{
			int[] temp = matrix[i];
			
			matrix[i] = matrix[matrix.length - i - 1];
			matrix[matrix.length - i - 1] = temp;
		}
		
		return matrix;
		
	}
	
	
	public int getColor(){
		return color;
	}
	
	public void setDeltaX(int deltaX){
		this.deltaX = deltaX;
	}
	
	public void speedUp(){
		delay = fast;
	}
	
	public void speedDown(){
		delay = normal;
	}
	
	public BufferedImage getBlock(){
		return block;
	}
	
	public int[][] getCoords(){
		return coords;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}
