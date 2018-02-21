import java.util.Map;
import java.util.TreeMap;

public class Building {
    private String name;
    private Map<String, Room> rooms;

    public Building(String name) {
        this.name = name;
        this.rooms = new TreeMap<String, Room>();
    }

    public void addRoom(String name, double area, int numberOfWindows) {
        try {
            rooms.put(name, new Room(name, area, numberOfWindows));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Room getRoom(String name) {
        return rooms.get(name);
    }

    public String getName() {
        return name;
    }

    // print out all information about building
    public void describe() {
        System.out.println(getName());
        for (Room room : rooms.values()) {
            System.out.print(room);
        }

    }

    // check rooms if they fit to lower illuminance limit
    public boolean isValid() throws IlluminanceTooLowException {
        for (Room room : rooms.values()) {
            if (room.getLux() < Room.MIN_LUX) {
                throw new IlluminanceTooLowException(room.getName() + " illuminance is " +
                        room.getLux() + " and it is less than minimum " + Room.MIN_LUX);
            }
        }
        return true;
    }
}
