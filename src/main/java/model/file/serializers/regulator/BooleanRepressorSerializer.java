package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanActivator;
import model.regulators.BooleanRepressor;

public class BooleanRepressorSerializer implements EntitySerializer<BooleanRepressor> {

    private static BooleanRepressorSerializer instance;

    private BooleanRepressorSerializer(){}

    @Override
    public String getCode() {
        return "BooleanRepressor";
    }

    @Override
    public String serialize(BooleanRepressor entity, RegulatoryNetworkWriter writer) {
        return getCode() + " " + entity.getInfo();
    }

    @Override
    public BooleanRepressor deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        return new BooleanRepressor(Double.parseDouble(tokens[2]),
                reader.getGene(tokens[3]));

    }

    public static BooleanRepressorSerializer getInstance(){
        if (instance == null) {
            instance = new BooleanRepressorSerializer();
        }
        return instance;
    }
}
