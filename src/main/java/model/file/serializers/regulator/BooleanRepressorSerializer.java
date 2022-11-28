package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanRepressor;

public class BooleanRepressorSerializer implements EntitySerializer<BooleanRepressor> {

    private static BooleanRepressorSerializer instance;

    private static void BooleanRepressorSerializer(){}

    public static BooleanRepressorSerializer getInstance(){
        if (instance == null) {
            instance = new BooleanRepressorSerializer();
        }
        return instance;
    }

    @Override
    public String getCode() {
        return "BooleanRepressor";
    }

    @Override
    public String serialize(BooleanRepressor entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public BooleanRepressor deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }
}
