# spring-book-collector
Book Collector made with Spring Framework

## 1. How to Run
- sudo apt install -y openjdk-17-jdk
- sudo apt install -y gradle
- export JAVA_KEY=[????]
- sh run.sh

## 2. Read Books
- curl [IP]
  - curl http://000.000.000.000/list

## 3. Create a Book
- curl -d "title=[title]&content=[content]" -X POST [ID]
  - ex) curl -d "title=how to use curl&content=I know how to use curl" -X POST http://000.000.000.000/create
