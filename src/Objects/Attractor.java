package Objects;

import processing.core.PApplet;
import processing.core.PVector;

import static Helpers.Constants.G;
import static processing.core.PApplet.constrain;

/**
 * Created by brandyn on 23/12/16.
 */
public class Attractor {

    private PApplet app;
    private float mass;
    private PVector location;

    public Attractor(PApplet app, float mass, PVector location) {
        this.app = app;
        this.mass = mass;
        this.location = location;
    }

    public void display() {
        app.stroke(0);
        app.fill(175, 200);
        app.ellipse(location.x, location.y, (float) (mass * 1.5), (float) (mass * 1.5));
    }

    public PVector attract(Mover m) {
        // Get easier to read var for location
        PVector mLoc = m.getLocation().copy();

        // Start by getting the distance to the Mover
        PVector force = PVector.sub(location, mLoc);
        float distance = force.mag();

        // Ensure the distance doesn't exceed the following
        distance = constrain(distance, 10, 50);

        // Use basic gravitational pull equation
        float strength = (G * mass * m.getMass()) / (distance * distance);

        // Apply grav pull to force
        force.normalize();
        force.mult(strength);

        return force;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public PVector getLocation() {
        return location;
    }

    public void setLocation(PVector location) {
        this.location = location;
    }
}
