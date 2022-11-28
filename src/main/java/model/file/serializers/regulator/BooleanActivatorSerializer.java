package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.BooleanActivator;


public class BooleanActivatorSerializer implements EntitySerializer<BooleanActivator> {

    private static BooleanActivatorSerializer instance;

    private static void BooleanActivatorSerializer(){}

    public static BooleanActivatorSerializer getInstance(){
        if (instance == null) {
            instance = new BooleanActivatorSerializer();
        }
        return instance;
    }

    @Override
    public String getCode() {
        return "BooleanActivator";
    }

    @Override
    public String serialize(BooleanActivator entity, RegulatoryNetworkWriter writer) {
        return null;
    }

    @Override
    public BooleanActivator deserialize(String string, RegulatoryNetworkReader reader) {
        return null;
    }
}
