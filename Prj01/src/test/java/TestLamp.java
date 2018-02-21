public class TestLamp {
    public static void main(String[] args) throws NegativeLuxException{

        Lamp lamp1 = new Lamp(200);
        System.out.println(lamp1.getLux());

        Lamp lamp2 = new Lamp(0);
        System.out.println(lamp2.getLux());

        try {
            Lamp lamp3 = new Lamp(-1);
            System.out.println(lamp3.getLux());
        } catch (NegativeLuxException e) {
            e.printStackTrace();
        }

    }
}
