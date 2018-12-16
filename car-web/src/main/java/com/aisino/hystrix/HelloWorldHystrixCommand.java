package com.aisino.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;


/**
 *
 * 背景(不使用的坏处:)
 *
 * API接口可用性降低
 *      引用Hystrix官方的一个例子，假设tomcat对外提供的一个application，其内部依赖了30个服务，
 *      每个服务的可用性都很高，为99.99%。那整个applicatiion的可用性就是：99.99%的30次方 ＝ 99.7%，
 *      即0.3%的失败率。这也就意味着，每1亿个请求，有30万个失败；按时间来算，就是每个月的故障时间超过2小时。
 *
 * 系统被block
 *      假设一个请求的调用链上面有10个服务，只要这10个服务中有1个超时，就会导致这个请求超时。
 *      更严重的，如果该请求的并发数很高，所有该请求在短时间内都被block（等待超时），
 *      tomcat的所有线程都block在此请求上，导致其他请求没办法及时响应。
 *
 * 服务熔断
 *      为了解决上述问题，服务熔断的思想被提出来。类似现实世界中的“保险丝“，
 *      当某个异常条件被触发，直接熔断整个服务，而不是一直等到此服务超时。
 *      熔断的触发条件可以依据不同的场景有所不同，比如统计一个时间窗口内失败的调用次数。
 */
public class HelloWorldHystrixCommand extends HystrixCommand {

    private final String name;

    public HelloWorldHystrixCommand(String name) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        //关键点：把一个RPC调用，封装在一个HystrixCommand里面
        //此处调用真正的业务代码 或者 http、dubbo
        return "hello"+name;
    }

    public static void main(String[] args){

        //客户端调用：以前是直接调用远端RPC接口，现在是把RPC接口封装到HystrixCommand里面，它内部完成熔断逻辑
        String result = (String) new HelloWorldHystrixCommand("test").execute();
        System.out.println(result);
    }


    /**
     *
     * 隔离策略: 线程 vs 信号量
     *      缺省的，上面的HystrixCommand是被扔到一个线程中执行的，也就是说，缺省是线程隔离策略。
     *      还有一种策略就是不搞线程池，直接在调用者线程中执行，也就是信号量的隔离策略。
     *
     * 熔断的参数配置
     *      Hystrix提供了如下的几个关键参数，来对一个熔断器进行配置：
     *      circuitBreaker.requestVolumeThreshold //滑动窗口的大小，默认为20
     *
     *      //过多长时间，熔断器再次检测是否开启，默认为5000，即5s钟
     *      circuitBreaker.sleepWindowInMilliseconds
     *
     *      circuitBreaker.errorThresholdPercentage //错误率，默认50%
     *
     * 3个参数放在一起，所表达的意思就是：
     *      每当20个请求中，有50%失败时，熔断器就会打开，此时再调用此服务，将会直接返回失败，
     *      不再调远程服务。直到5s钟之后，重新检测该触发条件，判断是否把熔断器关闭，或者继续打开。
     *
     * 服务降级
     *      有了熔断，就得有降级。所谓降级，就是当某个服务熔断之后，服务器将不再被调用，
     *      此时客户端可以自己准备一个本地的fallback回调，返回一个缺省值。
     *      这样做，虽然服务水平下降，但好歹可用，比直接挂掉要强，当然这也要看适合的业务场景。
     *
     *
     * 服务限流
     *      限流在日常生活中也很常见，比如节假日你去一个旅游景点，为了不把景点撑爆，
     *      管理部门通常会在外面设置拦截，限制景点的进入人数（等有人出来之后，再放新的人进去）。
     *      对应到计算机中，比如要搞活动，秒杀等，通常都会限流。
     *      说到限流，有个关键问题就是：你根据什么策略进行限制？？
     *      比如在Hystrix中，如果是线程隔离，可以通过线程数 + 队列大小限制；
     *      如果是信号量隔离，可以设置最大并发请求数。
     *
     *      另外一个常见的策略就是根据QPS限制，比如我知道我调用的一个db服务，qps是3000，
     *      那如果不限制，超过3000，db就可能被打爆。这个时候，我可用在服务端做这个限流逻辑，也可以在客户端做。
     *
     *      还有一些场景下，可用限制总数：比如连接数，业务层面限制“库存“总量等等。。
     *
     * 限流的技术原理 －令牌桶算法
     *      关于限流的原理，相信很多人都听说过令牌桶算法，Guava的RateLimiter也已经有成熟做法，这个自己去搜索之。
     *      此处想强调的是，令牌桶算法针对的是限制“速率“。至于其他限制策略，比如限制总数，限制某个业务量的count值，
     *      则要具体业务场景具体分析。
     *
     * 异步RPC
     *      异步RPC主要目的是提高并发，比如你的接口，内部调用了3个服务，时间分别为T1, T2, T3。
     *      如果是顺序调用，则总时间是T1 + T2 + T3；如果并发调用，总时间是Max(T1,T2,T3)。
     *      当然，这里有1个前提条件，这3个调用直接，互相不依赖。
     *      同样，一般成熟的RPC框架，本身都提高了异步化接口，Future或者Callback形式。
     *      同样，Hystrix也提高了同步调用、异步调用方式，此处不再详述。
     *
     * 总结
     *      服务限流、熔断、降级、异步RPC是基于SOA的分布式系统中一些常见的基本策略，
     *      并且这些策略现在都有成熟的开源框架支持。用好这些策略，对整个系统的容错性、稳定性有很大帮助。
     */
}
