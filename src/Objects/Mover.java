package Objects;

import processing.core.PVector;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Mover {

    protected PApplet app;
    protected PVector location;
    protected PVector velocity;
    protected PVector acceleration;
    protected float topSpeed;
    protected ArrayList<Force> forces;

    /**
     * Constructs the bouncing ball with user defined parameters
     *
     * @param app       The app to handle the ball
     * @param location  Location of the ball on the screen
     * @param velocity  Velocity of the ball
     */
    public Mover(PApplet app, PVector location, PVector velocity, PVector acceleration, float topSpeed)
    {
        this.app = app;
        this.location = location;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.topSpeed = topSpeed;
        this.forces = new ArrayList<>();
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
        this.acceleration = new PVector((float) 0.0, (float) 0.1);
        this.topSpeed = 10;
        this.forces = new ArrayList<>();
    }

    /**
     * Handle updating the state of the ball
     */
    public void update()
    {
        // Determine acceleration applied by forces
        acceleration = addForces();

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
            forceSum.add(force.getForce());
        }

        return forceSum;
    }

    public void addForce(Force newForce)
    {
        boolean forceFound = false;

        // Check if force already exists in list of forces
        for (Force force : forces)
        {
            if (force.getName().equals(newForce.getName()))
            {
                force.setForce(newForce.getForce());
                forceFound = true;
                break;
            }
        }

        // Force doesn't exist, add it to list of forces
        if (!forceFound)
        {
            forces.add(newForce);
        }
    }

    public void addForces(List<Force> forces)
    {
        for (Force force : forces)
        {
            addForce(force);
        }
    }


    /**
     * Ensures the mover doesn't go out of screen bounds
     */
    public void checkEdges()
    {
        // Check that we aren't going past the walls
        if (location.x > app.width && velocity.x > 0)
        {
            velocity.x = velocity.x * -1;
        }

        if (location.x < 0 && velocity.x < 0)
        {
            velocity.x = velocity.x * -1;
        }

        if (location.y > app.height && velocity.y > 0)
        {
            velocity.y = velocity.y * -1;
        }

        if (location.y < 0 && velocity.y < 0)
        {
            velocity.y = velocity.y * -1;
        }

    }


    /**
     * Draw the ball to the main app
     */
    public void draw()
    {
        app.stroke(0);
        app.fill(175);
        app.ellipse(location.x, location.y, 16, 16);
    }


}
