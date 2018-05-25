import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hpk on 2018/4/1.
 */
public class Write {

    public static void creatFileCollection(String f) {
        File file = new File(f);
        System.out.println(f);
        if (!file.exists()) {
            file.mkdirs();
            System.out.println("fjka;dflksjf;asdjkf;alskfja;lskfj");
        }
    }

    public static void test(String path) {

        XWPFDocument document = new XWPFDocument();

        //Write the Document in file system
        try {
            File file = new File(path + "/test.doc");
            if (!file.exists()) {
                file.createNewFile();
            }

            FileOutputStream out = new FileOutputStream(file);
            //添加标题
            XWPFParagraph titleParagraph = document.createParagraph();
            //设置段落居中
            titleParagraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun titleParagraphRun = titleParagraph.createRun();
            titleParagraphRun.setText("Java Hpk");
            titleParagraphRun.setColor("97FFFF");
            titleParagraphRun.setFontSize(20);
            document.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static int output(ArrayList<Text> arrayList, String word, String path) {

        boolean Created = false;
        //Write the Document in file system

        try {

            XWPFDocument document = new XWPFDocument();
            File file = new File(path + "/" + word + "_文章.doc");

            XWPFDocument document2 = new XWPFDocument();
            File file2 = new File(path + "/" + word + "_句子.doc");

            if (!file.exists()) {
                file.createNewFile();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            FileOutputStream out2 = new FileOutputStream(file2);
            XWPFParagraph countParagraph = document2.createParagraph();
            //设置段落居中
            //titleParagraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun countParagraphRun = countParagraph.createRun();
            countParagraphRun.setText(word + "总共出现了：");
            XWPFRun countParagraphRun2 = countParagraph.createRun();

            int count = 0;

            for (Text t : arrayList) {
                ArrayList<Integer> location = new ArrayList<>();
                ArrayList<String> juzis = new ArrayList<>();
                int x = t.data[4].indexOf(word);
                while (x != -1) {
                    count++;
                    int qian, hou;
                    hou = t.data[4].indexOf("。", x);
                    String qianduan;
                    if (hou >= 0) {
                        qianduan = t.data[4].substring(0, hou);
                    } else {
                        qianduan = t.data[4];
                    }
                    qian = qianduan.lastIndexOf("。");
                    if (qian >= 0) {
                        String yaodejuzi = qianduan.substring(qian + 1);
                        juzis.add(yaodejuzi + "。");
                        //System.out.println("+++++++++++" + yaodejuzi);
                    } else {
                        juzis.add(qianduan + "。");
                        //System.out.println("-----------" + qianduan);
                    }
                    //System.out.println("---------->>>>>>>>>>" + count);
                    location.add(x);
                    x = t.data[4].indexOf(word, x + 1);
                }
                if (location.size() > 0) {

                    if (!Created) {
                        Created = true;
                    }
                    for (int i = 0; i < 4; i++) {
                        XWPFParagraph titleParagraph = document2.createParagraph();
                        //设置段落居中
                        //titleParagraph.setAlignment(ParagraphAlignment.CENTER);

                        XWPFRun titleParagraphRun = titleParagraph.createRun();
                        titleParagraphRun.setText(t.data[i]);

                    }
                    int Juzicount = 1;
                    for (String ju : juzis) {
                        XWPFParagraph titleParagraph = document2.createParagraph();

                        XWPFRun juzicountRun = titleParagraph.createRun();
                        juzicountRun.setText((Juzicount++) + "、--->");
                        String[] juzisplit = ju.split(word);
                        for (int i = 0; i < juzisplit.length - 1; i++) {
                            XWPFRun titleParagraphRun = titleParagraph.createRun();
                            titleParagraphRun.setText(juzisplit[i]);
                            XWPFRun runText2 = titleParagraph.createRun();
                            runText2.setText(word);
                            runText2.setColor("FF3333");
                        }

                        XWPFRun runText = titleParagraph.createRun();
                        runText.setText(juzisplit[juzisplit.length - 1]);
                    }


                    for (int i = 0; i < 4; i++) {
                        XWPFParagraph titleParagraph = document.createParagraph();
                        //设置段落居中
                        //titleParagraph.setAlignment(ParagraphAlignment.CENTER);

                        XWPFRun titleParagraphRun = titleParagraph.createRun();
                        titleParagraphRun.setText(t.data[i]);

                    }
/*
                        //添加标题
                        XWPFParagraph titleParagraph = document.createParagraph();
                        //设置段落居中
                        //titleParagraph.setAlignment(ParagraphAlignment.CENTER);
                        String str14="";
                        str14="\r\n"+t.data[1]+"\r\n\r\n"+t.data[3]+"\r\n";
                        XWPFRun titleParagraphRun = titleParagraph.createRun();
                        titleParagraphRun.setText(str14);
                        */
                    String[] contentsplit = t.data[4].split("\n");
                    for (String test : contentsplit) {
                        XWPFParagraph textParagraph = document.createParagraph();
                        String[] testsplit = test.split(word);
                        for (int i = 0; i < testsplit.length - 1; i++) {
                            XWPFRun runText = textParagraph.createRun();
                            runText.setText(testsplit[i]);
                            XWPFRun runText2 = textParagraph.createRun();
                            runText2.setText(word);
                            runText2.setColor("FF3333");
                        }
                        XWPFRun runText = textParagraph.createRun();
                        runText.setText(testsplit[testsplit.length - 1]);

                    }
                    //TextSegement text= titleParagraph.searchText(word,new PositionInParagraph());
                    // List<XWPFRun> runs = titleParagraph.getRuns();
                    // XWPFRun run1= runs.get(text.getBeginRun());
                    //run1.setColor("97FFFF");
                    //titleParagraphRun.setColor("97FFFF");
                    //itleParagraphRun.setFontSize(20);


                }
            }
            if (Created) {
                countParagraphRun2.setText(count + "次!");

                document.write(out);
                document2.write(out2);
                out2.close();
                out.close();
                return count;
            } else {
                file2.delete();
                file.delete();
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    public static void outCount(HashMap<String, ArrayList<Integer>> wordmap, String path) {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out;
        try {
            File file = new File(path + "/统计总次数.doc");
            if (!file.exists()) {

                file.createNewFile();

            }

            out = new FileOutputStream(file);
            for (String word : wordmap.keySet()) {
                XWPFParagraph countparagraph = document.createParagraph();
                XWPFRun runText = countparagraph.createRun();
                runText.setText(word + "出现次数：");
                int j = 50;
                for (Integer i : wordmap.get(word)) {
                    XWPFParagraph countparagraph2 = document.createParagraph();
                    XWPFRun runText2 = countparagraph2.createRun();
                    runText2.setText("在成绩" + j + "分的文章中出现次数：" + i);
                    j += 5;
                }
            }
            document.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void ABCout(Map<Integer, ArrayList<Text>> textlists, String path) {


        try {

            int a[]={65,80,101};
            int j=50;
            for(int i=0; i<=2; i++) {
                XWPFDocument document = new XWPFDocument();
                FileOutputStream out;
                File file = new File(path + "/第"+i+"组.doc");
                if (!file.exists()) {

                    file.createNewFile();

                }
                out = new FileOutputStream(file);
                XWPFParagraph countparagraph2 = document.createParagraph();
                XWPFRun runText2 = countparagraph2.createRun();
                int count=0;
                while(j<a[i]){
                    ArrayList<Text> arrayList= textlists.get(j);
                    System.out.println(j + "fkajdhfa" + i);
                    j+=5;
                    for(Text t: arrayList){
                        count++;
                        for(int k=0; k<5; k++){
                            XWPFParagraph countparagraph = document.createParagraph();
                            XWPFRun runText = countparagraph.createRun();
                            runText.setText(t.data[k]);
                        }
                    }
                }
                runText2.setText("文章总数："+count);
                document.write(out);
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
