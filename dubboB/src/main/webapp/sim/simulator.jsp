<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银行交易模拟器</title>
<link rel="stylesheet" href="../lib/css/common.css" />
<link rel="stylesheet" href="../lib/css/jquery.autocomplete.css" />
<style >
input{font-size:14px; font-weight:200;}
</style>
<style type="text/css"> 
	#divTranResult{ 
	position:absolute; 
	top:50%;
	left:50%; 
	margin:-150px 0 0 -100px;
	width:300px; 
	height:120px;
	background:#5ee372;
}  
</style> 
<script type="text/javascript" src="../lib/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="../lib/scripts/jquery.form.js"></script>
<script type="text/javascript" src="../lib/scripts/jquery.autocomplete.js"></script>
<script type="text/javascript" src="../lib/scripts/date.js"></script>
<script type="text/javascript" src="../lib/scripts/map.js"></script>
<script type="text/javascript" >

var abilityMap ;

$(document).ready(function () {
	
	 $("table#mainTab").click(function(){
		 $("#divTranResult").fadeOut();
	 });
	
  //银行列表下拉初始化
	initBankSelect();
  
	$("#bank").change(function(){
		if(this.value=='' || this.value==undefined || this.value==null)
			return ;
		
		$("#bankShow").text(this.value);
		$("#bankid").val(this.value);
		showBankTransactions( this.value  );
	});
	
	$("#simulator").change(function(){
		if(this.value=='' || this.value==undefined || this.value==null)
			return ;
		$("#simulatorShow").text(this.value);
		$("#simulatorid").val(this.value);
		showSimulatorAbility( this.value  );
		setChannelId( this.value  );
	});
	
	$("#simAbilities").change(function(){
		if(this.value=='' || this.value==undefined || this.value==null)
			return ;
		$("#simAbilitiesShow").text(this.value);
		$("#tranType").val(this.value);
		showTranParams( this.value  );
		setParamFormActionUrl();
	});
	
	
	 $("button[name=generate]").click(function(){
		 if($("#tranType").val()=="downloadBill"){
	        jQuery('#paramForm').submit();
		 }
		 else{
			 var options = {
			           beforeSubmit:  showRequest,
			            success:       showResponse,
			            //dataType:	   'json',
			            type:      'post'
			        };
			        // bind form using 'ajaxForm'
			       jQuery('#paramForm').ajaxForm(options);
			        jQuery('#paramForm').submit();
		 }
	    });
	 
	
	
});

function showRequest(formData, jqForm, options) {

}
function showResponse(responseText, statusText, xhr, $form)  {
	if(responseText!=''){
		$("#divTranResult").html(responseText);
		$("#divTranResult").fadeIn();
	}
}

function initSelect(data,$select){
	for(var i in data){
		$select.append($("<option></option>").attr("value",i.code).text(i.desc));
	}
}

function initBankSelect(){
	$.ajax({
        async:false,
        url: "getSimulatorBanks.do",
        success:function(data){
        	for(var i=0;i<data.length;i++){
        		var json = data[i];
        		$("#bank").append($("<option></option>").attr("value",json.code).text(json.desc));
        	}
        },
        error: function (data)
        {
        	alert(data.status);
        }
    });
	
}

function showBankTransactions( bankid ){
	$.ajax({
        async:false,
        url: "getSimulators.do",
        data:{bankid:bankid},
        success:function(data){
        	
        	for(var i=0;i<data.length;i++){
        		var json = data[i];
        		$("#simulator").append($("<option></option>").attr("value",json.code).text(json.desc));
        	}
        },
        error: function (data)
        {
        	alert(data.status);
        }
    });
}

function showSimulatorAbility( simulatorid  ){
	$.ajax({
        async:false,
        url: "getSimulatorAbility.do",
        data:{simulatorid:simulatorid},
        success:function(data){
        	abilityMap = new Map() ;
        	for(var i=0;i<data.length;i++){
        		var json = data[i];
        		$("#simAbilities").append($("<option></option>").attr("value",json.code).text(json.desc));
        		abilityMap.put(json.code,json.params);
        	}
        },
        error: function (data)
        {
        	alert(data.status);
        }
    });
}

function setChannelId(simulatorid  ){
	
	$.ajax({
        async:false,
        url: "getSimulatorChannelId.do",
        data:{simulatorid:simulatorid},
        success:function(data){
        		$("#channelId").val(data) ;
        },
        error: function (data)
        {
        	alert(data.status);
        }
    });
}

function showTranParams( tranId ){
	var paramMap = abilityMap.get(tranId) ;
	
	  if((typeof paramMap).toLowerCase()=="undefined") return;
	
	 $("table#serviceParamTab").empty();
	
	for(var key in paramMap){
		appendRow();
		setLastRowValue(key,paramMap[key]) ;
	}
}

function setParamFormActionUrl(){
	var simulatorid = $("#simulatorid").val() ;
	var tranType = $("#tranType").val() ;
	var url = simulatorid+"/"+tranType+"/simulator.do";
	
	$("#paramForm").attr("action",url);
}



function appendRow(freq){

    if((typeof freq).toLowerCase() != "number" ){
        freq = 1;
    }
    if( freq <0 ) removeRow(-freq);
    for(;freq>0;freq--){
        var tempTr = $("<tr />").append($("tr#templete_row").html()).clone();
        var idx = $("table#serviceParamTab tr").length+1;
        tempTr.children("th").each(function(){
            var obj = jQuery(this);
            var text = obj.text().replace(/_/g,idx);
            obj.text(text);
        });
        tempTr.appendTo("table#serviceParamTab");
    }
}

function removeRow(freq){
    if((typeof freq).toLowerCase() != "number" ){
        freq = 1;
    }
    if( freq <0 ) appendRow(-freq);
    for(;freq>0;freq--){
        $("table#serviceParamTab tr:last").remove();
    }
}

function setLastRowValue(key , value){
	var itmes =value.split("|");
	var lable=itmes[0];
	var type = itmes[1];
	
	var thObj = $("table#serviceParamTab th:last");
	thObj.text(lable) ;
	
    var trObj = $("table#serviceParamTab tr:last");
    var paramObj =  trObj.find("input[name=paramKey]");
    paramObj .attr("name",key) ;
    paramObj .attr("id",key) ;
    
    if(type=="date"){
    	var thObj = $("table#serviceParamTab span:last");
    	var date = new Date();
    	paramObj.val(date.format("yyyy-MM-dd"));
    	thObj.text("格式：yyyy-MM-dd") ;
    }
    else if( type== "boolean"){
    	 paramObj .attr("id","checkboxid") ;
    	changeAttrType(paramObj,"checkbox");
    }
    else if(type=="file"){
    	changeAttrType(paramObj,"file");
    }
}

function changeAttrType(node, newType){
	var elemt = document.getElementById(node.attr("id"));
	var inputps = document.createElement('input');
    inputps.setAttribute("name",node.attr("name"));
    inputps.setAttribute("id",node.attr("id"));
    inputps.setAttribute("type",newType);
    elemt.parentNode.replaceChild(inputps,elemt);
}



</script>

</head>

<body  >
 <div class="wrapper">

        <div class="w920">

            <div>
                <div class="box-b">
                    <h3 class="h3">请选择需要模拟的银行：</h3>
                      <form id="paramForm" action="" method="post">
                          <input type="hidden" id="bankid"  name="bankid"  value="" >
                          <input type="hidden" id="simulatorid"  name="simulatorid"  value="" >
                          <input type="hidden" id="tranType"  name="tranType"  value="" >
                    <table id="mainTab">
                      
                        <tr>
                            <th>交易银行：</th>
                            <td><select id="bank" name="bank"  ><option value="">请选择银行</option></select><span id="bankShow"></span></td>
                        </tr>
                        
                        <tr>
                            <th>交易模拟器：</th>
                            <td><select id="simulator" name="simulator"  ><option value="">请选模拟器</option></select><span id="simulatorShow"></span></td>
                        </tr>
                        
                         <tr>
                            <th>交易渠道ID：</th>
                            <td><input type="text"  id="channelId" name="channelId" value="" class="inp"  readonly="readonly"   style="width:120px;"/></td>
                        </tr>
                        
                         <tr>
                            <th>交易类型：</th>
                            <td><select id="simAbilities" name="simAbilities"  ><option value="">请选交易类型</option></select><span id="simAbilitiesShow"></span></td>
                        </tr>
                        
                          <tr id="templete_row" style="display:none;" >
                          	<th>参数名称_：</th>
                            <td><input type="text" name="paramKey" value="" class="inp" style="width:250px;"/><span id=""></span></td>
                        </tr>
                        
                    </table>
                  
                   <table id="serviceParamTab">
                    </table>
                    </form>
                    
                      <div class="but-box">
                        <button type="button" class="but-165" name="generate">提交请求</button>
                    </div>
                    
                </div>
            </div>
            <div  id="divTranResult"  style="display:none;z-index:9999" ></div>
            <div class="clear"></div>

		    <div class="footer">
              <p>International Business | About msec 民生电商版权所有 2013-2014 ICP证：xxxxxx</p>
         </div>

        </div>
    </div>
</body>
</html>