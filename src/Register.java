import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

public class Register {
    JFrame myWindow;
    JFrame login;
    JTextField input1;
    JPasswordField input2;
    public Register(JFrame login) {
        myWindow = new JFrame("注册页面");
        this.login = login;
        Container cp = myWindow.getContentPane();

        JLabel zhanghao = new JLabel("账号：");
        JLabel mima = new JLabel("密码：");
        input1 = new JTextField();
        input2 = new JPasswordField();
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
        
        regBtn.addActionListener(new handler());

        myWindow.setBounds(700,420,450,200);
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setVisible(true);
    }
    class handler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = input1.getText();
            String password = new String(input2.getPassword());

            if (username.length() != 0 && password.length() != 0) {
                try {
                    Connection conn = JDBCUtils.getConnection();
                    Statement stmt = conn.createStatement();
                    String sql = "insert into users (username,password) values ('"+username+"','"+password+"')";
                    int i = stmt.executeUpdate(sql);
                    if (i>0){
                        JOptionPane.showMessageDialog(null,"注册成功！");
                        myWindow.setVisible(false);
                        login.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null,"请输入正确的账号密码！");
                    }
                    JDBCUtils.close(stmt,conn);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"请输入正确的账号密码！");
                    ex.printStackTrace();
                }
            }else {
                JOptionPane.showMessageDialog(null,"密码和账号不能为空");
            }
        }
    }
}
