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
            switch(a
                    ){
                case Constants.LevelA:
                    return 3;
                case Constants.LevelB:
                    return 2;
                case Constants.LevelC:
                    return 1;
                case Constants.LevelWU:
                    return 0;
                default:
                    System.out.println("Error: LevelError:"+a+"----->"+s);
                    break;

            }
        }
        return 0;
    }

}
