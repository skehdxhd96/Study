 # *3-tier Architecture*
 
 ### 프레젠테이션 로직, 비즈니스로직, 데이터베이스 로직을 각각 다른 플랫폼 상에서 구현 한 것

# 

*<h3>tier</h3>* 컴포넌트들의 물리적인 분리
<br><br>
*<h3>layer</h3>* 컴포넌트들의 논리적인 분리
<br><br>

*<h3>장점</h3>*  업무분담 가능, 효율성의 증가.<Br>
서로 다른 서버들을 구성하므로 리스크 완화 가능<br>
여러대의 서버로 나누어서 각 계층이 동작하므로, 서버의 부하를 줄일수도 있고, scaleup을 고려할수도 있다.
<br><br>

*<h3>단점</h3>*  관리 포인트와 장애가 발생하는 포인트, 비용이 그만큼 늘어나게 됨. 

# 

1계층 구조(1Tier)
![1tier](https://t1.daumcdn.net/cfile/tistory/99FF9C3D5B5F22DF03)

**한 클라이언트 컴퓨터에 3가지 로직을 다 구현. 한 클라이언트 서버에서 모든걸 지원하므로 새로운 컴퓨터를 사용하고자 할 경우 모두 새로 변경해야 한다는 단점(한가지 로직을 바꾸기 위해 다른 로직의 변경이 필요함)존재**

2계층구조
![2tier](https://t1.daumcdn.net/cfile/tistory/990F36345B5F23111B)

**client tier와 data tier로 2개의 물리적 컴퓨터로 구성. 클라이언트와 서버를 분리하여 어플리케이션과 데이터베이스가 분리되어있기 때문에 데이터베이스의 변경이 편리함**

3계층구조
![3tier](https://t1.daumcdn.net/cfile/tistory/9964013E5B5F25572E)

**각 계층은 물리적으로도 독립적이며, 각 계층의 변경이 다른 계층에 의존하지 않는다.**

#

*presentation(client) 계층*

 >> 서로 다른 층에 있는 데이터 등과 커뮤니케이션<br>
인터페이스 지원(정적인 데이터 제공)<br>
GUI, Front-end라고도 불림<br>
비즈니스로직이나 데이터관리와 관련된 코드를 포함해서는 안됨.<br>
주로 웹서버를 뜻함(WEB서버)

*application 계층*

 >> business tier 혹은 transaction tier라고도 함. 클라이언트의 요청에 대해 서버처럼 행동.<br>
어떤 데이터가 필요한지 결정하는 역할.<br>
정보처리 규칙이 있으며(동적인 데이터 제공)<br>
middleware혹은 back-end라고도 불림.<br>
프레젠테이션코드나 데이터관리 코드를 포함해서는 안됨.<br>
주로 어플리케이션서버(WAS서버)

*데이터계층*

 >> 데이터베이스와 그것에 접근하여 읽거나 쓰는것을 관리하는 프로그램
주로 DB를 뜻하며, DB와 File system을 접근, 관리<br>
back-end라고도 불림.<br>
주로 DB서버를 뜻함(DB서버)

#

출처 : http://blog.naver.com/PostView.nhn?blogId=limoremo&logNo=220073573980
출처 : https://www.stevenjlee.net/2020/05/08/%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0-3%EA%B3%84%EC%B8%B5-%EA%B5%AC%EC%A1%B0-3-tier-architecture/

