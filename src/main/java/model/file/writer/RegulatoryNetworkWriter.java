package model.file.writer;

import model.events.SimulationEvent;
import model.genes.RegulatoryGene;
import model.network.RegulatoryNetwork;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class RegulatoryNetworkWriter {

    private GeneVisitor geneVisitor = new ConcreteGeneVisitor(this);
    private EventVisitor eventVisitor = new ConcreteEventVisitor(this);
    private RegulatorVisitor regulatorVisitor = new ConcreteRegulatorVisitor(this);

    // METHOD section
    public RegulatoryNetworkWriter(){}

    public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
        writeConfiguration(bufferedWriter, regulatoryNetwork);
        writeGenes(bufferedWriter, regulatoryNetwork);
        writeRegulators(bufferedWriter, regulatoryNetwork);
        writeEvents(bufferedWriter, regulatoryNetwork);

    }

    // write genes using geneVisitor
    private void writeGenes(BufferedWriter bufferedWriter,RegulatoryNetwork regulatoryNetwork) throws IOException {
        for(RegulatoryGene gene : regulatoryNetwork.getGenes()){
            String geneString = gene.accept(geneVisitor);
            geneString += "\n";
            bufferedWriter.write(geneString);

        }
    }

    // write events using geneVisitor
    private void writeEvents(BufferedWriter bufferedWriter,RegulatoryNetwork regulatoryNetwork) throws IOException {
        for(SimulationEvent event : regulatoryNetwork.getSimulationEvents()){
            String eventString = event.accept(eventVisitor);
            eventString += "\n";
            bufferedWriter.write(eventString);

        }
    }

    private void writeRegulators(BufferedWriter bufferedWriter,RegulatoryNetwork regulatoryNetwork) throws IOException {
        for(RegulatoryGene gene : regulatoryNetwork.getGenes()){
            String regulatorString ="";
            if ( gene.getRegulator() != null ) {
                regulatorString += gene.getName() + " "
                        + gene.getRegulator().accept(regulatorVisitor)+ "\n";
            }
            bufferedWriter.write(regulatorString);
        }
    }


    // write TimeStep and TimeUpperBound
    private void writeConfiguration(BufferedWriter bufferedWriter ,RegulatoryNetwork regulatoryNetwork)throws IOException {
        bufferedWriter.write("TimeStep " + regulatoryNetwork.getTimeStepLength() + "\n");
        bufferedWriter.write("TimeUpperBound " + regulatoryNetwork.getTimeUpperBound() + "\n");

    }
}
