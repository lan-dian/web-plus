# Web-Plus

​	结合实际开发对web做的增强，目的在于快速完成项目初期准备工作

# 功能

## 统一接口返回封装类

​	CommonResult

​	提供有理论设计支持的统一接口返回封装类，支持静态方法和普通方法共存

## 统一业务异常类

​	BusinessException

​	抛出运行时异常，在service里面直接throw

## 提供请求全局日志

​	自动与CommonResult，权限框架Guardian配合，支持自定义回收日志，而且支持自定义，实现RequestLog接口，而且@Scope必须是**多例**。自定义日志收集器，实现接口RequestLogCollector

## objectMapper加强

​	支持java8的新日期格式类序列化，支持Long返回前段时自动转换为String防止进度丢失。



# 组件

## CommonResult

​	先谈理论，首先基于项目的可读性，可维护性以及各种文档解析工具，我们这边的核心是采用了泛型，这是一切的基本。但是，众所周知，java泛型是编辑器有效的，在运行期间会被擦除，所以泛型也给我们带来了很多的限制。那，我们就结合业务实际，来思考，如何设计这个类。

​	先“排除”几种情况：那有人说，我的公司用的dubbo，在service返回的就是一个DTO，那这种在Controller层就是把DTO解析写进去就可以。我们不考虑这种情况，带泛型的DTO就是多套了一层，不带的话，和Object没有什么区别，我这里不提供实现，那种静态方法直接胜任。

---

​	首先，我们针对返回类型分类，一种接口是获取数据的，一种接口是向服务器发布指令的。比如，获取我的个人信息，这明显是一个获取数据的接口，而注册则是向服务器发布指令的。那这两种是截然分开的吗？

​	发布指令的接口有没有可能获取数据？举个例子，文件上传解析接口，如果公司有解析文件上传解析数据到数据库的业务，这个看似是向服务器发送指令，但是存在一个问题，如果我们的部分数据有误，这个是需要回显给用户的，那这种问题怎么解决？很简单，把这个返回变成获取数据的，如果没有错误，就按成功返回。那现在，是不是所有接口都是获取数据接口，可以这么理解，但是基于会有接口是永远不可能返回处了文字信息以外的任何数据的，我先基于这样的假设，来设计我们的类。

> 永远不可能返回数据

​	这样接口的设计肯定CommonResult<Void>然后把msg返回给签到，那么现在情况就剩下怎么决定msg是什么。我们来考虑这么几种情况，我们不在controller处理数据，那肯定就是service帮助我们处理，service能返回什么样的结果，一般来讲，不返回数据的service不就是boolen和void吗

​	先聊聊void，void肯定是这个会发生很多异常，然后通过业务异常抛出，在全局异常处理器中捕获，这样，我们只要提供一个有全参数构造器构造方法，和一个一一对应的BusinessException，然后泛型设计为<Object>这样问题就解决了。

​	参数校验异常，我们给出err的静态返回，boolen和void我们提供自动校验的静态返回，而且都是静态返回。那么这个问题被很好的解决了。

> 获取数据

​	先来一个结论，如果你中途失败不能返回数据，你返回一个空数据就可以。同时由于java泛型的限制，你要是想返回数据的话，你必须实例化这个类，就是你必须把它new出来，所以，这些数据肯定都只能是静态方法了。

​	理论冲突来了，err方法静态和普通的冲突了？怎么解决，其实不难哈，我换一个名字就可以了，而且比较是静态是普通的发生了冲突，我们在调用方法的时候是感觉不出来的。

​	牛啊，那为什么error没有data参数呢？其实你可以这样想，我们的实体类肯定只能返回一个参数，如果你data创建出来了，那么你返回data就肯定不是失败的情况，如果是失败了，那data肯定就没有被创建出来。这就是逻辑的力量！

​	那我们考虑一下data，我们直接反向考虑，如果data传入了code，那。。它是成功吗？既然data传入了，那肯定是成功啊，那你用这个code干什么？如果是msg呢，那可以，虽然没有什么用，我们还是把扩展留给你了。但肯定是成功吧。另外我提供一个全参数构造方法，灵活度直接打开。

 	唉，那失败返回失败原因或者相关数据呢？你看下面。

> 异常处理

​	那只剩下最后一个情况了，就是获取数据的时候，发生错误，要返回其他的数据，这个时候，肯定是需要异常处理器来解决的，而且异常处理器肯定是不会有参数的，所以我们这边直接提供静态方法，提供一个接口，让你自己来构造。

> 特殊情况

​	目前一切理论和实际都已经完美了，我决定这次突破问题的核心就是在于，我把静态和动态两种情况给分离开了，而且我还提供了链式调用，让你去修改不满意的地方，可以说是灵活度和简单性都大满了！

​	有的比如上传文件解析那种，返回的特殊情况比较固定，他给当成有返回值的看了，行，我认，我想想怎么解决这个问题，首先分析它，这个返回的情况有解析成功，解析结果在boolen中，解析失败返回。

​	首先是解析失败返回，在code里面，真是逼的我把这种情况给加上了。然后说你解析成功，那我直接body试试？成功了，但我不满意，语义化没上去。

---

```java
@GetMapping("/test")
public CommonResult<Void> result(String msg){
    if(!StringUtils.hasText(msg)){
        return CommonResult.err("msg不能为空");
    }
    return CommonResult.ok(true);
}
```

```java
@GetMapping("/test")
public CommonResult<User> result(String msg){
    CommonResult<User> result=new CommonResult<>();

    if(!StringUtils.hasText(msg)){
        return result.error("msg不能为空");
    }
    
    User user=new User();

    return result.body(user);
}
```

```java
@GetMapping("/test")
public CommonResult<ErrList> result(String msg){
    CommonResult<ErrList> result=new CommonResult<>();


    ErrList errList=new ErrList();
    if(CollectionUtils.isEmpty(errList)){
        return result.err(errList);
    }

    return result.success();
}
```

