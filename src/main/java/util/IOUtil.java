package util;

import model.Tweet;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adem on 8.9.2016.
 */
public final class IOUtil {

    public static void writeLinesToFile(String path, List<String> lines) throws IOException {
        writeLinesToFile(path, lines, Charset.forName("UTF8"));
    }

    public static void writeLinesToFile(String path, List<String> lines, Charset charset) throws IOException {
        Path file = Paths.get(path);
        Files.write(file, lines, charset);
    }

    public static void writeTweetsToFile(String path, List<Tweet> tweets) throws IOException {
        List<String> lines = new ArrayList<>();
        tweets.forEach(tweet -> lines.add(tweet.toString()));
        writeLinesToFile(path, lines);
    }
}
