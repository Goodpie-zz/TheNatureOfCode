package Objects;

import processing.core.PVector;

public class Force {

    private String name;
    private PVector force;

    public Force(String name, PVector force) {
        this.name = name;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PVector getForce() {
        return force;
    }

    public void setForce(PVector force) {
        this.force = force.copy();
    }
}
