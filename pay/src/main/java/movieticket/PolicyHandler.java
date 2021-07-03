package movieticket;

import movieticket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    PaymentRepository paymentRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReserveCancelled_PayCancel(@Payload ReserveCancelled reserveCancelled){

        if(reserveCancelled.isMe()){
            List<Payment> list = paymentRepository.findByReserveId(reserveCancelled.getId());
            
            for(Payment payment : list){
            	payment.setCancelYn("Y");           
            	paymentRepository.save(payment);
            }
            
        }
            
    }
}
