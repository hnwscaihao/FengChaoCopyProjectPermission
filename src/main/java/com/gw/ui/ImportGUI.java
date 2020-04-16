package com.gw.ui;

import com.gw.service.ImportService;
import com.gw.ui.swingUI.InfiniteProgressPanel;
import com.gw.util.MKSCommand;
import com.gw.util.Result;
import com.mks.api.response.APIException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.gw.util.DealService.allUserList;
import static com.gw.util.DealService.groupMemberRecord;
import static com.gw.util.DealService.All_user;
import static javax.swing.SpringLayout.EAST;

/**
 * Swing面板 lxg
 *
 */public class ImportGUI extends JFrame {

    private static final Log log = LogFactory.getLog(ImportService.class);

    public File selectedFile = new File(""); //导入文件
    public InfiniteProgressPanel glasspane = new InfiniteProgressPanel(); //加载中

    //流式布局
    SpringLayout springLayout = new SpringLayout();
    // 创建一个进度条
    public JProgressBar progressBar = new JProgressBar();
    private static final int MIN_PROGRESS = 0;
    private static final int MAX_PROGRESS = 100;
    private static int currentProgress = MIN_PROGRESS;
    //选项卡
    JTabbedPane jtp = new JTabbedPane();
    Container con = getContentPane();//获得窗体容器对象

    public JComboBox cmb=new JComboBox();    //创建JComboBox
    JTextField txtfield1=new JTextField(30);    //创建文本框

    public String sessionid = "";
    public String caseId = "";
    public String projectId = "";
    public  Map<String,String> caseIds = new HashMap<String,String>();

    List<Map<String,String>> testReulst;
    List<Map<String,String>> CaseReulst;
    String  TypeStr;//保存longtext 类型

    Box box1 = Box.createHorizontalBox();
    Box box2 = Box.createHorizontalBox();
    Box box3 = Box.createHorizontalBox();
    Box box5 = Box.createVerticalBox();
    Box box4 = Box.createHorizontalBox();
    Box box6= Box.createVerticalBox();

    Box ASWEngineerDGBox= Box.createHorizontalBox();
    Box ASWLeaderDGBox= Box.createHorizontalBox();
    Box BSWEngineerDGBox= Box.createHorizontalBox();
    Box BSWLeaderDGBox= Box.createHorizontalBox();
    Box CCBDGBox= Box.createHorizontalBox();
    Box CCBLeaderDGBox= Box.createHorizontalBox();
    Box ConfigurationManagerDGBox= Box.createHorizontalBox();
    Box DirectorDGBox= Box.createHorizontalBox();
    Box FunctionSafetyEngineerDGBox= Box.createHorizontalBox();
    Box FunctionSafetyLeaderDGBox= Box.createHorizontalBox();
    Box HardwareEngineerDGBox= Box.createHorizontalBox();
    Box HardwareEnginnerLeaderDGBox= Box.createHorizontalBox();
    Box PRCDGBox= Box.createHorizontalBox();
    Box PRCGroupLeaderDGBox= Box.createHorizontalBox();
    Box ProjectManagerDGBox= Box.createHorizontalBox();
    Box QADGBox= Box.createHorizontalBox();
    Box ReviewCommitteeDGBox= Box.createHorizontalBox();
    Box ReviewCommitteeLeaderDGBox= Box.createHorizontalBox();
    Box SystemEngineerDGBox= Box.createHorizontalBox();
    Box SystemLeaderDGBox= Box.createHorizontalBox();
    Box TestEngineerDGBox= Box.createHorizontalBox();
    Box TestLeaderDGBox= Box.createHorizontalBox();



    Box box9= Box.createVerticalBox();
    JPanel jp1 = new JPanel();
    JPanel jp2 = new JPanel();
    JPanel tabGUi1 = new JPanel();  //总容器
    JPanel tabUp1 = new JPanel();   //
    JPanel tabUp2 = new JPanel();

    //导入界面 lxg
    public void ImportGUI() {

        //替换java默认图标
        ImageIcon icon=new ImageIcon("client.jpg"); //图片和项目同一路径，故不用图片的路径
        setIconImage(icon.getImage());


        setTitle("Project动态组设置");
        setBounds(0, 0, 742, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(springLayout);//设置窗体布局格式为弹簧式布局
        setLocationRelativeTo(null);//窗体居中显示
        setResizable(false);//窗体是否可以放大

        //加载组件
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        glasspane.setBounds(100, 100, (dimension.width) / 2, (dimension.height) / 2);
        setGlassPane(glasspane);

        tab1();//tab1

        tab2(); //tab2

        initBtn(0);//按钮
//        int tabIndex = jtp.getSelectedIndex();
        tabGUi1.setLayout(new BorderLayout(20,-5));
        jtp.setPreferredSize(new Dimension(724,400));
        tabUp1.add(jtp);
        tabGUi1.add(tabUp1);
        tabGUi1.add(tabUp2,BorderLayout.SOUTH);

        add(tabGUi1);
        setVisible(true); //设置窗口是否可见

    }

    public void tab2(){
//        jp2.setLayout(new SpringLayout());
        JButton button1 = new JButton("返回");
        button1.setFocusPainted(false);  //去掉按钮字体焦点框
        box4.add(button1);

        box9.add(ASWEngineerDGBox);
        box9.add(new JPanel());
        box9.add(ASWLeaderDGBox);
        box9.add(new JPanel());
        box9.add(BSWEngineerDGBox);
        box9.add(new JPanel());
        box9.add(BSWLeaderDGBox);
        box9.add(new JPanel());
        box9.add(CCBDGBox);
        box9.add(new JPanel());
        box9.add(CCBLeaderDGBox);
        box9.add(new JPanel());
        box9.add(ConfigurationManagerDGBox);
        box9.add(new JPanel());
        box9.add(DirectorDGBox);
        box9.add(new JPanel());
        box9.add(FunctionSafetyEngineerDGBox);
        box9.add(new JPanel());
        box9.add(FunctionSafetyLeaderDGBox);
        box9.add(new JPanel());
        box9.add(HardwareEngineerDGBox);
        box9.add(new JPanel());
        box9.add(HardwareEnginnerLeaderDGBox);
        box9.add(new JPanel());
        box9.add(PRCDGBox);
        box9.add(new JPanel());
        box9.add(PRCGroupLeaderDGBox);
        box9.add(new JPanel());
        box9.add(ProjectManagerDGBox);
        box9.add(new JPanel());
        box9.add(QADGBox);
        box9.add(new JPanel());
        box9.add(ReviewCommitteeDGBox);
        box9.add(new JPanel());
        box9.add(ReviewCommitteeLeaderDGBox);
        box9.add(new JPanel());
        box9.add(SystemEngineerDGBox);
        box9.add(new JPanel());
        box9.add(SystemLeaderDGBox);
        box9.add(new JPanel());
        box9.add(TestEngineerDGBox);
        box9.add(new JPanel());
        box9.add(TestLeaderDGBox);

        jp2.add(box9);
        // 创建滚动面板, 指定滚动显示的视图组件(textArea), 垂直滚动条按需显示, 水平滚动条从不显示
        JScrollPane scrollPane = new JScrollPane(
                jp2,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                //ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED // 需要时显示（默认）
        );
        jtp.addTab("Project动态组设置" ,scrollPane);
        jtp.setEnabledAt(1,false); //tab不可选
    }

    public void tab1(){
        JLabel btn1=new JLabel("Project :");
//        cmb.addItem("--请选择--");    //向下拉列表中添加一项、

        box1.add(Box.createHorizontalStrut(20));
        box1.add(btn1);
        box1.add(Box.createHorizontalStrut(25));
        box1.add(cmb);
        cmb.setPreferredSize(new Dimension (300,28));
        cmb.addItem("——请选择——");
        box1.add(Box.createHorizontalStrut(20));
//        JRadioButton rb1=new JRadioButton("是否复制已经选中的project");
//        box2.add(rb1);
        box5.add(Box.createVerticalStrut(120));
        box5.add(box1);
        box5.add(Box.createVerticalStrut(25));
        box5.add(box2);

        jp1.add(box5);
        jtp.addTab("Search" ,jp1);
        jtp.setEnabledAt(0,false);

    }


    //搜索按钮监听 lxg
    public void Listener1(JButton btn1, final int  index) {
        btn1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){

                ASWEngineerDGBox.removeAll();
                ASWLeaderDGBox.removeAll();
                BSWEngineerDGBox.removeAll();
                BSWLeaderDGBox.removeAll();
                CCBDGBox.removeAll();
                CCBLeaderDGBox.removeAll();
                ConfigurationManagerDGBox.removeAll();
                DirectorDGBox.removeAll();
                FunctionSafetyEngineerDGBox.removeAll();
                FunctionSafetyLeaderDGBox.removeAll();
                HardwareEngineerDGBox.removeAll();
                HardwareEnginnerLeaderDGBox.removeAll();
                PRCDGBox.removeAll();
                PRCGroupLeaderDGBox.removeAll();
                ProjectManagerDGBox.removeAll();
                QADGBox.removeAll();
                ReviewCommitteeDGBox.removeAll();
                ReviewCommitteeLeaderDGBox.removeAll();
                SystemEngineerDGBox.removeAll();
                SystemLeaderDGBox.removeAll();
                TestEngineerDGBox.removeAll();
                TestLeaderDGBox.removeAll();

                groupMemberRecord =  new HashMap<>();
                All_user = new ArrayList();
                if(index == 1) {
                    MKSCommand m = new MKSCommand();
                    //获取session判断是否为空
                    String caseName = cmb.getSelectedItem().toString();
                    if(caseName.equals("——请选择——")){
                        JOptionPane.showMessageDialog(null,"请选选择项目", "提示", 1);
                        return;
                    }
                    try {
                        List<String> DynamicGroupNames = new ArrayList();
                        DynamicGroupNames.add("ASW Engineer DG");
                        DynamicGroupNames.add("ASW Leader DG");
                        DynamicGroupNames.add("BSW Engineer DG");
                        DynamicGroupNames.add("BSW Leader DG");
                        DynamicGroupNames.add("CCB DG");
                        DynamicGroupNames.add("CCB Leader DG");
                        DynamicGroupNames.add("Configuration Manager DG");
                        DynamicGroupNames.add("Director DG");
                        DynamicGroupNames.add("Functional Safety Engineer DG");
                        DynamicGroupNames.add("Functional Safety Leader DG");
                        DynamicGroupNames.add("Hardware Engineer DG");
                        DynamicGroupNames.add("Hardware Engineer Leader DG");
                        DynamicGroupNames.add("PRC DG");
                        DynamicGroupNames.add("PRC Group Leader DG");
                        DynamicGroupNames.add("Project Manager DG");
                        DynamicGroupNames.add("QA DG");
                        DynamicGroupNames.add("Review Committee DG");
                        DynamicGroupNames.add("Review Committee Leader DG");
                        DynamicGroupNames.add("System Engineer DG");
                        DynamicGroupNames.add("System Leaders DG");
                        DynamicGroupNames.add("Test Engineer DG");
                        DynamicGroupNames.add("Test Leader DG");
                        //判断是否选中projectid
//                        Component[]  jcbs = box2.getComponents();
//                        for(Component component : jcbs){
//                            JRadioButton jcb = (JRadioButton) component;//需要强制转换成jcheckbox
//                            if(jcb.isSelected()) {
//                                if ( m.tsIds.size() == 1) {//如果选中的id则复制项用户
//                                    String projectName =  m.getProjectNameById(m.tsIds.get(0));
//                                    Map<String,List<String>> resultMap = m.getProjectDynamicGroupsMember1(DynamicGroupNames,projectName);
//                                    for(String key : resultMap.keySet()){
//                                        m.updateDynamicGroup(caseName,key,resultMap.get(key));
//                                    }
//                                } else {
//                                    JOptionPane.showMessageDialog(null,"请先选中需要复制projectId", "错误", 0);
//                                    System.exit(0); //关闭主程序
//                                }
//                            }
//                        }
                        if ( m.tsIds.size() == 1) {//如果选中的id则复制项用户
                            String projectName =  m.getProjectNameById(m.tsIds.get(0));
                            Map<String,List<String>> resultMap = m.getProjectDynamicGroupsMember1(DynamicGroupNames,projectName);
                            for(String key : resultMap.keySet()){
                                m.updateDynamicGroup(caseName,key,resultMap.get(key));
                            }
                        }
                        m.getProjectDynamicGroupsMember(DynamicGroupNames, caseName);//根据项目查询组用户
                        m.getAllUser();//查询全部用户
//                      m.getProjectDynamicGroupsMember(Arrays.asList("ASW Engineer DG", "Project Team"), "/ALM项目组");
                    } catch (APIException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", 0);
                        System.exit(0); //关闭主程序
                    }

                    jtp.setSelectedIndex(index);
                   initBtn(1);
                   Swingfy(caseName);
//                log.info("点击搜索");
//                jtp.setSelectedIndex(index);
                }else {
                    jtp.setSelectedIndex(index);
                    initBtn(0);
                }
            }
        });
    }

    //info详情 复用代码
    public void Swingfy(String ProjectName){
        setTitle("Project动态组设置 ");
        MKSCommand m = new MKSCommand();
        //动态组和数据
        String ASWEngineerDGName = "";
        List<String> ASWEngineerDGUsers = new ArrayList<>();
        List<String> ASWEngineerDGExcludeUsers = new ArrayList<>();

        String ASWLeaderDGName = "";
        List<String> ASWLeaderDGUsers = new ArrayList<>();
        List<String> ASWLeaderDGExcludeUsers = new ArrayList<>();

        String BSWEngineerDGName = "";
        List<String> BSWEngineerDGUsers = new ArrayList<>();
        List<String> BSWEngineerDGExcludeUsers = new ArrayList<>();

        String BSWLeaderDGName = "";
        List<String> BSWLeaderDGUsers = new ArrayList<>();
        List<String> BSWLeaderDGExcludeUsers = new ArrayList<>();

        String CCBDGName = "";
        List<String> CCBDGUsers = new ArrayList<>();
        List<String> CCBDGExcludeUsers = new ArrayList<>();

        String CCBLeaderDGName = "";
        List<String> CCBLeaderDGUsers = new ArrayList<>();
        List<String> CCBLeaderDGExcludeUsers = new ArrayList<>();

        String ConfigurationManagerDGName = "";
        List<String> ConfigurationManagerDGUsers = new ArrayList<>();
        List<String> ConfigurationManagerDGExcludeUsers = new ArrayList<>();

        String DirectorDGName = "";
        List<String> DirectorDGUsers = new ArrayList<>();
        List<String> DirectorDGExcludeUsers = new ArrayList<>();

        String FunctionSafetyEngineerDGName = "";
        List<String> FunctionSafetyEngineerDGUsers = new ArrayList<>();
        List<String> FunctionSafetyEngineerDGExcludeUsers = new ArrayList<>();

        String FunctionSafetyLeaderDGName = "";
        List<String> FunctionSafetyLeaderDGUsers = new ArrayList<>();
        List<String> FunctionSafetyLeaderDGExcludeUsers = new ArrayList<>();

        String HardwareEngineerDGName = "";
        List<String> HardwareEngineerDGUsers = new ArrayList<>();
        List<String> HardwareEngineerDGExcludeUsers = new ArrayList<>();

        String HardwareEnginnerLeaderDGName = "";
        List<String> HardwareEnginnerLeaderDGUsers = new ArrayList<>();
        List<String> HardwareEnginnerLeaderDGExcludeUsers = new ArrayList<>();

        String PRCDGName = "";
        List<String> PRCDGUsers = new ArrayList<>();
        List<String> PRCDGExcludeUsers = new ArrayList<>();

        String PRCGroupLeaderDGName = "";
        List<String> PRCGroupLeaderDGUsers = new ArrayList<>();
        List<String> PRCGroupLeaderDGExcludeUsers = new ArrayList<>();

        String ProjectManagerDGName = "";
        List<String> ProjectManagerDGUsers = new ArrayList<>();
        List<String> ProjectManagerDGExcludeUsers = new ArrayList<>();

        String QADGName = "";
        List<String> QADGUsers = new ArrayList<>();
        List<String> QADGExcludeUsers = new ArrayList<>();

        String ReviewCommitteeDGName = "";
        List<String> ReviewCommitteeDGUsers = new ArrayList<>();
        List<String> ReviewCommitteeDGExcludeUsers = new ArrayList<>();

        String ReviewCommitteeLeaderDGName = "";
        List<String> ReviewCommitteeLeaderDGUsers = new ArrayList<>();
        List<String> ReviewCommitteeLeaderDGExcludeUsers = new ArrayList<>();

        String SystemEngineerDGName = "";
        List<String> SystemEngineerDGUsers = new ArrayList<>();
        List<String> SystemEngineerDGExcludeUsers = new ArrayList<>();

        String SystemLeaderDGName = "";
        List<String> SystemLeaderDGUsers = new ArrayList<>();
        List<String> SystemLeaderDGExcludeUsers = new ArrayList<>();

        String TestEngineerDGName = "";
        List<String> TestEngineerDGUsers = new ArrayList<>();
        List<String> TestEngineerDGExcludeUsers = new ArrayList<>();

        String TestLeaderDGName = "";
        List<String> TestLeaderDGUsers = new ArrayList<>();
        List<String> TestLeaderDGExcludeUsers = new ArrayList<>();


        for (String key : groupMemberRecord.keySet()) {
            if(key.equals("ASW Engineer DG")){
                ASWEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ASWEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                      e.printStackTrace();
                }
                for(String s : All_user){
                    if(ASWEngineerDGUsers.indexOf(s) == -1){
                        ASWEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("ASW Leader DG")){
                ASWLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ASWLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(ASWLeaderDGUsers.indexOf(s) == -1){
                        ASWLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("BSW Engineer DG")){
                BSWEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        BSWEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(BSWEngineerDGUsers.indexOf(s) == -1){
                        BSWEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("BSW Leader DG")){
                BSWLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        BSWLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(BSWLeaderDGUsers.indexOf(s) == -1){
                        BSWLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("CCB DG")){
                CCBDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        CCBDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(CCBDGUsers.indexOf(s) == -1){
                        CCBDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("CCB Leader DG")){
                CCBLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        CCBLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(CCBLeaderDGUsers.indexOf(s) == -1){
                        CCBLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Configuration Manager DG")){
                ConfigurationManagerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ConfigurationManagerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(ConfigurationManagerDGUsers.indexOf(s) == -1){
                        ConfigurationManagerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Director DG")){
                DirectorDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        DirectorDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(DirectorDGUsers.indexOf(s) == -1){
                        DirectorDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Functional Safety Engineer DG")){
                FunctionSafetyEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        FunctionSafetyEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(FunctionSafetyEngineerDGUsers.indexOf(s) == -1){
                        FunctionSafetyEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Functional Safety Leader DG")){
                FunctionSafetyLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        FunctionSafetyLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(FunctionSafetyLeaderDGUsers.indexOf(s) == -1){
                        FunctionSafetyLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Hardware Engineer DG")){
                HardwareEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        HardwareEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(HardwareEngineerDGUsers.indexOf(s) == -1){
                        HardwareEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Hardware Engineer Leader DG")){
                HardwareEnginnerLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        HardwareEnginnerLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(HardwareEnginnerLeaderDGUsers.indexOf(s) == -1){
                        HardwareEnginnerLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("PRC DG")){
                PRCDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        PRCDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(PRCDGUsers.indexOf(s) == -1){
                        PRCDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("PRC Group Leader DG")){
                PRCGroupLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        PRCGroupLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(PRCGroupLeaderDGUsers.indexOf(s) == -1){
                        PRCGroupLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Project Manager DG")){
                ProjectManagerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ProjectManagerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(ProjectManagerDGUsers.indexOf(s) == -1){
                        ProjectManagerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("QA DG")){
                QADGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        QADGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(QADGUsers.indexOf(s) == -1){
                        QADGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Review Committee DG")){
                ReviewCommitteeDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ReviewCommitteeDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(ReviewCommitteeDGUsers.indexOf(s) == -1){
                        ReviewCommitteeDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Review Committee Leader DG")){
                ReviewCommitteeLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        ReviewCommitteeLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(ReviewCommitteeLeaderDGUsers.indexOf(s) == -1){
                        ReviewCommitteeLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("System Engineer DG")){
                SystemEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        SystemEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(SystemEngineerDGUsers.indexOf(s) == -1){
                        SystemEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("System Leaders DG")){
                SystemLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        SystemLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(SystemLeaderDGUsers.indexOf(s) == -1){
                        SystemLeaderDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Test Engineer DG")){
                TestEngineerDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        TestEngineerDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(TestEngineerDGUsers.indexOf(s) == -1){
                        TestEngineerDGExcludeUsers.add(s);
                    }
                }
            }
            if(key.equals("Test Leader DG")){
                TestLeaderDGName = key;
                try {
                    if(groupMemberRecord.get(key).size() > 0){
                        TestLeaderDGUsers =  m.getAllUserIdAndName1(groupMemberRecord.get(key));
                    }
                } catch (APIException e) {
                    e.printStackTrace();
                }
                for(String s : All_user){
                    if(TestLeaderDGUsers.indexOf(s) == -1){
                        TestLeaderDGExcludeUsers.add(s);
                    }
                }
            }
        }

        ASWEngineerDG(ProjectName,ASWEngineerDGName,ASWEngineerDGUsers,ASWEngineerDGExcludeUsers,ASWEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,ASWLeaderDGName,ASWLeaderDGUsers,ASWLeaderDGExcludeUsers,ASWLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,BSWEngineerDGName,BSWEngineerDGUsers,BSWEngineerDGExcludeUsers,BSWEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,BSWLeaderDGName,BSWLeaderDGUsers,BSWLeaderDGExcludeUsers,BSWLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,CCBDGName,CCBDGUsers,CCBDGExcludeUsers,CCBDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,CCBLeaderDGName,CCBLeaderDGUsers,CCBLeaderDGExcludeUsers,CCBLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,ConfigurationManagerDGName,ConfigurationManagerDGUsers,ConfigurationManagerDGExcludeUsers,ConfigurationManagerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,DirectorDGName,DirectorDGUsers,DirectorDGExcludeUsers,DirectorDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,FunctionSafetyEngineerDGName,FunctionSafetyEngineerDGUsers,FunctionSafetyEngineerDGExcludeUsers,FunctionSafetyEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,FunctionSafetyLeaderDGName,FunctionSafetyLeaderDGUsers,FunctionSafetyLeaderDGExcludeUsers,FunctionSafetyLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,HardwareEngineerDGName,HardwareEngineerDGUsers,HardwareEngineerDGExcludeUsers,HardwareEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,HardwareEnginnerLeaderDGName,HardwareEnginnerLeaderDGUsers,HardwareEnginnerLeaderDGExcludeUsers,HardwareEnginnerLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,PRCDGName,PRCDGUsers,PRCDGExcludeUsers,PRCDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,PRCGroupLeaderDGName,PRCGroupLeaderDGUsers,PRCGroupLeaderDGExcludeUsers,PRCGroupLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,ProjectManagerDGName,ProjectManagerDGUsers,ProjectManagerDGExcludeUsers,ProjectManagerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,QADGName,QADGUsers,QADGExcludeUsers,QADGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,ReviewCommitteeDGName,ReviewCommitteeDGUsers,ReviewCommitteeDGExcludeUsers,ReviewCommitteeDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,ReviewCommitteeLeaderDGName,ReviewCommitteeLeaderDGUsers,ReviewCommitteeLeaderDGExcludeUsers,ReviewCommitteeLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,SystemEngineerDGName,SystemEngineerDGUsers,SystemEngineerDGExcludeUsers,SystemEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,SystemLeaderDGName,SystemLeaderDGUsers,SystemLeaderDGExcludeUsers,SystemLeaderDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,TestEngineerDGName,TestEngineerDGUsers,TestEngineerDGExcludeUsers,TestEngineerDGBox,new JButton(">"),new JButton("<"));
        ASWEngineerDG(ProjectName,TestLeaderDGName,TestLeaderDGUsers,TestLeaderDGExcludeUsers,TestLeaderDGBox,new JButton(">"),new JButton("<"));

    }


    public void ASWEngineerDG(String ProjectName,String name,List<String> users,List<String> AllUsers,Box dtBox,JButton jb1,JButton jb2){
        MKSCommand m = new MKSCommand();
        Box box7= Box.createVerticalBox();
        Box box71= Box.createVerticalBox();
        Box box72= Box.createVerticalBox();
        Box box73= Box.createVerticalBox();

        JPanel jplab = new JPanel();
        JTextField jl=new JTextField(name);
        jl.setEditable(false);  //不可编辑
        jl.setBorder(null);  //不显示边框
        jl.setFont(new Font("宋体",Font.PLAIN,12));
        jl.setHorizontalAlignment(JTextField.LEFT);
//        jplab.add(jl);
//        jplab.setPreferredSize(new Dimension(230, 120));
        box7.add(jl);

        Box allUserbox= Box.createVerticalBox();
        for(String str : AllUsers){
            JCheckBox chkbox1=new JCheckBox(str);    //创建指定文本和状态的复选框
            allUserbox.add(chkbox1);
        }
        box71.add(allUserbox);
        JPanel jp = new JPanel();
        jp.setLayout(new BorderLayout());
        jp.setBorder(BorderFactory.createLoweredBevelBorder());
//        jp.add(box71);
        jp.add(box71,BorderLayout.WEST);
        JScrollPane js=new JScrollPane(jp);
        //分别设置水平和垂直滚动条自动出现
        js.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        js.setPreferredSize(new Dimension(210, 120));

//        JButton jb1 = new JButton(">");
//        JButton jb2 = new JButton("<");
        box73.add(jb1);
        box73.add(jb2);

        JPanel jp2 = new JPanel();
        jp2.setLayout(new BorderLayout());
        Box GUserbox= Box.createVerticalBox();
        for(String str : users){
            JCheckBox chkbox1=new JCheckBox(str);    //创建指定文本和状态的复选框
            GUserbox.add(chkbox1);
        }
        box72.add(GUserbox);
        jp2.setBorder(BorderFactory.createLoweredBevelBorder());
//        jp2.setPreferredSize(new Dimension(210, 120));
//        jp2.add(box72);
        jp2.add(box72,BorderLayout.WEST);
        JScrollPane js2=new JScrollPane(jp2);
        //分别设置水平和垂直滚动条自动出现
        js2.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        js2.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        js2.setPreferredSize(new Dimension(210, 120));

        jb1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                //获取选中的值
                List<String> l = new ArrayList<>();
                Component[]  jcbs = allUserbox.getComponents();
                for(Component component : jcbs){
                    JCheckBox jcb = (JCheckBox) component;//需要强制转换成jcheckbox
                    if(jcb.isSelected()) {
                        l.add(jcb.getText());
                    }
                }
                if(l.size() == 0){
                    return;
                }
                //组用户添加选中的值
                List<String> zyh = users;;
                for(String s : l){
                    zyh.add(s);
                }
                //全部用户减去选中的值
                List<String> qbyh = new ArrayList<>();
                for(String s : AllUsers){
                    if(l.indexOf(s) == -1){
                        qbyh.add(s);
                    }
                }

                try {
                    m.updateDynamicGroup(ProjectName,name,zyh);//后台处理
                    dtBox.removeAll();
                    ASWEngineerDG(ProjectName,name,zyh,qbyh,dtBox,new JButton(">"),new JButton("<"));//页面处理
                } catch (APIException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", 0);
                    System.exit(0); //关闭主程序
                    ex.printStackTrace();
                }
            }
        });
        jb2.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                List<String> l = new ArrayList<>();
                Component[]  jcbs = GUserbox.getComponents();//获取jp1里的全部组件(我们只在里面存放了jcheckbox)
                for(Component component : jcbs){
                    JCheckBox jcb = (JCheckBox) component;//需要强制转换成jcheckbox
                    if(jcb.isSelected()) {
                        l.add(jcb.getText());
                    }
                }
                if(l.size() == 0){
                    return;
                }
                //组用户减去选中的值
                List<String> zyh = new ArrayList<>();
                for(String s : users){
                    if(l.indexOf(s) == -1){
                        zyh.add(s);
                    }
                }

                //全部用户添加选中的值
                List<String> qbyh = AllUsers;
                for(String s : l){
                    qbyh.add(s);
                }
                try {
                    m.updateDynamicGroup(ProjectName,name,zyh);
                    dtBox.removeAll();
                    ASWEngineerDG(ProjectName,name,zyh,qbyh,dtBox,new JButton(">"),new JButton("<"));
                } catch (APIException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "错误", 0);
                    System.exit(0); //关闭主程序
                    ex.printStackTrace();
                }
            }
        });

        dtBox.add(box7);
        dtBox.add(js);
        dtBox.add(box73);
        dtBox.add(js2);
    }

//大于10的字体缩略
    public String textxz(String str,String id){
        String  r = "";
//        log.info("字符串长度："+str.length());
        if(str.length()>15){
            r = str.substring(0,15)  + "..." + "(" +id+ ")";
        }else {
            r = str + "(" +id+ ")" ;
        }
//        log.info("缩略后字符串："+r );
        return r;
    }

    //去掉...对比字符串
    public Boolean dzzfc(String oldstr,String newstr){
        String str = "";
        if(oldstr.indexOf("...") > -1){
            str =  oldstr.substring(0,oldstr.length()-3);
        }
        if(newstr.contains(str)){
            return true;
        }else {
            return false;
        }
    }

    public void initBtn(int index){
        tabUp2.removeAll();
        JPanel jp = new JPanel();
        if(index == 0){
            setTitle("Project动态组设置");
            JButton button = new JButton("Search");
            button.setFocusPainted(false);  //去掉按钮字体焦点框
            button.setPreferredSize(new Dimension(78,34));
            button.setLocation(120,0);
            button.setFont(new Font("宋体",Font.PLAIN,12));
            Listener1(button,1);
            jp.add(button);
            tabUp2.setLayout(new BorderLayout());
            tabUp2.add(jp,BorderLayout.EAST);
        }else {
            JButton button = new JButton("back");
            button.setFocusPainted(false);  //去掉按钮字体焦点框
            button.setPreferredSize(new Dimension(81,32));
            button.setFont(new Font("宋体",Font.PLAIN,12));
            Listener1(button,0);
            jp.add(button);
            tabUp2.setLayout(new BorderLayout());
            tabUp2.add(jp,BorderLayout.EAST);
        }

    }

}
