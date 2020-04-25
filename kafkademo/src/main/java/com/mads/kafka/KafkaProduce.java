package com.mads.kafka;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/******
 * kafka的生产者
 * @author  mads
 */
public class KafkaProduce {

    //卡夫卡集群 这里直接 , 号写多个
    private String KAFKA_BROKER_LIST = "192.168.1.3:9092";

    //相当于 RabbitMQ里的队列名字
    public static final String kafka_topic = "mads-one";

    //生产者对象
    private KafkaProducer producer;

    public KafkaProduce (){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_LIST);
        //这两行是 kafka是根据key来进行消息的路由。存储在不同的partition中，需要对key和value进行加密，这里就是配置加密的方式
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        //这个参数 是为了我们查看日志
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "client-producer-1");
        //消息的确认机制。默认是1 取值 0,1，-1
        properties.put(ProducerConfig.ACKS_CONFIG, "1");

        //日志的压缩方式
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");

        this.producer = new KafkaProducer(properties);
    }


    public void sendMsg() {

        int i = 0;
        while (true) {
            //消息的载体。
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>(kafka_topic, "key" + i, "value" + i);

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("消息发送到"+recordMetadata.partition()+" offset:"+recordMetadata.offset());
                }
            });

        }

    }


}
