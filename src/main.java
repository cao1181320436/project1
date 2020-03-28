import java.util.TreeSet;

public class main {
    public static void main(String[] args) {
        mysql msql = new mysql();
        String[][] arr = new String[10][6];
        String[] arr1 = {"0", "0", "0", "0"};

        //插入帖子信息 从第一个到最后一个分别是 标签（label）-标题（title）-详细信息（detailed）-帖子序号(message_id)-帖子顺序(message_order)-发帖人姓名（host_name）
        //  msql.insert_message("明日方舟2", "我想找个明日方舟model2", "我叫蒋骐羽2，计算机72班，我想找一个明日方舟队友，求大佬抱",222001,1,"蒋骐羽2");
        //插入帖子信息 基本同上 如果省略帖子序号和帖子顺序 将自动用最大序号下一个序号填充
        //  msql.insert_message("明日方舟2", "我想找个明日方舟model2", "我叫蒋骐羽2，计算机72班，我想找一个明日方舟队友，求大佬抱","蒋骐羽2");

        //删除帖子 用帖子的id删除
        // msql.delete_message(222102);
        //当输入两个String字符串 前面为标题 后面为发帖人
     //   msql.delete_message("我想找个明日方舟model2","蒋骐羽2");
        //输入一个字符串 则为用户名 删除该用户所有帖子
       //  msql.delete_message("蒋骐羽");


        //查询帖子 根据关键词查询 存到后面的10*6的二维数组arr里
        //二维数组每一行分别为tag-label-detailed-message_id-message_order-host_name
        //没有结果的数组全部填充为字符串“NULL”
      /*    mysql.search_message("数模", arr);
        for (int i = 0; i <= 9; i++) {
            if (arr[i][0] != "NULL") {
                System.out.println("order:"+i);
                System.out.println("tag:"+arr[i][0]);
                System.out.println("label:"+arr[i][1]);
                System.out.println("detailed:"+arr[i][2]);
                System.out.println("message_id:"+arr[i][3]);
                System.out.println("message_order:"+arr[i][4]);
                System.out.println("host_name:"+arr[i][5]);
                System.out.println();
            }
        }*/

        //修改帖子信息 前面为id（6位），后面为详情（detailed，注意用双引号）
        //  msql.update_message_detailed(222100,"数模真好玩");
       // msql.update_message_detailed("蒋骐羽","我想找个明日方舟model","嘻嘻嘻");





        //  创建用户 分别为 密码-名字-ip地址-is_login 注意密码、名字和ip是字符串 is_login是1位的int型
       //   msql.insert_user("tgr123456.","谭果然","202.117.15.15",1);

        //根据学生的姓名进行删除
        //删除用户的同时 该用户的所有帖子将会删除
       //    msql.delete_user("谭果然");

        //exist_user_name用来查询是否有重复的名字 如果有为false
         /*  if(msql.exist_user_name("张"))
            {System.out.println("id重复");}
            else
            {System.out.println("id可用");}*/

        //找到学生信息 用姓名来查找 把结果存到arr
        // arr1【】依次存的是password-name-ip-is_login
        // arr1【】为一个String类的数组，长度为4 String arr1【4】
            /*   msql.find_user_name("凛冬" ,arr1);
                 System.out.println(arr1[0]);
                 System.out.println(arr1[1]);
                 System.out.println(arr1[2]);
                 System.out.println(arr1[3]);*/


        //更新信息 用名字更新密码
        // msql.update_user_password("凛冬","jqy123456.");
        //用名字查询 更新is_login
        //  msql.update_user_is_login("蒋骐羽",1);
        //用名字查询 更新名字 前面为旧名字，后面为新名字
        //  msql.update_user_name("凛冬","蒋骐羽");
        //用名字查询 更新IP地址 更改的同时会将is_login更改为0
        //msql.update_user_ip("蒋骐羽","119.120.14.15");


        //数据库维护函数 第一个用来清除所有的帖子信息
        // msql.delete_all_message();
        //第二个用来清除所有的用户信息
        // msql.delete_all_user();
    }
}