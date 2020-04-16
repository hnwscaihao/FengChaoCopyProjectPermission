package com.gw;

import com.gw.service.ImportService;
import com.gw.ui.ImportGUI;
import com.gw.util.MKSCommand;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gw.util.MKSCommand.getAllProject;
import static com.gw.util.MKSCommand.initMksCommand;

public class App {

    private static final Log log = LogFactory.getLog(ImportService.class);

    public static void main(String[] args) {
        try {

//            String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";//设置windows的风格
//            UIManager.setLookAndFeel(lookAndFeel);
            ImportGUI imp = new ImportGUI();
            imp.ImportGUI();
            imp.glasspane.start();//开始动画加载效果
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//设置与本机适配的swing样式
            MKSCommand m = new MKSCommand();
            initMksCommand();//初始化MKSCommand中的参数，并获得连接
            List<Map<String, String>> l = getAllProject(Arrays.asList("id", "name"));//获取到当前选中的id添加进集合Ids集合
            if (l.size() > 0) {
                log.info("连接成功！");
                for (Map<String, String> s : l) {
                    String casename = s.get("name");
//                    String casename = imp.textxz(s.get("name"), s.get("id"));
                    imp.cmb.addItem(casename);
                }
                imp.glasspane.stop();
            } else {
                log.info("连接失败！");
                JOptionPane.showMessageDialog(null, "连接失败...", "错误", 0);
                System.exit(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", 0);
            System.exit(0); //关闭主程序
        }
    }

}
