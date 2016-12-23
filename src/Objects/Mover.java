package Objects;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static Helpers.Constants.MAX_SPEED;

public class Mover {

    protected PApplet app;
    protected PVector location;
    protected PVector velocity;
    protected PVector acceleration;
    protected float topSpeed;
    protected ArrayList<Force> forces;
    protected float mass;


    public Mover(PApplet app, PVector location, PVector velocity, PVector acceleration, float topSpeed, float mass)
    {
        this.app = app;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.topSpeed = topSpeed;
        this.forces = new ArrayList<>();
        this.mass = mass;
    }

    /**
     * Constructs the Mover with random parameters
     *
     * @param app   The app to handle the ball
     */
    public Mover(PApplet app)
    {
        this.app = app;
        this.location = new PVector( (float) (Math.random() * this.app.width + 1), (float) (Math.random() * this.app.height + 1));
        this.velocity = new PVector(0, 0);
        this.acceleration = new PVector(0, 0);
        this.topSpeed = MAX_SPEED;
        this.forces = new ArrayList<>();
        this.mass = 1;
    }

    /**
     * Handle updating the state of the ball
     */
    public void update()
    {
        // Determine acceleration applied by forces
        acceleration = acceleration.add(addForces());

        // Add velocity to location
        velocity.add(acceleration);
        location.add(velocity);

        // Limit the speed
        velocity.limit(topSpeed);

        // Reset acceleration
        acceleration.mult(0);
    }

    /**
     * Determines the total amount of force being applied to the Mover
     * @return  Total force
     */
    private PVector addForces()
    {
        PVector forceSum = new PVector(0, 0);
        for (Force force : forces)
        {
            PVector tmpForce = force.getForce().get();
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

    public void applyForce(Force force) {
        // System.out.printf("Applying force: %s\nx = %f\ny = %f\n%n", force.getName(), force.getForce().x, force.getForce().y);
        PVector newForce = force.getForce().copy();
        newForce.div(mass);
        acceleration.add(newForce);
    }

    public void applyForce(PVector force) {
        PVector newForce = force.copy();
        newForce.div(mass);
        acceleration.add(newForce);
    }

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

    /**
     * Draw the ball to the main app
     */
    public void display() {
        app.stroke(0);
        app.fill(175);
        app.ellipse(location.x, location.y, (float) (mass * 2.5), (float) (mass * 2.5));
    }

    /* Getters and setters */

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
