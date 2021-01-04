# Controller Return Type
>> ### ModelandView
```
@RequestMapping("/hello")
public ModelAndView hello() { 
     ModelAndView view = new ModelAndView();
     view.setViewName("hello");
     view.addObject("name", "Lim"); 
     return view; 
     } 
 }

setViewName에서 설정한 "hello.jsp"가 View가 된다.

```

>> ### String
```
@RequestMapping("/hello")
public String hello() { 

    return "hello"; 

}

return 안에있는 문자열인 hello.jsp가 View가 된다.

```

>> ### Void
```
@RequestMapping("/hello") 
public void hello() { 

}

RequestToViewNameResolver를 통해 자동생성되는 View이름이 사용된다.
URL과 View이름을 통일해서 사용해야 한다.

```

출처 : <https://sambalim.tistory.com/69>

