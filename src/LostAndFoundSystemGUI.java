import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LostAndFoundSystemGUI extends JFrame {
    private List<LostItem> items = new ArrayList<>();
    private User currentUser = null;
    private JPanel mainPanel;
    private JButton addButton, listAdminButton, listUserButton, claimButton, changeRoleButton, exitButton;
    private JTextArea outputArea;
    private JTextField usernameField, roleField;
    private JPasswordField passwordField; // 修改这里

    public LostAndFoundSystemGUI() {
        setTitle("失物招领系统");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initializeUI();
        login();
    }

    private void initializeUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // 输出区域
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 1));

        addButton = new JButton("添加物品");
        listAdminButton = new JButton("列出所有物品（管理员视图）");
        listUserButton = new JButton("列出所有物品（用户视图）");
        claimButton = new JButton("认领物品");
        changeRoleButton = new JButton("切换身份");
        exitButton = new JButton("退出");

        buttonPanel.add(addButton);
        buttonPanel.add(listAdminButton);
        buttonPanel.add(listUserButton);
        buttonPanel.add(claimButton);
        buttonPanel.add(changeRoleButton);
        buttonPanel.add(exitButton);

        mainPanel.add(buttonPanel, BorderLayout.EAST);

        // 登录面板
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(4, 2));

        loginPanel.add(new JLabel("用户名:"));
        usernameField = new JTextField();
        loginPanel.add(usernameField);

        loginPanel.add(new JLabel("身份 (user/admin):"));
        roleField = new JTextField();
        loginPanel.add(roleField);

        loginPanel.add(new JLabel("密码 (仅管理员):"));
        passwordField = new JPasswordField(); // 修改这里
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("登录");
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        mainPanel.add(loginPanel, BorderLayout.NORTH);

        setContentPane(mainPanel);

        // 添加事件监听器
        addButton.addActionListener(e -> addItem());
        listAdminButton.addActionListener(e -> listItemsForAdmin());
        listUserButton.addActionListener(e -> listItemsForUser());
        claimButton.addActionListener(e -> claimItem());
        changeRoleButton.addActionListener(e -> changeRole());
        exitButton.addActionListener(e -> System.exit(0));
        loginButton.addActionListener(e -> login());
    }

    private void login() {
        String username = usernameField.getText();
        String roleInput = roleField.getText().toLowerCase();
        if (roleInput.equals("admin")) {
            String password = new String(passwordField.getPassword()); // 修改这里
            if (!password.equals("admin")) {
                outputArea.append("密码错误，请重试。\n");
                return;
            }
        }
        User.Role role = roleInput.equalsIgnoreCase("admin") ? User.Role.ADMIN : User.Role.USER;
        currentUser = new User(username, role);
        updateUI();
    }

    private void updateUI() {
        if (currentUser.getRole() == User.Role.ADMIN) {
            listAdminButton.setEnabled(true);
        } else {
            listAdminButton.setEnabled(false);
        }
    }

    private void addItem() {
        String name = JOptionPane.showInputDialog(this, "请输入物品名称");
        String description = JOptionPane.showInputDialog(this, "请输入物品描述");
        String lostDate = JOptionPane.showInputDialog(this, "请输入丢失日期 (yyyy/mm/dd)");
        String lostPlace = JOptionPane.showInputDialog(this, "请输入丢失地点");
        String whetherHavePersonalID = JOptionPane.showInputDialog(this, "是否有可辨识物主身份信息 (yes/no)").toLowerCase();

        String identifiableInfo;
        if (whetherHavePersonalID.equalsIgnoreCase("yes")) {
            identifiableInfo = JOptionPane.showInputDialog(this, "请输入可辨识物主身份信息");
        } else {
            identifiableInfo = "无";
        }

        items.add(new LostItem(name, description, lostDate, lostPlace, whetherHavePersonalID, identifiableInfo));
        outputArea.append("物品已添加成功。\n");
    }

    private void listItemsForAdmin() {
        if (currentUser.getRole() != User.Role.ADMIN) {
            outputArea.append("此操作仅限管理员。\n");
            return;
        }

        outputArea.append("所有物品（管理员视图）:\n");
        for (int i = 0; i < items.size(); i++) {
            LostItem item = items.get(i);
            outputArea.append("[" + (i + 1) + "] 物品名称: " + item.getName() + " 物品描述: " + item.getDescription()
                    + " 丢失日期: " + item.getLostDate() + " 丢失地点: " + item.getLostPlace()
                    + " 物主身份信息: " + item.getIdentifiableInfo() + "\n");
        }
    }

    private void listItemsForUser() {
        outputArea.append("所有物品（用户视图）:\n");
        for (int i = 0; i < items.size(); i++) {
            LostItem item = items.get(i);
            outputArea.append("[" + (i + 1) + "] 物品名称: " + item.getName() + " 物品描述: " + item.getDescription()
                    + " 丢失日期: " + item.getLostDate() + " 丢失地点: " + item.getLostPlace() + "\n");
        }
    }

    private void claimItem() {
        String name = JOptionPane.showInputDialog(this, "请输入要认领的物品名称");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                outputArea.append("物品已认领成功。\n");
                return;
            }
        }
        outputArea.append("未找到该物品。\n");
    }

    private void changeRole() {
        String roleInput = JOptionPane.showInputDialog(this, "请选择新身份 (user/admin)").toLowerCase();
        if (roleInput.equals("admin")) {
            String password = JOptionPane.showInputDialog(this, "请输入管理员密码");
            if (!password.equals("admin")) {
                outputArea.append("密码错误，请重试。\n");
                return;
            }
        }
        User.Role role = roleInput.equalsIgnoreCase("admin") ? User.Role.ADMIN : User.Role.USER;
        currentUser.setRole(role);
        outputArea.append("身份已切换为：" + roleInput + "\n");
        updateUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LostAndFoundSystemGUI().setVisible(true);
        });
    }
}
   