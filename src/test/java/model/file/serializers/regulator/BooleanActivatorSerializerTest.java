package model.file.serializers.regulator;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConcreteRegulatoryGene;
import model.regulators.BooleanActivator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BooleanActivatorSerializerTest {

    @Test
    public void testSerialize() {
        ConcreteRegulatoryGene gene2 = new ConcreteRegulatoryGene("Y",
                12.,0.5,2.1,true);
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        BooleanActivator regulator = new BooleanActivator(12.0,gene2);

        assertEquals("BooleanActivator 12.0 Y",
                BooleanActivatorSerializer.getInstance().serialize(regulator,writer));

    }

    @Test
    public void testDeserialize() {
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        ConcreteRegulatoryGene gene2 = new ConcreteRegulatoryGene("Y",
                12.,0.5,2.1,true);
        BooleanActivator expected_reg = new BooleanActivator(12., gene2);
        reader.addGene(gene2);
        String line = "X BooleanActivator 12.0 Y";

        assertEquals("BooleanActivator", BooleanActivatorSerializer.getInstance().deserialize(line, reader).getClass().getSimpleName());
        assertEquals(BooleanActivatorSerializer.getInstance().deserialize(line, reader), expected_reg);

    }

}
