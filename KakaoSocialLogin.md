# *<h3>카카오 로그인 이해하기</h3>*

**카카오계정 - application**을 연결하여 **토큰**을 발급받고 그 토큰으로 카카오API를 이용할수 있도록 하는 기능

#

토큰은 크게 "카카오 API호출 권한"을 부여하는 **Access Token**과
            Access Token을 갱신하는 **Refresh Token**이 존재한다.

"로그아웃"은 로그인을 통해 발급받은 토큰을 만료시는 방법으로
사용자가 서비스에서 로그아웃을 요청했을때, 서버에서 특정 사용자를 로그아웃하도록 요청할때 해당 사용자를 로그아웃 시킬 수 있다.

"연결 끊기"는 카카오계정과 application의 연결을 끊는 기능.
"연결 끊기"를 하면 앱에서 더이상 해당 사용자 정보로 API를 호출할 수 없고, 카카오 플랫폼에서도 해당 앱에 대한 해당 사용자의 데이터를 지운다.
만약, 로그아웃 또는 연결끊기 후에 앱에 로그인 또는 연결을 할 경우, 이는 가능하다. 그러나 사용자가 "연결을 끊었던" 앱에 같은 카카오 계정으로 로그인 및 연결하더라도 기존 정보는 남아있지 않으며 복구도 불가능하다.

**"가입과 탈퇴 등 회원정보에 대한 처리"는 서비스에서 자체적으로 구현해야 함.**

카카오 로그인 진행 과정
![kako](https://developers.kakao.com/docs/latest/ko/assets/style/images/kakaologin/kakaologin_process.png)
1. 사용자가 앱에서 카카오 로그인 버튼 클릭
2. "카카오톡으로 로그인" 선택 시 : 카카오톡 실행 또는 실행중인 카카오톡으로 연결
   "다른 카카오계정으로 로그인" 선택 시 : 직접 계정 정보 입력화면 출력
3. "카카오톡으로 간편로그인" 카카오톡에 연결된 카카오계정의 자격정보(Credentials)를 통해 사용자를 인식. 직접 카카오계정을 입력해 로그인한 경우, 해당 계정의 자격정보로 인식
4. 자격정보가 맞다면, 카카오로그인 동의화면을 통해 정보및 기능활용동의 요청
5. 사용자가 필수항목에 동의하고 로그인을 요청하면 "인가코드 발급" 이 코드는 앱 정보의 RedirectURI에 전달
6. 앱은 전달받은 인가코드를 기반으로 토큰을 요청하고 받음.

즉<br>
>**카카오계정의 사용자 자격정보로 인가코드를 받아오고**<br> **인가코드로 Access Token과 Refresh Token을 얻는 과정**으로 구성.<br>
Refresh Token은 사용자가 매번 카카오계정 정보를 입력하거나 로그인하지 않고도 Access Token을 발급받을수 있도록 함.

위 사진에서는 kakaoServer가 하나로 표현되어있으나, KakaoServers는 **인증서버**와 **API서버** 두가지로 나뉜다. 카카오로그인은 인증서버가, 사용자 정보 가져오기는 다른 API서버들이 각각 요청을 받고 작업을 수행함.
로그인을 통해 앱과 사용자가 연결되고, 인가코드로 인해 토큰을 발급받았다면 사용자 정보 가져오기를 통해 현재 로그인한 사용자의 정보를 확인하여 서비스에 활용할 수 있다.

#

출처 & 그 이후의 사용자 정보와 변수에 대한 정보 : https://developers.kakao.com/docs/latest/ko/kakaologin/common#login