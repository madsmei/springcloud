package com.mads.kafka;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Collections;
import java.util.Properties;

/*******
 * kafka的消费者
 * @author mads
 */
public class KafkaConsumer extends ShutdownableThread {

    //卡夫卡集群 这里直接 , 号写多个
    private String KAFKA_BROKER_LIST = "192.168.1.3:9092";

    //相当于 RabbitMQ里的队列名字
    public static final String kafka_topic = "mads-one";

    private org.apache.kafka.clients.consumer.KafkaConsumer consumer;


    public void getMsg() {
    }

    public KafkaConsumer() {
        super("kafkaConsumerTest", false);

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,KAFKA_BROKER_LIST);
        //消费者的 组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "madsgroup1");

        //提交机制是自动提交。生产中 这个配置是不可取的。这里只是展示下
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        //提交确认的时间间隔
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //一次最大取回的消息数量，默认是500条
        properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);

        /***
         * 消费策略 取值 ：
         *    1.earliest 当各分区下有已经提交的offset时，从提交的offset开始消费，无offset提交时 从头开始消费
         *    2.latest  当各分区下有已经提交的offset时，从提交的offset开始消费，无offset提交时 消费新产生的该分区下的消息（从后往前）
         *    3.none topic各分区都存在已提交offset，从offset后开始消费，只要 有一个分区不存在已提交offset，则抛异常
         */
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //超时时间
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "3000");

        //key的解密
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        //value的解密
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new org.apache.kafka.clients.consumer.KafkaConsumer(properties);
    }

    @Override
    public void doWork() {
        //订阅 队列
        consumer.subscribe(Collections.singletonList(kafka_topic));
        //主动去 拉取消息
        ConsumerRecords<String,String> records = consumer.poll(1000);

        for (ConsumerRecord<String,String> record:records) {
            System.out.println("["+record.partition()+"]接收到消息 ：["+record.key()+"-->"+record.value()+"],offset:"
            +record.offset());
        }
    }
}
