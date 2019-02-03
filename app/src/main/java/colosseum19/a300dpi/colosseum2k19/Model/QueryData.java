package colosseum19.a300dpi.colosseum2k19.Model;

import java.util.ArrayList;

public class QueryData {
    private static ArrayList<Fixture>queryData = new ArrayList<>();

    public static void setData(ArrayList<Fixture> data){
        queryData.clear();
        queryData = data;
    }

    public static ArrayList<Fixture>getData(){
        return queryData;
    }

}
