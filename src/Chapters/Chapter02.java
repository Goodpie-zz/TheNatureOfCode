package Chapters;

import Objects.Force;
import Objects.Mover;
import Objects.RandomMover;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Chapter02 extends PApplet{

    private List<Mover> movers;
    private List<Force> forces;

    public void settings()
    {
        size(640, 360);
    }

    public void setup()
    {
        movers = new ArrayList<>();
        forces = new ArrayList<>();

        // Create a bunch of forces
        forces.add(new Force("Wind", new PVector((float) 0.005, 0)));
        forces.add(new Force("Gravity", new PVector(0, (float) 0.21)));

        for (int i = 0; i <= 50; i++)
        {
            Mover newMover = new Mover(this);
            newMover.addForces(forces);
            movers.add(newMover);
        }

    }

    public void draw()
    {
        background(255);

        for (Mover mover : movers)
        {
            mover.update();
            mover.checkEdges();
            mover.draw();
        }

    }


    public static void main(String... args)
    {
        PApplet.main("Chapters.Chapter02");
    }
}
