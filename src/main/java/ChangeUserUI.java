import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUserUI {

    public JFrame view;

    public JButton btnChange = new JButton("Change");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);

    UserModel user;

    public ChangeUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Change a User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("Username "));
        line.add(txtUsername);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(new JLabel("UserType "));
        line.add(txtUserType);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnChange);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnChange.addActionListener(new ChangeUserButtonListener());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    class ChangeUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();

            String username = txtUsername.getText();
            if (username.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null");
                return;
            }
            user.mUsername = username;

            try {
                user = StoreManager.getInstance().getDataAdapter().loadUser(username);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String user_type = txtUserType.getText();
            if (user_type.toLowerCase().equals("customer")) {
                user.mUserType = 0;
            } else if (user_type.toLowerCase().equals("cashier")) {
                user.mUserType = 1;
            } else if (user_type.toLowerCase().equals("manager")) {
                user.mUserType = 2;
            } else if (user_type.toLowerCase().equals("admin")) {
                user.mUserType = 3;
            } else {
                try {
                    user.mUserType = Integer.parseInt(user_type);
                } catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "User Type is invalid!");
                    return;
                }
            }


            try {
                int res = StoreManager.getInstance().getDataAdapter().saveUser(user);

                if (res == SQLiteDataAdapter.USER_SAVE_FAILED) {
                    JOptionPane.showMessageDialog(null, "User is NOT saved");
                } else {
                    JOptionPane.showMessageDialog(null, "User is SAVED successfully");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
