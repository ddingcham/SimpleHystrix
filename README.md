# Simple Research Test  

## [Spring-Cloud-Hystrix](https://cloud.spring.io/spring-cloud-netflix/multi/multi__circuit_breaker_hystrix_clients.html)  

## [Hystrix-How To Use](https://github.com/Netflix/Hystrix/wiki/How-To-Use)  

### [Common Patterns](https://github.com/Netflix/Hystrix/wiki/How-To-Use#common-patterns)  
// common uses and patterns of use for **Hystrix(Observable)Command**  

* Fail  
  > see also [execution-exception-types](https://github.com/Netflix/Hystrix/wiki/How-To-Use#common-patterns)  
  > **HystrixRuntimeException (FailureType)**, **HystrixBadRequestException**  
  * Fast  
  * Silent  

* Fallback  
  * Static  
  * Stubbed  
  * Cache via Network  

* Primary + Secondary with Fallback  

* Client Doesn't Perform Network Access  

* Get-Set-Get with Request Cache Invalidation  

## [Migrating a Library to Hystrix](https://github.com/Netflix/Hystrix/wiki/How-To-Use#migrating-a-library-to-hystrix)  
  > When you are migrating an existing client library to use Hystrix,  
  > you should replace each of the "service methods" with a **HystrixCommand**  
  
After migrating,  
#### users of a library will be able to access the HystrixCommands directly or indirectly  
#### via the service facade that delegates to the HystrixCommands  
