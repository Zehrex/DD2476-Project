



mvn:
	mvn archetype:generate  -DgroupId=ir -DartifactId=GitHubProject -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false

run_se:
	    mvn exec:java -Dexec.mainClass="ir.Engine"  
