$(function () {
		if(array!=""){
		array=array.split(" "); //array分割，自动转为数组
		
		for(var i = 0; i < array.length; i=i+1 ){
			//document.write(array[i]+"</br>");
			array[i]=array[i].split("-"); //array[i]分割
			for(var j = 0; j < array[i].length; j=j+1 ){
				//document.write(array[i][j]+"</br>");
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
		       array=arr;
		       /*for(var i=0;i<arr.length;i++){
		    	    for(var j=0;j<arr[i].length;j++){
		    	        document.write(arr[i][j]);
		    	    }
		    	    document.write("<br/>");  
		    	}*/
		}
    $('#container1').highcharts({   
		 chart: {
			<!--  backgroundColor: {
           
            type: 'scatter',
            plotBorderWidth: 1,
            zoomType: 'xy'
			
        },
		credits:{
     		enabled: false // 禁用版权信息
		},
        legend: {
            enabled: true,
			align: 'right',  //图例说明的显示位置
			showInLegend: true,
			backgroundColor:'#F3F3F3',  
			padding:10,    
			verticalAlign:'middle',
			maxHeight:700,
			width:100,
			margin:10,
			 title: {
            	text:'员工姓名',
				style:{
					fontSize:25,
					color:'#CCCCCC',
					},
				
        	},
		  itemStyle : {      
		    fontSize : '18px',
			color:'#CCCCCC'
		    }
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
            gridLineWidth: 0,
            title: {
                text: '加班次数[次]',
				color:'gray',
				
            },
            labels: {
                format: '{value} '
            },
            plotLines: [{
                color: '#ff6600',
                dashStyle: 'longdash',
                width: 2,
                value: 75,
                label: {
					align: 'left',
                    rotation: 0,
                    y: 150,
                    style: {
                        fontStyle: '黑体',
						fontSize:16,
						color:'#ff9933'
                    },
                    text: '<b>中<br/><b>间<br/><b>平<br/><b>均<br/><b>次<br/><b>数'
                },
                zIndex: 4
            }],
			plotBands: [{
				from: 0,               // 标示带开始值
				to:75 ,                 // 标示带结束值
				label: {   
					text:'精英区',
					//align:'' ,
					verticalAlign: 'middle',
					style:{
						fontSize: '32px',
						fontStyle: '黑体',
						color:'#6699ff',
					},
					// color: 'orange',
				   // 标示带文字标签配置，详见API
				},
				color: '#FFE6F8',            // 标示带背景颜色
				borderWidth: 0,        // 标示带边框宽度
				borderColor:'red' ,        // 标示带边框颜色
				//id: ,                 // 标示带 id，用于删除等操作
				zIndex:3,              // 标示带层叠，用于调整显示层次
				events: {             // 事件，支持 click、mouseover、mouseout、mousemove等事件
					click: function(e) {
					},
					mouseover: function(e) {
					},
					mouseout: function(e) {
					},
					mousemove: function(e) {
					}
				}

    },
	{
				from: 50,               // 标示带开始值
				to:148 ,                 // 标示带结束值
				label: {   
					text:'<br><br>苦<br>劳<br>区',
					verticalAlign: 'middle',
					style:{
						fontSize: '32px',
						fontStyle: '黑体',
						color:'#ff66ff',
					},
					// color: 'orange',
				   // 标示带文字标签配置，详见API
				},
				color: '#E1E1FF',            // 标示带背景颜色
				borderWidth: 0,        // 标示带边框宽度
				borderColor:'red' ,        // 标示带边框颜色
				//id: ,                 // 标示带 id，用于删除等操作
				zIndex:2,              // 标示带层叠，用于调整显示层次
				events: {             // 事件，支持 click、mouseover、mouseout、mousemove等事件
					click: function(e) {
					},
					mouseover: function(e) {
					},
					mouseout: function(e) {
					},
					mousemove: function(e) {
					}
				}

    },

	
	],
	
        },
        yAxis: {
			
			gridLineWidth: 1,
            startOnTick: false,
            endOnTick: false,
			
            title: {
                text: '加班时长',
				
            },
            labels: {
                format: '{value} '
            },
            maxPadding: 0.2,
            plotLines: [{
                color: '#ff6600',
                dashStyle: 'longdash',
                width: 2,
                value: 50,
                label: {
                    align: 'right',
                    style: {
                        fontStyle: 'italic'
						
                    },
                    text: '',
                    x: -10
                },
                zIndex: 4
            }],
		plotBands: [{
				from: 0,               // 标示带开始值
				to:50 ,                 // 标示带结束值
				label: {   
					text:'清闲区',
					//align:'' ,
					style:{
						fontSize: '32px',
						fontStyle: '黑体',
						color:'#6699cc',
					},
					// color: 'orange',
				   // 标示带文字标签配置，详见API
				},
				color: '',            // 标示带背景颜色
				borderWidth: 0,        // 标示带边框宽度
				borderColor:'red' ,        // 标示带边框颜色
				//id: ,                 // 标示带 id，用于删除等操作
				zIndex:3,              // 标示带层叠，用于调整显示层次
				events: {             // 事件，支持 click、mouseover、mouseout、mousemove等事件
					click: function(e) {
					},
					mouseover: function(e) {
					},
					mouseout: function(e) {
					},
					mousemove: function(e) {
					}
				}

    },
	{
				from: 0,               // 标示带开始值
				to:50 ,                 // 标示带结束值
				label: {   
					text:'精英区',
					align:'center' ,
					style:{
						fontSize: '32px',
						fontStyle: '黑体',
						color:'#00ccff',
					},
					// color: 'orange',
				   // 标示带文字标签配置，详见API
				},
				color: '#D5F4FF',            // 标示带背景颜色
				borderWidth: 0,        // 标示带边框宽度
				borderColor:'red' ,        // 标示带边框颜色
				//id: ,                 // 标示带 id，用于删除等操作
				zIndex:2,              // 标示带层叠，用于调整显示层次
				events: {             // 事件，支持 click、mouseover、mouseout、mousemove等事件
					click: function(e) {
					},
					mouseover: function(e) {
					},
					mouseout: function(e) {
					},
					mousemove: function(e) {
					}
				}

    },
	],

        },
        tooltip: {
            useHTML: true,
            headerFormat: '<table>',
            pointFormat: '<tr><th colspan="2"><h3>{point.name}</h3></th></tr>' +
            '<tr><th>加班次数:</th><td>{point.x}次</td></tr>' +
            '<tr><th>加工时长:</th><td>{point.y}小时</td></tr>' ,
            footerFormat: '</table>',
            followPointer: true
        },
        plotOptions: {
			  scatter: {
				 marker: {
					radius: 5,
					color:'black',
					states: {
					   hover: {
						  enabled: true,
						  lineColor: 'white'
					   }
					}
				 },
				 states: {
					hover: {
					   marker: {
						  enabled: false
					   }
					}
				 },
			  },
            series: {
                dataLabels: {
                    enabled: true,
                    format: '{point.name}'
                }
            }
        },
        series: [
        {
			
            data: [
                { x: Number(array[0][0]), y: Number(array[0][1]), z:7, name:""+array[0][2],color:'red',  },
            ],
			name:""+array[0][2]
        },
		{
			
            data: [
               { x: Number(array[1][0]), y: Number(array[1][1]), z:7, name:""+array[1][2],color:'green', },
            ],
			name:""+array[1][2]
        },
		{
			
            data: [
               { x: 86.5, y: 102.9, z: 7, name: '琳琳',color:'green', },
            ],
			name:'琳琳'
        },
		{
			
            data: [
              { x: 80.8, y: 91.5, z: 7, name: '郭伊纳',color:'blue', },
            ],
			name:'郭伊纳'
        },
		{
			
            data: [
             { x: 80.4, y: 102.5, z: 7, name: '何亚丽', color:'orange', },
            ],
			name:'何亚丽'
        },
		{
			
            data: [
              { x: 80.3, y: 86.1, z: 7, name: '黄盼盼', },
            ],
			name:'黄盼盼'
        },
		{
			
            data: [
              { x: 78.4, y: 70.1, z:7, name: '李婷婷',color:'blue',  },
            ],
			name:'李婷婷'
        },
		{
			
            data: [
             { x: 74.2, y: 68.5, z:7, name: '沈夏琳', color:'orange', },
            ],
			name:'沈夏琳'
        },
			
			
            {
			
            data: [
             { x: 73.5, y: 83.1, z: 7, name: '庄雅燕',  },
            ],
			name:'庄雅燕'
        },
		{
			
            data: [
             { x: 71, y: 93.2, z:7, name: '方程点',  },
            ],
			name:'方程点'
        },
		{
			
            data: [
            { x: 69.2, y: 57.6, z:7, name: '郑思木',  },
            ],
			name:'郑思木'
        },
		{
			
            data: [
             { x: 68.6, y: 20, z:7, name: '宝清',color:'orange',  },
            ],
			name:'宝清'
        },
		{
			
            data: [
            { x: 65.5, y: 126.4, z:7, name: '蒋伶俐', },
            ],
			name:'蒋伶俐'
        },
		{
			
            data: [
            { x: 65.4, y: 50.8, z:7, name: '陆巧敏',color:'blue',  },
            ],
			name:'陆巧敏'
        },
		{
			
            data: [
            { x: 63.4, y: 51.8, z: 7, name: '秋瑾琳',  },
            ],
			name:'秋瑾琳'
        },
		{
			
            data: [
             { x: 64, y: 82.9, z: 20, name: '黄辉祥', color:'blue', }
            ],
			
			name:'黄辉祥'
        },
		],
		
		
    });
});
	