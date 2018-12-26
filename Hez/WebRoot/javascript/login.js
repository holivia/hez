 function submitCheck(){
 	var txt1 = document.getElementById("gh");
 	
    if(txt1.value==""){
    	alert("kong");
	    txt1.focus();
	    return false;  
    }
    return true;
  }