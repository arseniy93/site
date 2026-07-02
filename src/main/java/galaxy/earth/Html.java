package galaxy.earth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Html {
    private String url;
    private int counterLevel;
    private int counterLecture;
    private boolean flag;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Html(String url) {
        this.flag = false;
        this.url = url;
    }

    //    https://javarush.com/quests/lectures/questservlets.level11.lecture00
    public boolean page(String pathToSave, String pathFromFolder) throws IOException {
        // String urlString = url; // URL страницы для скачивания

        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD"); // Используем метод HEAD для проверки существования страницы
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            URLConnection urlConnection = url.openConnection();
            // Установка заголовков запроса (опционально)
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            // Чтение данных со страницы
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
//                String sanitizedFileName = this.url.replaceAll("[\\\\/:*?\"<>|]", "_");
//                Files.write(Paths.get(pathToSave +  sanitizedFileName+pathFromFolder + sanitizedFileName+ ".html"), content.toString().getBytes(StandardCharsets.UTF_8));
//                saveCss(content.toString(), pathToSave, sanitizedFileName);

//                String sanitizedFileName = sanitizeFileName(this.url);
//                Path htmlFilePath = Paths.get(pathToSave + sanitizedFileName + ".html");
//                Files.write(htmlFilePath, document.html().getBytes(StandardCharsets.UTF_8));
                String sanitizedFileName = this.url.replaceAll("[\\\\/:*?\"<>|]", "_");
                // Создаем полный путь к файлу, включая подпапки
                Path fullPath = Paths.get(pathToSave, pathFromFolder,  sanitizedFileName + ".html");

                // Создаем все необходимые директории, если они не существуют
                Files.createDirectories(fullPath.getParent());

                // Записываем файл
                Files.write(fullPath, content.toString().getBytes(StandardCharsets.UTF_8));

                // Сохраняем CSS
               // saveCss(content.toString(), pathToSave ,pathFromFolder,sanitizedFileName);
            }

            setCounterLecture(0);
            flag = true;
            return true;
        } else {
            setCounterLecture(getCounterLecture() + 1);
            if (getCounterLecture() >= 1) {
                setCounterLevel(getCounterLevel() + 1);
                setCounterLecture(0);
                flag = false;
            }
            return false;
        }
    }



//    private void saveCss(String htmlContent, String pathToSave, String pathFromFolder, String fileNamePrefix) throws IOException {
//        List<String> cssUrls = extractCssUrls(htmlContent);
//
//        for (int i = 0; i < cssUrls.size(); i++) {
//            String cssUrl = cssUrls.get(i);
//            cssUrl = toAbsoluteUrl(cssUrl);
//
//            System.out.println("CSS URL " + (i + 1) + ": " + cssUrl);
//
//            try {
//                URL url = new URL(cssUrl);
//                HttpURLConnection cssConnection = (HttpURLConnection) url.openConnection();
//                cssConnection.setRequestMethod("GET");
//                if (cssConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    try (BufferedReader cssReader = new BufferedReader(new InputStreamReader(cssConnection.getInputStream()))) {
//                        String line;
//                        StringBuilder cssContent = new StringBuilder();
//                        while ((line = cssReader.readLine()) != null) {
//                            cssContent.append(line).append("\n");
//                        }
//
//                        String sanitizedCssFileName = cssUrl.replaceAll("[\\\\/:*?\"<>|]", "_");
//                        Path fullCssPath = Paths.get(pathToSave, pathFromFolder, fileNamePrefix + "_" + sanitizedCssFileName + ".css");
//                        Files.createDirectories(fullCssPath.getParent());
//                        Files.write(fullCssPath, cssContent.toString().getBytes(StandardCharsets.UTF_8));
//                    }
//                } else {
//                    System.err.println("Ошибка загрузки CSS: " + cssUrl + ", код ответа: " + cssConnection.getResponseCode());
//                }
//
//            } catch (IOException e) {
//                System.err.println("Ошибка при сохранении CSS " + cssUrl + ": " + e.getMessage());
//            }
//        }
//    }
//
//
//
//    private List<String> extractCssUrls(String htmlContent) {
//        List<String> cssUrls = new ArrayList<>();
//        // Более надежное регулярное выражение для поиска CSS ссылок
//        Pattern pattern = Pattern.compile("<link\\s+[^>]*?href\\s*=\\s*['\"]([^'\"]*)['\"][^>]*?rel\\s*=\\s*['\"]stylesheet['\"][^>]*?>", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(htmlContent);
//        while (matcher.find()) {
//            String cssUrl = matcher.group(1).trim(); // Удаляем пробелы
//            if (!cssUrl.isEmpty()) { // Проверяем, что URL не пустой
//                cssUrls.add(cssUrl);
//            }
//        }
//        return cssUrls;
//    }
//
//    private String toAbsoluteUrl(String url) {
//        try {
//            URL baseUrl = new URL(this.url);
//            return new URL(baseUrl, url).toString();
//        } catch (MalformedURLException e) {
//            System.err.println("Ошибка преобразования URL: " + url + ", сообщение: " + e.getMessage());
//            return url;
//        }
//    }

    public int getCounterLevel() {
        return counterLevel;
    }

    public void setCounterLevel(int counterLevel) {
        this.counterLevel = counterLevel;
    }

    public int getCounterLecture() {
        return counterLecture;
    }

    public void setCounterLecture(int counterLecture) {
        this.counterLecture = counterLecture;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public  void createDirectoryIfNotExists(String dirPath) throws IOException {
        Path directory = Paths.get(dirPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
            System.out.println("Папка создана: " + directory.toAbsolutePath());
        } else {
            System.out.println("Папка уже существует: " + directory.toAbsolutePath());
        }
    }

    public  boolean directoryExists(String dirPath) {
        Path directory = Paths.get(dirPath);
        return Files.exists(directory);
    }
    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
    }

    public Optional<String> findButtonLink(String buttonText, String buttonClass) throws IOException {
        URL url = new URL(this.url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("HEAD");
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                Document document = Jsoup.parse(content.toString());

                // Поиск кнопки по тексту и классу
                Elements buttons = document.select("a.button.button--info.button--md.ng-star-inserted");
                for (Element button : buttons) {
                    if (button.text().contains(buttonText) && button.hasClass(buttonClass)) {
                        return Optional.ofNullable(button.attr("href"));
                    }
                }

                // Поиск ссылки на следующую лекцию
                Element nextLecture = document.selectFirst("div.lectures-nav__next a");
                if (nextLecture != null) {
                    return Optional.ofNullable(nextLecture.attr("href"));
                }
            }
        }
        return Optional.empty();
    }
}