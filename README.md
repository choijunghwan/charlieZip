# charlieZip
내가 방문했던 카페의 커피 맛을 기록하고 공유하는 서비스입니다.
### 프로젝트 목표
* 실제 배포해 서비스가 운영한다고 가정하고 구현합니다.
* 단순 기능 구현뿐 아니라 대용량 트래픽 처리도 고려하여 구현하는 것이 목표입니다.
* 프로젝트의 확장 및 변경을 고려하여 설계하는것이 목표입니다.
* 개인 프로젝트이지만 규칙을 정해 통일성 있게 진행합니다.

<br><br>

# 프로젝트 기술 스택
<img src="https://user-images.githubusercontent.com/14924689/135228800-fbdcb53e-33b1-434b-889f-7391358745ea.png"></img>


<br><br>

# Git 브랜치 전략
Git-Flow 전략과 Github-Flow 중에 Github-Flow 브랜치 전략을 사용하였습니다.  
develop 브랜치에서 기능을 개발 후 Pull Request를 통해 master로 merge 합니다

* master : 출시 가능한 배포 버전을 의미합니다.  
* develop : 배포하기 전 기능개발, hotfix 등 모든 과정을 하는 브랜치입니다.

[Git-Flow 와 Github-Flow 차이](https://github.com/choijunghwan/Today-I-Learn/blob/main/Git/Git-flow.md)

<br><br>

# 패키지 구조
* study.charlieZip
  * config
  * controller
  * service
  * repository
  * entity

(패키지구조 리팩토링 준비중)

<br><br>

# 기술적 issue 해결과정
[#1. thymeleaf Refactoring](https://github.com/choijunghwan/Today-I-Learn/blob/main/Project/CharlieZip/thymeleaf_refactoring.md)  
[#2. Bean validation Refactoring](https://github.com/choijunghwan/Today-I-Learn/blob/main/Project/CharlieZip/validation_refactoring.md)

<br><br>

# DB ERD
(그림 준비중)

<br><br>

# 프로젝트 화면 구성도
<img src="https://user-images.githubusercontent.com/14924689/135229193-9603e186-df4f-43f9-80ec-e8f6c0c5e805.png"></img>
<img src="https://user-images.githubusercontent.com/14924689/135229215-dde0f85b-7a41-4043-8a24-f1e7165c9eb3.png"></img>
<img src="https://user-images.githubusercontent.com/14924689/135229227-08dd11a3-c83c-4704-86d3-aeea863e4474.png"></img>







