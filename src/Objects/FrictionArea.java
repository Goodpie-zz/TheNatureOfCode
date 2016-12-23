package Objects;

import processing.core.PApplet;
import processing.core.PVector;

import static Helpers.Helpers.determineDrag;

public class FrictionArea {

    private PApplet app;
    private PVector location;
    private PVector dimensions;
    private float color;
    private float dragForce;

    public FrictionArea(PApplet app, PVector location, PVector dimensions, float dragAmount) {
        this.app = app;
        this.location = location;
        this.dimensions = dimensions;
        this.color = 175;
        this.dragForce = dragAmount;
    }

    public void display() {
        app.noStroke();
        app.fill(color);
        app.rect(location.x, location.y, dimensions.x, dimensions.y);
    }

    public void setColor(float color) {
        this.color = color;
    }

    public PVector getDragForce(PVector velocity) {
        return determineDrag(this.dragForce, velocity);
    }

    public PVector getDimensions() {
        return dimensions;
    }

    public PVector getLocation() {
        return location;
    }
}
