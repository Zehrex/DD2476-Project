#class Crawler
crawls public repos of github for a certain language (here is java, but can be changed to crawl any other language). The class need an access token in order to work (or using username and password). The crawled repos are saved then in files locally (which can be processed by the index, however it is possible to send them directly to the index, changes needs to be made to the crawler and indexer in order for that to work).

# class indexer
use the files saved locally to create an index (json file saved locally). It uses javalang library for parsing java files, in order to parse for  other languages you need a different parser.
