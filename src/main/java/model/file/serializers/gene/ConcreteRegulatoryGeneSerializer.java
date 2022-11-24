package model.file.serializers.gene;

import model.genes.ConcreteRegulatoryGene;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;

public class ConcreteRegulatoryGeneSerializer implements EntitySerializer<ConcreteRegulatoryGene> {
    private static ConcreteRegulatoryGeneSerializer instance;
    //Singleton: only one object copy exists
    // default private constructor
    private static void ConcreteRegulatoryGeneSerializer(){}

    //class name of object to be (de)serialised ( needed for the reader )
    @Override
    public String getCode() {
        return getInstance().getClass().getSimpleName(); //to be changed
    }

    //return string describing serializable object
    @Override
    public String serialize(ConcreteRegulatoryGene entity, RegulatoryNetworkWriter writer) {
        return entity.getClass().getSimpleName() + " " + entity.getInfo();
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

        return null;
    }


}
