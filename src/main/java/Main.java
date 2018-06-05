import java.io.File;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        Controller controller = new Controller();

        File fileword = new File("F:/罗斐语料/罗斐所需语料/词库");

        if (fileword.isDirectory()) {
            File list[] = fileword.listFiles();
            for (File f : list) {
                if((!f.isDirectory())&& (f.getName().indexOf("biao")!=-1)){
                    System.out.println("F:/罗斐语料/罗斐所需语料/词库/"+f.getName());
                }else{
                    continue;
                }
                ArrayList<String> wordlist = Read.readWordList(f);
                if(!wordlist.isEmpty()){
                    for(String word: wordlist){
                        System.out.println(wordlist.size()+word+"________!!!");
                    }

                    controller.addWordList(wordlist);
                }
            }
        }

        //File file = new File("F:/罗斐语料/罗斐所需语料/绿色食品与饥饿（韩国）/");
        //File file = new File("F:/罗斐语料/罗斐所需语料/绿色食品与饥饿（日本）/");
        //File file = new File("F:/罗斐语料/罗斐所需语料/记对我影响最大的一个人（韩国）/");
        File file = new File("F:/罗斐语料/罗斐所需语料/记对我影响最大的一个人（日本）/");


        if (file.isDirectory()) {
            File list[] = file.listFiles();
            for (File f : list) {
                if (!f.isDirectory()) {
                    System.out.println(f.getName());
                }
                Text t = Read.txt2Object(f);
                if (t.score > 45) {
                    controller.addtext(t);
                    //for (String i : t.data) {
                      //  System.out.println(t.score+"--->" + i);
                    //}
                } else {
                    controller.addtextlower50(t);
                    //for (String i : t.data) {
                       // System.out.println("--->" + i);
                  //  }
                }


            }

            controller.run("F:/罗斐语料/罗斐所需语料/结果");

        }


    }


}
