# jsp+servlet实现数据库增删改查

## 三层结构

（1）表示层（USL，即User Show Layer）：视图层
    a. 前台：对应于MVC中的View，用于和用户交互、界面的显示。
    b. 对应于MVC中的Controller，用于控制跳转、调用业务逻辑层。
（2）业务逻辑层（BLL，即Business Logic Layer）：Service层
    a. 接收表示层的请求及调用
    b. 组装数据访问层
（3）数据访问层（DAL，即Data Access Object）：Dao层
    直接访问数据库的操作

## tomcat+eclipse搭建服务器

（eclipse里配置tomcat的方法百度一搜一大堆，不细说）

### eclipse里下载好JavaEE插件：

+ 打开eclipse,点击help-Install new software-单击add，在弹出窗口的work with中输入网址：2020-09 - http://download.eclipse.org/releases/2020-09
+ 按回车，点击小三角选择`  Web, XML, Java EE and OSGi Enterprise Development	`

### 创建javaee项目：

+ 点击左上角 File > new > project，在弹出来的框里选择 Web > Dynamic Web Project，这样一个简单的javaee项目框架就自动搭建好了

### 项目结构分析

+ 通常在创建好的项目里会有 Java resources 和 WebContent 两个大文件夹，顾名思义，一个是放java后台的文件，一个是放html css js jsp等文件。
+ Java Resources：
  + src放代码文件：
    + dao：DatabaseUtil.java 直接对数据库进行操作的文件；
    + service：Config.java获取配置信息、PersonUtil.java对Person表进行操作（调用DatabaseUtil里的方法）、UserUtil.java对User表进行操作；
    + utils：PersonTable.java、UserTable.java分别对person表和user表的数据进行获取，其实就是在增加数据的时候方便使用写的类；
    + servlet：AddServlet.java、DeleteServlet.java、ShowServlet.java分别实现根据请求的不同实现添加、删除、显示的功能；
  + libraries放库文件；
+ WebContent ：
  + css、images、js前端文件；
  + META-INF不用管；
  + WEB-INF下有lib文件夹放要使用到的jar文件，web.xml配置文件用来配置jsp和servlet（比如首页面 和 注册各个servlet）；
  + jsp文件直接放在WebContent下。

### 启动tomcat服务

+ 右键点击项目文件夹，选择 Run as > Run on server ，选择自己的tomcat即可；
+ 注意每次关闭的时候要重新部署项目文件哦， 不然会出现不可预计的错误，详见 [莫名其妙的404](https://www.cnblogs.com/TRY0929/p/13960673.html)

## jsp和servlet配置

由于jsp里就是大量的html加上一些java，所以可以直接将项目首页设置为一个jsp页面，刚刚也说了jsp都是直接放在 WebContent 下的。

###  index.jsp（首页）

+ 用来显示对person表的插入和对user表的删除操作的页面；
+ 实现的话就采用的两个form标签来实现。form标签的action可以制定将form表单提交到哪里，这里person表的操作是提交到AddServlet，user是提交到DeleteServlet，method可以制指定方式（这里都是post）；
  + form标签的action不仅可以是url，还可以是servlet哦；
+ 里面的每个type为text的input输入框一定都要有name属性，这就是后面在servlet中通过request可以获取到的参数键值；
+ 点击按钮就是submit，直接post到指定地方。

+ 同时由于数据库最好同时不要进行多次连接，所以在index.jsp中实例化一次DatabaseUtil类，将实例化后的databaseUtil存入到servlet上下文里（application），后来每次要用的时候不需要再重新进行数据库连接，而是可以直接通过servlet上下文获得：

```java
ServletContext sc = getServletConfig().getServletContext();
        DatabaseUtil databaseUtil = (DatabaseUtil)sc.getAttribute("databaseUtil");
```

### result.jsp

+ 用来显示插入或更新或删除之后的结果页面；
+ 这个页面是在AddServlet或DeleteServlet里操作结束之后进行请求转发跳转到的页面（注意不是直接在刚刚的index.jsp直接跳到这个页面哦）；
+ 用jsp的核心标签来显示各项数据，这里使用的是 `c:choose 和 c:when`来根据表以及操作结果的不同来显示不同的信息，通过c:when的test来写一些条件看是否显示当前标签；
  + 使用这个标签要先引入`<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`；
  + 再将standerd和jstl包放到 WebContent/WEB-INF/lib下；
+ AddServlet和DeleteServlet都根据当前表和操作成功与否的不同设置了tableType、operatorResult以及当前操作的username，它们通过request来传递到result.jsp里让它可以拿到（通过`${}`）；
+ 最下面有一个按钮可以查看当前数据库中的数据，通过一个a标签，将href属性设置为ShowServlet（这里可以看到，a标签的href也不仅仅可以是url 还能是servlet，其实这俩都是能够一起出现的）。

### showList.jsp

+ 用来显示两个列表里的所有条目；
+ 这个页面是在ShowServlet里获取好数据后进行请求转发跳转到的页面，注意要将得到的列表数据放到request里一起传到showList里；
+ 用两个jsp的核心标签来显示两个表里的数据，`c:forEach`遍历两个数组，里面用item来获取各数组的元素；
+ 最下面有个a标签可以点继续进行操作界面（直接把a的href属性设置为index.jsp）。

### AddServlet和DeleteServelet

+ 用来对person表进行添加或更新数据的操作；
+ 首先从request里获取到各项要添加/删除的数据，获取到的age是string类型，但实际上要存入的是int类型，所以这里通过java的Integer.getInteger(string)来转换为字符串；
+ 这里要在操作进行之前获取到之前在index.jsp中实例化的databaseUtil对象，就不用每次对数据进行操作的时候都进行一次数据库的连接了；

```java
// 获取servlet上下文对象
ServletContext sc = getServletConfig().getServletContext();
DatabaseUtil databaseUtil = (DatabaseUtil)sc.getAttribute("databaseUtil");
```

+ 获取到之后在try...catch里调用user或person的util对象来进行增删改查操作；
+ 操作进行完毕要将tableType、operatorResult、username放到请求里，继续将请求转发给result.jsp，这样在result.jsp里也能拿到这些数据并进行判断了。

### ShowServlet

+ 用来获取person和user表里的所有条目；
+ 同样还是要先获取存储在到servlet上下文对象里的databaseUtil对象，再在try..catch里调用两个表的queAll函数获取到所有数据，放到request里；
+ 转发请求到showList.jsp页面。

## web.xml

### 作用

+ web.xml文件是用来初始化配置信息：比如Welcome页面、servlet、servlet-mapping、filter、listener、启动加载级别等；

### 启动流程

+ 启动一个WEB项目的时候，WEB容器会去读取它的配置文件web.xml，读取`<listener>`和`<context-param>`两个结点。 

+ 紧急着，容创建一个ServletContext（servlet上下文），这个web项目的所有部分都将共享这个上下文。 

+ 容器将`<context-param>`转换为键值对，并交给servletContext。 

+ 容器创建`<listener>`中的类实例，创建监听器。 

### 配置（这里只说用到了的）

#### 欢迎页面\<welcome-file-list\>

+ 访问一个网站时，默认看到的第一个页面就叫欢迎页，一般情况下是由首页来充当欢迎页的。一般情况下，我们会在web.xml中指定欢迎页;
+ 但 web.xml并不是一个Web的必要文件，没有web.xml，网站仍然是可以正常工作的；
+ 只不过网站的功能复杂起来后，web.xml的确有非常大用处，所以，默认创建的动态web工程在WEB-INF文件夹下面都有一个web.xml文件；
+ 显示时按顺序从第一个找起，如果第一个存在，就显示第一个，后面的不起作用。如果第一个不存在，就找第二个，以此类推，如果所有文件都不存在，则抛出404错误，如下：

```xml
<welcome-file-list>
	<welcome-file>index.jsp</welcome-file>
	<welcome-file>index.html</welcome-file>
</welcome-file-list>
```

#### 命名与定制URL\<servlet\>

+ 我们可以为Servlet和JSP文件命名并定制URL,其中定制URL是依赖命名的，命名必须在定制URL前；
+ 注意在定制URL的时候`<url-pattern>`和servlet里`@WebServlet("/")`只能出现一个，要不然会出现冲突，所以在servlet里写了WebServlet的话这里就不要再写servlet-mapping啦；

```xml
<!-- 命名URL -->
<servlet>
	<servlet-name>AddServlet</servlet-name>
  <!-- 下面写package.className -->
	<servlet-class>servlet.AddServlet</servlet-class>
</servlet>
<!-- 为 Servlet 定制 URL -->
<servlet-mapping>
	<servlet-name>AddServlet</servlet-name>
	<url-pattern>/AddServlet</url-pattern>
</servlet-mapping>
```

## 发送请求的几种方式

能够发送请求的方式很多，前端后端都能发，这里就说几种项目里用到的：

### form标签

+ form标签可以直接指定action为要发送请求的目的，method为发送请求的方式(post get等)；
+ 点击form里的submit按钮即可进行提交，提交的内容为form里包括的所有input的标签，注意要设置它们的name属性，在后端可以通过`request.getParameter('name')`拿到数据。

### a标签

+ a标签的href可以指定要发送请求的目的，只能以get方式发送请求；

+ 点击a标签就发送出去了，只能在请求url里携带一些请求信息。

### request.getRequestDispatcher(url or servlet)

+ **属于转发，是服务器跳转**，相当于方法调用，在执行当前文件的过程中转向执行目标文件，两个文件(当前文件和目标文件)属于**同一次请求，前后页共用一个request**，可以通过此来传递一些数据或者session信息，request.setAttribute()和request.getAttribute()；

+ 在前后两次执行后，**地址栏不变**，仍是当前文件的地址；

+ 不能转向到本web应用之外的页面和网站，所以转向的速度较快；

+ URL中所包含的“/”表示应用程序(项目)的路径。

### response.sendRedirect(url or servlet)

+ **属于重定向，是客户端跳转**，相当于客户端向服务端发送请求之后，服务器返回一个响应，客户端接收到响应之后又向服务端发送一次请求，**一共是2次请求，前后页不共用一个request**，不能读取转向前通过request.setAttribute()设置的属性值；

+ 在前后两次执行后，**地址栏发生改变**，是目标文件的地址；

+ 可以转向到本web应用之外的页面和网站，所以转向的速度相对较慢；

+ URL种所包含的"/"表示根目录的路径。

### 总结

后端里的话一般采用都没两种，对数据进行修改、删除、添加操作的时候，应该用response.sendRedirect()。如果是采用了request.getRequestDispatcher().forward(request,response)，那么操作前后的地址栏都不会发生改变，仍然是修改的控制器，如果此时再对当前页面刷新的话，就会重新发送一次请求对数据进行修改，这也就是有的人在刷新一次页面就增加一条数据的原因。

## 总结

+ 这次实验是在之前的java控制数据库的基础上做的，之前的实验内容就是放在Java Resources里的各种连接和操作数据库的封装好的方法，在这次实验里就只要调用那些封装好的接口就可以了；
+ 服务端部分的代码逻辑一定要十分明确，之前写前端的时候涉及到过js的后端代码，但这次是纯用java写的，非常不一样的体验；
+ js和前端交涉非常深，甚至可以直接在页面上写js逻辑，当然后端各种api还是要和前端分开写的，但总的来说前端人员还是对js比较熟悉一些；
+ Java写的服务器就更加注重逻辑了，这次略微学习了一些jsp和servlet的知识，jsp给我的感觉就像是之前在html页面里写js一样，这里是在html里写Java，有少量的逻辑。servlet就像是之前写的api接口，不过这里是通过jsp里的标签跳转的方式发送请求到servlet，servlet接收到请求再进行业务逻辑的实现；
+ 后面还会完善一下前端那边的部分啦，现在只是大体写完了后端Java部分的代码，像参数校验 UI什么的还没怎么写（页面丑的无法直视），也没写什么js，有空补充~

