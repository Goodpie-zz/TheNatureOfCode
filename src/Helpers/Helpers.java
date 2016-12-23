package Helpers;

import processing.core.PVector;

import static Helpers.Constants.FRICTION;

public class Helpers {

    public static PVector determineFriction(PVector velocity) {
        return determineFriction(FRICTION, velocity);
    }

    public static PVector determineFriction(float frictionConst, PVector velocity) {
        PVector friction = velocity.copy();
        friction.mult(-1);
        friction.normalize();
        friction.mult(frictionConst);

        return friction;
    }

    public static PVector determineDrag(float dragConst, PVector velocity) {
        float speed = velocity.copy().mag();
        float dragMag = dragConst * speed * speed;

        PVector drag = determineFriction(dragConst, velocity);
        drag.mult(dragMag);

        return drag;
    }

}
