/zserver/spark-2.4.3-bin-hadoop2.7/bin/spark-submit \
--master "spark://cpu11453:7077" \
--executor-memory 13G \
--total-executor-cores 3 \
--class LightGBMExperimentTrain \
 "target/scala-2.11/lightgbm_spark_1-assembly-0.1.jar" \


#/zserver/spark-2.4.3-bin-hadoop2.7/bin/spark-submit \
#--master "local" \
#--class LightGBMExperimentTrain \
# "target/scala-2.11/lightgbm_spark_1-assembly-0.1.jar"