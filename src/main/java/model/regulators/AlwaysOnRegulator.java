package model.regulators;

import model.file.writer.RegulatorVisitor;

public class AlwaysOnRegulator implements Regulator{
    @Override
    public double inputFunction() {
        return 1.;
    }

    @Override
    public String getInfo() {
        return null;
    }

    public String accept(RegulatorVisitor visitor ){
        return visitor.visit(this);
    }
}
