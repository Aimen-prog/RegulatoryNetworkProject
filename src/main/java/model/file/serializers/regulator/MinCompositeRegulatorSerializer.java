package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MaxCompositeRegulator;
import model.regulators.MinCompositeRegulator;

public class MinCompositeRegulatorSerializer implements EntitySerializer<MinCompositeRegulator> {

    private static MinCompositeRegulatorSerializer instance;

    private MinCompositeRegulatorSerializer(){}

    @Override
    public String getCode() {
        return "MinCompositeRegulator";
    }

    @Override
    public String serialize(MinCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        ListRegulatorSerializer listRegulators = ListRegulatorSerializer.getInstance();
        return getCode() + " " +  listRegulators.serialize(entity.getRegulators(),writer) ;
    }

    @Override
    public MinCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        ListRegulatorSerializer regulators =ListRegulatorSerializer.getInstance();
        int start = string.indexOf("[");
        String sub = string.substring(start);
        return new MinCompositeRegulator(regulators.deserialize(sub,reader));
    }

    public static MinCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MinCompositeRegulatorSerializer();
        }
        return instance;
    }
}
