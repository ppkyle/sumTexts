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
        wordList =new ArrayList<>();
        wordmap = new HashMap<String, ArrayList<Integer>>() ;
    }


    public void addtext(Text t) {
        if((t.score<65) && t.level==0) {
            textlists.get(new Integer(t.score)).add(t);
        }
        if((t.score>60) &&(t.score<80) &&(t.level ==1)){
            textlists.get(new Integer(t.score)).add(t);
        }
        if((t.score>=80) && (t.level>1)){
            textlists.get(new Integer(t.score)).add(t);
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

    public void run(String p) {
        String path;
        for(int i=50; i<105; i+=5) {
           // path =p+ "/记对我影响最大的一个人/成绩"+i+"分的语料";
            path =p+ "/绿色食品与健康/成绩"+i+"分的语料";
            Write.creatFileCollection(path);
            //Write.test(path);
            for(String word: wordList) {
                wordmap.get(word).add(findWord(word, new Integer(i), path));
                //System.out.println("----->" + word);
            }
            System.out.println("finish!");
        }

        Write.outCount(wordmap,p+"/绿色食品与健康");
        //Write.outCount(wordmap,p+"/记对我影响最大的一个人");
        Write.ABCout(textlists,p+"/绿色食品与健康");
       // Write.ABCout(textlists,p+"/记对我影响最大的一个人");

    }

    private int findWord(String word,Integer integer,String path) {
        ArrayList<Text> arrayList = textlists.get(integer);

        return Write.output(arrayList, word, path) ;
    }

    public void addWordList(ArrayList<String> wordlist) {
        wordList.addAll(wordlist);
        for(String word: wordList){
            wordmap.put(word, new ArrayList<Integer>());
            System.out.println("word!!!!"+word);
        }
    }
}
