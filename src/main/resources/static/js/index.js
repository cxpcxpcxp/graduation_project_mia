/**
 * 分页主要参数和调用方法
  var obj={
    obj_box:'.class/#id...',          //翻页容器(css选择器均可)
    total_item:'自然数,必填',           //条目总数
    per_num:'自然数,选填,默认10',       //每页条目数
    current_page:'自然数,选填,默认1',   //当前页
    change_content:function(per_num,current_page){
      //内容加载方法,可参考示例
    }
  };
  page_ctrl(obj);//调用分页插件
*/
//示例:
var obj_1={//定义分页参数和方法
  obj_box:'.page_1',//翻页容器
  total_item:20,//条目总数
  per_num:5,//每页条目数
  current_page:1,//当前页
  //此处,请为页面翻页展示内容定义方法
  change_content:function(per_num,current_page){
    per_num = per_num ? per_num : 10;//每页显示条数,默认为10条
    current_page = current_page ? current_page : 1;//当前页,默认为1
    /*此处根据项目实际自行编写页面显示内容的方法,举例说明:*/
    var page_content = '<ul style="width: 300px;margin: 10px auto;">';//当前页内容
    for (var i = 0; i < per_num; i++) {
      page_content += '<li>' + ((current_page - 1) * per_num + i + 1) + ',分页内容方法1</li>';
    }
    page_content += '</ul>';
    $(this.obj_box).children('.page_content').html(page_content);
  }
};
var obj_2={//定义分页参数和方法
  obj_box:'.page_2',//翻页容器
  total_item:10,//条目总数
  per_num:5,//每页条目数
  current_page:1,//当前页
  //此处,请为页面翻页展示内容定义方法
  change_content:function change_content(per_num,current_page) {
    per_num = per_num ? per_num : 10;//每页显示条数,默认为10条
    current_page = current_page ? current_page : 1;//当前页,默认为1
    /*此处根据项目实际自行编写页面显示内容的方法,举例说明:*/
    var page_content='<ul style="width: 300px;margin: 10px auto;">';//当前页内容
    for(var i=0;i<per_num;i++){
      page_content+='<li>'+((current_page-1)*per_num+i+1)+',分页内容方法2</li>';
    }
    page_content+='</ul>';
    $(this.obj_box).children('.page_content').html(page_content);
  }
};
 page_ctrl(obj_1);//调用分页插件
page_ctrl(obj_2);//调用分页插件
