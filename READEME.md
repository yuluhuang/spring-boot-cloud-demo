    curl service-hi:123456@localhost:5006/uaa/oauth/token -d grant_type=password -d username=miya -d password=123456
    curl -l -H "Authorization:Bearer d33a9399-6c49-4619-8437-a957ab791e41" -X GET "localhost:5007/hello"
    curl -l -H "Authorization:Bearer d33a9399-6c49-4619-8437-a957ab791e41" -X GET "localhost:5007/getPrinciple"
    
    
    
   keytool -genkeypair -alias fzp-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jwt,O=jwt,L=zurich,S=zurich,C=CH" -keypass fzp123 -keystore fzp-jwt.jks -storepass fzp123
   keytool -list -rfc --keystore fzp-jwt.jks | openssl x509 -inform pem -pubkey
   
   
   
   // oauth/token 固定写法
   curl user-service:123456@localhost:5008/uaa/oauth/token -d grant_type=password -d username=miyaa -d password=123456
   curl -l -H "Authorization:Bearer {access_token}" -X GET "localhost:9412/foo"


   curl user-service:123456@localhost:5008/oauth2/oauth/token -d grant_type=password -d username=miya -d password=123456
