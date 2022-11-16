package model.regulators;

public class AlwaysOnRegulator implements Regulator{
    @Override
    public double inputFunction() {
        return 1.;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
