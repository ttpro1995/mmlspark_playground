import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import com.microsoft.ml.spark.lightgbm.LightGBMClassifier
import org.apache.spark.ml.feature.VectorAssembler

object SimpleLightGBMClassification {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("SimpleLightGBMClassification")
    val spark = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()

    val parent_folder = "/home/cpu11453/workplace/experiment/lightgbm_experiment/data"
    val train_path = parent_folder+"/"+"binary.train.txt"
    val test_path = parent_folder + "/" + "binary.test.txt"
    val pred_path = parent_folder + "/" + "pred_100"

    val train_df = spark.read.option("delimiter", "\t").option("header", "false").option("inferSchema", "true").csv(train_path)
    val test_df = spark.read.option("delimiter", "\t").option("header", "false").option("inferSchema", "true").csv(test_path)

    val feature_cols_name_list = train_df.drop("_c0").columns

    val featureAssembler = (new VectorAssembler()
      .setInputCols(feature_cols_name_list)
      .setOutputCol("features_vector"))

    val train_df_transform = featureAssembler.transform(train_df).withColumnRenamed("_c0", "label").select("label", "features_vector")
    train_df_transform.show()

    val test_df_transform = featureAssembler.transform(test_df).withColumnRenamed("_c0", "label").select("label", "features_vector")
    test_df_transform.show()

    val classifier = new LightGBMClassifier()
      .setFeaturesCol("features_vector")
      .setLabelCol("label").setNumIterations(100)

    val classifier_model = classifier.fit(train_df_transform)

    classifier_model.saveNativeModel("/home/cpu11453/workplace/experiment/lightgbm_experiment/model/simple_classifier_100_mml", overwrite = true)

    val pred = classifier_model.transform(test_df_transform)

    pred.select("label", "prediction").coalesce(1).write.option("delimiter", "\t").option("header", "false").csv(pred_path)

  }
}
