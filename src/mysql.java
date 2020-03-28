import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class mysql {
    Connection conn=null;
    Statement stmt=null;
    /**
     * 连接服务器
     */
    public void startMysql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        String url="jdbc:mysql://175.24.57.212:3306/test";
        String user="user";
        String password="123456";
        try {
            conn=DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 创建Statement对象
     */
    public void createStatement() {
        try {
            stmt=conn.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 关闭资源
     */
    public void closeAll() {
        if(stmt!=null)
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if(conn!=null)
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }





//message table
    //insert ***
    public void insert_message(String label,String title,String detailed,String host_name) {

    String sql="insert into message  values('"+label+"','"+title+"','"+detailed+"',"+max_message_id()+","+max_message_order()+",'"+host_name+"');";


    int count=0;
    this.startMysql();
    this.createStatement();
    try {
        count=stmt.executeUpdate(sql);
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    if(count==1) System.out.println("帖子插入成功!");
    else System.out.println("帖子插入失败！");
    this.closeAll();
}
    public void insert_message(String label,String title,String detailed,int message_id,int  message_order,String host_name) {

        String sql="insert into message  values('"+label+"','"+title+"','"+detailed+"',"+message_id+","+message_order+",'"+host_name+"');";


        int count=0;
        this.startMysql();
        this.createStatement();
        try {
            count=stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("帖子插入成功!");
        else System.out.println("帖子插入失败！");
        this.closeAll();
    }
    public int max_message_id()
    {
        Connection con=null;
        Statement stm=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://175.24.57.212:3306/test";
            String user = "user";
            String password = "123456";
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            con.setAutoCommit(false);// 更改jdbc事务的默认提交方式
            String sql="select max(message_id) from message ";
            ResultSet rs = stm.executeQuery(sql);
            con.commit();//事务提交
            con.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            List<String> list = new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数
            while (rs.next()) {//如果有数据，取7列的数据添加进list  label为第1列 title为第2列 detailed为第3列 host_id为第六列 host_name为第七列
                list.add(rs.getString(1));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t1 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t1[i] = list.get(i);//数组赋值了。
                        return Integer.parseInt( t1[i] )+1;
                    }

                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
    public int max_message_order()
    {
        Connection con=null;
        Statement stm=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://175.24.57.212:3306/test";
            String user = "user";
            String password = "123456";
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            con.setAutoCommit(false);// 更改jdbc事务的默认提交方式
            String sql="select max(message_order) from message ";
            ResultSet rs = stm.executeQuery(sql);
            con.commit();//事务提交
            con.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            List<String> list = new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数
            while (rs.next()) {//如果有数据，取7列的数据添加进list  label为第1列 title为第2列 detailed为第3列 host_id为第六列 host_name为第七列
                list.add(rs.getString(1));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t1 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t1[i] = list.get(i);//数组赋值了。
                        return Integer.parseInt( t1[i] )+1;
                    }

                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

    //delete ***
    public void delete_message(int message_id) {
        String sql="delete from message where message_id="+message_id+"";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("删除帖子成功！");
        else System.out.println("删除帖子失败！");
        this.closeAll();
    }
    public void delete_message(String  keyword,String name) {
        String sql = "delete from message where title like '"+keyword+"' and host_name like '"+name+"'";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("删除帖子成功！");
        else System.out.println("删除帖子失败！");
        this.closeAll();
    }
    public void delete_message(String name) {
        String sql = "delete from message where host_name like '"+name+"'";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("删除帖子成功！");
        else System.out.println("删除帖子失败！");
        this.closeAll();
    }
    //search ***
    public static void search_message(String keyword, String[][] arr) {

        Connection con=null;
        Statement stm=null;
        int count=0;
       for(int i=0;i<arr.length;i++)
       {
           arr[i][0]="NULL";
           arr[i][1]="NULL";
           arr[i][2]="NULL";
           arr[i][3]="NULL";
           arr[i][4]="NULL";
           arr[i][5]="NULL";
       }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://175.24.57.212:3306/test";
            String user = "user";
            String password = "123456";
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            con.setAutoCommit(false);// 更改jdbc事务的默认提交方式
            String sql = "select * from message where detailed like '%"+keyword+"%' or label like '%"+keyword+"%' or title like '%"+keyword+"%' or host_name like '%"+keyword+"%'";
            ResultSet rs = stm.executeQuery(sql);
            con.commit();//事务提交
            con.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            List<String> list = new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数
            while (rs.next()) {//如果有数据，取7列的数据添加进list  label为第1列 title为第2列 detailed为第3列 host_id为第六列 host_name为第七列
                list.add(rs.getString(1));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t1 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t1[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组

                    for (int i = 0; i < t1.length; i++) {
                       // System.out.println(t1[i]);
                        arr[count][0]=t1[i];
                    }
                }
                list.clear();

                list.add(rs.getString(2));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t2 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t2[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组
                    for (int i = 0; i < t2.length; i++) {
                      //  System.out.println(t2[i]);
                        arr[count][1]=t2[i];
                    }
                }
                list.clear();

                list.add(rs.getString(3));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t3 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t3[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组
                    for (int i = 0; i < t3.length; i++) {
                       // System.out.println(t3[i]);
                        arr[count][2]=t3[i];
                    }
                }
                list.clear();

                list.add(rs.getString(4));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t4 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t4[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组
                    for (int i = 0; i < t4.length; i++) {
                      //  System.out.println(t4[i]);
                        arr[count][3]=t4[i];
                    }
                }
                list.clear();

                list.add(rs.getString(5));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t5 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t5[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组
                    for (int i = 0; i < t5.length; i++) {
                      //  System.out.println(t5[i]);
                        arr[count][4]=t5[i];
                    }
                }
                list.clear();

                list.add(rs.getString(6));
                if(list != null && list.size()>0) {//如果list中存入了数据，转化为数组
                    String[] t6 = new String[list.size()];//创建一个和list长度一样的数组
                    for (int i = 0; i < list.size(); i++) {
                        t6[i] = list.get(i);//数组赋值了。
                    }
                    //输出数组
                    for (int i = 0; i < t6.length; i++) {
                        //System.out.println(t6[i]);
                        arr[count][5]=t6[i];
                    }
                }
                count++;
                list.clear();


            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //update ***
    public void update_message_detailed(int message_id, String detailed) {
        String sql="UPDATE message SET detailed ='"+detailed+"' where message_id= "+message_id+" ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("message_detailed修改成功！");
        else System.out.println("message_detailed修改失败！");
        this.closeAll();
    }
    public void update_message_detailed(String name,String title, String detailed) {
        String sql="UPDATE message SET detailed ='"+detailed+"' where host_name like '"+name+"' and title like '"+title+"'";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("message_detailed修改成功！");
        else System.out.println("message_detailed修改失败！");
        this.closeAll();
    }







    //user table
    //insert **
    public void insert_user(String password,String name,String ip,int is_login) {
       if(!exist_user_name(name))
       {
           String sql="insert into user values('"+password+"','"+name+"','"+ip+"',"+is_login+");";


           int count=0;
           this.startMysql();
           this.createStatement();
           try {
               count=stmt.executeUpdate(sql);
           } catch (SQLException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
           if(count==1) System.out.println("数据插入成功!");
           else System.out.println("数据插入失败！");
           this.closeAll();
       }
       else
           System.out.println("用户名重复！");
    }
    //delete **
    public void delete_user(String name) {
        String sql="delete from user where name='"+name+"'";
        delete_message(name);

        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("删除数据成功！");
        else System.out.println("删除数据失败！");
        this.closeAll();
    }
    //exist **
    public boolean exist_user_name(String name) {
        this.startMysql();
        this.createStatement();
        try {
            String sql = "SELECT * FROM user WHERE name ='"+name+"';";
            //System.out.println(sql);
            ResultSet rs=stmt.executeQuery(sql);
            if (rs.next())
            {
                if (rs.getInt(1) == 0)
                { return false;}
                else {return true;}
            } return false;}
        catch (SQLException e)
        {  e.printStackTrace();}
        return false;
    }
    //search **
    public static void find_user_name(String name, String[] arr) {
        Connection con=null;
        Statement stm=null;
        arr[0]="NULL";
        arr[1]="NULL";
        arr[2]="NULL";
        arr[3]="NULL";
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();
        StringBuffer buffer3 = new StringBuffer();
        StringBuffer buffer4 = new StringBuffer();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            String url = "jdbc:mysql://175.24.57.212:3306/test";
            String user = "user";
            String password = "123456";
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            con.setAutoCommit(false);// 更改jdbc事务的默认提交方式
            String sql = "select * from user where name ='"+name+"'";
            ResultSet rs = stm.executeQuery(sql);
            con.commit();//事务提交
            con.setAutoCommit(true);// 更改jdbc事务的默认提交方式
            List<String> list = new ArrayList<String>();//创建取结果的列表，之所以使用列表，不用数组，因为现在还不知道结果有多少，不能确定数组长度，所有先用list接收，然后转为数组
            while (rs.next()) {//如果有数据，取7列的数据添加进list  label为第1列 title为第2列 detailed为第3列 host_id为第六列 host_name为第七列
                for (int i = 1; i <= 4; i++)
                    list.add(rs.getString(i));
            }
            if (list != null && list.size() > 0) {//如果list中存入了数据，将其转化为数组
                String[] arr1 = new String[list.size() / 4];//创建五个七分之一list长度一样的数组
                String[] arr2 = new String[list.size() / 4];
                String[] arr3 = new String[list.size() / 4];
                String[] arr4 = new String[list.size() / 4];
                for (int i = 0, j = 0; i < list.size() / 4; i++) {
                    arr1[j] = list.get(i);//arr1数组赋值label
                    j++;
                }
                for (int i = list.size() / 4, j = 0; i < 2 * list.size() / 4; i++) {
                    arr2[j] = list.get(i);//arr2数组赋值title
                    j++;
                }
                for (int i = 2 * list.size() / 4, j = 0; i < 3 * list.size() / 4; i++) {
                    arr3[j] = list.get(i);//arr3数组赋值detailed
                    j++;
                }
                for (int i = 3 * list.size() / 4, j = 0; i < list.size() ; i++) {
                    arr4[j] = list.get(i);//arr4数组赋值host_id
                    j++;
                }
                list.clear();
                //输出label数组arr1
                for (int i = 0; i < arr1.length; i++) {
                    //  System.out.println(arr1[i]);
                    buffer1.append(arr1[i]);
                }
                //输出title数组arr2
                for (int i = 0; i < arr2.length; i++) {
                    //  System.out.println(arr2[i]);
                    buffer2.append(arr2[i]);
                }
                //输出detailed数组arr3
                for (int i = 0; i < arr3.length; i++) {
                    //  System.out.println(arr3[i]);
                    buffer3.append(arr3[i]);
                }
                //输出host_id数组arr4
                for (int i = 0; i < arr4.length; i++) {
                    //   System.out.println(arr4[i]);
                    buffer4.append(arr4[i]);
                }
                //输出host_name数组arr5

                String s1= new String(buffer1);
                String s2= new String(buffer2);
                String s3= new String(buffer3);
                String s4= new String(buffer4);

                arr[0]=s1;
                arr[1]=s2;
                arr[2]=s3;
                arr[3]=s4;

            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //   this.closeAll();
    }
    // update **
    public void update_user_password(String name, String password) {
        String sql="UPDATE user SET password ='"+password+"' where name= '"+name+"' ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("password修改成功！");
        else System.out.println("password修改失败！");
        this.closeAll();
    }
    public void update_user_is_login(String name, int is_login) {
        String sql="UPDATE user SET is_login ="+is_login+" where name= '"+name+"' ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("is_login修改成功！");
        else System.out.println("is_login修改失败！");
        this.closeAll();
    }
    public void update_user_name(String name ,String new_name) {
        String sql="UPDATE user SET name ='"+new_name+"' where name= '"+name+"' ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("name修改成功！");
        else System.out.println("name修改失败！");
        this.closeAll();
    }
    public void update_user_ip(String name, String ip) {
        String sql="UPDATE user SET ip ='"+ip+"' where name= '"+name+"' ";
        update_user_is_login(name,0);

        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("ip修改成功！");
        else System.out.println("ip修改失败！");
        this.closeAll();
    }



    //database management **
    public void delete_all_user() {
        String sql="DELETE from user ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("表user清除成功！");
        else System.out.println("表user清除失败！");
        this.closeAll();
    }
    public void delete_all_message() {
        String sql="DELETE from message ";


        this.startMysql();
        this.createStatement();
        int count=0;
        try {
            count = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(count==1) System.out.println("表message清除成功！");
        else System.out.println("表message清除失败！");
        this.closeAll();
    }
}

