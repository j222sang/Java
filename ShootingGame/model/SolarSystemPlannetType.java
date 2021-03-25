package shootingGame.model;

public enum SolarSystemPlannetType {
	// 행성의 종류
	MERCURY(3.302e+23, 2.439e6), VENUS(4.869e+24, 6.052e6), EARTH(5.975e+24, 6.378e6), MARS(6.419e+23, 3.393e6),
	JUPITER(1.899e+27, 7.149e7), SATURN(5.685e+26, 6.027e7), URANUS(8.683e+25, 2.556e7), NEPTUNE(1.024e+26, 2.477e7);

	private final double mass; // 질량(단위: 킬로그램)
	private final double radius; // 반지름(단위: 미터)
	private final double surfaceGravity; // 표면중력(단위: m / s^2)

	// 중력상수(단위: m^3 / kg s^2)
	private static final double G = 6.67300E-11;

	// 생성자
	SolarSystemPlannetType(double mass, double radius) {
		this.mass = mass;
		this.radius = radius;
		// 표면 중력 = 중력상수 * 질량 / 반지릅^2
		surfaceGravity = G * mass / (radius * radius);
	}
	//질량 반환
	public double mass() {
		return mass;
	}
	// 반지름 반환
	public double radius() {
		return radius;
	}
	// 표면 장력 
	public double surfaceGravity() {
		return surfaceGravity;
	}

	public double surfaceWeight(double mass) {
		return mass * surfaceGravity; // F = ma
	}
}
