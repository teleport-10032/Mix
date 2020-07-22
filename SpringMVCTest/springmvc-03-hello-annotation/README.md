	使用注解配置;

​	在springmvc-servlet下规定扫描包的路径，之后只需要在Controller中配置注解即可;

​	可使用model传值然后返回到页面;

​	jsp页面放在WEB-INF中以保证安全;

​	若是@RestController则不会经过试图解析器，直接返回String

