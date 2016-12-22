package Chapters;

import Objects.BouncingBall;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Chapter01 extends PApplet
{

    List<BouncingBall> balls;

    public void settings()
    {
        size(640, 360);
    }

    public void setup()
    {
        balls = new ArrayList<>();

        for (int i = 0; i <= 20; i++)
        {
            balls.add(new BouncingBall(this));
        }
        System.out.printf("Height: %s\nWidth: %s%n", height, width);
    }

    public void draw()
    {
        background(255);

        for (BouncingBall ball : balls)
        {
            ball.step();
            ball.draw();
        }

    }


    public static void main(String... args)
    {
        PApplet.main("Chapters.Chapter01");
    }
}
