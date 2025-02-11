package game.ui;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener , ActionListener {

    // 记录图片路径
    public static String[] path = new String[]{
            "Image/anime1/2025111152236577屏幕截图 2025-02-09 212712 (1)_",
            "Image/anime2/2025111152236577屏幕截图 2025-02-09 212712 (1)_",
            "Image/anime3/2025111152236577屏幕截图 2025-02-09 212712 (1)_",
    };
    int step = 0 ; // 记录步数
    int PictureNum = 0 ; // 记录随机图片数
    int[][] win = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0},
    };
    int[][] randomNum = new int[4][4];
    int x = 0, y = 0; // 记录空白方块坐标

    // 创建条目 -- 接在JMenu成员内部类
    JMenuItem changePicture = new JMenuItem("更换拼图");
    JMenuItem reLoad = new JMenuItem("重新游戏");
    JMenuItem reLogin = new JMenuItem("重新登录");
    JMenuItem exitGame = new JMenuItem("退出游戏");


    JMenuItem storage = new JMenuItem("Github仓库");
    public GameJFrame() {
        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initImage() {
        this.getContentPane().removeAll();
        if(victory()) {
            JLabel victoryP = new JLabel(new ImageIcon("Image/a8ccd60f0c16104e779aaabf3f7baad21435113223.png"));
            victoryP.setBounds(375, 280, 162, 162);
            victoryP.setBorder(new BevelBorder(BevelBorder.RAISED));
            this.getContentPane().add(victoryP);
            //return;
        }

        // 添加字体
        JLabel cntStep = new JLabel("当前步数为："+step);
        Font font = new Font("STEP", Font.BOLD, 30);
        cntStep.setBounds(10, 5, 250, 250);
        cntStep.setFont(font);
        add(cntStep);
        this.getContentPane().add(cntStep);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                // 创建icon
                // 创建JLabel容器
                JLabel label = new JLabel(new ImageIcon( path[PictureNum]+randomNum[i][j] + ".png"));
                // 指定图片位置
                label.setBounds(200 * j + 80, 150 * i + 150, 200, 150);
                // 为图片增加边框
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 添加到界面
                this.getContentPane().add(label);
            }
        }

        JLabel background = new JLabel(new ImageIcon("background/屏幕截图 2025-02-11 163038 (1).png"));
        background.setBounds(0, -15, 960, 1024);
        this.getContentPane().add(background);

        this.getContentPane().repaint();
    }

    private void initData() {
        Random r = new Random();
        int[] temp = new int[16];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = i;
        }
        for (int i = 0; i < temp.length; i++) {
            int index = r.nextInt(temp.length);
            int tempNum = temp[i];
            temp[i] = temp[index];
            temp[index] = tempNum;
        }

        // 二维数组进行存储
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0) {
                x = i/4;
                y = i%4;
            }
            randomNum[i / 4][i % 4] = temp[i];
        }
    }

    private void initJMenuBar() {
        // 设置JMenuBar功能菜单
        JMenuBar jMenuBar = new JMenuBar();

        //创建目录
        JMenu jMenuFun = new JMenu("功能");
        JMenu jMenuAbout = new JMenu("关于游戏");



        // 添加条目
        jMenuFun.add(changePicture);
        jMenuFun.add(reLoad);
        jMenuFun.add(reLogin);
        jMenuFun.add(exitGame);


        jMenuAbout.add(storage);

        // 绑定事件 - - - 功能选项监听
        reLoad.addActionListener(this);
        reLogin.addActionListener(this);
        exitGame.addActionListener(this);
        changePicture.addActionListener(this);
        storage.addActionListener(this);
        // 添加进Bar
        jMenuBar.add(jMenuFun);
        jMenuBar.add(jMenuAbout);

        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        // 设置宽高
        this.setSize(960, 800);
        // 标题
        this.setTitle("Puzzle Game  -- version 1.0");
        // 顶层弹出
        this.setAlwaysOnTop(true);
        // 位置居中
        this.setLocationRelativeTo(null);
        // 取消默认放置
        this.setLayout(null);
        // 增加监听事件
        this.addKeyListener(this);

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            this.getContentPane().removeAll();

            JLabel allPicture = new JLabel(new ImageIcon("Image/anime"+(PictureNum+1)+"/all.png"));
            allPicture.setBounds(80, -20, 800, 1024);
            this.getContentPane().add(allPicture);

            JLabel background = new JLabel(new ImageIcon("background/屏幕截图 2025-02-11 163038 (1).png"));
            background.setBounds(0, -15, 960, 1024);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()) return;
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
           if (x == 3) return;
           randomNum[x][y] = randomNum[x + 1][y];
           randomNum[x + 1][y] = 0;
           x++;

           step++;
            initImage();
            System.out.println(e.getKeyChar());
        } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            if (x == 0) return;
            randomNum[x][y] = randomNum[x - 1][y];
            randomNum[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
            if (y == 3) return;
            randomNum[x][y] = randomNum[x][y+1];
            randomNum[x][y+1] = 0;
            y++;
            step++;
            initImage();
            } else if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT) {
            if (y == 0) return;
            randomNum[x][y] = randomNum[x][y-1];
            randomNum[x][y-1] = 0;
            y--;
            step++;
            initImage();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            initImage();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            randomNum = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0},
            };
            initImage();
        }
    }

    public boolean victory(){
        for (int i = 0; i < randomNum.length; i++) {
            for (int j = 0; j < randomNum.length; j++) {
                if (randomNum[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      Object obj = e.getSource();
      if (obj == changePicture) {
          Random r = new Random(path.length);
          PictureNum = r.nextInt(path.length-1);
          initImage();
          System.out.println("更换图片");
      } else if (obj == reLoad) {
          // 计数器清零
          step = 0;
          // 数据初始化
          initData();
          // 重新加载图片
          initImage();
      }else if(obj == reLogin) {
          System.out.println("reLogin");
      } else if (obj == exitGame) {
          System.exit(0);
      }else if (obj == storage) {

          JDialog jd = new JDialog();
          jd.setLocationRelativeTo(null);
          JLabel str = new JLabel(new ImageIcon("Image/a8ccd60f0c16104e779aaabf3f7baad21435113223.png"));
          JLabel text = new JLabel("   这是我的Github仓库:https://github.com/DralTempest?tab=repositories");
          str.setBounds(150, 80, 162, 162);
          str.setBorder(new BevelBorder(BevelBorder.RAISED));
          Font font = new Font("GIT", Font.BOLD, 12);
          text.setBounds(10, 20, 100, 100);
          text.setFont(font);
          add(text);
          jd.getContentPane().add(str);
          jd.getContentPane().add(text);
          jd.setSize(480, 560);
          jd.setAlwaysOnTop(true);
          jd.setLocationRelativeTo(null);
          jd.setVisible(true);
      }
    }
}
