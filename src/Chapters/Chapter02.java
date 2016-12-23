package Chapters;

import Objects.Attractor;
import Objects.Mover;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static Helpers.Constants.MAX_SPEED;
import static Helpers.Helpers.determineFriction;

public class Chapter02 extends PApplet{

    Attractor sun;
    private List<Mover> movers;

    public static void main(String... args) {
        PApplet.main("Chapters.Chapter02");
    }

    public void settings()
    {
        size(640, 360);
    }

    public void setup()
    {
        // Initialize the movers
        movers = new ArrayList<>();
        for (int i = 0; i < 5; i += 1)
        {
            movers.add(new Mover(
                    this,
                    new PVector(50 * i, 50),
                    new PVector(0, 0),
                    new PVector(0, 0),
                    MAX_SPEED,
                    (float) (Math.random() * 10 + 10)
            ));
        }

        // Initialize the attractors
        sun = new Attractor(this, 100, new PVector(width / 2, height / 2));
    }

    public void draw()
    {
        background(255);
        sun.display();

        for (Mover mover : movers)
        {
            float gravity = (float) (0.05 * mover.getMass());
            // mover.applyForce(new Force(GRAVITY, new PVector(0, gravity)));
            // mover.applyForce(new Force(WIND, new PVector((float) 0.001, 0)));
            mover.applyForce(determineFriction(mover.getVelocity()));

            // Apply attractor forces
            PVector sunForce = sun.attract(mover);
            mover.applyForce(sunForce);

            mover.update();
            // mover.checkEdges();
            mover.display();
        }

    }
}
