/**
 * Created by daras on 14-Mar-16.
 */
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SparkStreaming  {
    int count =0;
List<Status> GetData(String Keyword)
{
	 List<Status> Data = new ArrayList<>();

	try
	{
		String Key[] = {Keyword};
		ConfigurationBuilder Build = new ConfigurationBuilder();
		Build.setDebugEnabled(true)
				.setOAuthConsumerKey("VH6GiAICrhCuymPApBoUmovST")
				.setOAuthConsumerSecret("svtDBD1q041UYZFlIHfyA104JHsNML16l1OtyrkXbKfcuunEtQ")
				.setOAuthAccessToken("476949496-B3uaZcX9ssxxu4D1vnQK6YdGocECPzux8nVnF3RS")
				.setOAuthAccessTokenSecret("e9PGyfR7I95KoJDaNw92EVvLLTXQKXJyqL1q1uCsJaVX5");

		TwitterFactory Tf = new TwitterFactory(Build.build());
		Twitter ObjTwitter = Tf.getInstance();

		// Query ObjQuery = new Query(Keyword + " -filter:links -filter:replies - filter:images");
		Query ObjQuery = new Query(Keyword );
	   ObjQuery.setCount(100);
	   ObjQuery.setLang("en");
		ObjQuery.resultType("MIXED");

		QueryResult Objresult = ObjTwitter.search(ObjQuery);
		Data = Objresult.getTweets();

	}catch (Exception ex)
	{
		ex.printStackTrace();
	}
	return Data;
}

	public void Stream(String KeyWords[])
	{
		try
		{

			ConfigurationBuilder Build = new ConfigurationBuilder();
			Build.setDebugEnabled(true)
					.setOAuthConsumerKey("VH6GiAICrhCuymPApBoUmovST")
					.setOAuthConsumerSecret("svtDBD1q041UYZFlIHfyA104JHsNML16l1OtyrkXbKfcuunEtQ")
					.setOAuthAccessToken("476949496-B3uaZcX9ssxxu4D1vnQK6YdGocECPzux8nVnF3RS")
					.setOAuthAccessTokenSecret("e9PGyfR7I95KoJDaNw92EVvLLTXQKXJyqL1q1uCsJaVX5");

			TwitterStream tf =  new TwitterStreamFactory(Build.build()).getInstance();


			FilterQuery ObjFilterQuey = new FilterQuery();
			ObjFilterQuey.track(KeyWords);
			ObjFilterQuey.language(new String[]{"en"});
             int Countter = 0;

           /* final File Fl = new File("C:\\Users\\daras\\Desktop\\tweets.txt");
            final FileWriter Fw = new FileWriter(Fl);*/
			tf.addListener(new StatusListener() {
				@Override
				public void onStatus(Status status) {

                        WritetoFile(status);
                }

				@Override
				public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

				}

				@Override
				public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

				}

				@Override
				public void onScrubGeo(long userId, long upToStatusId) {

				}

				@Override
				public void onStallWarning(StallWarning warning) {

				}

				@Override
				public void onException(Exception ex) {

				}
			});
			tf.filter(ObjFilterQuey);

		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public void WritetoFile(Status status)
    {
        try
        {


            File file =  new File("F:\\TweetData\\Test.txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            else
            {
                FileWriter Fw = new FileWriter(file,true);
                Fw.write(status.toString()+ "\n\n");
                Fw.close();
            }

            count++;
            if(count%10 == 0) {
                System.out.println(count);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
