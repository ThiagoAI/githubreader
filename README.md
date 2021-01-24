
# GitHub Repo Reader
Scrapes a GitHub repo returning the name, line count and byte size of all files, grouped by file extension.

# AWS
* Public DNS: `ec2-3-139-73-99.us-east-2.compute.amazonaws.com`  
* Port: `8080`  
* Example: `ec2-3-139-73-99.us-east-2.compute.amazonaws.com:8080/readrepo`  

# Api Endpoints

Detailed API documentation can be found at [githubreader.yaml](https://github.com/ThiagoAI/githubreader/blob/main/src/main/java/com/thiago/githubreader/adapters/web/specs/reference/githubreader.yaml) and at this [link](https://thiagoandersimhoff.stoplight.io/docs/githubreporeader-1/GitHubReader.yaml). What follows is a summary.

## Read GitHubRepo  

Endpoint for scraping a GitHub repo and returning all file info, grouped by file extension.

* Method: `POST`
* Path: `/readrepo`
* Request type: `application/json` (**must** have `Content-Type` header)
* Response type: `application/json`
* Request example:
```json
{
    "url": "https://github.com/sarcasticadmin/empty-repo"
}
```
* Response for above example:
```json
{
    "totalLines": 47,
    "totalSize": "686,00 Bytes",
    "fileCount": 2,
    "fileGroups": [
        {
            "fileExtension": "gitignore",
            "lineCount": 44,
            "totalSize": "624,00 Bytes",
            "files": [
                {
                    "name": ".gitignore",
                    "lineCount": 44,
                    "size": "624.0 Bytes"
                }
            ]
        },
        {
            "fileExtension": "md",
            "lineCount": 3,
            "totalSize": "62,00 Bytes",
            "files": [
                {
                    "name": "README.md",
                    "lineCount": 3,
                    "size": "62.0 Bytes"
                }
            ]
        }
    ]
}
```
