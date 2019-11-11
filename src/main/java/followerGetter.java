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
                    final Document document = Jsoup.connect(apiUrl + "?access_token=cdea46c74ec89bb3e076fbd77ade35d181e77af7" +"&page="+page).ignoreContentType(true).get();

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

