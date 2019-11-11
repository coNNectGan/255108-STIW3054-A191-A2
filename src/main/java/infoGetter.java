import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class infoGetter{

    static String[] getInfo(String url){

        String[] userData = new String[6];

        while (true) {
            try {
                final Document document = Jsoup.connect(url + "?access_token=cdea46c74ec89bb3e076fbd77ade35d181e77af7").ignoreContentType(true).get();

                Pattern loginId = Pattern.compile("(\"login\": \")(.*)(\", \"id\":)");
                Matcher matchLoginId = loginId.matcher(document.text());
                if (matchLoginId.find()) {
                    userData[0] = matchLoginId.group(2);
                }

                Pattern repo = Pattern.compile("(\"public_repos\": )(\\d+)");
                Matcher matchRepo = repo.matcher(document.text());
                if (matchRepo.find()) {
                    userData[1] = matchRepo.group(2);
                }

                Pattern followers = Pattern.compile("(\"followers\": )(\\d+)");
                Matcher matchFollowers = followers.matcher(document.text());
                if (matchFollowers.find()) {
                    userData[2] = matchFollowers.group(2);
                }

                Pattern following = Pattern.compile("(\"id\": )(\\d+)");
                Matcher matchFollowing = following.matcher(document.text());
                if (matchFollowing.find()) {
                    userData[3] = matchFollowing.group(2);
                }

                Pattern createdAt = Pattern.compile("(\"type\": \")(.*)(\", \"site_admin\":)");
                Matcher matchCreatedAt = createdAt.matcher(document.text());
                if (matchCreatedAt.find()) {
                    userData[4] = matchCreatedAt.group(2).replaceAll("T", " ").replaceAll("Z", "");
                }

                Pattern followers_url = Pattern.compile("(\"followers_url\": \")(.*)(\", \"following_url\":)");
                Matcher matchFollowers_url = followers_url.matcher(document.text());
                if (matchFollowers_url.find()) {
                    userData[5] = matchFollowers_url.group(2);
                }

                break;

            } catch (Exception ignored) { }
        }

        return userData;
    }
}