package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Tweet;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adem on 8.9.2016.
 */
public final class TweetUtil {

    public static List<Status> getTweetStatusesByUserName(String userName, int numberRequestedTweets) throws TwitterException {
        Twitter unauthenticatedTwitter = new TwitterFactory().getInstance();
        List<Status> statuses = new ArrayList<>();
        int pageNumber = 1;
        while(statuses.size() < numberRequestedTweets && pageNumber < ((numberRequestedTweets / 200))+1) {
            Paging paging = new Paging(pageNumber++, 200);
            statuses.addAll(unauthenticatedTwitter.getUserTimeline(userName, paging));
        }

        return statuses;
    }

    public static String getTweetByStatus(Status status)  throws JsonProcessingException{
        Tweet tweet = new Tweet();
        tweet.setCreateDate(status.getCreatedAt());
        tweet.setRetweetCount(status.getRetweetCount());
        tweet.setText(clearTweetContent(status));
        tweet.setUserName(status.getUser().getName());
        if(tweet.getText() != "") {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(tweet);
        }
        return "";
    }

    private static String removeURLFromText(Status status) {
        if(status.getMediaEntities().length == 0) return status.getText();

        return status.getText().replace(status.getMediaEntities()[0].getURL(), "");
    }

    private static boolean checkNumberOfWordsInText(String text) {
        return StringUtil.countNumberOfWords(text) > 5;
    }

    /**
     * Tweet'in içinde bulunan media URL'ini siler ve kelime sayısı az olan tweet'leri eler.
     *
     * @param status
     * @return
     * @throws JsonProcessingException
     */
    private static String clearTweetContent(Status status) throws JsonProcessingException{
        String text = removeURLFromText(status);
        if(!checkNumberOfWordsInText(text)) {
            text = "";
        }
        return text;
    }
}
