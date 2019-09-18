# Springboot-eCommerce
  * 2019. 7. 8. 시작
  * Ubuntu 18.04LTS 기반
  * Build Tools은 Maven, ORM은 JPA, Template Engine은 Thymeleaf 사용
  * application.properties를 사용한 스프링부트 환경설정
  * Porto사의 eCommerce-Template 사용
  * 가상 옷 쇼핑몰
  * http://keencho.ml:8900 (vmware 상에서 돌아가는 가상 웹서버이므로 서버를 항상 켜둘수는 없습니다. seyoung050412@gmail.com으로 연락주시면 빠르게 조치하겠습니다.)

# 주요 특징
 * REST API를 사용한 개발 - GET, POST, PUT, DELETE Method 사용
 * 대분류, 중분류, 소분류로 이루어진 계층 구조
 * 관리자 페이지 구현
 * Interceptor를 이용한 세션 인증 처리 절차를 통해 관리자 페이지 접근 제한 (관리자 페이지는 'admin', '1' 로 로그인후 접근 가능합니다.)
 * @PageableDefault와 Ajax, jQuery의 .load() Method를 사용한 Pagination 처리
 * 파일 업로드, 파일 다운로드, 장바구니, 위시리스트, 마이페이지, 고객센터, 상품별 문의, 상품별 리뷰 등 부가 서비스 구현
 * 결제 페이지로 넘어갈시 많은 데이터를 jSon 객체를 사용해 쉽게 처리
 * 아임포트 결제 api를 사용해 결제 데모 시스템 구현
