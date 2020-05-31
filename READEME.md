## start
1. run eureka-server and eureka-server-2 only one can error
2. run docker mysql
3. run auth-service user-service service-hi uaa-service

## request
service-auth: 5006
service-hi: 5007
uaa-service: 5008
user-service: 9412

service-auth:5006 和 uaa-service:5008 都是提供认证的功能 分别对象 service-hi:5007 和 user-service:9412

1. curl -l -d "username=ylh&password=ylh" -X POST "localhost:5007/user/registry"
 `{"id":2,"username":"ylh","password":"$2a$10$CdwGz4jfSOt6cUqOYBsfquor1.fOqn6XOgMGSDDYaNJ/Gp4OdpMRa","authorities":null,"accountNonExpired":true,"accountNonLocked":true,"credentialsNonExpired":true,"enabled":true}`
2. curl service-hi:123456@localhost:5006/uaa/oauth/token -d grant_type=password -d username=ylh -d password=ylh
 ```
{"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTA5MTUwOTQsInVzZXJfbmFtZSI6InlsaCIsImp0aSI6IjkzNTY4OWJkLWU3MDUtNGFhOS1iYTY2LTFkYzRhZWYxZTFlNiIsImNsaWVudF9pZCI6InNlcnZpY2UtaGkiLCJzY29wZSI6WyJzZXJ2ZXIiXX0.TnSNrOoeG_sBxwr4TR23xN47c5_Mvd3AI4fqypplMaQ","token_type":"bearer","refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTM1MDE2MjQsInVzZXJfbmFtZSI6InlsaCIsImp0aSI6IjcxZmY0ZDJhLWYwNzEtNDVlYy05ZWE4LTQ5M2UwY2UwNTUyMCIsImNsaWVudF9pZCI6InNlcnZpY2UtaGkiLCJzY29wZSI6WyJzZXJ2ZXIiXSwiYXRpIjoiOTM1Njg5YmQtZTcwNS00YWE5LWJhNjYtMWRjNGFlZjFlMWU2In0.pbxjovEP13mPz8xmBwP88viDD2HWusCTw3NpySicSyk","expires_in":1199,"scope":"server","jti":"935689bd-e705-4aa9-ba66-1dc4aef1e1e6"}
```
3. curl -l -H "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTA5MTA4MjQsInVzZXJfbmFtZSI6InlsaCIsImp0aSI6ImRmMjBkMGU0LTY0ZmUtNDdiOC05MmZlLWUyN2ZkZGVjOTE2ZSIsImNsaWVudF9pZCI6InNlcnZpY2UtaGkiLCJzY29wZSI6WyJzZXJ2ZXIiXX0.qAEeMYsybz0PJj83IZTBtmtDzK8YKSODclBubcvFH7w"  -X GET "localhost:5006/uaa/users/current"
```
{"authorities":[],"details":{"remoteAddress":"0:0:0:0:0:0:0:1","sessionId":null,"tokenValue":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTA5MTA4MjQsInVzZXJfbmFtZSI6InlsaCIsImp0aSI6ImRmMjBkMGU0LTY0ZmUtNDdiOC05MmZlLWUyN2ZkZGVjOTE2ZSIsImNsaWVudF9pZCI6InNlcnZpY2UtaGkiLCJzY29wZSI6WyJzZXJ2ZXIiXX0.qAEeMYsybz0PJj83IZTBtmtDzK8YKSODclBubcvFH7w","tokenType":"Bearer","decodedDetails":null},"authenticated":true,"userAuthentication":{"authorities":[],"details":null,"authenticated":true,"principal":"ylh","credentials":"N/A","name":"ylh"},"oauth2Request":{"clientId":"service-hi","scope":["server"],"requestParameters":{"client_id":"service-hi"},"resourceIds":[],"authorities":[],"approved":true,"refresh":false,"redirectUri":null,"responseTypes":[],"extensions":{},"grantType":null,"refreshTokenRequest":null},"credentials":"","principal":"ylh","clientOnly":false,"name":"ylh"}
```    
4. curl -l -H "Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTA5MTUwOTQsInVzZXJfbmFtZSI6InlsaCIsImp0aSI6IjkzNTY4OWJkLWU3MDUtNGFhOS1iYTY2LTFkYzRhZWYxZTFlNiIsImNsaWVudF9pZCI6InNlcnZpY2UtaGkiLCJzY29wZSI6WyJzZXJ2ZXIiXX0.TnSNrOoeG_sBxwr4TR23xN47c5_Mvd3AI4fqypplMaQ" -X GET "localhost:5007/hi"
    
# other  
    
curl service-hi:123456@localhost:5006/uaa/oauth/token -d grant_type=password -d username=miya -d password=123456
curl -l -H "Authorization:Bearer 7cb74d48-85df-4fb3-9758-3f96333f80bd" -X GET "localhost:5007/hello"
curl -l -H "Authorization:Bearer 7cb74d48-85df-4fb3-9758-3f96333f80bd" -X GET "localhost:5007/getPrinciple"

    
    
keytool -genkeypair -alias fzp-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jwt,O=jwt,L=zurich,S=zurich,C=CH" -keypass fzp123 -keystore fzp-jwt.jks -storepass fzp123
keytool -list -rfc --keystore fzp-jwt.jks | openssl x509 -inform pem -pubkey



// oauth/token 固定写法
curl user-service:123456@localhost:5008/uaa/oauth/token -d grant_type=password -d username=miya -d password=123456
curl -l -H "Authorization:Bearer {access_token}" -X GET "localhost:9412/foo"


curl user-service:123456@localhost:5008/oauth2/oauth/token -d grant_type=password -d username=miya -d password=123456
curl -l -H "Authorization:Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1OTA4MzE1MjMsInVzZXJfbmFtZSI6Im1peWEiLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImp0aSI6IjFkMWRmM2ViLWQ5NDUtNDJmMS1iZDMxLTE2YmYzMWEyZWFiMSIsImNsaWVudF9pZCI6InVzZXItc2VydmljZSIsInNjb3BlIjpbInNlcnZpY2UiXX0.VzbI7v7l45q2jK8h5Zkex6mk6-GVFpPV2sxQL0kRsHhWSJWe7sw7V3uqfWRh-09j-NGp-6f1q5QUfoV4zeYxr7wZh3iNny0ihxNFjrQ9cv1KF-HuXNQrsAd_j71PB-WAvMM47HaaWXAWZh8C2oJNNo8cg7BryxTIxD7mkEqyWcZb4aZtQZy-p3eHTLt1S61oq16zoAFq9cL8QMlJ_ju9M3kzfW6L8UGBzvvB_0o7tpJxdgHNdR-55nLWjnYQVoe2NgTp7X8qHHjiC3LdHme1T2W96jXhqvpmKQA8_ldFgnbsv7VgWKx-BZ6iu0zCSElrwobi7G7Y6f8i_92Y0SfGTA" -X GET "localhost:9412/foo"
