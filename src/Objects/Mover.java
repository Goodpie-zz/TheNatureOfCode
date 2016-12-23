package Objects;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static Helpers.Constants.G;
import static Helpers.Constants.MAX_SPEED;
import static processing.core.PApplet.constrain;
import static processing.core.PConstants.CENTER;

public class Mover {

    protected PApplet app;
    protected PVector location;
    protected PVector velocity;
    protected PVector acceleration;
    protected float topSpeed;
    protected ArrayList<Force> forces;
    protected float mass;

    private float angle;
    private float aVelocity;
    private float aAcceleration;

    /**
     * Constructs the Mover with random parameters
     *
     * @param app   The app to handle the ball
     */
    public Mover(PApplet app)
    {
        this.app = app;
        this.location = new PVector(0, 0);
        this.velocity = new PVector(0, 0);
        this.acceleration = new PVector(0, 0);
        this.topSpeed = MAX_SPEED;
        this.forces = new ArrayList<>();
        this.mass = 1;
        this.angle = 0;
        this.aVelocity = 0;
        this.aAcceleration = (float) 0.01;
    }

    /**
     * Handle updating the state of the ball
     */
    public void update()
    {
        // Determine acceleration applied by forces
        acceleration = acceleration.add(calculateTotalForces());

        // Add velocity to location
        velocity.add(acceleration);
        location.add(velocity);

        // Update the angular movement
        aAcceleration = (float) (acceleration.x / 20.0);
        aVelocity += aAcceleration;
        angle += aVelocity;

        // Limit the speed
        velocity.limit(topSpeed);

        // Reset acceleration
        acceleration.mult(0);
    }

    /**
     * Draw the ball to the main app
     */
    public void display() {
        app.stroke(0);
        app.strokeWeight(2);
        app.fill(175, 200);
        app.rectMode(CENTER);
        app.pushMatrix();
        app.translate(location.x, location.y);
        app.rotate(angle);
        app.rect(0, 0, (float) (mass * 1.5), (float) (mass * 1.5));
        app.popMatrix();
    }

    /**
     * Applies a single vector force to the Mover for one tick
     *
     * @param force Vector force to apply
     */
    public void applyForce(PVector force) {
        PVector newForce = force.copy();
        newForce.div(mass);
        acceleration.add(newForce);
    }

    /**
     * Applies a single force to the Mover for one tick
     *
     * @param force Vector force to apply
     */
    public void applyForce(Force force) {
        PVector newForce = force.getForce().copy();
        newForce.div(mass);
        acceleration.add(newForce);
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

    /**
     * Checks if the mover is currently inside a friction area
     *
     * @param fa The friction area to check
     * @return Whether inside the friction area
     */
    public boolean insideFrictionArea(FrictionArea fa) {
        boolean inside = false;

        float faXRange = fa.getLocation().x + fa.getDimensions().x;
        float faYRange = fa.getLocation().y + fa.getDimensions().y;
        if ((location.x < faXRange && location.x > fa.getLocation().x) && (location.y < faYRange && location.y > fa.getLocation().y)) {
            inside = true;
        }

        return inside;
    }

    /**
     * Determines the total amount of force being applied to the Mover
     * @return  Total force
     */
    private PVector calculateTotalForces()
    {
        PVector forceSum = new PVector(0, 0);
        for (Force force : forces) {
            PVector tmpForce = force.getForce().copy();
            tmpForce.div(mass);
            forceSum.add(tmpForce);
        }

        return forceSum;
    }

    /**
     * Adds a single, unique force to the Mover
     * Or updates already existing force
     *
     * @param newForce New force to add2
     */
    public void addForce(Force newForce) {
        boolean forceFound = false;

        // Check if force already exists in list of forces
        for (Force force : forces) {
            if (force.getName().equals(newForce.getName())) {
                force.setForce(newForce.getForce());
                forceFound = true;
                break;
            }
        }

        // Force doesn't exist, add it to list of forces
        if (!forceFound) {
            forces.add(newForce);
        }
    }

    /**
     * Adds a list of forces to the Mover
     * @param forces List of forces to add
     */
    public void addForces(List<Force> forces) {
        for (Force force : forces) {
            addForce(force);
        }
    }

    /**
     * Ensures the mover doesn't go out of screen bounds
     */
    public void checkEdges() {
        if (location.x > app.width || location.x < 0)
        {
            velocity.x = velocity.x * -1;
        }

        if (location.y > app.height || location.y < 0)
        {
            velocity.y = velocity.y * -1;
        }
    }

    /* Getters */
    public PVector getLocation() {
        return location;
    }

    public void setLocation(PVector location) {
        this.location = location;
    }

    public PVector getVelocity() {
        return velocity;
    }

    public void setVelocity(PVector velocity) {
        this.velocity = velocity;
    }

    public PVector getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(PVector acceleration) {
        this.acceleration = acceleration;
    }

    public float getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(float topSpeed) {
        this.topSpeed = topSpeed;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
