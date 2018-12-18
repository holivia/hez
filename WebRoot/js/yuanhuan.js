$(function(){
		if(pie!=""){
		pie=pie.split(" "); //array分割，自动转为数组
		
		for(var i = 0; i < pie.length; i=i+1 ){
			//document.write(pie[i]+"</br>");
			pie[i]=pie[i].split("-"); 
			for(var j = 0; j < pie[i].length; j=j+1 ){
				//document.write(pie[i][j]+"</br>");
			}
		}
		}else{
			var arr = new Array();         //先声明一维
		    for(var i=0;i<5;i=i+1){          //一维长度为5
		       arr[i]=new Array(0);    //在声明二维
		       for(var j=0;j<5;j=j+1){      //二维长度为5
		           arr[i][j]=0;
		       }
		    } 
		       pie=arr;
		       /*for(var i=0;i<arr.length;i++){
		    	    for(var j=0;j<arr[i].length;j++){
		    	        document.write(arr[i][j]);
		    	    }
		    	    document.write("<br/>");  
		    	}*/
		}
				var data = [
				        	{name : ""+pie[0][0],value : Number(pie[0][1]),color:'#ff9b77'},
				        	{name : ""+pie[1][0],value : Number(pie[1][1]),color:'#ffe171'},
				        	{name : ""+pie[2][0],value : Number(pie[2][1]),color:'#bddc7f'},
							//{name : ""+pie[3][0],value : Number(pie[3][1]),color:'#E2F5F5'}
							
				        	
			        	];

	        	
				var chart = new iChart.Donut2D({
					render : 'canvasDiv',
					data: data,
					center : {
						text:'总工时数'+totalDuration,
						color:'black',
						fontsize :20
//						shadow:true,//显示阴影
//						shadow_blur : 2,
//						shadow_color : '#557797',
//						shadow_offsetx : 0,
//						shadow_offsety : 0
					},
					sub_option : {
						label : {
							background_color:null,
							sign:false,//设置禁用label的小图标
							padding:'0 4',
							border:{//圆环图与数据的连线
								enable:false,
								color:'#666666'
							},
							fontsize:15,
							fontweight:600,
							color : '#bddc7f'
						},
						border : {
							width : 2,
							color : '#ffffff'
						}
					},
					shadow : true,
					shadow_blur : 6,
					shadow_color : '#aaaaaa',
					shadow_offsetx : 0,
					shadow_offsety : 0,
					background_color:'#fefefe',
					offset_angle:-120,//逆时针偏移120度
					showpercent:false,//是否展示百分比
					decimalsnum:2,
					width : 496,
					height : 300,
					radius:120
				});
				
				chart.draw();
			});