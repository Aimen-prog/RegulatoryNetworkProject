package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MaxCompositeRegulator;

public class MaxCompositeRegulatorSerializer implements EntitySerializer<MaxCompositeRegulator> {

    private static MaxCompositeRegulatorSerializer instance;

    private static void MaxCompositeRegulatorSerializer(){}

    public static MaxCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MaxCompositeRegulatorSerializer();
        }
        return instance;
    }


    @Override
    public String getCode() {
        return "MaxCompositeRegulator";
    }

    @Override
    public String serialize(MaxCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public MaxCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }
}
