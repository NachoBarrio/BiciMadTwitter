import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONException;
import org.xml.sax.SAXException;

public class BiciMadReader {
	
	Properties props;
	// The producer is a Kafka client that publishes records to the Kafka
	// cluster.
	KafkaProducer<String, String> producer;
	BiciMadReader() {
	Properties props = new Properties();
	props.put("bootstrap.servers", "localhost:9092");
	// Serializer for conversion the key type to bytes
	props.put(ConsumerConfig.GROUP_ID_CONFIG,
            "Group1");
	props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	// Serializer for conversion the value type to bytes
	props.put("value.serializer",
	"org.apache.kafka.common.serialization.StringSerializer");
	
	producer = new KafkaProducer<>(props);
	
	
	
	}
	void produceAndPrint() {
	
	// Fire­and­forget send(topic, key, value)
	// Send adds records to unsent records buffer and return
		try {
			
			/** each stations is a producer connected to the kafka system **/
			Stations stations = BidiMadFeed.getStations();
			
			for (int i=1;i<= stations.getStations().size();i++) {
				producer.send(new ProducerRecord<String,String>("bicimad"+i, 
						String.valueOf(i), stations.stations.get(i-1).JsonStation));
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	void stop() {
	producer.close();
	}
	public static void main(String[] args) {
	BiciMadReader myProducer = new BiciMadReader();
	myProducer.produceAndPrint();
	myProducer.stop();
	}
	
	
	
	
}
