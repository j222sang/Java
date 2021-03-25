package shootingGame.model;

import java.util.ArrayList;
import java.util.List;

public class Planet {
	static SolarSystemPlannetType planetType;
	private List<Thing> things = new ArrayList<>();

	public Planet(SolarSystemPlannetType planetType) {
		this.planetType = planetType;
	}

	public void load(Thing thing) {
		things.add(thing);
	}

}
