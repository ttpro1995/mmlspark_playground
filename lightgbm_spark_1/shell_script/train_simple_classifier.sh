/zserver/spark-2.4.3-bin-hadoop2.7/bin/spark-submit \
--master "spark://cpu11453:7077" \
--executor-memory 10G \
--total-executor-cores 3 \
--class SimpleLightGBMClassification \
 "target/scala-2.11/lightgbm_spark_1-assembly-0.1.jar"