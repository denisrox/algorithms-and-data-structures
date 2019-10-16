import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class webSpider {
    public static String BasicSyte="https://vk.com/";
    public static void main(String[] args) {
        exec();

    }
    public static void exec() {
        Stack<URL> sites = new Stack<URL>();
        BufferedReader reader = null;
        try {
            sites.push(new URL(BasicSyte));
            while (!sites.empty()){
                URL site = sites.pop();
                File htmlFile = new File(site.toString().replaceAll("[^a-zA-Z0-9]", "")+".html");
                if(htmlFile.createNewFile()){
                    try {
                        reader = new BufferedReader(new InputStreamReader(site.openStream()));
                        String line;
                        FileWriter writer = new FileWriter(htmlFile);
                        while ((line = reader.readLine()) != null) {
                            if(regex(line)!="")
                                try {
                                    sites.push(new URL(regex(line)));
                                }catch (Exception ex){}

                            writer.write(line);
                        }
                    }catch (Exception ex){}

                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {

            }
        }
    }
    public static String regex(String url){
        System.out.println(BasicSyte.substring(8));
        Pattern pattern = Pattern.compile("https.+?"+BasicSyte.substring(8)+".+?\"", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        if(matcher.find())
            return (url.substring(matcher.start(), matcher.end()-1)).replaceAll("\\\\",""); //в ссылках, которые создаются javascript, встречаются символы: "\". Убрал
        else
            return "";
    }


}