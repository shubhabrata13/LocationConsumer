# LocationConsumer
az ad sp create-for-rbac --name "LocationConsumer" --role contributor --scopes /subscriptions/3bcb201f-aa6d-4cdb-9e58-25fcdff2dc3a/resourceGroups/cloud-poc --json-auth

{
  "clientId": "2525d9a5-802a-49dc-bb9d-4c776859ee65",
  "clientSecret": "8Z_8Q~ihR31zQoHhRoi9TRd8.IHmTS3feepQOc_L",
  "subscriptionId": "3bcb201f-aa6d-4cdb-9e58-25fcdff2dc3a",
  "tenantId": "b030234f-2018-44d4-988f-171292ddcc6e",
  "activeDirectoryEndpointUrl": "https://login.microsoftonline.com",
  "resourceManagerEndpointUrl": "https://management.azure.com/",
  "activeDirectoryGraphResourceId": "https://graph.windows.net/",
  "sqlManagementEndpointUrl": "https://management.core.windows.net:8443/",
  "galleryEndpointUrl": "https://gallery.azure.com/",
  "managementEndpointUrl": "https://management.core.windows.net/"
}
