var $u = {};
/**
 * @param content
 * @param $input
 * @param $area
 * 
 */
$u.tip = function(content,$input,$area){
	var width = (content.length * 12) + 50;//12为fontsize 的大小
	var $tip = $("<div class='autoTipContainer' style='width:"+width+"px;'></div>");
	var _tr1 = $("<tr><td><div class='area1'></div></td><td class='area2'><div class='arrow' style='margin-left: 0px;'></div></td><td><div class='area3'></div></td></tr>");
	var _tr2 = $("<tr><td class='area4'></td><td><div class='area5'>"+content+"</div></td></tr>");
	var _tr2_td3 = $("<td class='area6'></td>");
	_tr2_td3.bind("click",$tip,function(e){
		e.data.hide();
	});
	_tr2.append(_tr2_td3);
	var _tr3 = $("<tr><td><div class='area7'></div></td><td class='area8'><div class='arrow' style='display: none;'></div></td><td><div class='area9'></div></td></tr>");
	var _tbody = $("<tbody></tbody>").append(_tr1).append(_tr2).append(_tr3);
	var _table = $("<table></table>").append(_tbody);
	$tip.append(_table);
	if(!$area){
		$("body").append($tip);
	}else{
		$area.append($tip);
	}
	$tip.showTip = function(){
		var left = $input.position().left;
		var top = $input.position().top;
		this.css("left",left+40);
		this.css("top",top+20);
		this.show();
	};
	$tip.hide();
	
	return $tip;
};