#!/bin/bash

# update elasticsearch url
sed -i "s|%ES_URL%|$ES_URL|g" /opt/kibana/config/kibana.yml
