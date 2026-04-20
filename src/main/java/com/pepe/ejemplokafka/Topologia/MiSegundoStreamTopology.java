package com.pepe.ejemplokafka.Topologia;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pepe.ejemplokafka.Modelos.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.stereotype.Component;

import java.util.Map;

import static org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES;

@Component
@Slf4j
public class MiSegundoStreamTopology {
    public static String MENSAJE = "pruebitaJson";
    public static String OUTPUT_MENSAJE = "output-mensajeJson";
    @Autowired
    public void procesar (StreamsBuilder streamsBuilder){
        //Solución alternativa formal
        /*
        ObjectMapper mapper = new ObjectMapper();
        mapper.activateDefaultTyping(
                mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );
        mapper.deactivateDefaultTyping();
        JsonSerde<Usuario> jsonSerde = new JsonSerde<>(Usuario.class, mapper);
        jsonSerde.configure(Map.of(TRUSTED_PACKAGES, "com.wintux.ejemplo.DTO"), false);
        KStream<String, Usuario> mensajeStream2 = streamsBuilder
                .stream(MENSAJE, Consumed.with(Serdes.String(), jsonSerde));
        */
        JsonSerde<Usuario> usuarioSerde = new JsonSerde<>(Usuario.class);
        usuarioSerde.deserializer().addTrustedPackages("*");
        KStream<String, Usuario> mensajeStream = streamsBuilder
                .stream(MENSAJE, Consumed.with(Serdes.String(), usuarioSerde));
        // Mostrar el mensaje original
        mensajeStream.print(Printed.<String,Usuario>toSysOut().withLabel("original"));
        KStream<String,Usuario> mensajeModificadoStream =
                mensajeStream.mapValues((llave, valor)->
                        new Usuario(
                                valor.getId(),
                                valor.getNombre().toUpperCase(),
                                valor.getApellido().toUpperCase()
                        )
                );
        // Mostrar el mensaje modificado
        mensajeModificadoStream.print(Printed.<String,Usuario>toSysOut().withLabel("modificado"));
        mensajeModificadoStream.to(OUTPUT_MENSAJE, Produced.with(Serdes.String(), new JsonSerde<>(Usuario.class)));
        // http://localhost:8080/api/v1/kafka/publicar?mensajito=Pepe
    }

}