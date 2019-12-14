import com.google.gson.Gson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeNameUI {

    public JFrame view;

    public JButton btnChange = new JButton("Change");
    public JButton btnCancel = new JButton("Cancel");

    public JTextField txtFullName = new JTextField(10);

    UserModel user;
    String m_username;

    public ChangeNameUI(String username) {
        this.view = new JFrame();
        m_username = username;

        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        view.setTitle("Change Full Name");
        view.setSize(600, 400);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JPanel line = new JPanel(new FlowLayout());
        line.add(new JLabel("New Name "));
        line.add(txtFullName);
        view.getContentPane().add(line);

        line = new JPanel(new FlowLayout());
        line.add(btnChange);
        line.add(btnCancel);
        view.getContentPane().add(line);

        btnChange.addActionListener(new ChangeUserButtonListener());
    }

    public void run() {
        try {
            user = StoreManager.getInstance().getDataAdapter().loadUser(m_username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        user = new UserModel();
        view.setVisible(true);
    }

    class ChangeUserButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Gson gson = new Gson();

            String full_name = txtFullName.getText();
            if (full_name.length() == 0) {
                JOptionPane.showMessageDialog(null, "Name cannot be null");
                return;
            }
            user.mFullname = full_name;

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
