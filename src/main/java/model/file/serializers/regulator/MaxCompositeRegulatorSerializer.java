package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanRepressor;
import model.regulators.MaxCompositeRegulator;

import java.util.List;

public class MaxCompositeRegulatorSerializer implements EntitySerializer<MaxCompositeRegulator> {

    private static MaxCompositeRegulatorSerializer instance;

    private MaxCompositeRegulatorSerializer(){}


    @Override
    public String getCode() {
        return "MaxCompositeRegulator";
    }


    @Override
    public String serialize(MaxCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        ListRegulatorSerializer listRegulators = ListRegulatorSerializer.getInstance();
        return getCode() + " " +  listRegulators.serialize(entity.getRegulators(),writer) ;
    }

    
    @Override
    public MaxCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        ListRegulatorSerializer regulators = ListRegulatorSerializer.getInstance();
        int start = string.indexOf("[");
        String sub = string.substring(start);
        return new MaxCompositeRegulator(regulators.deserialize(sub,reader));
    }

    public static MaxCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MaxCompositeRegulatorSerializer();
        }
        return instance;
    }
}
