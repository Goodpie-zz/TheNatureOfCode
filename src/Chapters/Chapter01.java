package Chapters;

import Objects.Mover;
import Objects.RandomMover;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;
import java.util.List;

public class Chapter01 extends PApplet
{

    List<Mover> movers;
    List<RandomMover> rMovers;

    public void settings()
    {
        size(640, 360);
    }

    public void setup()
    {
        movers = new ArrayList<>();
        rMovers = new ArrayList<>();

        for (int i = 0; i <= 50; i++)
        {
            movers.add(new Mover(this));
        }

    }

    public void draw()
    {
        background(255);

        for (Mover mover : movers)
        {
            mover.update(new PVector(mouseX, mouseY));
            mover.checkEdges();
            mover.draw();
        }

    }


    public static void main(String... args)
    {
        PApplet.main("Chapters.Chapter01");
    }
}
