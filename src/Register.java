import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Register {
    public Register(JFrame login) {
        JFrame myWindow = new JFrame("注册页面");
        Container cp = myWindow.getContentPane();

        JLabel zhanghao = new JLabel("账号：");
        JLabel mima = new JLabel("密码：");
        JTextField input1 = new JTextField();
        JPasswordField input2 = new JPasswordField();
        JButton regBtn = new JButton("提交");

        zhanghao.setFont(new Font("黑体",Font.BOLD,15));
        mima.setFont(new Font("黑体",Font.BOLD,15));

        zhanghao.setBounds(75,15,50,30);
        input1.setBounds(125,15,200,30);
        mima.setBounds(75,50,50,30);
        input2.setBounds(125,50,200,30);
        regBtn.setBounds(150,100,100,30);

        cp.setLayout(null);

        cp.add(zhanghao);
        cp.add(input1);
        cp.add(mima);
        cp.add(input2);
        cp.add(regBtn);
        
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = input1.getText();
                String p = new String(input2.getPassword());
                String username = "'"+n+"'";
                String password = "'"+p+"'";

                if (n.length()>1 && n.length()<6 && p.length()>3 && p.length()<10) {
                    try {
                        Connection conn = JDBCUtils.getConnection();
                        Statement stmt = conn.createStatement();
                        String sql = "insert into users (username,password) values ("+username+","+password+")";
                        int i = stmt.executeUpdate(sql);
                        if (i>0){
                            JOptionPane.showMessageDialog(new JFrame(),"注册成功！");
                            myWindow.setVisible(false);
                            login.setVisible(true);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"请输入正确的账号密码！");
                        }
                        JDBCUtils.close(stmt,conn);



                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(new JFrame(),"请输入正确的账号密码！");
                        ex.printStackTrace();
                    }
                }else {
                    JOptionPane.showMessageDialog(new JFrame(),"账号或密码长度不合法。账号字符长度：2~5; 密码字符长度：4~9");
                }
            }
        });

        myWindow.setBounds(700,420,450,200);
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setVisible(true);
    }
}
