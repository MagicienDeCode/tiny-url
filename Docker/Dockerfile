FROM public.ecr.aws/amazoncorretto/amazoncorretto:21

# folder creation
RUN mkdir -p /tiny-url

# binary and conf copy paste
COPY tinyurl.jar /tiny-url

# redis
ENV INTERNAL_PORT TO_BE_CHANGED
ENV REDIS_URI TO_BE_CHANGED
ENV REDIS_PORT TO_BE_CHANGED

# java opts
ENV JAVA_OPTS TO_BE_CHANGED

# log
ENV KAFKA_LOG_TOPIC	TO_BE_CHANGED
ENV KAFKA_SERVER	TO_BE_CHANGED
ENV LOG_LEVEL	TO_BE_CHANGED
ENV MS_NAME	TO_BE_CHANGED
ENV MS_VERSION	TO_BE_CHANGED

ENTRYPOINT ["java","-jar","/tiny-url/tinyurl.jar"]
