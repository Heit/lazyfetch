## An example how @Transactional works with Spring AOP

Same code with different AOP settings will work in differently.

ASPECTJ mode will proxy each method call, but default proxy mode works only for external method call.

There are two test cases in project:

 * LazyFetchServiceAspectJTest shows many-to-many lazy fetch for AspectJ mode;
 * LazyFetchServiceTest shows many-to-many lazy fetch for default proxy mode, throws LazyInitializationException;

To run:

 mvn test
