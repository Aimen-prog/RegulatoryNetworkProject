package model.file.serializers.gene;

import model.file.serializers.EntitySerializer;
import model.genes.ConcreteRegulatoryGene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;


public class ConcreteRegulatoryGeneSerializer implements EntitySerializer<ConcreteRegulatoryGene> {
    private static ConcreteRegulatoryGeneSerializer instance;
    //Singleton: only one object copy exists
    // default private constructor
    private ConcreteRegulatoryGeneSerializer(){}


    //class name of object to be (de)serialised
    @Override
    public String getCode() {
        return "ConcreteRegulatoryGene";
    }

    //return string describing serializable object

    @Override
    public String serialize(ConcreteRegulatoryGene entity, RegulatoryNetworkWriter writer) {
        return getCode() + " "  + entity.getName() + " "  +
                entity.getInitialProteinConcentration() + " "
                + entity.getMaximalProduction() + " "
                + entity.getDegradationRate() + " "
                + entity.getInitialIsSignaled();

    }

    // get the singleton
    public static ConcreteRegulatoryGeneSerializer getInstance(){
        if (instance == null) {
            instance = new ConcreteRegulatoryGeneSerializer();
        }
        return instance;
    }

    //rebuild an object from a string
    @Override
    public ConcreteRegulatoryGene deserialize(String string, RegulatoryNetworkReader reader) {
        String[] tokens = string.split(" ");
        return new ConcreteRegulatoryGene(tokens[1],
                                            Double.parseDouble(tokens[3]),
                                                    Double.parseDouble(tokens[4]),
                                                        Double.parseDouble(tokens[2]),
                                                                Boolean.parseBoolean(tokens[5]));
    }

}
