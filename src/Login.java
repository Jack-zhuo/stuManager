import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    public Login() {
        JFrame myWindow = new JFrame("登录页面");
        Container cp = myWindow.getContentPane();

        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        JLabel zhanghao = new JLabel("账号：");
        JLabel mima = new JLabel("密码：");
        JTextField input1 = new JTextField();
        JPasswordField input2 = new JPasswordField();
        JButton loginBtn = new JButton("登录");
        JButton regBtn = new JButton("注册管理员账号！");


        zhanghao.setFont(new Font("黑体",Font.BOLD,15));
        mima.setFont(new Font("黑体",Font.BOLD,15));
        regBtn.setFont(new Font("微软雅黑",Font.BOLD,10));

        zhanghao.setBounds(75,5,50,30);
        input1.setBounds(125,5,200,30);
        mima.setBounds(75,40,50,30);
        input2.setBounds(125,40,200,30);
        loginBtn.setBounds(150,90,100,30);
        regBtn.setBounds(310,130,115,20);


        URL url = Login.class.getResource("top.jpg");
        ImageIcon imageIcon = new ImageIcon(url);
        JLabel topPic = new JLabel(imageIcon);
        jp1.add(topPic,BorderLayout.NORTH);
        jp2.setLayout(null);

        jp2.add(zhanghao);
        jp2.add(input1);
        jp2.add(mima);
        jp2.add(input2);
        jp2.add(loginBtn);
        jp2.add(regBtn);
        cp.add(jp1,BorderLayout.NORTH);
        cp.add(jp2,BorderLayout.CENTER);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String n = input1.getText();
                String p = new String(input2.getPassword());

                    try {
                        String sql = "select * from users where username = ? and password = ?";
                        PreparedStatement pstmt = JDBCUtils.getConnection().prepareStatement(sql);
                        pstmt.setString(1,n);
                        pstmt.setString(2,p);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()){
                            new HomePage(rs.getString("username"));
                            myWindow.setVisible(false);
                        }else {
                            JOptionPane.showMessageDialog(new JFrame(),"账号或密码错误，请重新输入！");
                            input1.setText("");
                            input2.setText("");
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }
        });
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register(myWindow);
                myWindow.setVisible(false);
            }
        });
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setBounds(700,350,450,350);
        myWindow.setVisible(true);
    }

}
