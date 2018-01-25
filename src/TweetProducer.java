

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetProducer {

	private String consumerKeyStr;
	private String consumerSecretStr;
	private String accessTokenStr;
	private String accessTokenSecretStr;
	private Twitter twitter;

	public TweetProducer(String consumerKeyStr,String consumerSecretStr, 
		String accessTokenStr,String accessTokenSecretStr) {
		this.consumerKeyStr = consumerKeyStr;
		this.consumerSecretStr = consumerSecretStr;
		this.accessTokenStr = accessTokenStr;
		this.accessTokenSecretStr = accessTokenSecretStr;
	}

	public Twitter authenticate() {
		twitter = new TwitterFactory().getInstance();

		twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
		AccessToken accessToken = new AccessToken(accessTokenStr,
				accessTokenSecretStr);

		twitter.setOAuthAccessToken(accessToken);
		
		return twitter;
	}
	
	public void PostTweet(String message) {
		try {			
			twitter.updateStatus(message);
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Successfully updated the status in Twitter.");
	}
}

