package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.MinCompositeRegulator;

public class MinCompositeRegulatorSerializer implements EntitySerializer<MinCompositeRegulator> {

    private static MinCompositeRegulatorSerializer instance;

    private static void MinCompositeRegulatorSerializer(){}

    public static MinCompositeRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new MinCompositeRegulatorSerializer();
        }
        return instance;
    }

    @Override
    public String getCode() {
        return "MinCompositeRegulator";
    }

    @Override
    public String serialize(MinCompositeRegulator entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public MinCompositeRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }
}
