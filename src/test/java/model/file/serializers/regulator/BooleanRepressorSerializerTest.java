package model.file.serializers.regulator;

import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import model.regulators.BooleanRepressor;
import model.regulators.BooleanRepressor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BooleanRepressorSerializerTest {

    @Test
    public void testSerialize() {

        ConcreteRegulatoryGene gene2 = new ConcreteRegulatoryGene("Y",
                12.,0.5,2.1,true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        BooleanRepressor regulator = new BooleanRepressor(12.0,gene2);

        assertEquals("BooleanRepressor 12.0 Y",
                BooleanRepressorSerializer.getInstance().serialize(regulator,writer));

    }

    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        ConcreteRegulatoryGene gene2 = new ConcreteRegulatoryGene("Y",
                12.,0.5,2.1,true);
        BooleanRepressor expected_reg = new BooleanRepressor(12., gene2);
        reader.addGene(gene2);
        String line = "X BooleanRepressor 12.0 Y";
        assertTrue(BooleanRepressorSerializer.getInstance().deserialize(line,reader).getClass().getSimpleName()
                .equals("BooleanRepressor"));
        assertTrue(BooleanRepressorSerializer.getInstance().deserialize(line,reader)
                .equals(expected_reg));



    }
}
