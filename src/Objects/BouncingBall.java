package Objects;

import processing.core.PVector;
import processing.core.PApplet;

public class BouncingBall {

    private PApplet app;
    private PVector location;
    private PVector velocity;

    /**
     * Constructs the bouncing ball with user defined parameters
     *
     * @param app       The app to handle the ball
     * @param location  Location of the ball on the screen
     * @param velocity  Velocity of the ball
     */
    public BouncingBall(PApplet app, PVector location, PVector velocity)
    {
        this.app = app;
        this.location = location;
        this.velocity = velocity;
    }

    /**
     * Constructs the BouncingBall with random parameters
     *
     * @param app   The app to handle the ball
     */
    public BouncingBall(PApplet app)
    {
        this.app = app;
        this.location = new PVector( (float) (Math.random() * this.app.width + 1), (float) (Math.random() * this.app.height + 1));
        this.velocity = new PVector((float) (Math.random() * 10 + 1), (float) (Math.random() * 10 + 1));
    }

    /**
     * Handle updating the state of the ball
     */
    public void step()
    {
        // Add velocity to location
        location.add(velocity);

        // Check that we aren't going past the walls
        if ((location.x > app.width) || (location.x < 0))
        {
            velocity.x = velocity.x * -1;
        }

        if ((location.y > app.height) || (location.y < 0))
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
