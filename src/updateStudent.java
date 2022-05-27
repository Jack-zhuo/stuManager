import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class updateStudent {
    String s;
    updateStudent(String s) throws Exception {
        this.s = s;
        JFrame myWindow = new JFrame("修改学生信息");
        Container cp = myWindow.getContentPane();
        cp.setLayout(null);

        JLabel sId = new JLabel("请在下面输入修改信息！！！！");
        JLabel sName = new JLabel("姓   名：");
        JLabel sAge = new JLabel("年   龄：");
        JLabel sPhone = new JLabel("手机号：");
        JLabel sAddress = new JLabel("地   址：");

        JTextField inputName = new JTextField();
        JTextField inputAge = new JTextField();
        JTextField inputPhone = new JTextField();
        JTextField inputAddress = new JTextField();

        JButton submit = new JButton("提交");

        sId.setFont(new Font("方正康体简体", Font.PLAIN, 20));
        sName.setFont(new Font("方正康体简体", Font.PLAIN, 20));
        sAge.setFont(new Font("方正康体简体", Font.PLAIN, 20));
        sPhone.setFont(new Font("方正康体简体", Font.PLAIN, 20));
        sAddress.setFont(new Font("方正康体简体", Font.PLAIN, 20));

        inputName.setBounds(120, 60, 200, 30);
        inputAge.setBounds(120, 100, 200, 30);
        inputPhone.setBounds(120, 140, 200, 30);
        inputAddress.setBounds(120, 180, 200, 30);

        sId.setBounds(40, 20, 300, 30);
        sName.setBounds(40, 60, 100, 30);
        sAge.setBounds(40, 100, 100, 30);
        sPhone.setBounds(40, 140, 100, 30);
        sAddress.setBounds(40, 180, 100, 30);

        submit.setBounds(90, 220, 150, 30);

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

        ResultSet rs = JDBCUtils.runQuery("select * from students where sId =" + s);
        if (rs.next()){
            inputName.setText(rs.getString("sName"));
            inputAge.setText(rs.getString("sAge"));
            inputPhone.setText(rs.getString("sPhone"));
            inputAddress.setText(rs.getString("sAddress"));
        }


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int Id = Integer.parseInt(inputId.getText());
                String Name = "'" + inputName.getText() + "'";
                int Age = Integer.parseInt(inputAge.getText());
                String Phone = "'" + inputPhone.getText() + "'";
                String Address = "'" + inputAddress.getText() + "'";
                // UPDATE table_anem SET column_name1 = value1, column_name2 = value2, WHERE ;
                String sql = "update students set sName = " + Name + ", sAge = " + Age + ",sPhone =" + Phone + ",sAddress =" + Address + "where sId=" + s;

                try {

                    int i = JDBCUtils.runUpdate(sql);
                   if (i == 1){
                       JOptionPane.showMessageDialog(null,"修改成功！");
                       myWindow.setVisible(false);
                   }
                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        myWindow.setVisible(true);
        myWindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myWindow.setBounds(770, 300, 400, 300);
    }
}
