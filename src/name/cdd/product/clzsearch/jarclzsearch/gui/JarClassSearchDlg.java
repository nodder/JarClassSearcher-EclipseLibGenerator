package name.cdd.product.clzsearch.jarclzsearch.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import name.cdd.product.clzsearch.business.SearchCaller;
import name.cdd.product.clzsearch.business.SearcherAsynchronous;

public class JarClassSearchDlg extends JFrame implements SearchCaller
{
    private static final long serialVersionUID = 1L;

    private SearcherAsynchronous searcher = new SearcherAsynchronous(this);
    private ConditionHolder conditionHolder = new ConditionHolder();

    private int count;

    public JarClassSearchDlg()
    {
        jbInit();
    }

    private void jbInit()
    {
        this.setSize(550, 420);
        this.setTitle("Jar Class Searcher " + VersionConst.VERSION);
        this.setIconImage(this.getToolkit().getImage("res\\icon.gif"));
        
        this.getContentPane().add(pnParent);
        pnParent.setLayout(bl);
        pnParent.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        
        pnParent.add(pnNorth, BorderLayout.NORTH);
        pnParent.add(pnCenter, BorderLayout.CENTER);
        pnParent.add(pnSouth, BorderLayout.SOUTH);
        
        pnNorth.setLayout(gbl);
        pnNorth.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(
            Color.white, new Color(148, 145, 140)), BorderFactory.createEmptyBorder(0, 0, 0, 0)), "Conditions"));
        pnNorth.add(lblPath, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(cmbPath, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(btnPathBrowser, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(lblClassName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 6, 6), 0, 0));
        pnNorth.add(txtClassName, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 6, 6), 0, 0));
        pnNorth.add(btnSearch, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 6, 6), 0, 0));
        
        pnCenter.setLayout(bl2);
        pnCenter.add(scrPnResult, BorderLayout.CENTER);
        
        scrPnResult.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(
            Color.white, new Color(148, 145, 140)), BorderFactory.createEmptyBorder(0, 0, 0, 0)), "Result"));
        scrPnResult.getViewport().add(txtResult, null);
        
        pnSouth.setLayout(blSouth);
        pnSouth.add(pnSouthWest, BorderLayout.WEST);
        pnSouth.add(pnSouthEast, BorderLayout.EAST);
        
        pnSouthWest.setLayout(flWest);
        flEast.setAlignment(FlowLayout.LEFT);
        
        pnSouthWest.add(lblTips);
        
        pnSouthEast.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, -3));
        pnSouthEast.setLayout(flEast);
        flEast.setAlignment(FlowLayout.RIGHT);
        flEast.setHgap(6);
        flEast.setVgap(6);
        
        pnSouthEast.add(btnClear);
        pnSouthEast.add(btnClose);
        
        lblClassName.setText("Class Name:");
        lblPath.setText("Jar File Path:");
        cmbPath.setEditable(true);
        txtResult.setEditable(false);
        txtResult.setAutoscrolls(true);
        txtResult.setWrapStyleWord(true);
        txtResult.setLineWrap(true);
        btnClear.setVisible(false);
        
        cmbPath.setPreferredSize(new Dimension(32767, 22));
        cmbPath.setMinimumSize(new Dimension(32767, 22));
        cmbPath.setMinimumSize(new Dimension(32767, 22));
        
        btnPathBrowser.setPreferredSize(new Dimension(90, 22));
        btnPathBrowser.setMinimumSize(new Dimension(90, 22));
        btnPathBrowser.setMaximumSize(new Dimension(90, 22));
        btnPathBrowser.setText("Browse...");
        btnPathBrowser.setMnemonic('B');
        btnPathBrowser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnPathBrowser_actionPerformed(e);
            }
        });
        
        
        btnSearch.setPreferredSize(new Dimension(90, 22));
        btnSearch.setMinimumSize(new Dimension(90, 22));
        btnSearch.setMaximumSize(new Dimension(90, 22));
        btnSearch.setText("Search");
        btnSearch.setMnemonic('S');
        btnSearch.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnSearch_actionPerformed(e);
            }
        });
        
        btnClear.setPreferredSize(new Dimension(75, 22));
        btnClear.setText("Clear");
        btnClear.setMnemonic('L');
        btnClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnClear_actionPerformed(e);
            }
        });
        
        btnClose.setPreferredSize(new Dimension(75, 22));
        btnClose.setText("Close");
        btnClose.setMnemonic('C');
        btnClose.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnClose_actionPerformed(e);
            }
        });
        
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(WindowEvent e)//关闭窗口时退出javaw
            {
                System.exit(1);
            }
        });
    }
    
    public void init() 
    {
        this.txtResult.setText(HelpContent.getContent());
        
        try
        {
            conditionHolder.load();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Load params.obj failed.\n" +  e.getMessage());
        }
        
        this.txtClassName.setText(this.conditionHolder.getClassName());
        this.cmbPath.setSelectedItem(this.conditionHolder.getPath());
        PathListMaint.getInstance().initList(this.conditionHolder.getPathList());
        
        List<String> pathList = PathListMaint.getInstance().getPathList();
        for(String path : pathList)
        {
            this.cmbPath.addItem(path);
        }
        
        centerWindow(this);
    }
    
    private static void centerWindow(Window window)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = window.getWidth();
        int windowHeight = window.getHeight();
        window.setLocation((screenSize.width - windowWidth) / 2, (screenSize.height - windowHeight) / 2);
        
        window.setVisible(true);
    }
    
    private void btnPathBrowser_actionPerformed(ActionEvent e)
    {
        String path = (String) this.cmbPath.getSelectedItem();
        File filePath = "".equals(path) ? null : new File(path);
            
        filePath = selectDirectoryFromDialog(filePath);
        
        updateCmbPath(filePath);
    }

    private void updateCmbPath(File filePath)
    {
        if(filePath != null)
        {
            this.cmbPath.setSelectedItem(filePath.getAbsolutePath());
        }
    }
    
    private File selectDirectoryFromDialog(File selectedFile)
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jFileChooser.setSelectedFile(selectedFile);
        int result = jFileChooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = jFileChooser.getSelectedFile();
        }
        
        return selectedFile;
    }
    
    private void btnSearch_actionPerformed(ActionEvent e)
    {
        if(this.txtClassName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Please input class name");
            txtClassName.grabFocus();
            return;
        }
        
        if("".equals((String) cmbPath.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(this, "Please input jar file path");
            cmbPath.grabFocus();
            return;
        }
        
        try
        {
            enableUI(false);
            this.txtResult.setText("");
            this.count = 0;
            searcher.startSearch(this.txtClassName.getText(), new File((String) this.cmbPath.getSelectedItem()));
        }
        catch(IOException e1)
        {
            JOptionPane.showMessageDialog(this, e1.getMessage());
            enableUI(true);
        }
    }
    
    private void btnClear_actionPerformed(ActionEvent e)
    {
        this.txtResult.setText("");
    }
    
    private void btnClose_actionPerformed(ActionEvent e)
    {
        int option = JOptionPane.showConfirmDialog(this, "确定关闭吗？", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.NO_OPTION)
        {
            return;
        }
        
        this.dispose();
    }
    
    protected void processWindowEvent(WindowEvent e)
    {
        if(e.getID() == WindowEvent.WINDOW_CLOSING)
        {
            btnClose_actionPerformed(null);
        }
    }
    
    private void enableUI(boolean en)
    {
        this.txtClassName.setEnabled(en);
//        this.txtResult.setEnabled(en);
        this.cmbPath.setEnabled(en);
        this.btnClear.setEnabled(en);
        this.btnPathBrowser.setEnabled(en);
        this.btnSearch.setEnabled(en);
    }
    
    @Override
    public void onFindOneResult(File file)
    {
        System.out.println(file.getAbsolutePath());
        this.txtResult.append("No." + (++count) + "\n" + file.getAbsolutePath() 
            + "\n--------------------------------------------------\n\n");

        JScrollBar verticalBar = this.scrPnResult.getVerticalScrollBar();
        verticalBar.setValue(verticalBar.getHeight());        
    }
    
    @Override
    public void onFinish()
    {
        updatePathList();
        onTips("Saving parameters...");
        saveCondition();
        enableUI(true);
        onTips("");
    }

    private void updatePathList()
    {
        PathListMaint.getInstance().addNewPath((String) cmbPath.getSelectedItem());
        List<String> pathList = PathListMaint.getInstance().getPathList();
        
        cmbPath.removeAllItems();
        for(String path : pathList)
        {
            cmbPath.addItem(path);
        }
    }
    
    private void saveCondition()
    {
        conditionHolder.saveClassName(this.txtClassName.getText());
        conditionHolder.savePath((String) this.cmbPath.getSelectedItem());
        conditionHolder.savePathList(getItemList(cmbPath));
        
        try
        {
            conditionHolder.commit();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Save parameters failed.\n" + e.getMessage());
        }
    }

    private ArrayList<String> getItemList(JComboBox cmb)
    {
        ArrayList<String> itemList = new  ArrayList<String>();
        
        for(int i = 0; i < cmb.getItemCount(); i++)
        {
            itemList.add((String) cmb.getItemAt(i));
        }
        
        return itemList;
    }

    @Override
    public void onFinishWithFindNothing()
    {
        this.txtResult.setText("No match is found.");
        enableUI(true);
        onTips("");
    }

    @Override
    public void onTips(String tips)
    {
        lblTips.setText(tips);
    }
    
    private JPanel pnParent = new JPanel();
    private BorderLayout bl = new BorderLayout();
    private BorderLayout bl2 = new BorderLayout();
    private BorderLayout blSouth = new BorderLayout();
    private FlowLayout flEast = new FlowLayout();
    private FlowLayout flWest = new FlowLayout();
    private JPanel pnNorth = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel pnSouth = new JPanel();
    private JPanel pnSouthWest = new JPanel();
    private JPanel pnSouthEast = new JPanel();
    
    private GridBagLayout gbl = new GridBagLayout();
    private JLabel lblPath = new JLabel();
    private JLabel lblClassName = new JLabel();
    private JComboBox cmbPath = new JComboBox();
    private JTextField txtClassName = new JTextField();
    private JButton btnPathBrowser = new JButton();
    private JButton btnSearch = new JButton();
    private JButton btnClear = new JButton();
    private JButton btnClose = new JButton();
    private JLabel lblTips = new JLabel();
    
    private JTextArea txtResult = new JTextArea();
    private JScrollPane scrPnResult = new JScrollPane();
    
    public static void main(String[] args)
    {
        JarClassSearchDlg dlg = new JarClassSearchDlg();
        dlg.init();
    }
}
