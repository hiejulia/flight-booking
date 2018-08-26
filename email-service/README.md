Entity : 

+ Mail 
    + Config : Queue, TopicExchange, Binding, SimpleMessageListenerContainer, Receiver, MessageListenerAdapter 
+ Message 
+ Receiver (RabbitMQ )
    + receiveMessage(byte[] message) throw Exception 
        + Mail(

        )
        + mail.sendEmail
+ TaskMessage 
    + emailID