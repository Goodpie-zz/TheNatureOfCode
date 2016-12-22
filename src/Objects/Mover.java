package Objects;

import processing.core.PVector;
import processing.core.PApplet;

public class Mover {

    protected PApplet app;
    protected PVector location;
    protected PVector velocity;
    protected PVector acceleration;
    protected float topSpeed;

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
    }

    /**
     * Handle updating the state of the ball
     */
    public void update()
    {
        // Add velocity to location
        velocity.add(acceleration);
        location.add(velocity);

        // Limit the speed
        velocity.limit(topSpeed);
    }

    public void update(PVector point)
    {
        PVector dir = PVector.sub(point, location);
        dir.normalize();
        dir.mult((float) 0.5);

        acceleration = dir;

        update();
    }

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
