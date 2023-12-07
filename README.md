```
docker build -t kkstream . && docker run -v $(pwd)/datadir:/app/tmp --name kkstream -d kkstream:latest
```