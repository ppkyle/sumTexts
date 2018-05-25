import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hpk on 2018/4/1.
 */
public class Read {
    public static Text txt2Object(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String temp= result.toString();
        String list[]= temp.split("\n",5);

        Text text=new Text(list);
        return text;
    }


    public static ArrayList<String> readWordList(File file) {

        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String temp= result.toString();
        String[] listpre =temp.split("\n");
        String[] list= listpre[1].split("、");
        ArrayList<String> wordList=new ArrayList<>(Arrays.asList(list));

        return wordList;
    }
}
