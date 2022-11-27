package model.file.serializers.event;

import model.events.SetProteinConcentrationEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;

public class SetProteinConcentrationEventSerializer implements EntitySerializer<SetProteinConcentrationEvent> {


    private static SetProteinConcentrationEventSerializer instance;
    //Singleton: only one object copy exists
    // default private constructor
    private static void SetProteinConcentrationEventSerializer(){}

    @Override
    public String getCode() {
        return "SetProteinConcentrationEvent";
    }

    //SetProteinConcentration 10.0 [X] 3.0
    @Override
    public String serialize(SetProteinConcentrationEvent entity, RegulatoryNetworkWriter writer) {
        ListGeneSerializer event = new ListGeneSerializer();
        return getCode() + " " + entity.getTime()  + " " + event.serialize(entity.getGenes(),writer) + " "
                + entity.getInfo(); //newConcentration
    }

    public static SetProteinConcentrationEventSerializer getInstance(){
        if (instance == null) {
            instance = new SetProteinConcentrationEventSerializer();
        }
        return instance;
    }

    @Override
    public SetProteinConcentrationEvent deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        String gene_reg =  tokens[2];  // regulatory gene name list -> list of objects
        ListGeneSerializer event = new ListGeneSerializer();
        return new SetProteinConcentrationEvent ( event.deserialize(gene_reg,reader),
                                                    Double.parseDouble(tokens[1] ),
                                                        Double.parseDouble(tokens[3]));
    }
}
