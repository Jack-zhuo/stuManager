import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class selectStudent {
    selectStudent(String s) throws Exception {

        JFrame myWindow = new JFrame("查询学生");
        Container cp = myWindow.getContentPane();
        cp.setLayout(null);

        JLabel title = new JLabel("你好！你的信息如下：");
        JLabel sName = new JLabel("姓   名：");
        JLabel sAge = new JLabel("年   龄：");
        JLabel sPhone = new JLabel("手机号：");
        JLabel sAddress = new JLabel("地   址：");

        ResultSet rs = JDBCUtils.runQuery("select * from students where sId =" + s);
            rs.next();
            String sNameStr = rs.getString("sName");
            String sAgeStr = rs.getString("sAge");
            String sPhoneStr = rs.getString("sPhone");
            String sAddressStr = rs.getString("sAddress");


        JLabel inputName = new JLabel(sNameStr);
        JLabel inputAge = new JLabel(sAgeStr);
        JLabel inputPhone = new JLabel(sPhoneStr);
        JLabel inputAddress = new JLabel(sAddressStr);

        title.setFont(new Font("宋体",Font.PLAIN,25));
        sName.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sAge.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sPhone.setFont(new Font("方正康体简体",Font.PLAIN,20));
        sAddress.setFont(new Font("方正康体简体",Font.PLAIN,20));

        inputName.setBounds(120,60,200,30);
        inputAge.setBounds(120,100,200,30);
        inputPhone.setBounds(120,140,200,30);
        inputAddress.setBounds(120,180,200,30);

        title.setBounds(40,20,300,30);
        sName.setBounds(40,60,100,30);
        sAge.setBounds(40,100,100,30);
        sPhone.setBounds(40,140,100,30);
        sAddress.setBounds(40,180,100,30);

//        cp.add(inputId);
        cp.add(inputName);
        cp.add(inputAge);
        cp.add(inputPhone);
        cp.add(inputAddress);

        cp.add(title);
        cp.add(sName);
        cp.add(sAge);
        cp.add(sPhone);
        cp.add(sAddress);

        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setBounds(770,300,400,300);
    }
}
