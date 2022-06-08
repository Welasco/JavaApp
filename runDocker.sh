#!/bin/bash
appinsightsconfig=$(cat applicationinsights.json)
appinsightsconfig=$(<applicationinsights.json)
docker run -p 8080:8080 -d -e "APPLICATIONINSIGHTS_CONFIGURATION_CONTENT=$(<applicationinsights.json)" -e "APPLICATIONINSIGHTS_CONNECTION_STRING=InstrumentationKey=key;IngestionEndpoint=https://centralus-2.in.applicationinsights.azure.com/;LiveEndpoint=https://centralus.livediagnostics.monitor.azure.com/" welasco/javaapp