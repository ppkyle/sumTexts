import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hpk on 2018/4/1.
 */
public class Controller {
    Map<Integer, ArrayList<Text>> textlists;
    ArrayList<String> wordList;
    HashMap<String,ArrayList<Integer>> wordmap;

    public Controller(){
        textlists = new HashMap<Integer, ArrayList<Text>>();
        for (int i = 0; i < 12; i++) {
            textlists.put(45 + i * 5, new ArrayList<Text>());
        }
        wordList =new ArrayList<String>();
        wordmap = new HashMap<String, ArrayList<Integer>>() ;
    }

    public void cleartext(){
        for (int i = 0; i < 12; i++) {
            textlists.get(45 + i * 5).clear();
        }
        return;
    }

    public void addtext(Text t , int k) {
        if(k%2 == 0) {
            if ((t.score < 65) && t.level == 0) {
                textlists.get(new Integer(t.score)).add(t);
            }
            if ((t.score > 60) && (t.score < 80) && (t.level == 1)) {
                textlists.get(new Integer(t.score)).add(t);
            }
            if ((t.score >= 80) && (t.level > 1)) {
                textlists.get(new Integer(t.score)).add(t);
            }
            System.out.print("卡证书");
        }else{
            textlists.get(new Integer(t.score)).add(t);
            System.out.print("不卡证书");
        }

    }

    public void addtextlower50(Text t){
        textlists.get(new Integer(45)).add(t);
    }

    public void print(Integer integer) {
        ArrayList<Text> arrayList =textlists.get(integer);
        for(Text t:arrayList){
            for (String i : t.data) {
                System.out.println("--->" + i);
            }
        }
    }

    public void run(String p, int k) {
        String path;
        ArrayList<String> path1 = new ArrayList<String>();
        path1.add("/绿色食品与饥饿（韩国）");
        path1.add("/绿色食品与饥饿（韩国）不卡证书");
        path1.add("/绿色食品与饥饿（日本）");
        path1.add("/绿色食品与饥饿（日本）不卡证书");
        path1.add("/记对我影响最大的一个人（韩国）");
        path1.add("/记对我影响最大的一个人（韩国）不卡证书");
        path1.add("/记对我影响最大的一个人（日本）");
        path1.add("/记对我影响最大的一个人（日本）不卡证书");

        for(String word: wordList) {
            wordmap.get(word).clear();
        }
        for(int i=50; i<105; i+=5) {
           // path =p+ "/记对我影响最大的一个人（韩国）不卡证书/成绩"+i+"分的语料";
            //path =p+ "/绿色食品与健康（韩国）不卡证书/成绩"+i+"分的语料";
            //path =p+ "/绿色食品与健康（韩国）/成绩"+i+"分的语料";
            //path =p+ "/记对我影响最大的一个人（韩国）不卡证书/成绩"+i+"分的语料";
           // path =p+ "/记对我影响最大的一个人（韩国）/成绩"+i+"分的语料";
           //path =p+ "/绿色食品与健康（日本）/成绩"+i+"分的语料";
           // path =p+ "/绿色食品与健康（日本）不卡证书/成绩"+i+"分的语料";
           // path =p+ "/记对我影响最大的一个人（日本）/成绩"+i+"分的语料";
             //path =p+ "/记对我影响最大的一个人（日本）不卡证书/成绩"+i+"分的语料";

            path =p +path1.get(k)+"/成绩"+i+"分的语料";
            Write.creatFileCollection(path);
            //Write.test(path);
            for(String word: wordList) {
                wordmap.get(word).add(findWord(word, new Integer(i), path));
                //System.out.println("----->" + word);
            }
            System.out.println("finish!");
        }

       // Write.outCount(wordmap,p+"/绿色食品与健康（韩国）");
        //Write.outCount(wordmap,p+"/记对我影响最大的一个人（韩国）");
        //Write.outCount(wordmap,p+"/绿色食品与健康（日本）");
        //Write.outCount(wordmap,p+"/绿色食品与健康（韩国）不卡证书");
       // Write.outCount(wordmap,p+"/绿色食品与健康（日本）不卡证书");
        //Write.outCount(wordmap,p+"/记对我影响最大的一个人（日本）");
        Write.outCount(wordmap,p+path1.get(k));
        //Write.outCount(wordmap,p+"/记对我影响最大的一个人（韩国）不卡证书");
       // Write.outCount(wordmap,p+"/记对我影响最大的一个人（日本）不卡证书");
        //Write.outCount(wordmap,p+"/记对我影响最大的一个人");
      //  Write.ABCout(textlists,p+"/绿色食品与健康（韩国）");
         // Write.ABCout(textlists,p+"/绿色食品与健康（日本）");
        //Write.ABCout(textlists,p+"/绿色食品与健康（韩国）不卡证书");
        //Write.ABCout(textlists,p+"/绿色食品与健康（日本）不卡证书");
          //Write.ABCout(textlists,p+"/记对我影响最大的一个人（韩国）");
        // Write.ABCout(textlists,p+"/记对我影响最大的一个人（日本）");
        //Write.ABCout(textlists,p+"/记对我影响最大的一个人（韩国）不卡证书");
        //Write.ABCout(textlists,p+"/记对我影响最大的一个人（日本）不卡证书");
       // Write.ABCout(textlists,p+"/记对我影响最大的一个人");
        Write.ABCout(textlists,p+path1.get(k));
    }

    private int findWord(String word,Integer integer,String path) {
        ArrayList<Text> arrayList = textlists.get(integer);

        return Write.output(arrayList, word, path) ;
    }

    public void addWordList(ArrayList<String> wordlist) {
        for(String word:wordlist) {
            if( word.compareTo("")!=0) {
                //wordList.addAll(wordlist);
                wordList.add(word);

                    wordmap.put(word, new ArrayList<Integer>());
                    System.out.println("word!!!!" + word);

            }
        }
    }
}
