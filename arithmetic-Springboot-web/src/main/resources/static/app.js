$('#runner').runner({

milliseconds: false,
format: function millisecondsToString(milliseconds) {
            var oneHour = 3600000;
            var oneMinute = 60000;
            var oneSecond = 1000;
            var seconds = 0;
            var minutes = 0;
            var hours = 0;
            var result;

            if (milliseconds >= oneHour) {
                hours = Math.floor(milliseconds / oneHour);
            }

            milliseconds = hours > 0 ? (milliseconds - hours * oneHour) : milliseconds;

            if (milliseconds >= oneMinute) {
                minutes = Math.floor(milliseconds / oneMinute);
            }

            milliseconds = minutes > 0 ? (milliseconds - minutes * oneMinute) : milliseconds;

            if (milliseconds >= oneSecond) {
                seconds = Math.floor(milliseconds / oneSecond);
            }

            milliseconds = seconds > 0 ? (milliseconds - seconds * oneSecond) : milliseconds;

            if (hours > 0) {
                result = (hours > 9 ? hours : "0" + hours) + ":";
            } else {
                result = "00:";
            }

            if (minutes > 0) {
                result += (minutes > 9 ? minutes : "0" + minutes) + ":";
            } else {
                result += "00:";
            }

            if (seconds > 0) {
                result += (seconds > 9 ? seconds : "0" + seconds);
            } else {
                result += "00";
            }
           
            return result;
        }  
       
   });

//buttons

$('#startBtn').click(function() {
    $('#runner').runner('start');
    $(this).addClass('activeBtn');
    $('#stopBtn').removeClass('activeBtn');

});

$('#stopBtn').click(function() {
    $('#runner').runner('stop');
    $(this).addClass('activeBtn');
    $('#startBtn').removeClass('activeBtn');
});

$('#resetBtn').click(function() {
    $('#runner').runner('reset');
    $('#stopBtn').removeClass('activeBtn');
    $('#startBtn').removeClass('activeBtn');
});

$('#enBtn').click(function() {
    lang = "en";
    ChangeLanguage();
});

$('#chBtn').click(function() {
    lang = "ch";
    ChangeLanguage();
});

$('#hkBtn').click(function() {
    lang = "hk";
    ChangeLanguage();
});

$('#numberBtn').click(function(){
	var number = document.getElementById("numberText");
	$.get("/number",{Action:"Number",Number:number.value},function(data,textStatus){
		document.clear();
		document.write(data);
		});
}

);

var flag = false;
function chkform(){ 
	if(flag){
		alert("已经判断过对错，请刷新页面！");
		return;
	}
	flag=true;
	var list = document.getElementById("list");
	var items = list.getElementsByTagName("span");
	var inputs = list.getElementsByTagName("input");
	var right = 0;
	var wrong = 0;
	for(var i=0;i<items.length;i++){
		if(i%2==1){
		var item = items[i];
		if(item.innerText == inputs[(i-1)/2].value){
			item.style.display="";
			right++;
   	 }else{
   		var item = items[i];
   		item.style.display="";
   		wrong++;
   	 }
	}
	}
	$.post("/index",{Action:"chkform",Right:right,Wrong:wrong},function(data,textStatus){
		alert("right:"+right+"wrong:"+wrong);});
	
}
var lang = "hk";
function ChangeLanguage() {  
    var langpath = lang + ".xml";//资源文件路径  
    TranslateElementsAsy(document, 'SPAN', 'innerHTML', langpath);  
    TranslateElementsAsy(document, 'INPUT', 'value', langpath);  
    TranslateElementsAsy(document, 'th', 'innerHTML', langpath); 
    TranslateElementsAsy(document, 'h3', 'innerHTML', langpath); 
    TranslateElementsAsy(document, 'h1', 'innerHTML', langpath); 
}

function getString(path, req_name, xmlDoc) {  
    //解析XML  
    //var oError = xmlDoc.parseError;  
    var nodeName = xmlDoc.getElementsByTagName(req_name);  
    if (nodeName[0] == null || nodeName[0] == "undefined") {  
        return null;  
    } else {  
        return nodeName[0].childNodes[0].nodeValue;  
    }  
}  


function TranslateElementsAsy(targetDocument, tag, propertyToSet, path) { //方法一  
    $.ajax({  
            url: path,  
            type: 'get',  
            async: false,  
            success: function (data) {  
                var e = targetDocument.getElementsByTagName(tag);  
                for (var i = 0 ; i < e.length ; i++) {  
                    var sKey  
                    sKey = e[i].getAttribute('id');  
                    if (sKey) {  
                        var s = getString(path, sKey, data);  
                        if (s) {  
                            eval('e[i].' + propertyToSet + ' = s');  
                        }  
                    }  
                }  
            }  
        });  
    }  