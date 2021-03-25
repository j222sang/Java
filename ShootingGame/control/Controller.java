package shootingGame.control;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import shootingGame.model.Bullet;
import shootingGame.model.Cannon;
import shootingGame.model.Planet;
import shootingGame.model.SolarSystemPlannetType;
import shootingGame.model.Target;
import src.shootingGame.view.CliDisplayer;

public class Controller {
	/** 1초마다 한번씩 날아가는 모습을 보여 줄 것임 단위는 milliSecond */
	static final int PERIOD = 1000;

	public static void main(String[] args) {
		/** 포와 포탄과 목표물을 준비한다 */
		Planet earth = new Planet(SolarSystemPlannetType.EARTH); // 포를 쏠 행성을 준비
		// 포는 하늘에 띄워놓지 않으니 x축과 속도를 줌
		Cannon cannon = new Cannon(1000, 1000); // 포를 준비
//		Cannon cannon2 = new Cannon(1000); // 굴곡이 있는 지표면에서
//		earth.deposite(999, cannon2); // 캐논을 놓는 위치를 지표면에서 설정
		// 포탄을 만들때 시간이 지나면서 자신의 위치 정보가 바뀌고 그때 생성되니 처음에는 넘겨줄 값 없음
		Bullet bullet = new Bullet();
		cannon.load(bullet); // 포에 포탄을 장착
		Target target = new Target(20000); // 타겟의 위치를 지정
		earth.load(cannon); // overloading // 행성에 포를 놓는다
		earth.load(target); // overloading // 행성에 타겟을 놓는다
//		System.out.println("목표물까지의 거리는 " + (target.getX() - cannon.getX()) / 1000 + "km입니다."); //-> encapsulation 위배

		// 5WH1 포는 목표물의 위치를 파악하여 그 거리를 km로 환산하여 반환한다
		// 주체성을 가지고 있는 실체에게 요청해야한다.
		System.out.println("목표물까지의 거리는 " + cannon.calcDistance(target) + "km입니다.");

		/** 각도를 입력받는다 */
		// 모듈 = 함수 = 메소드 의 내부에 들어있는 문장 총 길이는 제한한다. 고품질 9줄, 중품질 20줄 7+-2
		double degree = inputDegree();
		cannon.setDegree(degree);

		/** 발사 */
		cannon.shoot();
		// 시간이 흐르면서 포탄은 날아가고 땅에 닿을때까지 위치를 계산하면서 이를 그림으로 그려 준다
		CliDisplayer displayer = new CliDisplayer(cannon, bullet, target); 
		startFly(bullet, displayer);
		// 땅에 닿으면 포탄과 목표물 사이의 거리를 산출하여 출력해줌

	}

	private static void startFly(Bullet bullet, CliDisplayer displayer) {

		ScheduledJob job = new ScheduledJob(bullet);
		// 시계
		Timer jobScheduler = new Timer();
		// 알람 울리기 설정 //어떤알람 //몇초 쉴래 //몇초마다 출력할래
		jobScheduler.scheduleAtFixedRate(job, 0, PERIOD);

		do {
			// 그림으로 그려준다
			try {
				Thread.sleep(PERIOD);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			displayer.display();
		} while (bullet.isInSky());

		// 알람끝내기
		jobScheduler.cancel();
	}

	// 입력 받은 값을 0 ~ 360 사이에 있는지 검사한다
	/**
	 * 스캐너로부터 입력 받은 값이 안전 범위에 있는지 검사하고 이를 반환
	 * 
	 * @return
	 */

	private static double inputDegree() {
		double ret = 0;
		Scanner s = new Scanner(System.in);
		do {
			ret = s.nextDouble();

		} while (!Cannon.isSafe(ret));

		return ret;
	}

}

/**
 * 알람이 울리면 처리하는 클래스
 * 
 * @author User
 *
 */
class ScheduledJob extends TimerTask {
	private Bullet bullet;

	ScheduledJob(Bullet bullet) {
		this.bullet = bullet;
	}

	public void run() {
		bullet.fly(Controller.PERIOD);
	}
}
