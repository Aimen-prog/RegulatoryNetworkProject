package model.file.writer;

import model.file.serializers.regulator.*;
import model.regulators.*;

public class ConcreteRegulatorVisitor implements RegulatorVisitor{
    private RegulatoryNetworkWriter writer;

    ConcreteRegulatorVisitor(RegulatoryNetworkWriter writer){
            this.writer= writer;
    }

    @Override
    public String visit(AlwaysOffRegulator regulator) {
        return AlwaysOffRegulatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(AlwaysOnRegulator regulator) {
        return AlwaysOnRegulatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(BooleanActivator regulator) {
        return BooleanActivatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(BooleanRepressor regulator) {
        return BooleanRepressorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(MaxCompositeRegulator regulator) {
        return MaxCompositeRegulatorSerializer.getInstance().serialize(regulator,writer);
    }

    @Override
    public String visit(MinCompositeRegulator regulator) {
        return MinCompositeRegulatorSerializer.getInstance().serialize(regulator,writer);
    }
}
