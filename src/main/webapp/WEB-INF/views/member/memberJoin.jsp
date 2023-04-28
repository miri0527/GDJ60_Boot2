<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
 <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Modern Business - Start Bootstrap Template</title>
        <!-- css favicon -->
        <c:import url="../temp/style.jsp"></c:import>
<title>Insert title here</title>
</head>
<body class="d-flex flex-column h-100">
  <main class="flex-shrink-0">
      	<!-- Navigation -->
      <c:import url="../temp/header.jsp"></c:import>
         <!-- Header-->
    	 <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Get in touch</h1>
                            <p class="lead fw-normal text-muted mb-0">We'd love to hear from you</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- * * SB Forms Contact Form * *-->
                                <!-- * * * * * * * * * * * * * * *-->
                                <!-- This form is pre-integrated with SB Forms.-->
                                <!-- To make this form functional, sign up at-->
                                <!-- https://startbootstrap.com/solution/contact-forms-->
                                <!-- to get an API token!-->
                                <!-- <form id="joinForm" action="./memberJoin" method="post" data-sb-form-api-token="API_TOKEN" enctype="multipart/form-data">-->
              						<form:form id="joinForm" modelAttribute="memberVO" >
                                    <!-- 아이디 input-->
                                      <div class="form-floating mb-3">
                                        
                                        <form:input path="username" id="userName" cssClass="form-control"/>
                                        <form:label path="username" for="userName">아이디 입력</form:label>
                                        <form:errors path="username" cssStyle="color:red;"></form:errors>
                                    </div>
                                    <!-- 비밀번호 input-->
                                    <div class="form-floating mb-3">
                                       
                                        <form:password path="password" id="password" cssClass="form-control"/>
                                        <form:label path="password" for="password">비밀번호 입력</form:label>
                                       	<form:errors path="password" cssStyle="color:red;"></form:errors>
                                    </div>
                                     <!-- 비밀번호 check-->
                                    <div class="form-floating mb-3">
                                        <form:password path="passwordCheck" id="passwordCheck" cssClass="form-control"></form:password>
                                        <label for="passwordCheck">비밀번호 확인</label>
                                        <form:errors path="passwordCheck"></form:errors>
                                      
                                    </div>
                                    <!-- 이름 입력 -->
                                     <div class="form-floating mb-3">
                                        <form:input path="name" id="name" cssClass="form-control"/>
                                        <label for="name">이름</label>
                                         <form:errors path="name" cssStyle="color:red;"></form:errors>
                                        
                                    </div>
                                   <!-- 이메일 입력 -->
                                    <div class="form-floating mb-3">
                                        <form:input cssClass="form-control" id="email" path="email"/>
                                        <form:label path="email" for="email">이메일</form:label>
                                       
                                    </div>
                                    <!-- 생년월일 입력 -->
                                     <div class="form-floating mb-3">
                                        <input class="form-control" id="birth" name="birth" type="date" data-sb-validations="required,email" />
                                        <label for="birth">생년월일 입력</label>
                                        <form:errors path="birth" ></form:errors>
                                    </div>
                                    <!--  -->
                                 	
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Submit</button></div>
                               </form:form>
                            </div>
                        </div>
                    </div>
                    <!-- Contact cards-->
                    <div class="row gx-5 row-cols-2 row-cols-lg-4 py-5">
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-chat-dots"></i></div>
                            <div class="h5 mb-2">Chat with us</div>
                            <p class="text-muted mb-0">Chat live with one of our support specialists.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-people"></i></div>
                            <div class="h5">Ask the community</div>
                            <p class="text-muted mb-0">Explore our community forums and communicate with other users.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-question-circle"></i></div>
                            <div class="h5">Support center</div>
                            <p class="text-muted mb-0">Browse FAQ's and support articles to find solutions.</p>
                        </div>
                        <div class="col">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-telephone"></i></div>
                            <div class="h5">Call us</div>
                            <p class="text-muted mb-0">Call us during normal business hours at (555) 892-9403.</p>
                        </div>
                    </div>
                </div>
            </section>      
  </main>
 <!-- footer 적용 -->
      <c:import url="../temp/footer.jsp"></c:import>
     <!-- footer 끝 -->
     <script type="text/javascript" src="/js/joinFormCheck.js"></script>
  
</body>
</html>