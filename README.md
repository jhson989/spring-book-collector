# spring-book-collector
Book Collector made with Spring Framework

## 1. How to Run
- sudo apt install -y openjdk-17-jdk
- sudo apt install -y gradle
- export JAVA_KEY=[????]
- sh run.sh


## 2. Create book
- curl -d "title=[title]&content=[content]" -X POST http://152.67.210.106/create
  - ex) curl -d "title=how to use curl&content=I know how to use curl" -X POST http://152.67.210.106/create
