
import org.apache.spark.sql.SparkSession
import com.microsoft.ml.spark.lightgbm.LightGBMClassifier
import org.apache.spark.SparkConf

object LightGBMExperimentTrain {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("LightGBMExperiment")
    val spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    val train_path = "/data/public/HIGGS/higgs.train.one"
    val train_df = spark.read.format("libsvm").option("numFeatures", "28").load(train_path)
    val classifier = new LightGBMClassifier().setLabelCol("label").setFeaturesCol("features")
      .setObjective("binary").setNumIterations(30)
      .setNumLeaves(225)
      .setLearningRate(0.1)

    val trained_higgs = classifier.fit(train_df)
    // You can save and load the model as needed
    //trained_higgs.write.overwrite().save("/data/public/HIGGS/trained_higgs_1_model.mml")
    trained_higgs.saveNativeModel("/data/public/HIGGS/trained_higgs_30_clustermode_native_model.mml", overwrite = true)
  }
}
