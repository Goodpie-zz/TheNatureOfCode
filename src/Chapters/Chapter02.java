package Chapters;

import Objects.Attractor;
import Objects.Mover;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

import static Helpers.Helpers.determineFriction;

public class Chapter02 extends PApplet{

    private static final int NUM_MOVERS = 10;
    private Attractor sun;
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
        for (int i = 0; i < NUM_MOVERS; i += 1) {
            Mover mover = new Mover(this);
            mover.setLocation(new PVector((float) (Math.random() * width), (float) (Math.random() * height)));
            mover.setMass((float) (Math.random() * 10 + 10));
            movers.add(mover);
        }

        // Initialize the attractors
        sun = new Attractor(this, 100, new PVector(width / 2, height / 2));
    }

    public void draw()
    {
        background(255);
        sun.display();

        for (int i = 0; i < NUM_MOVERS; i++)
        {
            Mover mover = movers.get(i);
            float gravity = (float) (0.05 * mover.getMass());

            // Apply gravity and wind forces
            // mover.applyForce(new Force(GRAVITY, new PVector(0, gravity)));
            // mover.applyForce(new Force(WIND, new PVector((float) 0.001, 0)));

            // Apply friction
            mover.applyForce(determineFriction(mover.getVelocity()));

            // Apply attractor forces
            PVector sunForce = sun.attract(mover);
            mover.applyForce(sunForce);

            // Apply attraction to other movers
            for (int j = 0; j < NUM_MOVERS; j++) {
                if (i != j) {
                    PVector moverAttraction = mover.attract(movers.get(j));
                    movers.get(j).applyForce(moverAttraction);
                }
            }

            mover.update();
            // mover.checkEdges();
            mover.display();
        }

    }
}
