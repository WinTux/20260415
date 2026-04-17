package com.pepe.ejemmplokafka3.Configuraciones;

import com.pepe.ejemmplokafka3.Topologia.MiPrimerStreamTopology;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class MensajeStreamConfiguration {
    @Bean
    public NewTopic mensajeTopic(){
        return TopicBuilder.name(MiPrimerStreamTopology.MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
    @Bean
    public NewTopic mensajeSalidaTopic(){
        return TopicBuilder.name(MiPrimerStreamTopology.OUTPUT_MENSAJE)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
