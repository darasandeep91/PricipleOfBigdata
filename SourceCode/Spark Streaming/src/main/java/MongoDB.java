/**
 * Created by daras on 15-Mar-16.
 */
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.dmg.pmml.True;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MongoDB {

    private MongoClient mongo = new MongoClient( "localhost" , 27017 );
    private String DatabaseName = "";

    private MongoDatabase GetDatabase()
    {
      MongoDatabase Objdatabase = null;
        try
        {
            if (DatabaseName != "") {
                Objdatabase = mongo.getDatabase(DatabaseName);
            }
        }
        catch (Exception ex)
        {
         ex.printStackTrace();
        }
        return  Objdatabase;
    }

    public void SetDatabaseName(String StrDatabaseName)
    {
        try
        {
            DatabaseName = StrDatabaseName;
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }


    }

    public List<String> ShowDatabases()
    {
        List<String> databases = new ArrayList<String>();
        try
        {
            databases =  mongo.getDatabaseNames();
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return  databases;
    }

    public void CreateCollection(String StrCollectionName)
    {
        try
        {
            boolean Present = false;
            MongoDatabase ObjDataBase = GetDatabase();
           List<String> list = (List<String>) ObjDataBase.listCollectionNames();
            for (String str : list )
            {
                if (StrCollectionName == str)
                {
                    Present = true;
                break;
                }

                else
                {
                    Present = false;
                }
            }
            if(Present == false) {
                ObjDataBase.createCollection(StrCollectionName);
            }
        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private MongoCollection GetCollection(String StrCollectionName)
    {
        MongoCollection ObjCollection= null;
        try
        {
            MongoDatabase ObjDataBase = GetDatabase();
            ObjCollection = ObjDataBase.getCollection(StrCollectionName);

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return ObjCollection;
    }

    public void insertDocument(String StrCollectionName)
    {
        try
        {
            MongoCollection ObjCollection = GetCollection(StrCollectionName);



        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


}
