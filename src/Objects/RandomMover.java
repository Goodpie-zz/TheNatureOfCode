package Objects;

import processing.core.PApplet;
import processing.core.PVector;

public class RandomMover extends Mover {

    public RandomMover(PApplet app, PVector location, PVector velocity, PVector acceleration, float topSpeed) {
        super(app, location, velocity, acceleration, topSpeed);

    }

    public RandomMover(PApplet app) {
        super(app);
    }

    @Override
    public void update() {
        this.velocity = PVector.random2D();
        this.velocity.mult((float) (Math.random() * 2));
        super.update();
    }
}
