Tutum API Client in Java
-------------------------------
[ ![Codeship Status for Tutum API Client](https://codeship.com/projects/560e8190-9495-0132-77ca-568d385d87f2/status)](https://codeship.com/projects/62585)

Welcome to Tutum API Client written in Java. Created a simple and meaningful wrapper methods for [Tutum RESTful APIs][1].


Getting Started
---------------
Tutum API Client library per version project dependencies:

* **For v1:** maps to branch [master][11]
    * Maven dependency
    <pre>&lt;dependency>
            &lt;groupId>com.pennassurancesoftware.tutum&lt;/groupId>
            &lt;artifactId>tutum-api-client&lt;/artifactId>
            &lt;version>1.00.00-SNAPSHOT&lt;/version>
        &lt;/dependency></pre>
    * Grails dependency
    <pre>compile 'com.pennassurancesoftware.tutum:tutum-api-client:1.0-SNAPSHOT'</pre>
    * Groovy Grape
    <pre>@Grapes( 
@Grab(group='com.pennassurancesoftware.tutum', module='tutum-api-client', version='1.0-SNAPSHOT') 
)</pre>
    * Scala SBT
    <pre>libraryDependencies += "com.pennassurancesoftware.tutum" % "tutum-api-client" % "1.0-SNAPSHOT"</pre>

* * *

Samples
-------
* Creating a Tutum Client in three simple ways!
<pre>// Way one, just pass on authToken
Tutum apiClient = new TutumClient(authToken);</pre>
<pre>// Way two, pass on version number & authToken
Tutum apiClient = new TutumClient("v2", authToken);</pre>
<pre>// Way three, pass on version number, authToken & httpClient
// Go ahead and customize httpClient attributes for requirements
HttpClient httpclient = new DefaultHttpClient();  
Tutum apiClient = new TutumClient("v2", authToken, httpClient);
</pre>

* Let's invoke the method(s) as per need via <code>apiClient</code>
<pre>// Fetching all the available droplets from control panel 
Droplets droplets = apiClient.getAvailableDroplets(pageNo);</pre>
<pre>// Fetching all the available kernels for droplet
Kernels kernels = apiClient.getAvailableKernels(dropletId, pageNo);</pre>
<pre>// Create a new droplet
Droplet newDroplet = new Droplet();
newDroplet.setName("api-client-test-host");
newDroplet.setSize(new Size("512mb")); // setting size by slug value
newDroplet.setRegion(new Region("sgp1")); // setting region by slug value; sgp1 => Singapore 1 Data center
newDroplet.setImage(new Image(1601)); // setting by Image Id 1601 => centos-5-8-x64 also available in image slug value
newDroplet.setEnableBackup(Boolean.TRUE);
newDroplet.setEnableIpv6(Boolean.TRUE);
newDroplet.setEnablePrivateNetworking(Boolean.TRUE);
// Adding SSH key info
List&lt;Key> keys = new ArrayList&lt;Key>();
keys.add(new Key(6536653));
keys.add(new Key(6536654));
newDroplet.setKeys(keys);
// Adding Metadata API - User Data
newDroplet.setUserData(" &lt; YAML Content > "); // Follow Tutum documentation to prepare user_data value
Droplet droplet = apiClient.createDroplet(newDroplet);</pre> 
<pre>// Fetch droplet information 
Droplet droplet = apiClient.getDropletInfo(dropletId);</pre> 
<pre>// Fetch Available Plans/Sizes supported by Tutum
Sizes sizes = apiClient.getAvailableSizes(pageNo);</pre> 
<pre>// Fetch Available Regions supported by Tutum
Sizes sizes = apiClient.getAvailableRegions(pageNo);</pre>

* Accessing <code>RateLimit</code> header values in the return object
<pre>Droplets droplets = getAvailableDroplets(1);
RateLimit rateLimit = droplets.getRateLimit();</pre>
<pre>Actions actions = getAvailableActions(2);
RateLimit rateLimit = actions.getRateLimit();</pre>
<pre>Domain domain = getDomainInfo("myjeeva.com");
RateLimit rateLimit = domain.getRateLimit();</pre>
<pre>Droplet droplet = getDropletInfo(10000001);
RateLimit rateLimit = droplet.getRateLimit();</pre>

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
