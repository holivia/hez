$(function() {
			var click = $('#target_2 a');
			function add(Name) {
				var add_li = $('<li><a class="now"><span>' + Name + '</span><span>��</span></a></li>');//����������һ��ѡ��
				var add_ifame = $('<iframe name="' + Name + '" src="#"></iframe>');//���ҳ��
				$('#div3 iframe').hide();
				$('.uu').append(add_li);//׷�ӵ�����
				$('#div3').append(add_ifame);
			}
			click.click(function() {
				var texts = $(this).text();
				var show1a = $('#div6 ul li a span:first-child');
				var nowgeshu = $('.uu li').length;
				for (var i = 0; i < show1a.length; i++) {
					if (show1a.eq(i).text() == texts) {
						alert('���Ѵ���һ����ͬ�ı�ǩҳ��')
						return false;
					}
				}
				if (nowgeshu < 8) {
					$(this).attr('target', texts);
					$('.uu li a').removeClass('now');
					add(texts);
				} else if (nowgeshu == 8) {
					alert('���Ѵ���8����ǩ����رղ��ֱ�ǩ���ٴ��±�ǩ��');
					return false;
				}								
			})
			
			$('#div6').on('click','ul li a span:last-child',function(event){
				if($(this).parent().hasClass('now')){
					$('#div6 ul li a').eq($('#div6 ul li').length-2).addClass('now');
				}
				var index = $('#div6 ul li').index($(this).parent().parent());
				$(this).parent().parent().remove();
				$('#div3 iframe').eq(index).remove();
				if($('#div3 iframe:visible').length==0){
					$('#div3 iframe:last-child').show(); 
					$("#button-right").show();
				}
				event.stopPropagation();
			})
			$('#div6').on('click','ul li a',function(){
				if($(this).hasClass('now')){
					return false;
				}else{
					 $(this).addClass('now').parent().siblings().children().removeClass('now');
					 var index = $('#div6 ul li').index($(this).parent());
					 $('#div3 iframe').hide().eq(index).show();
				}
			})
			
})	
		

