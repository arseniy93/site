package galaxy.earth;

import java.io.IOException;

public class Main {//37

    public static void main(String[] args) throws IOException {
        boolean isNextPage = true;
        int indexOfLesson = 0;
        int indexOfLecture = 0;
        int counter = 0;

        Main main = new Main();
//        https://javarush.com/quests/lectures/jru.module6.lecture00
//       https://javarush.com/quests/lectures/ru.javarush.python.core.lecture.level20.lecture00
//        https://javarush.com/quests/lectures/ru.javarush.docker.fullstack.lecture.level08.lecture00
//        https://javarush.com/quests/lectures/ru.javarush.system.linux.lecture.level01.lecture01
        String wholeLink = "https://javarush.com/quests/lectures/ru.javarush.java.spring.lecture.level18.lecture10";
        // String firstLink = main.separeteFirstLink(wholeLink);
        Integer level = 1;
        Integer lecture = 0;
        while (isNextPage) {
            Html html = new Html(wholeLink);
            if ((html.page("html/", "level" + level + "/"))) {
//                counter = 0;
//                String s = main.separateLectureAndLevel(wholeLink);
//                main.getNumberOfLevel(s);
//                level = Integer.parseInt(main.getNumberOfLevel(s));
//                lecture = Integer.parseInt(main.getNumberOfLecture(s)) + 1;


               // wholeLink = html.findButtonLink("Следующая", "button--info").get();
                wholeLink = html.findButtonLink("Предыдущая", "button--info").get();
            }
        }

//        String s = main.separateLectureAndLevel("https://javarush.com/quests/lectures/jru.module1.lecture00");
//        main.getNumberOfLevel(s);
//        Integer in = Integer.parseInt(main.getNumberOfLevel(s));
//        System.out.println("level= " + in);
//        Integer in1 = Integer.parseInt(main.getNumberOfLecture(s));
//        System.out.println("lecture= " + in1);
//        System.out.println(main.separeteFirstLink("first path =" + wholeLink));
//        System.out.println("modifyNumberOfLevel= " + main.separeteFirstLink(wholeLink) + main.modifyNumberOfLevel(in.toString()));
//        System.out.println("modifyNumberOfLecture= " + main.separeteFirstLink(wholeLink) + main.modifyNumberOfLevel(in.toString()) + main.modifyNumberOfLecture(in1.toString()));

    }

    private String separateLectureAndLevel(String link) {
        String s = new String(link);
        s = s.substring(s.indexOf(".")).substring(s.indexOf("."));
        s = s.substring(s.indexOf("."), s.length());
        System.out.println(s);
        return s;
    }

//    public String getNumberOfLevel(String separateLink) {
    // var s=separateLink.substring(separateLink.indexOf("."));
//        separateLink = separateLink.substring(separateLink.indexOf("l")).substring(separateLink.indexOf("l"));
//        separateLink = separateLink.substring(separateLink.indexOf("l") + 1, separateLink.length());


//        separateLink = separateLink.substring(separateLink.indexOf("1")-1).substring(separateLink.indexOf(".")+1);
////        separateLink = separateLink.substring(separateLink.indexOf("module1") , separateLink.length());
//        //separateLink = separateLink.substring(separateLink.indexOf("l")).substring(separateLink.indexOf("l"));
//        separateLink = separateLink.substring(0, 1);
//        System.out.println(separateLink);
//        return separateLink;
//    }
//
//    public String getNumberOfLecture(String separateLink) {
//        var minus = getNumberOfLevel(separateLink);
//        separateLink = separateLink.substring(separateLink.indexOf(minus));
//        separateLink = separateLink.substring(separateLink.indexOf("re") + 2, separateLink.length());
//        System.out.println(separateLink);
//        return separateLink;
//    }
//
//    private boolean isLessThanTen(String number) {
//        return Integer.parseInt(number) < 10;
//    }
//
//    public String separeteFirstLink(String link) {
//        return link.substring(0, link.length() - separateLectureAndLevel(link).length());
//    }

//    public String modifyNumberOfLevel(String pathOfLinksLevel) {
//        return pathOfLinksLevel = ".level" +
//                ((isLessThanTen(pathOfLinksLevel))
//                        ? pathOfLinksLevel = "0"
//                        + pathOfLinksLevel : pathOfLinksLevel);
//    }

//    public String modifyNumberOfLecture(String pathOfLinksLecture) {
//        return pathOfLinksLecture = ".lecture" +
//                ((isLessThanTen(pathOfLinksLecture))
//                        ? pathOfLinksLecture = "0"
//                        + pathOfLinksLecture : pathOfLinksLecture);
//    }
}


//Integer level=1;
//Integer lecture=0;
//        while (isNextPage) {
//Html html=new Html(wholeLink);
//            if ((html.page("html/","level"+level+"/"))) {
//counter = 0;
//String s = main.separateLectureAndLevel(wholeLink);
//                main.getNumberOfLevel(s);
//level = Integer.parseInt(main.getNumberOfLevel(s));
//lecture = Integer.parseInt(main.getNumberOfLecture(s)) + 1;
//
//        if(lecture>7){
//        System.out.println();
//                }
//wholeLink=main.separeteFirstLink(wholeLink) + main.modifyNumberOfLevel(level.toString()) + main.modifyNumberOfLecture(lecture.toString());
//        } else {
//        if (!html.getFlag()) {
//counter++;
//level++;
//lecture=0;
//wholeLink=main.separeteFirstLink(wholeLink) + main.modifyNumberOfLevel(level.toString()) + main.modifyNumberOfLecture(lecture.toString());
//        }
//        if (counter >= 3) {
//        System.out.println("Уровень закончился");
//isNextPage = false;
//        break;
//        }
//        //TODO set counet++
//        }
//        }