package model.file.serializers.regulator;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import model.file.reader.RegulatoryNetworkReader;
import model.file.serializers.list.ListGeneSerializer;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.genes.RegulatoryGene;
import model.regulators.AlwaysOffRegulator;
import org.junit.jupiter.api.Test;

public class AlwaysOffRegulatorSerializerTest {

    @Test
    public void testSerialize() {
        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        AlwaysOffRegulator regulator = new AlwaysOffRegulator();
        assertThat(AlwaysOffRegulatorSerializer.getInstance().serialize(regulator,writer))
                .isEqualTo("AlwaysOffRegulator");

    }

    @Test
    public void testDeserialize() {
        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        AlwaysOffRegulator regulator = new AlwaysOffRegulator();
        assertTrue(AlwaysOffRegulatorSerializer.getInstance().
                deserialize("",reader).getClass().getSimpleName().equals("AlwaysOffRegulator"));
        assertTrue(AlwaysOffRegulatorSerializer.getInstance().
                deserialize("",reader).inputFunction()==0.);

    }
}
