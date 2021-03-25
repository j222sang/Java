package shootingGame.model;

public class Bullet extends Thing {

	/** m/sec : 수평 및 수직 속도. 수직은 하늘 방향이 양수 */
	private double xSpeed, ySpeed;

	public Bullet() {
		// 부모 클래스 불러야함 default생성자는 super()로 호출하지 않아도 자동응로 불러옴
	}

	// protected도 같은 패키지 내에서 는 사용 가능
	/**
	 * Default Visibility 는 동일한 패키지 내에서 허용된다.
	 * 
	 * @param xSpeed
	 * @param ySpeed
	 */
	void setSpeed(double xSpeed, double ySpeed) {
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;

	}

	/**
	 * 총알이 날라가는 시간을 보여줌
	 * 
	 * @param period
	 */
	public void fly(int period) {
		// 수직 속도는 자유낙하
		SolarSystemPlannetType planetType = Planet.planetType;
		// 1초
		ySpeed -= (period / 1000) * planetType.surfaceGravity(); // 속도 변화분
		// 수평 속도는 등속운동이여서 xSpeed의 변화는 없다
		x += xSpeed * (period / 1000);
		y += ySpeed * (period / 1000);
	}

	/**
	 * 총알이 하늘에 계속 있는지 검사하는 함수
	 * 
	 * @return
	 */
	public boolean isInSky() {
		return y >= 0;
	}

	@Override
	public String toString() {
		return "Bullet [x=" + x + ", y=" + y +", xSpeed=" + xSpeed + ", ySpeed=" + ySpeed + "]";
	}
}
