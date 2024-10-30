import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LostAndFoundSystem {
    private static List<LostItem> items = new ArrayList<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {// 登录逻辑
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine();
        System.out.print("请选择身份 (user/admin): ");
        String roleInput = scanner.nextLine().toLowerCase();
        if (roleInput.equals("admin")){
            System.out.println("请输入管理员密码");
            String password = scanner.nextLine();
            if (!password.equals("admin")){
                System.out.println("密码错误，请重试。");
                return;
            }
            else {
                User.Role role = User.Role.ADMIN;
                currentUser = new User(username, role);
            }
        }
        else {
            User.Role role = User.Role.USER;
            currentUser = new User(username, role);
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            handleChoice(choice);
        }

        scanner.close();
    }

    private static void printMenu() {// 打印菜单
        System.out.println("失物招领系统");
        if (currentUser.getRole() == User.Role.ADMIN) {
            System.out.println("1. 添加物品");
            System.out.println("2. 列出所有物品（管理员视图）");
            System.out.println("3. 列出所有物品（用户视图）");
            System.out.println("4. 认领物品");
            System.out.println("5. 切换身份");
            System.out.println("0. 退出");
        } else {
            System.out.println("1. 添加物品");
            System.out.println("3. 列出所有物品（用户视图）");
            System.out.println("4. 认领物品");
            System.out.println("5. 切换身份");
            System.out.println("0. 退出");
        }
        System.out.print("请选择操作: ");
    }

    private static void handleChoice(int choice) {// 根据选择执行操作
        switch (choice) {
            case 1:
                addItem();
                break;
            case 2:
                if (currentUser.getRole() == User.Role.ADMIN) {
                    listItemsForAdmin();
                } else {
                    System.out.println("此操作仅限管理员。");
                }
                break;
            case 3:
                listItemsForUser();
                break;
            case 4:
                claimItem();
                break;
            case 5:
                changeRole();
                break;
            case 0:
                System.out.println("退出系统。");
                System.exit(0); // 退出程序
                break;
            default:
                System.out.println("无效选择，请重试。");
        }
    }

    private static void addItem() {// 添加物品
        System.out.print("请输入物品名称: ");
        String name = scanner.nextLine();
        System.out.print("请输入物品描述: ");
        String description = scanner.nextLine();
        System.out.print("请输入丢失日期(yyyy/mm/dd): ");
        String lostDate = scanner.nextLine();
        System.out.print("请输入丢失地点: ");
        String lostPlace = scanner.nextLine();
        System.out.print("是否有可辨识物主身份信息(yes/no): ");
        String whetherHavePersonalID = scanner.nextLine();
        String RCode = RandomCode.generateRandomCode();
        if (whetherHavePersonalID.equalsIgnoreCase("yes")) {
            System.out.print("请输入可辨识物主身份信息: ");
            String identifiableInfo = scanner.nextLine();
            items.add(new LostItem(name, description, lostDate, lostPlace, whetherHavePersonalID, identifiableInfo, RCode));
            System.out.println("物品已添加成功。");
        } else {
            String identifiableInfo = "无";
            items.add(new LostItem(name, description, lostDate, lostPlace, whetherHavePersonalID, identifiableInfo, RCode));
            System.out.println("物品已添加成功。");
        }
    }


    private static void listItemsForAdmin() {// 列出所有物品（管理员视图）
        System.out.println("所有物品（管理员视图）:");
        int i = 1;
        for (LostItem item : items) {
            System.out.println("["+i+"]"+" 上传者： "+ currentUser.getUsername()+" 物品唯一编号 "+item.getRCode()
                    +" 物品名称: "+item.getName()+" 物品描述: "+item.getDescription()
                    +" 丢失日期: "+item.getLostDate()+" 丢失地点: "+item.getLostPlace()
                    +" 物主身份信息: "+item.getIdentifiableInfo());//保护隐私
            i++;
        }
    }

    private static void listItemsForUser() {// 列出所有物品（用户视图）
        System.out.println("所有物品（用户视图）:");
        int i = 1;
        for (LostItem item : items) {
            System.out.println("["+i+"]"+" 物品唯一编号: "+item.getRCode()
                    +" 物品名称: "+item.getName()+" 物品描述: "+item.getDescription()
                    +" 丢失日期: "+item.getLostDate()+" 丢失地点: "+item.getLostPlace());
            i++;
        }
    }

    private static void claimItem() {// 认领物品
        System.out.print("请输入要认领物品的唯一编码: ");
        String rCode = scanner.nextLine();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getRCode().equals(rCode)) {
                items.remove(i);
                System.out.println("物品已认领成功。");
                return;
            }
        }
        System.out.println("未找到该物品。");
    }

    private static void changeRole() {// 切换身份
        System.out.print("请选择新身份(user/admin): ");
        String roleInput = scanner.nextLine().toLowerCase();
        if (roleInput.equals("admin")){
            System.out.println("请输入管理员密码");
            String password = scanner.nextLine();
            if (!password.equals("admin")){
                System.out.println("密码错误，请重试。");
                return;
            }
            else {
                User.Role role = User.Role.ADMIN;
                currentUser.setRole(role);
                System.out.println("身份已切换为："+ roleInput);
            }
        }
        else {
            User.Role role = User.Role.USER;
            currentUser.setRole(role);
            System.out.println("身份已切换为："+ roleInput);
        }
    }
}