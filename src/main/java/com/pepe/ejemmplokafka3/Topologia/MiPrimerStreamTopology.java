package com.pepe.ejemmplokafka3.Topologia;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MiPrimerStreamTopology {
    public static String MENSAJE = "mensaje";
    public static String OUTPUT_MENSAJE = "output-mensaje";
    @Autowired
    public void procesar (StreamsBuilder streamsBuilder){
        KStream<String,String> mensajeStream = streamsBuilder
                .stream(MENSAJE, Consumed.with(Serdes.String(), Serdes.String()));
    }
    // Continuar mostrando mensajes
}
