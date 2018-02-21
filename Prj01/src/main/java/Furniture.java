public abstract class Furniture {
    private String name;
    private double area;
    private double additionalArea;

    protected Furniture(String name, double area) throws NegativeAreaException {
        if (area < 0) {
            throw new NegativeAreaException("Area of object " + name + " cannot be negative!");
        }
        this.name = name;
        this.area = area;
        additionalArea = 0;
    }

    protected Furniture(String name, double areaFrom, double areaTo) throws NegativeAreaException {
        this(name, areaFrom);
        additionalArea = areaTo - areaFrom;
        if (additionalArea < 0) {
            throw new NegativeAreaException("Second area of extendable object " + name + " must be greater than first area!");
        }
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return area;
    }

    // check if extendable
    public boolean haveAdditionalArea() {
        return additionalArea != 0;
    }

    public double getAdditionalArea() {
        return additionalArea;
    }

    @Override
    public String toString() {
        String result = getName() + " (area ";
        if (!haveAdditionalArea()) {
            result += getArea();
        } else {
            result += "from " + getArea() + " m2 to " + (getArea() + getAdditionalArea());
        }
        return result + " m2)";
    }
}
