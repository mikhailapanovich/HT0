import java.util.ArrayList;
import java.util.List;

public class Room {
    public static final double MIN_LUX = 300;

    private String name;
    private double area;
    private double usedArea;
    private double usedAdditionalArea;
    private int numberOfWindows;
    private double lux;

    private List<Lamp> lamps;
    private List<Furniture> furniture;

    private static final double MAX_LUX = 4000;
    private static final double WINDOW_LUX = 700;
    private static final double MAX_USED_AREA = 0.7;

    public Room(String name, double area, int numberOfWindows) throws NegativeAreaException,
            NegativeNumberOfWindowsException, IlluminanceTooMuchException {
        this.name = name;

        // area
        if (area <= 0) {
            throw new NegativeAreaException("Area of room must be a positive number!");
        }
        this.area = area;
        usedArea = 0;
        usedAdditionalArea = 0;

        // illumination
        if (numberOfWindows < 0) {
            throw new NegativeNumberOfWindowsException("Number of windows must be positive!");
        }
        if (numberOfWindows * WINDOW_LUX > MAX_LUX) {
            throw new IlluminanceTooMuchException("Illumination from windows(" + numberOfWindows * WINDOW_LUX +
                    ") exceeds maximum illumination in room(" + MAX_LUX + ")");
        }
        this.numberOfWindows = numberOfWindows;
        lux = WINDOW_LUX * numberOfWindows;

        // lists
        lamps = new ArrayList<Lamp>();
        furniture = new ArrayList<Furniture>();
    }

    public void add(Lamp lamp) throws IlluminanceTooMuchException {
        if (getLux() + lamp.getLux() > MAX_LUX) {
            throw new IlluminanceTooMuchException("The illumination in the " + getName() +
                    "(" + (getLux() + lamp.getLux()) + ") exceeds legal illumination " + MAX_LUX);
        }
        lamps.add(lamp);
        lux += lamp.getLux();
    }

    // adds furniture if room's has free space for it
    public void add(Furniture f) throws SpaceUsageTooMuchException {
        if (f.getArea() + getUsedArea() > getArea() * MAX_USED_AREA) {
            throw new SpaceUsageTooMuchException("Area of the " + f.getName() +
                    " exceeds " + getName() + " limit of 70% used space");
        } else if (f.getArea() + f.getAdditionalArea() + getUsedArea() > getArea()) {
            throw new SpaceUsageTooMuchException("Extendable object " + f.getName() +
                    " exceeds area of " + getName() + " when extended");
        }
        furniture.add(f);
        usedArea += f.getArea();
        usedAdditionalArea += f.getAdditionalArea();
    }

    public String getName() {
        return name;
    }

    public double getLux() {
        return lux;
    }

    private int getNumberOfWindows() {
        return numberOfWindows;
    }

    private double getArea() {
        return area;
    }

    private double getUsedArea() {
        return usedArea;
    }

    private double getUsedAdditionalArea() {
        return usedAdditionalArea;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        // name of room
        result.append("  " + getName() + "\n");

        // illumination of room
        result.append(getIlluminationInfo() + "\n");

        // area of room
        result.append(getAreaInfo() + "\n");

        // furniture
        result.append(getFurnitureInfo());

        return result.toString();
    }

    // get all furniture info
    private String getFurnitureInfo() {
        StringBuilder result = new StringBuilder();

        if (furniture.size() == 0) {
            result.append("    Room has no furniture\n");
        } else {
            result.append("    Furniture:\n");
            for (Furniture f : furniture) {
                result.append("      " + f.toString() + "\n");
            }
        }

        return result.toString();
    }

    // gather illumination information about room
    private String getIlluminationInfo() {
        StringBuilder result = new StringBuilder();

        result.append("    Illumination = " + getLux() + " lx (");

        // windows
        if (getNumberOfWindows() > 0) {
            result.append(getNumberOfWindows() + " window(s) with 700 lx");
        } else {
            result.append("room has no windows");
        }

        // lamps
        if (lamps.size() == 0) {
            result.append(", there is no lamps in the room");
        } else {
            result.append(", lumps:");
            for (Lamp lamp : lamps) {
                result.append(" " + lamp.getLux() + " lx");
            }
        }
        result.append(")");

        return result.toString();
    }

    // gather information about room area
    private String getAreaInfo() {
        StringBuilder result = new StringBuilder();

        result.append("    Area = " + getArea() + " m2 (");

        if (getUsedArea() != 0) {
            result.append(getUsedArea());                                       // totally used
            if (getUsedAdditionalArea() != 0) {
                result.append("-" + (getUsedAdditionalArea() + getUsedArea())); // extended area
            }
            result.append(" m2 used, ");
        }
        double freeArea = getArea() - getUsedArea();
        result.append(freeArea + " m2 are free(" + (freeArea / getArea() * 100) + "%))");

        return result.toString();
    }
}
