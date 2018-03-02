# A Java 8 Spring Cloud demonstration of Scalable Microservices

For reasons explained [on my personal website](https://www.tristanperry.com/java8/spring/2017/12/23/java8-spring-cloud-microservice-demo.html), I decided to put together a quick demo of four Spring Cloud-based microservices which can be easily scaled, thus providing a lot of power and flexibility when launching a new application.

These four microservices are:

 * *eureka-server* - a bare-bones Spring Boot application, running on port 8001, which the other 3 microservices register with.
 * *jwt-auth-server* - a Spring Security OAuth2 (single-sign-on) service, running on port 8001, which uses [JWT](https://jwt.io/) (JSON Web Tokens) as the SSO implementation. JWT is stateless and is underpinned by a secret signing key (aka to prevent someone generating their own 'session' with their own data).
 * *business-logic-api* - a Spring Boot application, on port 8003, which in OAuth2 terms would be a 'resource server'. In other words, this is a generally 'authenticated' microservice (it requires a valid JWT token to use most of its endpoints). It's worth noting that I call this an "api" not a "server" since this is where various 'resource server' type API endpoints will live.
 * *reports-api* - ditto to *business-logic-api*, albeit on port 8004.

I have intentionally used two resource servers in this demo, because I wanted to give a simple example of Feign client. This is used in the *business-logic-api*'s *FizzBuzzController* which does a sideways call to an endpoint within *reports-api* to get some data.

This is to start showing the power of Spring Cloud and the microservice approach, since in production we might have 10 *reports-api* microservice instances. So when *business-logic-api* uses Feign client to 'speak to' *reports-api*, Feign uses Eureka (and then Ribbon) to choose which *reports-api* to actually contact for its request.

And since, as I mentioned earlier, JWT is stateless, we can also scale *jwt-auth-server* too since we don't have to worry about sticky sessions. [Eureka can naturally be scaled as well](http://cloud.spring.io/spring-cloud-static/spring-cloud.html#_peer_awareness).

## How it Works

All four microservices have a YAML config file at *src/main/resources/application.yml* which sets up the basic configuration of each microservice. All 4 are also a parent of the main *pom.xml* which uses Spring Boot V1.5.9 and Spring Cloud Edgware, and ultimately Spring Starter poms which automatically pull in the most useful set of default dependencies. Please see the full write-up on [my personal website](https://www.tristanperry.com/java8/spring/2017/12/23/java8-spring-cloud-microservice-demo.html) for a detailed explanation of the code in each microservice.

However this demo is setup such that you can just pull the code, and run each of the four microservices. It should then be ready to test out in a REST client.

## REST Calls to Use This Demo

_If you use [Postman](https://www.getpostman.com), you can import the environment and collection from the /postman-collection/ folder which will get you up and running with the below a lot quicker - especially the authenticated calls, since the JWT bearer token from the authenticate call is [saved as a variable](http://blog.getpostman.com/2014/01/27/extracting-data-from-responses-and-chaining-requests/) and is then automatially sent up in subsequent requests._

**To Authenticate (and thus get a JWT bearer token)**

The authentication endpoint requires an *Authorization: Basic ...* header to be sent up, where the value is the [base64 encoded](https://www.base64encode.org/) version of the clientId and secretKey, aka:

 > base64(default-client:sssshhh) = ZGVmYXVsdC1jbGllbnQ6c3Nzc2hoaA==

So our API request is:

HTTP POST http://localhost:8002/oauth/token

*Headers*
* Authorization: Basic ZGVmYXVsdC1jbGllbnQ6c3Nzc2hoaA==
* Content-Type: application/x-www-form-urlencoded

*Body*
* client_id:	default-client
* username:	user
* password:	password
* grant_type:	password

This will return various data, including our bearer token (also known as the access token). You can decode this on [jwt.io](http://jwt.io/) to see its contents, which includes the username and scopes of this authentication.

We can then use any of our authenticated endpoints simply by passing a header of:

 > Authorization: Bearer [bearer-token-here]

These endpoints are currently:

* HTTP GET http://localhost:8003/fizz-buzz
* HTTP GET http://localhost:8003/fizz-buzz/{id}
* HTTP POST http://localhost:8003/fizz-buzz with JSON such as: _{"fizz": "blah","buzz": "buzzing","fizzBuzz": true}_
* HTTP GET http://localhost:8003/fizz/report/1
* HTTP GET http://localhost:8004/fizz/collate

We can also get basic data from our public (unauthenticated) endpoint via HTTP GET http://localhost:8003/public/fizz-buzz (this endpoint naturally doesn't require an Authorization header).

## Future Work Planned

I've been re-visting this demo since it was first published, to make small improvements to it. Other future work planned includes:

* Currently the bearer token is manually captured and passed in the single Feign client example; this would be better as an interceptor.
* Enable the refresh token ability, since currently the bearer tokens expire after a set time with no way to renew it.

I hope you have found this useful. Please feel free to get in touch if you have any questions or comments. Thanks!
