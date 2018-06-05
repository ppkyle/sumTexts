/**
 * Created by hpk on 2018/4/1.
 */
public class Text {
    int level;
    int score;
    String[] data;

    Text(String[] s){
        data = s;
        level = findLevel(s[1]);
        score = findScore(s[1]);
        //System.out.println("~~~>"+level+score);
    }

    private int findScore(String s) {
        int x=s.indexOf("作文成绩：");
        String a=s.substring(x+5,x+7);

        return Integer.parseInt(a);
    }

    private int findLevel(String s) {
        int x=s.indexOf("证书级别：");
        if( x>0 ){
            String a= s.substring(x+5,x+6);
            if(a.compareTo(Constants.LevelA) == 0 ) {
                //System.out.println("Right:"+a+"----->"+s);
                return 3;
            }else
            if(a.compareTo(Constants.LevelB) == 0){
               // System.out.println("Right:"+a+"----->"+s);
                return 2;
            }else
            if(a.compareTo(Constants.LevelC) == 0) {
              //  System.out.println("Right:"+a+"----->"+s);
                return 1;
            }else
            if(a.compareTo(Constants.LevelWU) == 0) {
               // System.out.println("Right:"+a+"----->"+s);
                return 0;
            }else{
                    System.out.println("Error: LevelError:"+a+a.compareTo(Constants.LevelA)+"----->"+s);
            }
        }
        return 0;
    }

}
