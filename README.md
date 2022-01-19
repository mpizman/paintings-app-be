Deploy new version to app:
1)	Need to create a new image with the version tag update inside pom xml <version> tag:
    -	Update version number in pom.xml
    -	Run maven from IntelliJ (skip tests flag is marked!!)
    -	Run: docker image push “image_name”:”image_tag”
    -	In docker compose: update app version: mpizman/demo:0.0.x
2)	Copy docker-compose to ec2 instance use scp for Windows:
“scp -i C:\Users\MPIZM\Downloads\awsMatan.pem C:\Users\MPIZM\Documents\paintings\paintings\docker-compose.yaml ec2-user@ec2-13-59-210-173.us-east-2.compute.amazonaws.com:/var/www/html”
3)	Inside instance: pull new image from docker hub and run docker compose:
   
    • “sudo -i”
    
    • “docker pull mpizman/demo:0.0.2”
    
    • “docker-compose up -d”

    Can also run app container:
    docker run --rm -p 8080:8080 --name <name_of_app_container> --net backend --link <mongo_container_name>:mongo <name_of_the__app_image>

    link is important!

Thing to notice about app structure:

  •	In docker-compose.yaml the network has a static ip.
  
  •	In application.properties, the host a has the ip of the mongo db.
  
  •	All the containers are running on the same network: mongodb_network.
  
  •	Localhost cant exceed container limits – this is why we have to use the real ip of the container inside the network to access it.
  
  •	Make sure to check that the host configured in app.properties matches the ip of mongo container!!!!!!!!!!
