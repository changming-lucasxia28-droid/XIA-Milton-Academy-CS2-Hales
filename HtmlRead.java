import javax.swing.*;
import java.net.*;
import java.io.*;
import java.sql.SQLOutput;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead() {

        try {
            System.out.println();
            System.out.println("hi");
            URL fred = new URL("https://en.wikipedia.org/wiki/Logitech"); //aparently this is not the current version

            System.out.println("bye");
            URLConnection urlc = fred.openConnection();


            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));  //this is the bottleneck, unknownhost exeption might be a faulty link or something on wikipedia's side

            System.out.println("hi");
            String line;

            //i think everything about this is the given code
            while ((line = reader.readLine()) != null) {
                if (line.indexOf("href") == -1) {
                    continue;
                }
                    int from = 0;
                    while (true) {
                        int start = line.indexOf("href=\"", from);
                        if (start == -1) break; //so if the index is at -1, we skip the line because there are no hrefs

                        int startOfLink = start + 6; //+6 is to account for the href="
                        int endOfLink = line.indexOf("\"", startOfLink);
                        if (endOfLink == -1) break;

                        String link = line.substring(startOfLink, endOfLink);

                        String output = null;

                        if (link.startsWith("http")) {
                            if (link.contains("wikipedia.org")) output = link;
                        } else if (link.startsWith("//")) {
                            if (link.contains("wikipedia.org")) output ="https:" + link;
                        } else if (link.startsWith("/")) {
                            output = "https://en.wikipedia.org:" + link;
                        }

                        if (output != null) System.out.println(output);

                        from = endOfLink + 1;

                    }
                }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
            }
