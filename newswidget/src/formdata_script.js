$(document).ready(function() {
	$("#submit_button").click(function(){
		var message = document.getElementById("message").value;
		if(message) {
			var fd = new FormData();  

			var link_pic = "<img src=\""+$(".image .active").attr("src")+"\">";
			var link_title = "<a href=\""+$(".url").html()+"\">"+$(".title").html()+"</a>"; 
			var link_desc = $(".description").html();

			var link_box_string = "<div class=\"link_box\"><div class=\"link_picture\">"+link_pic+"</div><div class=\"link_text_wrapper\"><div class=\"link_title\">"+link_title+"</div><div class=\"link_description\">"+link_desc+"</div></div></div>";


			//var link_preview = link_var+link_title+link_desc;
			var type = "status_update";
			var userid = "user4";
			var displayName = "Tobi"; 
			var localDate = renderer.format(new Date());
			var rand = "AB" + Math.round(Math.random() * 1000000);
			message = message.replace(/\n/g, '<br />');
			message +=link_box_string;

			fd.append('type' ,type);
			fd.append('user_id' ,userid);
			fd.append('status_update_id' ,rand);
			fd.append('message' ,message);
			fd.append('status_update_type',"Plain");
			$.ajax({
			  url: 'http://localhost:8080/Graphity-Server-0.1/create',
			  //url: 'http://192.168.178.36:8080/Graphity-Server-0.1/create',
			  data: fd,
			  processData: false,
			  cache: false,
			  contentType: false,
			  processData: false,
			  type: 'POST',
			  success: function(data){
			    if(data.statusMessage ==="ok"){
			    	renderForm(rand, localDate, userid, message, displayName);
			    } //End if
			  }, //End success function
			  error:function(){
			  	renderForm("1","2","3","4");
			  }
			}); //End AJAX
			function renderForm(id_link, date,id_actor, message, actorName){
				//var stringOfValues = "<li><strong><a href=\""+id_link+"\">"+date+"</a></strong><br><span>"+id_actor+": "+message+"</span></li>";
				var stringOfValues = "<li id=\""+00+"\" class=\"single-comment-holder\"><div class=\"user-img\"><a href=\""+id_link+"\"><img src=\"http://www.metalcon.de/images/metal-community.jpg\" class=\"user-img-pic\"></a></div><div class=\"comment-body\"><h3 class=\"username-field\">"+actorName+"</h3><div class=\"comment-date\"><a href=\""+id_link+"\">"+date+"</a></div><div class=\"comment-text\">"+message+"</div></div></li>";
				$(stringOfValues).prependTo('.comments-holder').hide().slideDown("slow");
			} //End renderForm function
		} //End if	
	}); //End click function
}); //End ready function
