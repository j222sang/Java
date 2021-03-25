package shootingGame.model;

//물제는 우리가 새로 계속 만들 수 없으니 추상적 클래스로 지정
public abstract class Thing {
	/** meter */
	// 물체의 위치
	protected double x, y;

	public Thing() {

	}

	// Cannon(double x, double velocity)에서 넘어온 값들을 처리해주는 생성자
	public Thing(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//getter는 생성자 바로 밑에 만든다	
	public double getX() {
		return x;
	}


	public double getY() {
		return y;
	}


	//하위클래스에서 부를수 있도록 허용
	/** 어떤 물체의 위치를 다른 물체의 위치로 설정할 것임 */
	protected void copyPosition(Thing other) {
		other.x = x;
		other.y = y;
	}
	
	// 다음 함수처럼 자기가 가지고 있는 정보를 독자적으로 만들 수 있어야함
	public double calcDistance(Thing other) {
		return Math.abs(x - other.x);
	}
	
	

}
