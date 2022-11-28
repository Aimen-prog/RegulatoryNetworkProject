package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.EntitySerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.regulators.AlwaysOffRegulator;
import model.regulators.AlwaysOnRegulator;

public class AlwaysOffRegulatorSerializer implements EntitySerializer<AlwaysOffRegulator> {

    private static AlwaysOffRegulatorSerializer instance;

    private AlwaysOffRegulatorSerializer(){}

    @Override
    public String getCode() {
        return "AlwaysOffRegulator";
    }

    @Override
    public String serialize(AlwaysOffRegulator entity, RegulatoryNetworkWriter writer) {
        return getCode() ;
    }

    @Override
    public AlwaysOffRegulator deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        reader.getGene(tokens[0]).setRegulator (new AlwaysOffRegulator());
        return (AlwaysOffRegulator) reader.getGene(tokens[0]).getRegulator();
    }

    public static AlwaysOffRegulatorSerializer getInstance(){
        if (instance == null) {
            instance = new AlwaysOffRegulatorSerializer();
        }
        return instance;
    }
}
