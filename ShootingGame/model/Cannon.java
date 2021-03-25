package shootingGame.model;

public class Cannon extends Thing {
	/** 0도 ~ 360도 : 발사각 */
	private double degree;

	/** m/sec : 발사속도 */
	private double velocity;

	private Bullet bullet;

	// 포를 처음 생성 할때(Controller에서) 주는거 i 는 상위 클래스의(thing) x를 생각하고 줌, 뒤에껀 속도를 받아옴
	/**
	 * 포는 하늘에 띄워놓지 않으니 x축과 속도를 줌
	 * 
	 * @param x        수평위치
	 * @param velocity 발사속도
	 */
	public Cannon(double x, double velocity) {
		// 상위 생성자 호출
		super(x, 0);
		this.velocity = velocity;
	}

	/**
	 * 장전
	 * 
	 * @param bullet
	 */
	public void load(Bullet bullet) {
		this.bullet = bullet;
		// 캐논과 불릿의 위치는 같다는 것을 알려줘야한다.
		copyPosition(bullet);
	}

	/** 발사 안전 각도, 최소 ~ 최대 */
	/** 안전한 각도인지 검사하는 함수 */

	public void setDegree(double degree) {
		this.degree = degree;
	}

	// 발사를 해주는 함수
	/**
	 * 포의 발사 속도 및 각도를 바탕으로 벡터 분해하여 수평 및 수직 속도를 구하고 이들을 탄환에 설정한다.
	 */
	public void shoot() {
		double radians = Math.toRadians(degree);
		// 수평 속도
		// degree를 주고 radian을 돌려받는 함수 //Math.toRadians()
		double xSpeed = velocity * Math.cos(radians);
		// 수직 속도
		double ySpeed = velocity * Math.sin(radians);
		bullet.setSpeed(xSpeed, ySpeed);
	}

	private static double MIN_VALUE = 5, MAX_VALUE = 70;

	public static boolean isSafe(double degree) {
		return (MIN_VALUE <= degree && MAX_VALUE >= degree);
	}

}
