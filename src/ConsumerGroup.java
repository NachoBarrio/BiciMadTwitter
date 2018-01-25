
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.utils.threadsafe;

public class ConsumerGroup {


	public static void main(String[] args) {
		ExecutorService ServiceSanAndres = Executors.newFixedThreadPool(4); 
		
		TweetProducer tweetProducer1 = new TweetProducer("4iG1bxT8sFHqzvE8zDZdgukRZ", "XrqUR46laIwljQj5diCoB0P0b2zTT8h5dvgNDctL25bJUTs9UH", "922932077624004610-knJJcL4Qbm2WgsgL30BzC6VJHrZwciQ", "ljhH1BcuA7qfWMcRyf97nKmoydyvSdAW0mL3HKxskTybF"); 
					
		
		TweetProducer tweetProducer2 = new TweetProducer("cK8UJss3WQscOABM6DlIMzc7j", "Bsvkhry45bfWT4zUKK7YlW0Wb96ik3ki6mqsTQALdfrmEYPqjt", "922936089085661185-lLx1uOGO0ObSAs3D5OPNqyejhyjaTEK", "gLQ1rfHZVgCFIqDpcTfBy6eyIZf6ueb3w1ZG6HueArd2l");
		
		
		
		
		
		ServiceSanAndres.execute(new Runnable() { 
			public void run() { 
				ConsumerBiciMad.consume("bicimad1", "PuertaDelSol",tweetProducer1);
				} }); 
		ServiceSanAndres.execute(new Runnable() { 
			public void run() { 
				ConsumerBiciMad.consume("bicimad1", "PuertaDelSol",tweetProducer1);
				} });
		
		
		ServiceSanAndres.execute(new Runnable() { 
			public void run() { 
				ConsumerBiciMad.consume("bicimad2", "SanAndres",tweetProducer2);
				} }); 
		ServiceSanAndres.execute(new Runnable() { 
			public void run() { 
				ConsumerBiciMad.consume("bicimad2", "SanAndres",tweetProducer2);
				} });
		ServiceSanAndres.shutdown();
		
		
	}

	
	
}
