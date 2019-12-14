import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserUI {

    public JFrame view;

    public JButton btnAdd = new JButton("Add");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtUsername = new JTextField(10);
    public JTextField txtPassword = new JTextField(10);
    public JTextField txtFullname = new JTextField(10);
    public JTextField txtUserType = new JTextField(10);
    public JTextField txtCustomerID = new JTextField(10);

    UserModel user;

    public AddUserUI() {
        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Add a New User");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line1 = new JPanel(new FlowLayout());
        line1.add(new JLabel("Username "));
        line1.add(txtUsername);
        view.getContentPane().add(line1);

        JPanel line2 = new JPanel(new FlowLayout());
        line2.add(new JLabel("Password "));
        line2.add(txtPassword);
        view.getContentPane().add(line2);

        JPanel line3 = new JPanel(new FlowLayout());
        line3.add(new JLabel("Fullname "));
        line3.add(txtFullname);
        view.getContentPane().add(line3);

        JPanel line4 = new JPanel(new FlowLayout());
        line4.add(new JLabel("UserType "));
        line4.add(txtUserType);
        view.getContentPane().add(line4);

        JPanel line5 = new JPanel(new FlowLayout());
        line5.add(new JLabel("CustomerID "));
        line5.add(txtCustomerID);
        view.getContentPane().add(line5);

        JPanel line6 = new JPanel(new FlowLayout());
        line6.add(btnAdd);
        line6.add(btnCancel);
        view.getContentPane().add(line6);

        btnAdd.addActionListener(new AddUserButtonListener());
    }

    public void run() {
        user = new UserModel();
        view.setVisible(true);
    }

    class AddUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();

            String username = txtUsername.getText();
            if (username.length() == 0) {
                JOptionPane.showMessageDialog(null, "Username cannot be null");
                return;
            }
            user.mUsername = username;

            String password = txtPassword.getText();
            if (password.length() == 0) {
                JOptionPane.showMessageDialog(null, "Password cannot be null!");
                return;
            }
            user.mPassword = password;

            String full_name = txtFullname.getText();
            if (full_name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Full name cannot be null!");
                return;
            }
            user.mFullname = full_name;

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

            if (user.mUserType == 0) { // If user is a customer.
                String id = txtCustomerID.getText();
                if (id.length() == 0) {
                    JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
                    return;
                }

                try {
                    user.mCustomerID = Integer.parseInt(id);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "CustomerID is invalid!");
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
