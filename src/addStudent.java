import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addStudent {
    public addStudent(){
        JFrame myWindow = new JFrame("添加新同学");
        Container cp = myWindow.getContentPane();
        cp.setLayout(null);

        JLabel sId = new JLabel("学   号：");
        JLabel sName = new JLabel("姓   名：");
        JLabel sAge = new JLabel("年   龄：");
        JLabel sPhone = new JLabel("手机号：");
        JLabel sAddress = new JLabel("地   址：");

        JTextField inputId = new JTextField();
        JTextField inputName = new JTextField();
        JTextField inputAge = new JTextField();
        JTextField inputPhone = new JTextField();
        JTextField inputAddress = new JTextField();

        JButton submit = new JButton("提交");

        sId.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sName.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sAge.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sPhone.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sAddress.setFont(new Font("方正康体简体",Font.PLAIN,20));

        inputId.setBounds(120,20,200,30);
        inputName.setBounds(120,60,200,30);
        inputAge.setBounds(120,100,200,30);
        inputPhone.setBounds(120,140,200,30);
        inputAddress.setBounds(120,180,200,30);

        sId.setBounds(40,20,100,30);
        sName.setBounds(40,60,100,30);
        sAge.setBounds(40,100,100,30);
        sPhone.setBounds(40,140,100,30);
        sAddress.setBounds(40,180,100,30);

        submit.setBounds(90,220,150,30);

        cp.add(inputId);
        cp.add(inputName);
        cp.add(inputAge);
        cp.add(inputPhone);
        cp.add(inputAddress);

        cp.add(sId);
        cp.add(sName);
        cp.add(sAge);
        cp.add(sPhone);
        cp.add(sAddress);

        cp.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Id = Integer.parseInt(inputId.getText());
                String Name = "'" + inputName.getText() + "'";
                int Age = Integer.parseInt(inputAge.getText());
                String Phone = "'" + inputPhone.getText() + "'";
                String Address = "'" + inputAddress.getText() + "'";

                String sql = "insert into students (sId,sName,sAge,sPhone,sAddress) values ("+Id+","+Name+","+Age+","+Phone+","+Address+")";

                try {
                    int i = JDBCUtils.runUpdate(sql);
                    System.out.println(i);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setBounds(770,300,400,300);
    }

}
