# JSONparser
A JSON parsing application.

#### Prerequisites

JRE is required in order to run the executable JAR file.

#### Configuration

Run `mvn clean install` in the repository main directory.

#### Running the application

Run `java -jar json.jar` in the directory containing the executable JAR file.
The application will send a request to https://jsonplaceholder.typicode.com/posts and save the returned response in individual files.
Each file will be named according to the following convention: <post_id>.json.
All files will be saved in the same directory as the executable JAR file.
