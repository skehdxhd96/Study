# *<h3>웹 통신(Http)</h3>*

많은 정보들을 주고받는 인터넷에는 **엄격한 규약**이 존재하는데, 이것을 **protocol**이라고 함.

일반적으로 Http/Https<br>
TCP/IP프로토콜을 가지고 서버와 클라이언트 사이의 파일전송을 하기위한 FTP<br>
파일전송 프로토콜인 Telnet, SSH 등이 있다.

#

Http란? Hyper Text, 즉 **HTML을 전송하기 위한 프로토콜**<br>
HTML이란? Hyper Text Markup Language의 약자로, **웹 문서를 구성하는 언어.**

#

*<h2>통신 - Request/Response</h2>*

**기본적으로 client가 Server에게 Request를 보내고, 서버는 Request에 대한 Response를 보내는 방식으로 이루어진다.**<br>
![a](https://media.vlpt.us/images/sdc337dc/post/d87038b3-e1fb-4de9-bcd2-0930c87f475d/image.png)
![b](https://media.vlpt.us/images/sdc337dc/post/83c5250a-805e-4c2f-ac94-c221bb97ecba/image.png)

#

*<h2>Http-stateless</h2>*

Http통신은 state개념이 존재하지 않는다.<br>
즉, 통신을 주고받아도 클라이언트와 서버는 연결된것이 아니고, **각각의 통신은 독립적**이다. **상태를 저장하지 않는다는 소리이다.**(서로 각각의 요청을 기억하지 못한다.)<br>
그래서 로그인 같은 경우, **세션이나 저장소 같은 방식을 이용**하여 저장하는 것처럼 보이게 한다.

#

*<h2>Http 패킷</h2>*

![c](https://media.vlpt.us/images/sdc337dc/post/72ffbf58-513d-47b3-a8d3-0d6f0cb5fb52/image.png)<br>
Http통신은 요청을 보내고 응답을 받을 때, 그 정보들을 **패킷**에 넣어 보낸다.<br> 
패킷은 **Header/Body**로 나뉜다.
>>**Header** : 보내는 사람의 주소, 받는 사람의 주소, 패킷 생명시간<br>
**Body** : 실제 전달하고자 하는 내용

![d](https://media.vlpt.us/images/sdc337dc/post/19c3100a-bfba-4fb3-bce9-9b0d702a2f50/image.png)
>>**general** : 요청 url정보와 메소드, 상태코드를 확인할수 있다.<br>
**Response Headers** : 응답 헤더. 응답온 패킷의 헤더를 확인할수있으며, 서버의 종류, 연결상태 등이 담겨있다.<br>
**Request Headers** : 요청 헤더. 요청을 보낸 패킷의 헤더를 확인할 수 있다. 보낸 클라이언트의 종류, 요청한 파일의 종류 등을 알 수 있다.

#

*<h2>Http Request 구조</h2>*

1. start Line(요청 내용)
>>Http메소드<br>
Request target : 요청의 의도(GET, POST, DELETE, UPDATE)<br>
Http veresion : 버전에 따라 메세지나 데이터 구조가 다를 수 있어 버전명시

2. Header : Http 요청이 전송되는 목표 주소

3. Body

#

출처 : https://velog.io/@sdc337dc/%EC%9B%B9-%EA%B0%9C%EB%85%90-Http-%ED%86%B5%EC%8B%A0
