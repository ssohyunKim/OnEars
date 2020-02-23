# OnEars  

2018 AWS Project  
----------------

**1. Introduction**  

1.1 On Ears  
- 'On Ears' 란 사용자가 선택한 다양한 해외 뉴스를 모아 텍스트와 음성으로 제공해주는 서비스입니다.  
- 사용자가 선택한 기사를 요약본과 한글로 번역해주는 서비스를 제공합니다.   
- 사용자의 성향을 분석하여 개인 맞춤형 기사를 제공합니다.  

1.2 기획 의도    
- 기존 뉴스 서비스의 경우, 국내 뉴스에만 국한되어 있어 정보 제공이 제한적이고,  해외 뉴스를 원하는 사용자들의 수요를 충족시키지 못합니다.  
- 다양한 해외 영자 신문 사이트에 직접 접속해 기사를 읽을 필요 없이, 공신력 있는 외신들(BBC, CNN, The New York Times 등)의 주요기사를 모아 분야별 요약기사를 제공합니다.  
- 사용자가 음성만으로 원하는 정보를 선택하여 조작이 가능하다는 점에서 기존의 다른 서비스들보다 편리함을 증대시키고 개개인의 관심사를 기반으로 한 맞춤형 뉴스 서비스 제작을 목표로 하였습니다.   

1.3 작품 내용  
- STT를 이용하여 음성으로 원하는 기사 선택  
- 주요 외신을 Newspaper module로 데이터 크롤링하여 화면에 텍스트 출력  
- AWS Polly를 이용해 크롤링한 기사를 음성으로 제공  
- 크롤링한 기사를 요약하고 요약본을 Papago API를 이용하여 한글로 번역  

**2. 작춤의 개발 배경 및 필요성**

- 제작 동기
    기존의 해외 뉴스를 보려면 원하는 뉴스 사이트에 직접 접속하여 보고 싶은 기사를 찾아서 봐야합니다. 또한, 한글 번역본을 보려면 사용자가 따로 번역기를 이용해야하는 불편함이 있습니다. 본 프로젝트는 이러한 점을 해결하고자 합니다. 

- 제작 목적
    - 다양한 외신 기사를 제공하여 쉽게 접할 수 있다.
    - 기사의 요약본을 제공함으로써 핵심 내용을 파악하기 쉽게 한다.  
    - 영문기사를 음성으로 제공하여 영어 학습에도 도움을 준다. 
    - 맞춤형 기사를 통해 원하는 정보에 선택적 접근을 가능하게 한다. 

**3. 주요 적용 기술**

    STT, Newspaper, Beautiful Soup(Web Crawler), Papago API, AWS(API Gateway, Lambda, RDS, DynamoDB, Polly)

**4. 화면**

<img src="/images/1.jpeg" width="20%" height="70%"/></img>
<img src="/images/2.jpeg" width="20%" height="70%"/></img>
<img src="/images/3.jpeg" width="20%" height="70%"/></img>
<img src="/images/4.jpeg" width="20%" height="70%"/></img>
<img src="/images/5.jpeg" width="20%" height="70%"/></img>
<img src="/images/6.jpeg" width="20%" height="70%"/></img>
<img src="/images/7.jpeg" width="20%" height="70%"/></img>
<img src="/images/8.jpeg" width="20%" height="70%"/></img>