package model.file.writer;

import model.events.SimulationEvent;
import model.genes.RegulatoryGene;
import model.network.RegulatoryNetwork;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class RegulatoryNetworkWriter {

    private GeneVisitor geneVisitor = new ConcreteGeneVisitor(this);

    // METHOD section
    public RegulatoryNetworkWriter(){}

    public void write(BufferedWriter bufferedWriter, RegulatoryNetwork regulatoryNetwork) throws IOException {
        writeConfiguration(bufferedWriter, regulatoryNetwork);
        writeGenes(bufferedWriter, regulatoryNetwork);

    }

    // écrire les gènes en utilisant le visiteur geneVisitor
    private void writeGenes(BufferedWriter bufferedWriter,RegulatoryNetwork regulatoryNetwork) throws IOException {
        for(RegulatoryGene gene : regulatoryNetwork.getGenes()){
            String geneString = gene.accept(geneVisitor);
            geneString += "\n";
            bufferedWriter.write(geneString);

        }
    }


    // write TimeStep and TimeUpperBound
    private void writeConfiguration(BufferedWriter bufferedWriter ,RegulatoryNetwork regulatoryNetwork)throws IOException {
        bufferedWriter.write("TimeStep " + regulatoryNetwork.getTimeStepLength() + "\n");
        bufferedWriter.write("TimeUpperBound " + regulatoryNetwork.getTimeUpperBound() + "\n");

    }
}
