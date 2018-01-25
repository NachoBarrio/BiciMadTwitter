


import org.apache.kafka.common.serialization.StringDeserializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
public class SimpleConsumer {

	public static void main(String[] args)  throws Exception {
	      runConsumer();
	  }
	
	private static KafkaConsumer<String, String> createConsumer(){
		// TODO Auto-generated method stub
		final String TOPIC = "SDTF";
		
		
		final Properties props = new Properties();
	      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
	                                  "localhost:9092");
	      props.put(ConsumerConfig.GROUP_ID_CONFIG,
	                                  UUID.randomUUID().toString());
	      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
	    		  StringDeserializer.class.getName());
	      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
	              StringDeserializer.class.getName());


	      // Subscribe to the topic.
	      KafkaConsumer<String, String> consumer;
	      consumer = new KafkaConsumer<>(props);
	      
	      List<String> topics = Arrays.asList("prueba2", "SDTF");
	    		  consumer.subscribe(topics);
	      return consumer;
	}
	
	static void runConsumer() throws InterruptedException {
		KafkaConsumer<String, String> consumer = createConsumer();

        final int giveUp = 100;   int noRecordsCount = 0;

        while (true) {
            ConsumerRecords<String, String> consumerRecords = 
                    consumer.poll(10);

            if (consumerRecords.count()==0) {
                noRecordsCount++;
                if (noRecordsCount > giveUp) break;
                else continue;
            }
            System.out.printf("records: "+consumerRecords.count());
            consumerRecords.forEach(record -> {
                System.out.printf("Consumer Record:(%d, %s, %d, %d)\n",
                        record.key(), record.value(),
                        record.partition(), record.offset());
            });

            consumer.commitAsync();
        }
        consumer.close(); 
        System.out.println("DONE");
    }

}
