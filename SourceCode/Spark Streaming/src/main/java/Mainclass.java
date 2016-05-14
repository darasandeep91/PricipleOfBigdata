import com.mongodb.client.MongoCollection;

import java.util.List;

/**
 * Created by daras on 16-Mar-16.
 */
public class Mainclass {

    public static void main(String[] args)
    {
        try
        {
           /* SparkStreaming ObjStream = new SparkStreaming();
		   *//* List<Status> Data =  ObjStream.GetData("#traveldiaries");
			File Fl = new File("C:\\Users\\daras\\Desktop\\tweets.txt");
			FileWriter Fw = new FileWriter(Fl);
			for(Status x : Data)
			{
                Fw.write(x.toString());
				Fw.write("\n\n");
			}
			Fw.close();*//*

            String keyword[] = {"#TravelDiaries","#Travel","#traveling","travel","mountains","beaches","#travel","#trip","#vacation","#traveltheworld","#beach","#sea","#sunshine","#valley","#FreeSpirits","#tourism","#flight","#exploe",
                    "#mountaineering","#adventure","#ocean","#worldtravel","#honeymoon","#wanderlust","#ttot","#bridge","#nature","#mytripmyadventure"
            };
            ObjStream.Stream(keyword);*/

            MongoDB db = new MongoDB();

            /*db.SetDatabaseName("Sandeep");
            db.CreateCollection("Table");*/


        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
