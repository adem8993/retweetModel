import com.fasterxml.jackson.core.JsonProcessingException;
import model.Tweet;
import twitter4j.Status;
import twitter4j.TweetEntity;
import twitter4j.TwitterException;
import util.IOUtil;
import util.StringUtil;
import util.TweetUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Adem on 8.9.2016.
 */
public class Twitter4j {
    private static final int NUMBER_OF_TWEETS = 700;

    public static void main(String args[]) {
        String[] userList = {"berrakue", "iyiTweet", "falanca"};
        List<String> lines = new ArrayList<>();

        try {
            for (String userName : userList) {
                List<Status> statuses = TweetUtil.getTweetStatusesByUserName(userName, NUMBER_OF_TWEETS * 2);
                AtomicInteger tweetCount = new AtomicInteger(0);
                statuses.stream().forEach(status -> {
                    if (!status.isRetweet() && !status.getText().startsWith("@") && tweetCount.get() < NUMBER_OF_TWEETS) {

                        try {
                            String line = TweetUtil.getTweetByStatus(status);
                            if (line != "") {
                                lines.add(line);
                                tweetCount.getAndIncrement();
                            }
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            IOUtil.writeLinesToFile("a.json", lines);
        } catch (TwitterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
