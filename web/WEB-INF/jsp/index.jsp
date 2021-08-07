<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>ServeBite</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!--Less styles -->
   <!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
	<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->
	
<!-- Bootstrap style --> 
<link id="callCss" rel="stylesheet" href="<spring:url value="resources/themes/bootshop/bootstrap.min.css"/>" media="screen"/>
    <link href="<spring:url value="resources/themes/css/base.css"/>" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
<link href="<spring:url value="resources/themes/css/bootstrap-responsive.min.css"/>" rel="stylesheet"/>
<link href="<spring:url value="resources/themes/css/font-awesome.css"/>" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
<link href="<spring:url value="resources/themes/js/google-code-prettify/prettify.css"/>" rel="stylesheet"/>
<!-- fav and touch icons -->
<link rel="shortcut icon" href="<spring:url value="resources/themes/images/ico/favicon.ico"/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="<spring:url value="resources/themes/images/ico/apple-touch-icon-144-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<spring:url value="resources/themes/images/ico/apple-touch-icon-114-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="<spring:url value="resources/themes/images/ico/apple-touch-icon-72-precomposed.png"/>">
<link rel="apple-touch-icon-precomposed" href="<spring:url value="resources/themes/images/ico/apple-touch-icon-57-precomposed.png"/>">
	<style type="text/css" id="enject"></style>
  </head>
<body>
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
	<div class="span6">Welcome!<strong> User</strong></div>
	<div class="span6">
	<div class="pull-right">
	</div>
	</div>
</div>
<!-- Navbar ================================================== -->
<div id="logoArea" class="navbar">
<a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
</a>
  <div class="navbar-inner">
    <a class="brand" href="index.html">Serve Bite</a>
		<form class="form-inline navbar-search">
                    <select class="srchTxt" id="citiesSelect">
			<option>All</option>
                        <c:forEach var="i" begin="0" end="${cities.length() - 1}">
                            <option value="${cities.getJSONObject(i).getString("name")}">${cities.getJSONObject(i).getString("name")}</option>
                        </c:forEach>
		</select> 
		  <button type="button" id="submitButton" class="btn btn-primary">Go</button>
    </form>
    <ul id="topMenu" class="nav pull-right">
	 
	 <li class="">
	 <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span class="btn btn-large btn-success">Login</span></a>
	<div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h3>Login Block</h3>
		  </div>
		  <div class="modal-body">
			<form class="form-horizontal loginFrm">
			  <div class="control-group">								
				<input type="text" id="inputEmail" placeholder="Email">
			  </div>
			  <div class="control-group">
				<input type="password" id="inputPassword" placeholder="Password">
			  </div>
			  <div class="control-group">
				<label class="checkbox">
				<input type="checkbox"> Remember me
				</label>
			  </div>
			</form>		
			<button type="submit" class="btn btn-success">Sign in</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		  </div>
	</div>
	</li>
    </ul>
  </div>
</div>
</div>
</div>
<!-- Header End====================================================================== -->
<div id="carouselBlk">
	
</div>
<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->
	<div id="sidebar" class="span3">
            <div class="well well-small"><a id="myCart" href="product_summary.html"><img src="<spring:url value="resources/themes/images/ico-cart.png"/>" alt="cart">3 Items in your cart  <span class="badge badge-warning pull-right">$155.00</span></a></div>
		<ul id="sideManu" class="nav nav-tabs nav-stacked">
                        <li class="subMenu"><a>FOOD AND BEVERAGES [1000]</a>
				<ul style="display:none">
				<li><a href="products.html"><i class="icon-chevron-right"></i>Food </a></li>
															
			</ul>
			</li>
			<li class="subMenu"><a> ELECTRONICS [230]</a>
			</li>
			<li class="subMenu"><a> CLOTHES [840] </a>
			<ul style="display:none">											
			</ul>
			</li>
			<li><a href="products.html">HEALTH & BEAUTY [18]</a></li>
			<li><a href="products.html">SPORTS & LEISURE [58]</a></li>
			<li><a href="products.html">BOOKS & ENTERTAINMENTS [14]</a></li>
		</ul>
		<br/>
		  <div class="thumbnail">
			
		  </div><br/>
			<div class="thumbnail">
                            <img src="<spring:url value="resources/themes/images/products/kindle.png"/>" title="Bootshop New Kindel" alt="Bootshop Kindel">
				<div class="caption">
				  <h5>Kindle</h5>
				    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
				</div>
			  </div><br/>
			<div class="thumbnail">
                            <img src="<spring:url value="resources/themes/images/payment_methods.png"/>" title="Bootshop Payment Methods" alt="Payments Methods">
				<div class="caption">
				  <h5>Payment Methods</h5>
				</div>
			  </div>
	</div>
<!-- Sidebar end=============================================== -->
		<div class="span9">		
			<div class="well well-small">
			
		</div>
		<h4>Hottest Meals </h4>
			  <ul class="thumbnails">
                              <c:forEach var="i" begin="0" end="${meals.length() - 1}">
				<li class="span3">
				  <div class="thumbnail">
                                      <a  href="product_details.html"><img src="${meals.getJSONObject(i).getString("pictureString")}" alt="" width="150" height="150"/></a>
					<div class="caption">
					  <h5>${meals.getJSONObject(i).getString("description")}</h5>
					  <p> 
						Restaurant : ${meals.getJSONObject(i).getString("serviceProviderName")}
					  </p>
                                          <p> 
						Location : ${meals.getJSONObject(i).getString("location")}
					  </p>
                                          <p> 
						Preparation Time : ${meals.getJSONObject(i).getString("preparationTime")} mins
					  </p>
					 
                                          <h4 style="text-align:center"><a class="btn"> <i class="icon-zoom-in"></i></a> <a class="btn" onclick="order(${meals.getJSONObject(i).getString("price")})" type="button" id="orderbtn">Order <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">N${meals.getJSONObject(i).getString("price")}</a></h4>
					</div>
				  </div>
				</li>
                            </c:forEach>
			  </ul>	

		</div>
		</div>
	</div>
</div>
<!-- Footer ================================================================== -->
	<div  id="footerSection">
	<div class="container">
		<div class="row">
			<div class="span3">
				<h5>ACCOUNT</h5>
				<a href="login.html">YOUR ACCOUNT</a>
				<a href="login.html">PERSONAL INFORMATION</a> 
				<a href="login.html">ADDRESSES</a> 
				<a href="login.html">DISCOUNT</a>  
				<a href="login.html">ORDER HISTORY</a>
			 </div>
			<div class="span3">
				<h5>INFORMATION</h5>
				<a href="contact.html">CONTACT</a>  
				<a href="register.html">REGISTRATION</a>  
				<a href="legal_notice.html">LEGAL NOTICE</a>  
				<a href="tac.html">TERMS AND CONDITIONS</a> 
				<a href="faq.html">FAQ</a>
			 </div>
			<div class="span3">
				<h5>OUR OFFERS</h5>
				<a href="#">NEW PRODUCTS</a> 
				<a href="#">TOP SELLERS</a>  
				<a href="special_offer.html">SPECIAL OFFERS</a>  
				<a href="#">MANUFACTURERS</a> 
				<a href="#">SUPPLIERS</a> 
			 </div>
			<div id="socialMedia" class="span3 pull-right">
				<h5>SOCIAL MEDIA </h5>
                                <a href="#"><img width="60" height="60" src="<spring:url value="resources/themes/images/facebook.png"/>" title="facebook" alt="facebook"/></a>
                                <a href="#"><img width="60" height="60" src="<spring:url value="resources/themes/images/twitter.png"/>" title="twitter" alt="twitter"/></a>
                                <a href="#"><img width="60" height="60" src="<spring:url value="resources/themes/images/youtube.png"/>" title="youtube" alt="youtube"/></a>
			 </div> 
		 </div>
		<p class="pull-right">&copy; Bootshop</p>
	</div><!-- Container End -->
	</div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
<script src="<spring:url value="resources/themes/js/jquery.js"/>" type="text/javascript"></script>
<script src="<spring:url value="resources/themes/js/bootstrap.min.js"/>" type="text/javascript"></script>
<script src="<spring:url value="resources/themes/js/google-code-prettify/prettify.js"/>"></script>
	
<script src="<spring:url value="resources/themes/js/bootshop.js"/>"></script>
<script src="<spring:url value="resources/themes/js/jquery.lightbox-0.5.js"/>"></script>
	
	<!-- Themes switcher section ============================================================================================= -->
<div id="secectionBox">
    <link rel="stylesheet" href="<spring:url value="resources/themes/switch/themeswitch.css"/>" type="text/css" media="screen" />
<script src="<spring:url value="resources/themes/switch/theamswitcher.js"/>" type="text/javascript" charset="utf-8"></script>
	<div id="themeContainer">
	<div id="hideme" class="themeTitle">Style Selector</div>
	<div class="themeName">Oregional Skin</div>
	<div class="images style">
            <a href="<spring:url value="resources/themes/css/#"/>" name="bootshop"><img src="t<spring:url value="resources/hemes/switch/images/clr/bootshop.png"/>" alt="bootstrap business templates" class="active"></a>
            <a href="<spring:url value="resources/themes/css/#"/>" name="businessltd"><img src="<spring:url value="resources/themes/switch/images/clr/businessltd.png"/>" alt="bootstrap business templates" class="active"></a>
	</div>
	<div class="themeName">Bootswatch Skins (11)</div>
	<div class="images style">
            <a href="<spring:url value="resources/themes/css/#"/>" name="amelia" title="Amelia"><img src="<spring:url value="resources/themes/switch/images/clr/amelia.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="spruce" title="Spruce"><img src="<spring:url value="resources/themes/switch/images/clr/spruce.png"/>" alt="bootstrap business templates" ></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="superhero" title="Superhero"><img src="<spring:url value="resources/themes/switch/images/clr/superhero.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="cyborg"><img src="<spring:url value="resources/themes/switch/images/clr/cyborg.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="cerulean"><img src="<spring:url value="resources/themes/switch/images/clr/cerulean.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="journal"><img src="<spring:url value="resources/themes/switch/images/clr/journal.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="readable"><img src="<spring:url value="resources/themes/switch/images/clr/readable.png"/>" alt="bootstrap business templates"></a>	
		<a href="<spring:url value="resources/themes/css/#"/>" name="simplex"><img src="<spring:url value="resources/themes/switch/images/clr/simplex.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="slate"><img src="<spring:url value="resources/themes/switch/images/clr/slate.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="spacelab"><img src="<spring:url value="resources/themes/switch/images/clr/spacelab.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="united"><img src="<spring:url value="resources/themes/switch/images/clr/united.png"/>" alt="bootstrap business templates"></a>
		<p style="margin:0;line-height:normal;margin-left:-10px;display:none;"><small>These are just examples and you can build your own color scheme in the backend.</small></p>
	</div>
	<div class="themeName">Background Patterns </div>
	<div class="images patterns">
            <a href="<spring:url value="resources/themes/css/#"/>" name="pattern1"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern1.png"/>" alt="bootstrap business templates" class="active"></a>
            <a href="<spring:url value="resources/themes/css/#"/>" name="pattern2"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern2.png"/>" alt="bootstrap business templates"></a>
            <a href="<spring:url value="resources/themes/css/#"/>" name="pattern3"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern3.png"/> alt="bootstrap business templates"></a>
            <a href="<spring:url value="resources/themes/css/#"/>" name="pattern4"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern4.png"/>" alt="bootstrap business templates"></a>
            <a href="<spring:url value="resources/themes/css/#"/>" name="pattern5"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern5.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern6"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern6.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern7"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern7.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern8"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern8.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern9"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern9.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern10"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern10.png"/>" alt="bootstrap business templates"></a>
		
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern11"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern11.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern12"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern12.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern13"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern13.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern14"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern14.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern15"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern15.png"/>" alt="bootstrap business templates"></a>
		
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern16"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern16.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern17"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern17.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern18"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern18.png"/>" alt="bootstrap business templates"></a>
                <a href="<spring:url value="resources/themes/css/#"/>" name="pattern19"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern19.png"/>" alt="bootstrap business templates"></a>
		<a href="<spring:url value="resources/themes/css/#"/>" name="pattern20"><img src="<spring:url value="resources/themes/switch/images/pattern/pattern20.png"/>" alt="bootstrap business templates"></a>
		 
	</div>
	</div>
</div>
                
<div class="modal fade" id="buyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
  aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header d-flex justify-content-center" style="background-color: #6C3BD0;">
          <p class="heading" style="color: white;">Payment</p>
<!--        <h5 class="modal-title" id="exampleModalLabel">Fund Wallet</h5>-->
<!--        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>-->
      </div>
      <div class="modal-body">
          
          
              <input type="text" name="name" id="name" placeholder="Enter Name" class="form-control"/> 
              <input type="text" name="email" id="email" placeholder="Enter Email" class="form-control"/>
              <input type="number" name="amount" id="amount" placeholder="Enter Amount" class="form-control"/>
              <input type="number" name="phone" id="phone" placeholder="Enter Phone Number" class="form-control"/>
              <input type="hidden" id="amount" />
              <div class="text-white" id="amount2_error"></div>
          </div>
          
           
      </div>
      <div class="modal-footer justify-content-center">
          <button type="button" onclick="makePayment()" id="proceedbtn" class="btn btn-mdb-color">Proceed</button>
      </div>
    </div>
  </div>
</div>                
</body>

<script src="https://checkout.flutterwave.com/v3.js"></script>
<script>
    
    function order(amount){
        $("#amount").val(amount);
        $("#buyModal").modal('show');
    }
    
    function makePayment() {
     
        let amount = $("#amount").val();
        let email = $("#email").val();
        let phone = $("#phone").val();
        let name = $("#name").val();
    FlutterwaveCheckout({
      public_key: "FLWPUBK_TEST-cf6722a6858dcfd20e17a2d6810cb497-X",
      tx_ref: "hooli-tx-1920bbtyt",
      amount: amount,
      currency: "NGN",
      country: "NG",
      payment_options: "card, bank",
//      redirect_url: // specified redirect URL
//        "https://callbacks.piedpiper.com/flutterwave.aspx?ismobile=34",
      meta: {
        consumer_id: 23,
        consumer_mac: "92a3-912ba-1192a"
      },
      customer: {
        email: email,
        phone_number: phone,
        name: name
      },
      callback: function (data) {
        console.log(data);
        console.log("transaction status: "+data.status);
        console.log("transaction id: " +data.transaction_id);
        console.log("transaction ref: " +data.tx_ref);
       // saveSuccessfulTransaction(amount, phone, data.status, data.transaction_id, data.tx_ref, wallettype);
      },
      onclose: function() {
        // close modal
      },
      customizations: {
        title: "Wallet Funding",
        description: "Payment for wallet top-up",
        logo: "https://assets.piedpiper.com/logo.png"
      }
    });
  }
    
    
    $("#submitButton").click(function(){
        
       
         let city = $("#citiesSelect").val();
         
         let credentials = {
                        "city" : city
                    };
                    let url = "${pageContext.request.contextPath}/searchCity";
                    $.ajax({
                    method: "POST",
                    url: url,
                    data: credentials,
                    beforeSend: function (xhr) {
                            console.log(credentials);
                        },
                    success: function(response){
                        console.log(response);
                        if(response === "00") {
                            window.location.href = "${pageContext.request.contextPath}/citySearchResult";
                        }
                    }
                });   
    });
</script>
</html>
