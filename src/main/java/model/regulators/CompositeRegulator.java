package model.regulators;

import java.util.List;

public abstract class CompositeRegulator implements Regulator{
    private List<Regulator> regulators;

    protected CompositeRegulator(List<Regulator> regulators){
        this.regulators = regulators;
    }











////////////////////////////////////////////////
    @Override
    public double inputFunction() {
        return 1.;

    }

    @Override
    public String getInfo() {
        return null;
    }
///////////////////////////////////////////////////////////


}
