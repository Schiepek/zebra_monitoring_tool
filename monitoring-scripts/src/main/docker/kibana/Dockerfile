FROM ubuntu:14.10
MAINTAINER Michael Meyer <michael.meyer@ergon.ch>

# Install packages
RUN apt-get update && apt-get install -y curl sed dos2unix

# Install Kibana
RUN cd /tmp \
    && curl -O https://download.elasticsearch.org/kibana/kibana/kibana-4.0.2-linux-x64.tar.gz \
    && tar xzf kibana-*.tar.gz \
    && rm kibana-*.tar.gz \
    && mv kibana-* /opt/kibana
 
 # Include custom configuration
ADD config/kibana.yml /opt/kibana/config/kibana.yml

# Custom configuration
ADD config/write_kibana_config.sh /opt/kibana/bin/write_kibana_config.sh
RUN dos2unix /opt/kibana/bin/write_kibana_config.sh
RUN chmod +x /opt/kibana/bin/write_kibana_config.sh

# Ports
EXPOSE 5601

# Start the main process
CMD /opt/kibana/bin/write_kibana_config.sh && /opt/kibana/bin/kibana

# Cleanup
RUN apt-get clean && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*