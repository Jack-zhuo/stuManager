import utils.JDBCUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteStudent {
    deleteStudent(JFrame myWindow2){
        JFrame myWindow = new JFrame("删除学生");
        Container cp = myWindow.getContentPane();
        cp.setLayout(null);


        JLabel sName = new JLabel("姓   名：");


        JTextField inputName = new JTextField();


        JButton submit = new JButton("确认删除");

        sName.setFont(new Font("方正康体简体",Font.PLAIN,20));


        inputName.setBounds(120,60,200,30);

        sName.setBounds(40,60,100,30);

        submit.setBounds(90,220,150,30);

        cp.add(inputName);

        cp.add(sName);

        cp.add(submit);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String Name = "'" + inputName.getText() + "'";

                String sql = "delete from students where sName = "+Name;

                try {
                    int i = JDBCUtils.runUpdate(sql);
                    if (i == 1){
                        JOptionPane.showMessageDialog(null,"删除成功！");
                        myWindow.setVisible(false);
                        myWindow2.setVisible(true);

                    }else {
                        JOptionPane.showMessageDialog(null,"删除失败！");
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
