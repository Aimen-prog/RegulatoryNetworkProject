package model.file.serializers.event;
import model.events.SetProteinConcentrationEvent;
import model.events.SetSignaledEvent;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SetSignaledEventSerializerTest {

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

        SetSignaledEvent event = new SetSignaledEvent(genes,13., false);
        assertThat( SetSignaledEventSerializer.getInstance().serialize(event,writer))
                .isEqualTo("SetSignaledEvent 13.0 [INS,AGR] false");



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
        SetSignaledEvent expected_event =
                new SetSignaledEvent ( genelist, 13., false);

        String line = "SetSignaledEvent 13.0 [INS,AGR] false";
        SetSignaledEvent actual_event =
                SetSignaledEventSerializer.getInstance()
                        .deserialize(line,reader);
        assertNotNull(actual_event);
        assertTrue(expected_event.equals(actual_event));


    }


}
