package model.file.serializers.event;

import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;

public class SetSignaledEventSerializer implements EntitySerializer<SetSignaledEvent> {

    private static SetSignaledEventSerializer instance;
    //Singleton: only one object copy exists
    // default private constructor
    private static void SetSignaledEventSerializer(){}


    @Override
    public String getCode() {
        return "SetSignaledEvent";
    }

    @Override
    public String serialize(SetSignaledEvent entity, RegulatoryNetworkWriter writer) {
        ListGeneSerializer event = new ListGeneSerializer();
        return getCode() + " " + entity.getTime()  + " " + event.serialize(entity.getGenes(),writer) + " "
                + entity.getInfo(); //newSignaledValue
    }

    public static SetSignaledEventSerializer getInstance(){
        if (instance == null) {
            instance = new SetSignaledEventSerializer();
        }
        return instance;
    }

    @Override
    public SetSignaledEvent deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        String gene_reg =  tokens[2];  // regulatory gene name list -> list of objects
        ListGeneSerializer event = new ListGeneSerializer();
        return new SetSignaledEvent( event.deserialize(gene_reg,reader),
                Double.parseDouble(tokens[1] ),
                Boolean.parseBoolean(tokens[3]));


    }
}
