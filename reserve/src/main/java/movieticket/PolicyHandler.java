package movieticket;

import movieticket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @Autowired ReserveRepository reserveRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverAssigned_UpdateStatus(@Payload Assigned assigned){

        if(assigned.isMe()){
        	Optional<Reserve> optional = reserveRepository.findById(assigned.getReserveId());
            Reserve reserve = optional.get();
        	reserve.setStatus("updateStatus Policy Info : Cinema Assigned");              
            reserveRepository.save(reserve);        	
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverPayCancelled_UpdateStatus(@Payload PayCancelled payCancelled){

        if(payCancelled.isMe()){
        	Optional<Reserve> optional = reserveRepository.findById(payCancelled.getReserveId());
            Reserve reserve2 = optional.get();
        	reserve2.setStatus("updateStatus Policy Info : PayCancelled");              
            reserveRepository.save(reserve2);        	
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}
}
