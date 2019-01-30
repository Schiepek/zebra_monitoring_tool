# -------------------------------------------------------------------
# Elasticsearch
# -------------------------------------------------------------------

FROM docker.ergon.ch/ergon/java:oracle-java8

MAINTAINER Michael Meyer <michael.meyer@ergon.ch>

# Install packages
RUN apt-get update && apt-get install -y curl

# Install Elasticsearch
RUN cd /tmp \
    && curl -O https://download.elasticsearch.org/elasticsearch/elasticsearch/elasticsearch-1.5.2.tar.gz \
    && tar xzf elasticsearch-*.tar.gz \
    && rm elasticsearch-*.tar.gz \
    && mv elasticsearch-* /opt/elasticsearch

# Include custom configuration
ADD config/elasticsearch.yml /opt/elasticsearch/config/elasticsearch.yml

# Install HQ plugin
RUN /opt/elasticsearch/bin/plugin -install royrusso/elasticsearch-HQ

# Memory
ENV ES_HEAP_SIZE 2048m
    
# Define mountable directory
VOLUME ["/data"]

# Ports
EXPOSE 9200
EXPOSE 9300

# Switch working directory
WORKDIR /data

# Start the main process
CMD ["/opt/elasticsearch/bin/elasticsearch"]

# Cleanup
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*