package model.file.reader;

import model.events.SimulationEvent;
import model.file.serializers.event.SetProteinConcentrationEventSerializer;
import model.file.serializers.event.SetSignaledEventSerializer;
import model.file.serializers.gene.ConcreteRegulatoryGeneSerializer;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.file.serializers.EntitySerializer;
import model.genes.RegulatoryGene;
import model.network.RegulatoryNetwork;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegulatoryNetworkReader {
    private Map<String, EntitySerializer<? extends RegulatoryGene>> geneSerializers = new HashMap<>();
    private Map<String, RegulatoryGene> genes ;

    private Map<String, EntitySerializer <? extends SimulationEvent>> eventSerializers = new HashMap<>();
    private List<SimulationEvent> events = new ArrayList<>();;

    public void RegulatoryNetworkReader(){}


    private void addGeneSerializer(EntitySerializer<? extends RegulatoryGene> serializer ){
        geneSerializers.put(serializer.getCode(), serializer);
    }
    private EntitySerializer<? extends RegulatoryGene> getGeneSerializer(String code ){
        return geneSerializers.get(code);
    }

    public void addGene(RegulatoryGene gene){
        genes.put(gene.getName(),gene);
    }
    public RegulatoryGene getGene (String geneName){
        return genes.get(geneName);
    }


    public RegulatoryNetwork read(BufferedReader bufferedReader) throws IOException {
        genes =  new HashMap<>();
        double timeUpperBound = 20; double timeStepLength = 0.01;
        int lineNumber = 0;

        for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()){
            String[] tokens = line.split(" ");
            switch (tokens[0]){
                case "TimeStep" -> timeStepLength = Double.parseDouble(tokens[1]);
                case "TimeUpperBound" -> timeUpperBound = Double.parseDouble(tokens[1]);
                case "ConstantRegulatoryGene" -> {
                    addGeneSerializer(ConstantRegulatoryGeneSerializer.getInstance());
                    addGene(ConstantRegulatoryGeneSerializer.getInstance().deserialize(line,this));
                }
                case "ConcreteRegulatoryGene" -> {
                    addGeneSerializer(ConcreteRegulatoryGeneSerializer.getInstance());
                    addGene(ConcreteRegulatoryGeneSerializer.getInstance().deserialize(line,this));
                }
                case "SetProteinConcentrationEvent" -> events.add(SetProteinConcentrationEventSerializer.getInstance().deserialize(line,this));
                case "SetSignaledEvent" -> events.add(SetSignaledEventSerializer.getInstance().deserialize(line,this));
                default -> throw new IOException("Parse error line " + lineNumber);
            }
        }
        return new RegulatoryNetwork(new ArrayList<>(genes.values()), events, timeStepLength, timeUpperBound);
    }
}
