import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class followerGetter{
        static ArrayList<String> followersInfo(String apiUrl){

            ArrayList<String> arrayUrl = new ArrayList<>();

            int page = 1;

            while (true){
                try {
                    final Document document = Jsoup.connect(apiUrl + "?access_token=452f8e4c1d165937b1f645b6d696dfc3aa5dca74" +"&page="+page).ignoreContentType(true).get();

                    if(document.text().equals("[ ]")){
                        break;
                    }


                    String[] arrOfStr = document.text().split(" }");
                    for (String arrData : arrOfStr) {

                        Pattern ApiUrl = Pattern.compile("(\"url\": \")(.*)(\", \"html_url\":)");
                        Matcher matchApiUrl = ApiUrl.matcher(arrData);
                        if (matchApiUrl.find()) {
                            arrayUrl.add(matchApiUrl.group(2));
                        }
                    }

                    page++;

                } catch (Exception e) {
                    break;
                }
            }
            return arrayUrl;
        }
    }

