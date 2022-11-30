package model.regulators;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.*;

public abstract class CompositeRegulator implements Regulator{
    private List<Regulator> regulators;

    protected CompositeRegulator(List<Regulator> regulators){
        this.regulators = regulators;
    }


    @Override
    public double inputFunction() {
        double accumulator = initialValue();
        for(Regulator regulator: regulators){
            accumulator = cumulativeValue(accumulator, regulator.inputFunction());
        }
        return accumulator;
    }

    abstract protected double initialValue();
    abstract protected double cumulativeValue(double accumulator ,double value);

    @Override
    public String getInfo() {
        return null;
    }

    public List<Regulator> getRegulators(){
        return regulators;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeRegulator that = (CompositeRegulator) o;
        return regulators.equals(that.regulators);
    }

}
