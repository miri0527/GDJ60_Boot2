<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>         
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
                            <h1 class="fw-bolder">Member Login Page</h1>
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
                                
                                <c:if test="${not empty param.errorMessage}">
                                <h1>${param.errorMessage}</h1>
                                </c:if>
                                
                                <form id="contactForm" action="./memberLogin" method="post" data-sb-form-api-token="API_TOKEN" >
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <!-- userName input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="username" type="text" name="username" value="${cookie.remember.value} "placeholder="Enter your UserName..." data-sb-validations="required"/>
                                        <label for="userName">아이디</label>
                                        <div class="invalid-feedback" data-sb-feedback="name:required">A name is required.</div>
                                    </div>
                                    <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" name="password" type="password" data-sb-validations="required,email" />
                                        <label for="password">Password</label>
                                        <div class="invalid-feedback" data-sb-feedback="email:required">An email is required.</div>
                                        <div class="invalid-feedback" data-sb-feedback="email:email">Email is not valid.</div>
                                    </div>
                                    
                                    <div class="form-floating mb-3">
                                        <input class="" id="remember" name="remember" type="checkbox" value ="remember" data-sb-validations="required,email" />
                                        <label for="remember">ID기억하기</label>
                                       
                                    </div>
                                   
                                   
                            
                                    <!-- Submit Button-->
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Submit</button></div>
                                </form>
                                
                                <a href="./findPassword">비밀번호 찾기</a>
                                <a href="/oauth2/authorization/kakao">Kakao Login</a>
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
  <script type="text/javascript">
  /* UserLoginFailHandler에서 errorMessage를 redirect로 보내면 url창에 파라미터가 찍히니 그게 싫으면 여기에 이걸 추가해주면 된다 */
 	history.replaceState({}, null, location.pathname)
 </script>    
</body>
</html>