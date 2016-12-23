package Chapters;

import Objects.Mover;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Chapter01 extends PApplet
{

    List<Mover> movers;

    public static void main(String... args) {
        PApplet.main("Chapters.Chapter01");
    }

    public void settings()
    {
        size(640, 360);
    }

    public void setup()
    {
        movers = new ArrayList<>();

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
            mover.update();
            mover.checkEdges();
            mover.display();
        }

    }
}
