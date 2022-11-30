package model.file.serializers.event;
import model.events.AbstractSimulationEvent;
import model.events.SetProteinConcentrationEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.gene.ConstantRegulatoryGeneSerializer;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SetProteinConcentrationEventSerializerTest {

    @Test
    public void testSerialize() {
        List<RegulatoryGene> genes = new ArrayList<>();
        ConstantRegulatoryGene gene1 =
                new ConstantRegulatoryGene("INS",5.1, true);
        ConstantRegulatoryGene gene2 =
                new ConstantRegulatoryGene("AGR",5.1, true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        genes.add(gene1);
        genes.add(gene2);

        SetProteinConcentrationEvent event = new SetProteinConcentrationEvent(genes,13., 12.);
        assertThat( SetProteinConcentrationEventSerializer.getInstance().serialize(event,writer))
                .isEqualTo("SetProteinConcentrationEvent 13.0 [INS,AGR] 12.0");


    }
    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();

        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        ConstantRegulatoryGene gene2 = new ConstantRegulatoryGene("Y",
                17.,true);
        List<RegulatoryGene> genelist = new ArrayList<>();
        genelist.add(gene1); genelist.add(gene2);
        SetProteinConcentrationEvent expected_event =
                new SetProteinConcentrationEvent ( genelist, 13., 12.);

        String line = "SetProteinConcentrationEvent 13.0 [INS,AGR] 12.0";
        SetProteinConcentrationEvent actual_event =
                SetProteinConcentrationEventSerializer.getInstance()
                .deserialize(line,reader);
        assertNotNull(actual_event);
        assertTrue(expected_event.equals(actual_event));

    }
}
