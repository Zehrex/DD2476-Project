# DD2476-Project
**Project: GitHub search**
Contact: Johan Boye, jboye@kth.se

[Link to online project](https://github-search-f1cfc.web.app/)

[Video Demo](https://youtu.be/-T0MdBh0-PY)

Have you ever written methods/functions where you realize that it must have been written
before? On GitHub, millions of people are publishing their code for anyone to use, but there
is no obvious way of finding the code snippet that you are looking for. The goal of this
project is to solve just that!
The assignment:

* Crawl (a part of) the publicly available GitHub code.
* Filter out one programming language that you feel comfortable with.
* Process the files and separate class names, method names, modifiers (for example public, private, static, final etc.), variable names – things that you may want to search and filter!
* Index it into elasticsearch (https://github.com/elastic/elasticsearch), or another search engine of your choice.
* Create an interface where you can search and filter methods or classes based on the metadata you have created.
* A sample query could be methodName:quicksort AND returnType:List

i.e. search for quicksort, and filter by methods with returnType List. What would you
want to search for?


## Get app-search React app running

Install elastic search https://www.elastic.co/downloads/elasticsearch (I used homebrew to install)

Install app-search https://www.elastic.co/downloads/app-search
The config file you need to edit (`elasticsearch.yml`) is located at `/usr/local/etc/elasticsearch` if you installed using homebrew

Get elastic search running
```
elasticsearch
```

Check http://localhost:9200/ to see if it's running
An example result would be something like
```
{
  "name" : "MacBook-Pro.local",
  "cluster_name" : "elasticsearch_leviv",
  "cluster_uuid" : "hBs-dpB0S9iaHgrwFRMsxQ",
  "version" : {
    "number" : "7.6.2",
    "build_flavor" : "default",
    "build_type" : "tar",
    "build_hash" : "ef48eb35cf30adf4db14086e8aabd07ef6fb113f",
    "build_date" : "2020-03-26T06:34:37.794943Z",
    "build_snapshot" : false,
    "lucene_version" : "8.4.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

Now run app-search in the directory you downloaded.
```
cd app-search
bin/app-search
```

If you get the error:
```
Error: The application is in read-only mode, but it looks like we need to perform some installation/upgrade steps.

If you want to perform the migration, please remove the read-only flag and start the application again.

If you did not set the application to read-only mode, your Elasticsearch cluster may be unhealthy.
```

It was because there is not enough free space on my computer, so Elasticsearch could not index properly

Then open up http://localhost:3002/
You should have access to the app-search console

You should now be prompted to create a new engine. Name the engine "github-search". Upload the first .json file if prompted to create your index. To upload the rest, click on engines -> github-search -> documents -> Index Documents to add the other json files one at a time.

Then click on the left menu and then on the 'Credentials' tab. Copy the "search-key" key (should be in the format search-xxxxxx...). Replace the value of `searchKey`of the `connector` object in `search-web-app/src/Search.js line 19`.

Another important thing to change is the schema for app-search. Go to the github-search engine -> schema and set the "stars" field to type "number", and then update types. You may have to resolve some errors that come up if files are in the wrong format.
