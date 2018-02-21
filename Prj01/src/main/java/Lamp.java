public class Lamp {
    double lux;

    public Lamp(double lux) throws NegativeLuxException{
        if (lux < 0) {
            throw new NegativeLuxException("Lamp illumination cannot be negative!");
        }
        this.lux = lux;
    }

    public double getLux() {
        return lux;
    }
}