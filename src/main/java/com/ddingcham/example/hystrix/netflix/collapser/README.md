# [How To Use - Request Collapsing](https://github.com/Netflix/Hystrix/wiki/How-To-Use#request-collapsing)

#### Request collapsing enables multiple requests to be  
#### batched into a single HystrixCommand instance execution.

* A Collpaser Can Use  
  > creation of the batch as triggers for executing a batch  
  * batch size  
  * elapsed time  
  
## 2 styles of request-collapsing supported  
  > configured at collapser construction  

### request-scoped collapser // default  
* collects a batch per **HystrixRequestContext**  

### globally-scoped collapser  
* collects a batch across multiple **HystrixContext**s  

### How to select  
* if your downstream dependencies cannot handle  
  #### multiple HystrixContexts in a single command invocation  
  request-scoped collapsing is the proper choice.  

* Since the batches are per-request only,  
  collapsing is effective  
  > when commands occur in parallel  
  > with different arguments in the same request.  
  