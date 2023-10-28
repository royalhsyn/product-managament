# product-managament

 This is simple Product Managament app   
     
	   Java 17
	   Gradle
	   Docker 
	   Spring boot ,Spring Data , Spring web 
	   

		Java 17: If you don't have Java 17, you need to install OpenJDK for running this app
		Docker - We recommend Docker Desktop

  for build this application use 
  
  
./gradlew build


Then insert this commands (make sure docker daemon is on) 

docker run --name postgresdb -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -d postgres:latest

docker build . -t productmanagament-app

docker-compose up