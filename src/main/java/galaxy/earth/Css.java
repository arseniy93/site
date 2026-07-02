package galaxy.earth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Css {

    public static void main(String[] args) {
        String urlString = "https://www.example.com"; // URL страницы для скачивания

        try {
            String htmlContent = downloadPageContent(urlString);
            System.out.println("HTML Content:\n" + htmlContent);

            List<String> cssLinks = extractCssLinks(htmlContent);
            for (String cssLink : cssLinks) {
                String fullCssUrl = resolveCssUrl(urlString, cssLink);
                String cssContent = downloadPageContent(fullCssUrl);
                System.out.println("CSS Content from " + fullCssUrl + ":\n" + cssContent);

                // Сохранение CSS в файл (опционально)
                // Files.write(Paths.get(getCssFileName(fullCssUrl)), cssContent.getBytes());
            }

        } catch (IOException e) {
            System.err.println("Ошибка при скачивании страницы: " + e.getMessage());
        }
    }

    private static String downloadPageContent(String urlString) throws IOException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        }
    }

    private static List<String> extractCssLinks(String htmlContent) {
        List<String> cssLinks = new ArrayList<>();
        Pattern pattern = Pattern.compile("<link\\s+[^>]*rel\\s*=\\s*\"stylesheet\"[^>]*href\\s*=\\s*\"([^\"]+)\"[^>]*>");
        Matcher matcher = pattern.matcher(htmlContent);
        while (matcher.find()) {
            cssLinks.add(matcher.group(1));
        }

        // Также ищем встроенные стили
        pattern = Pattern.compile("<style[^>]*>(.*?)</style>", Pattern.DOTALL);
        matcher = pattern.matcher(htmlContent);
        while(matcher.find()){
            cssLinks.add(matcher.group(1)); // Добавляем встроенные стили как есть
        }

        return cssLinks;
    }

    private static String resolveCssUrl(String baseUrl, String cssLink) throws IOException {
        if (cssLink.startsWith("http://") || cssLink.startsWith("https://")) {
            return cssLink;
        } else {
            URL base = new URL(baseUrl);
            return new URL(base, cssLink).toString();
        }
    }

    private static String getCssFileName(String cssUrl) throws IOException{
        URL url = new URL(cssUrl);
        String path = url.getPath();
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        if(fileName.isEmpty()){
            fileName = "style.css";
        }
        return fileName;
    }
}