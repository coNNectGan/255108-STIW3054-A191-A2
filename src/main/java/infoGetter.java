import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class infoGetter{

    static String[] getInfo(String url){

        String[] userData = new String[6];

        while (true) {
            try {
                final Document document = Jsoup.connect(url + "?access_token=452f8e4c1d165937b1f645b6d696dfc3aa5dca74").ignoreContentType(true).get();

                Pattern loginID = Pattern.compile("(\"login\": \")(.*)(\", \"id\":)");
                Matcher LoginiD = loginID.matcher(document.text());
                if (LoginiD.find()) {
                    userData[0] = LoginiD.group(2);
                }

                Pattern rePo = Pattern.compile("(\"public_repos\": )(\\d+)");
                Matcher Repo = rePo.matcher(document.text());
                if (Repo.find()) {
                    userData[1] = Repo.group(2);
                }

                Pattern folloWers = Pattern.compile("(\"followers\": )(\\d+)");
                Matcher Followers = folloWers.matcher(document.text());
                if (Followers.find()) {
                    userData[2] = Followers.group(2);
                }

                Pattern idNum = Pattern.compile("(\"id\": )(\\d+)");
                Matcher IDnum = idNum.matcher(document.text());
                if (IDnum.find()) {
                    userData[3] = IDnum.group(2);
                }

                Pattern typeUser = Pattern.compile("(\"type\": \")(.*)(\", \"site_admin\":)");
                Matcher useR = typeUser.matcher(document.text());
                if (useR.find()) {
                    userData[4] = useR.group(2).replaceAll("T", " ").replaceAll("Z", "");
                }

                Pattern followersNum = Pattern.compile("(\"followers_url\": \")(.*)(\", \"following_url\":)");
                Matcher matchFollowers_url = followersNum.matcher(document.text());
                if (matchFollowers_url.find()) {
                    userData[5] = matchFollowers_url.group(2);
                }

                break;

            } catch (Exception ignored) { }
        }

        return userData;
    }
}