package src.shootingGame.view;

import shootingGame.model.Bullet;
import shootingGame.model.Cannon;
import shootingGame.model.Target;

public class CliDisplayer {
	/** 5km : 1칸으로 */
	private static final int WIDTH = 1000, WIDTH_RATIO = 50;
	/** 3km : 1칸으로 */
	private static final int HEIGHT = 200, HEIGHT_RATIO = 30;
	private char[][] screen = new char[HEIGHT][WIDTH];

	private Cannon cannon;
	private Bullet bullet;
	private Target target;

	public CliDisplayer(Cannon cannon, Bullet bullet, Target target) {
		this.cannon = cannon;
		this.bullet = bullet;
		this.target = target;
		/**
		 * 
		 * 
		 * 
		
		
		3000 : 1 = 2000 : ?
		물리적 정보에서 논리적 정보로 변환
				? = 20 - (int) (1*2000 / 3000)
		*/
		cannon.getY();
		screen[heightPhy2logical(cannon.getY())][widthPhy2logical(cannon.getX())] = 'C';
		screen[heightPhy2logical(target.getY())][widthPhy2logical(target.getX())] = 'T';
	}

	private int widthPhy2logical(double x) {
		return (int) (x/WIDTH_RATIO);
	}

	private int heightPhy2logical(double y) {
		return HEIGHT - 1 - (int)(y / HEIGHT_RATIO);
	}

	public void display() {
		screen[heightPhy2logical(bullet.getY())][widthPhy2logical(bullet.getX())] = '-';
		for(char[] row : screen) {
			for(char col : row) {
				System.out.print(col);
			}	
			System.out.println();
		}
	}

}
