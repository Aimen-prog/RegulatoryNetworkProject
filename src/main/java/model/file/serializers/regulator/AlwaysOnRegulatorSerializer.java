package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.serializers.list.ListRegulatorSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.regulators.AlwaysOffRegulator;
import model.regulators.AlwaysOnRegulator;

public class AlwaysOnRegulatorSerializer implements EntitySerializer<AlwaysOnRegulator> {

    private static AlwaysOnRegulatorSerializer instance;

    private AlwaysOnRegulatorSerializer(){}

    @Override
    public String getCode() {
        return "AlwaysOnRegulator";
    }


    @Override
    public String serialize(AlwaysOnRegulator entity, RegulatoryNetworkWriter writer) {
        return getCode() ;
    }

    @Override
    public AlwaysOnRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        reader.getGene(tokens[0]).setRegulator (new AlwaysOnRegulator());
        return (AlwaysOnRegulator) reader.getGene(tokens[0]).getRegulator();
    }

    public static AlwaysOnRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new AlwaysOnRegulatorSerializer();
        }
        return instance;
    }
}
