Tutum API Client in Java
-------------------------------
[ ![Codeship Status for Tutum API Client](https://codeship.com/projects/c1afc4a0-95f9-0132-0cbc-721f12f31a2c/status)](https://codeship.com/projects/62970)
[![DevOps By Rultor.com](http://www.rultor.com/b/PennAssuranceSoftware/tutum-api-client)](http://www.rultor.com/p/PennAssuranceSoftware/tutum-api-client)

Welcome to Tutum API Client written in Java. Created a simple and meaningful wrapper methods for [Tutum RESTful APIs][1].


Getting Started
---------------
Tutum API Client library per version project dependencies:

* **For v1:** maps to branch [master][11]
    * Maven dependency
    <pre>&lt;dependency>
            &lt;groupId>com.pennassurancesoftware.tutum&lt;/groupId>
            &lt;artifactId>tutum-api-client&lt;/artifactId>
            &lt;version>1.00.00&lt;/version>
        &lt;/dependency></pre>
    * Grails dependency
    <pre>compile 'com.pennassurancesoftware.tutum:tutum-api-client:1.00.00'</pre>
    * Groovy Grape
    <pre>@Grapes( 
@Grab(group='com.pennassurancesoftware.tutum', module='tutum-api-client', version='1.00.00') 
)</pre>
    * Scala SBT
    <pre>libraryDependencies += "com.pennassurancesoftware.tutum" % "tutum-api-client" % "1.00.00"</pre>

* * *

Samples
-------
* Creating a Tutum Client in three simple ways!
<pre>// Way one, just pass on authToken
Tutum apiClient = new TutumClient(authToken);</pre>
<pre>// Way two, pass on version number & authToken
Tutum apiClient = new TutumClient("v1", authToken);</pre>
<pre>// Way three, pass on version number, authToken & httpClient
// Go ahead and customize httpClient attributes for requirements
HttpClient httpclient = new DefaultHttpClient();  
Tutum apiClient = new TutumClient("v1", authToken, httpClient);
</pre>

* Let's invoke the method(s) as per need via <code>apiClient</code>
<pre>// Fetching all the available services from control panel 
Services services = apiClient.getServices();</pre>
<pre>// Fetching all the available containers
Containers containers = apiClient.getContainers();</pre>
<pre>// Create a new Node Cluster
final NodeCluster create = new NodeCluster();
create.setName( "junit3" );
create.setRegion( "/api/v1/region/digitalocean/nyc3/" );
create.setNodeType( "/api/v1/nodetype/digitalocean/512mb/" );
create.setTargetNumNodes( 1 );
create.setProvider( "digitalocean" );
final NodeCluster created = apiClient.createNodeCluster( create );
</pre> 

Reporting Issues
----------------
Tutum API Client uses [GitHub’s integrated issue tracking system][3] to record bugs and feature requests. If you want to raise an issue, please follow the recommendations bellow:

* Before you log a bug, please search the issue tracker to see if someone has already reported the problem. If the issue doesn’t already exist, create a new issue.
* Please provide as much information as possible with the issue report, we like to know the version of Tutum API Client that you are using.
* If you need to paste code, or include a stack trace use Markdown ``` escapes before and after your text.


Author
------
Jerome Bridge - jeromebridge@pennassurancesoftware.com

License
-------
The Tutum API Client is released under [MIT License][6].


[1]: https://docs.tutum.co/v2/api/
[3]: https://github.com/PennAssuranceSoftware/tutum-api-client/issues
[6]: https://github.com/PennAssuranceSoftware/tutum-api-client/blob/master/LICENSE.txt
[11]: https://github.com/PennAssuranceSoftware/tutum-api-client
