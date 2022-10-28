package model.regulators;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public abstract class CompositeRegulator implements Regulator{
    private List<Regulator> regulators;

    protected CompositeRegulator(List<Regulator> regulators){
        this.regulators = regulators;
    }

    @Override
    public double inputFunction() {
        List <Double> input = new ArrayList<>();
        for (int i =0; i<regulators.size(); i++)
            input.add(regulators.get(i).inputFunction());
        if (initialValue()==1.)
            return max(input);
        else
            return min(input);
    }

    abstract protected double initialValue();
    abstract protected double cumulativeValue(double accumulator ,double value);

    @Override
    public String getInfo() {
        return null;
    }

}
