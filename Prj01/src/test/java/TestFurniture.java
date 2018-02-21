public class TestFurniture {
    public static void main(String[] args) throws NegativeAreaException {
        Furniture table1 = new Table("Round table", 1);
        System.out.println(table1);

        Furniture table2 = new ExtendableTable("Diner table", 2, 3);
        System.out.println(table2);

        try {
            Furniture table3 = new Table("Round table", -1);
            System.out.println(table1);
        } catch (NegativeAreaException e) {
            e.printStackTrace();
        }

        try {
            Furniture table4 = new ExtendableTable("Diner table", 2, 1);
            System.out.println(table2);
        } catch (NegativeAreaException e) {
            e.printStackTrace();
        }
    }
}

