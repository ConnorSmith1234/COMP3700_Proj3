import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI {

    public UserModel user = null;

    public JFrame view;

    public JButton btnAddUser = new JButton("Add a User");
    public JButton btnChangeUser = new JButton("Change User type");
    public JButton btnChangePassword = new JButton("Change my password");
    public JButton btnChangeFullname = new JButton("Change my fullname");

    public AdminUI(UserModel user) {

        this.user = user;
        final String username = user.mUsername;

        this.view = new JFrame();

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setTitle("Store Management System - Administrator View");
        view.setSize(1000, 600);
        view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("Store Management System");

        title.setFont (title.getFont ().deriveFont (24.0f));
        view.getContentPane().add(title);

        JPanel panelButtons = new JPanel(new FlowLayout());
        panelButtons.add(btnAddUser);
        panelButtons.add(btnChangeUser);
        panelButtons.add(btnChangePassword);
        panelButtons.add(btnChangeFullname);
        view.getContentPane().add(panelButtons);


        btnAddUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AddUserUI ui = new AddUserUI();
                ui.run();
            }
        });

        btnChangeUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeUserUI ui = new ChangeUserUI();
                ui.run();
            }
        });

        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangePasswordUI ui = new ChangePasswordUI(username);
                ui.run();
            }
        });

        btnChangeFullname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeNameUI ui = new ChangeNameUI(username);
                ui.run();
            }
        });


    }

}
