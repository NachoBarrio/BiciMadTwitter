
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;
import java.util.Date;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import scala.annotation.StaticAnnotation;
import twitter4j.Twitter;

public class ConsumerBiciMad {
	// http://syncor.blogspot.com.es/2013/09/getting-started-with-log4j-2-in-eclipse.html
	public static TweetProducer tweetProducer;
	public static String[] ocupacion = new String[]{ "baja","media","alta"};
	public static String  consume(String topic, String group,TweetProducer tweetProducer) {

		// A client that consumes records from a Kafka cluster using TCP
		// connections.
		// This client transparently handles the failure of Kafka brokers and
		// partition topic migration
		
		System.setProperty("kafka.logs.dir", "user/home/logs");


		KafkaConsumer<String, String> consumer;

		Properties consumerConfig;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		consumerConfig = new Properties();
		consumerConfig.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"localhost:9092");
		// consumerConfig
		// .setProperty(ConsumerConfig.GROUP_ID_CONFIG, "Grupo1");
		//consumerConfig.setProperty(ConsumerConfig.GROUP_ID_CONFIG, UUID
		//		.randomUUID().toString());
		consumerConfig.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group);
		consumerConfig.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
				"earliest");
		consumerConfig.setProperty(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		consumerConfig.setProperty(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		consumerConfig.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		consumerConfig.setProperty("auto.commit.interval.ms", "1000");

		consumer = new KafkaConsumer<>(consumerConfig);

		consumer.subscribe(Arrays.asList(topic));
		System.out.println("topic "+topic+" para thread "+Thread.currentThread().getId());
		
		tweetProducer.authenticate();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
		
			for (ConsumerRecord<String, String> record : records) {
				
				/*System.out.printf("offset = %d, key = %s, value = %s, partition = %s hilo: "+Thread.currentThread().getId()+"%n",
						record.offset(), record.key(), record.value(), record.partition());*/
				
				String JsonFormated = record.value().toString().replace("\\", "");
		        JSONObject json;
				try {
					json = new JSONObject(record.value());
					String tweet;
					Date now = new Date();
					if(json.getInt("activate") == 0) {
						tweet = "La estacion se encuenta inactiva";
					}else {
						String disp = json.getInt("free_bases") > 0 ? " aún hay "+json.getInt("free_bases")+" bicis disponibles" :
							 "no hay bicis disponibles, no te lo perdonaré Carmena";
						tweet = "El grado de ocupacion es "+ocupacion[json.getInt("light")]+disp+" son las "+df.format(now);
					}
					tweetProducer.PostTweet(tweet);
					//System.out.println(tweet);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    
				
			}
			
			
			
			/*try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
			//consumer.close();
		
	} 
	
}