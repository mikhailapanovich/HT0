public class TestBuilding {
    public static void main(String[] args) throws NegativeLuxException, IlluminanceTooMuchException,
            NegativeAreaException, SpaceUsageTooMuchException, IlluminanceTooLowException {
        Building building = new Building("Здание 1");
        building.addRoom("Комната 1", 100, 3);
        building.addRoom("Комната 2", 5, 2);
        building.addRoom("Комната 3", 10, 1);
        building.getRoom("Комната 1").add(new Lamp(150));
        building.getRoom("Комната 1").add(new Lamp(250));
        building.getRoom("Комната 1").add(new Table("Стол письменный", 3));
        building.getRoom("Комната 1").add(new ExtendableTable("Кресло мягкое и пушистое", 1, 2));
        if (building.isValid()) {
            building.describe();
        }
    }
}
