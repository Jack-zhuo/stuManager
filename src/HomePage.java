import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class HomePage {
   public HomePage(String username){
       String s = "哈喽！"+username+"，欢迎来到三联学院学籍管理系统。";
       JFrame myWindow = new JFrame(s);
       Container cp = myWindow.getContentPane();
       JPanel jp1 = new JPanel();

       JButton jb1 = new JButton("添加学生");
       JButton jb2 = new JButton("删除学生");
       JButton jb3 = new JButton("修改学生信息");
       JButton jb4 = new JButton("查看学生");

       jp1.add(jb1);
       jp1.add(jb2);
       jp1.add(jb3);
       jp1.add(jb4);

       cp.add(jp1);

       jb1.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new addStudent();
           }
       });

       jb2.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               new deleteStudent(myWindow);
           }
       });

       jb3.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ev) {

                   String sId = JOptionPane.showInputDialog(myWindow,"请输入要修改的学生的学号！！！");
                   try {
                       ResultSet rs = JDBCUtils.runQuery("select * from students where sId =" + sId);

                       if (rs.next())
                           new updateStudent(sId);
                       else
                           JOptionPane.showMessageDialog(null, "没有查到此学号的信息！");

                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }


       });

       jb4.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent ev) {
               String sId = JOptionPane.showInputDialog(myWindow,"请输入要查询的学生的学号！！！");
               try {
                   ResultSet rs = JDBCUtils.runQuery("select * from students where sId =" + sId);
                   if (rs.next()) {
                       new selectStudent(sId);
                   }
                   else {
                       JOptionPane.showMessageDialog(null, "没有查到此学号的信息！");
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });

       myWindow.setVisible(true);
       myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       myWindow.setBounds(770,200,400,100);
   }

}
