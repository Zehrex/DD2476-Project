# DD2476-Project
**Project: GitHub search**
Contact: Johan Boye, jboye@kth.se

Have you ever written methods/functions where you realize that it must have been written
before? On GitHub, millions of people are publishing their code for anyone to use, but there
is no obvious way of finding the code snippet that you are looking for. The goal of this
project is to solve just that!
The assignment:
  • Crawl (a part of) the publicly available GitHub code.
  • Filter out one programming language that you feel comfortable with.
  • Process the files and separate class names, method names, modifiers (for example
  public, private, static, final etc.), variable names – things that you may want to search
  and filter!
  • Index it into elasticsearch (https://github.com/elastic/elasticsearch), or another
  search engine of your choice.
  • Create an interface where you can search and filter methods or classes based on the
  metadata you have created.
  • A sample query could be methodName:quicksort AND returnType:List

i.e. search for quicksort, and filter by methods with returnType List. What would you
want to search for?
