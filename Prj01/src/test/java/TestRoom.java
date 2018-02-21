public class TestRoom {
    public static void main(String[] args) throws NegativeNumberOfWindowsException,
            IlluminanceTooMuchException, NegativeAreaException, NegativeLuxException, SpaceUsageTooMuchException {

        // normal room
        Room room = new Room("room1", 100, 3);
        room.add(new Lamp(30));
        room.add(new Lamp(121.1));
        room.add(new Table("Oak table", 10));
        room.add(new ExtendableTable("IKEA table", 1, 2));
        System.out.println(room);

        // room without windows
        room = new Room("room2", 100, 0);
        room.add(new Lamp(150));
        room.add(new Lamp(300));
        System.out.println(room);

        // room with lots of windows
        try {
            room = new Room("room3", 100, 6);
            System.out.println(room);
        } catch (IlluminanceTooMuchException e) {
            System.out.println("OK " + e.getLocalizedMessage());
        }

        // room with more than MAX_LUX illumination
        room = new Room("room4", 100, 2);
        room.add(new Lamp(150));
        room.add(new Lamp(300));
        try {
            room.add(new Lamp(4000));
            System.out.println(room);
        } catch (IlluminanceTooMuchException e) {
            System.out.println("OK " + e.getMessage());
        }

        // room with negative number of windows
        try {
            room = new Room("room5", 100, -1);
            System.out.println(room);
        } catch (NegativeNumberOfWindowsException e) {
            System.out.println("OK " + e.getMessage());
        }

        // room with negative area
        try {
            room = new Room("room6", -2, 3);
            System.out.println(room);
        } catch (NegativeAreaException e) {
            System.out.println("OK " + e.getMessage());
        }

        // room with furniture
        room = new Room("room7", 100, 3);
        room.add(new Table("table1", 20));
        System.out.println(room);

        // exceed 70% limit
        room = new Room("room8", 100, 3);
        room.add(new Table("table1", 20));
        try {
            room.add(new Table("table2", 51));
            System.out.println(room);
        } catch (SpaceUsageTooMuchException e) {
            System.out.println("OK " + e.getMessage());
        }

        // exceed room area with additional area
        room = new Room("room9", 100, 3);
        room.add(new Table("table1", 20));
        try {
            room.add(new ExtendableTable("table2", 50, 81));
            System.out.println(room);
        } catch (SpaceUsageTooMuchException e) {
            System.out.println("OK " + e.getMessage());
        }
    }
}
