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

       myWindow.setVisible(true);
       myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
       myWindow.setLocation(700,500);
       myWindow.pack();
   }
}
