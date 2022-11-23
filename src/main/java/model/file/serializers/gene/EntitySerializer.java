package model.file.serializers.gene;

import model.network.RegulatoryNetworkReader;
import model.network.RegulatoryNetworkWriter;

public interface EntitySerializer<E> {
    String getCode () ;
    String serialize (E entity , RegulatoryNetworkWriter writer) ;
    E deserialize (String string , RegulatoryNetworkReader reader) ;

}
