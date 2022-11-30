package model.file.serializers.regulator;
import model.file.reader.RegulatoryNetworkReader;
import model.file.writer.RegulatoryNetworkWriter;
import model.genes.ConstantRegulatoryGene;
import model.regulators.AlwaysOffRegulator;
import model.regulators.AlwaysOnRegulator;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


public class AlwaysOnRegulatorSerializerTest {

    @Test
    public void testSerialize() {
        RegulatoryNetworkWriter writer = new RegulatoryNetworkWriter();
        AlwaysOnRegulator regulator = new AlwaysOnRegulator();
        ;
        assertThat(AlwaysOnRegulatorSerializer.getInstance().serialize(regulator,writer))
                .isEqualTo("AlwaysOnRegulator");

    }

    @Test
    public void testDeserialize() {
        ConstantRegulatoryGene gene1 = new ConstantRegulatoryGene("X",
                12.,true);
        RegulatoryNetworkReader reader = new RegulatoryNetworkReader();
        assertTrue(AlwaysOnRegulatorSerializer.getInstance().
                deserialize("",reader).getClass().getSimpleName().equals("AlwaysOnRegulator"));
        assertTrue(AlwaysOnRegulatorSerializer.getInstance().
                deserialize("",reader).inputFunction()==1.);

    }





}
