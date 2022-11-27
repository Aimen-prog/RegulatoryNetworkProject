package model.file.writer;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;

public class ConcreteEventVisitor implements EventVisitor{

    private RegulatoryNetworkWriter writer;

    ConcreteEventVisitor(RegulatoryNetworkWriter writer){
        this.writer= writer;
    }

    @Override
    public String visit(SetSignaledEvent event) {
        return SetSignaledEventSerializer.getInstance().serialize(event,writer);
    }

    @Override
    public String visit(SetProteinConcentrationEvent event) {
        return SetProteinConcentrationEventSerializer.getInstance().serialize(event,writer);
    }
}
