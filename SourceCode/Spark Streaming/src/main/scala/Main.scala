/**
  * Created by daras on 13-Mar-16.
  */

import org.apache.spark.{SparkConf, SparkContext}

object  Main {



  def main(args: Array[String]) {

    val filters = args

    System.setProperty("hadoop.home.dir", "F:\\winutils");

    val sparkConf = new SparkConf().setAppName("SsparkTest").setMaster("local[*]").set("spark.driver.allowMultipleContexts", "true")

    val sparkcontext = new SparkContext(sparkConf)

    //Create a Streaming COntext with 2 second window

    //val sparkstreamingcontext = new StreamingContext(sparkConf, Seconds(5))

    val sqlContext = new org.apache.spark.sql.SQLContext(sparkcontext)

    val path = "C:\\Users\\daras\\Desktop\\pbd.json"

    val jsondata = sqlContext.read.json(path)


    jsondata.printSchema()

    val table = jsondata.registerTempTable("Tweets")

   /* //val test = sqlContext.sql("select user.created_at from TestData")
    val test = sqlContext.sql("select user.lang,count(*) as count from Tweets where user.lang IS not NULL group by user.lang order by count desc limit 10 ");
    	/*test.registerTempTable("Months")
    	val query2=sqlContext.sql("select date,count(*) as cnt from Months group by date order by cnt desc ")
   /* val query2=sqlContext.sql("select date,count(*) as cnt from Months group by date order by cnt desc ")
    query2.coalesce(1).write.format("com.databricks.spark.csv").save("C:\\Users\\daras\\Desktop\\Outputs\\3.csv")*/
*/*/
    	/*val test = sqlContext.sql("select substring(user.created_at,5,3) as date from Tweets where user.created_at is not null ")
    	test.registerTempTable("Months")
    	val query2=sqlContext.sql("select date,count(*) as cnt from Months group by date order by cnt desc")

*/
   // val test = sqlContext.sql("SELECT place.country,COUNT(*) AS country_count from Tweets WHERE place.country is not null GROUP by place.country order by country_count desc limit 5 ")

   /* val test = sqlContext.sql ("SELECT entities.hashtags[0].text, count(entities.hashtags[0].text) as famous_tags FROM Tweets group by entities.hashtags[0].text order by famous_tags desc limit 10")
     test.show()
*/
    val test = sqlContext.sql ("SELECT text, user.location from Tweets where text like '%bus%' or text like '%cruise%' or text like '%flight%' or text like '%train%' or text like '%driving%'")

    	test.registerTempTable("TRAVELBY")

    	val test1 = sqlContext.sql(
      "SELECT CASE WHEN text like '%bus%' THEN 'Bus Travelling'" +
        "WHEN text like '%cruise%' THEN 'Cruise Travelling'" +
        "WHEN text like '%flight%' THEN 'Fligh Traveling'" +
        "WHEN text like '%train%' THEN 'TRain Traveling'" +
        "WHEN text LIKE '%driving%' THEN 'Own Vehicle'" +
        "END AS travelling,location from TRAVELBY where text is not null and location is not null ")
    	test1.registerTempTable("test2")

    val test3 = sqlContext.sql("select travelling,location, Count(*) as Count from test2 group by travelling,location order by Count DESC  ")

    	test3.registerTempTable("test3")

    	val test4 = sqlContext.sql("select travelling,max(Count) as Count from test3 group by travelling")

    	test4. registerTempTable("test4")

    	val test5 = sqlContext.sql("select location,t1.travelling,t1.Count from test3 t1,test4 t2 where t1.travelling=t2.travelling and t1.Count=t2.Count")

test5.show()
    //test.show()


  }
}
