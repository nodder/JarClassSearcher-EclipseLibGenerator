package name.cdd.product.clzsearch.userlibmaker.gui.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import name.cdd.product.clzsearch.userlibmaker.UserLibMakerVersionConst;
import name.cdd.product.clzsearch.userlibmaker.gui.UserLibMakerPresenter;

import org.springframework.util.AntPathMatcher;

public class UserLibMakerFrm extends JFrame implements UserLibeMakerView
{
    private static final long serialVersionUID = 1L;
    
    private UserLibMakerPresenter presenter = new UserLibMakerPresenter(this);
    private File lastSavedLibFile = presenter.getLibFilePath().getAbsoluteFile();
    
    public UserLibMakerFrm()
    {
        jbInit();
    }

    private void jbInit()
    {
        this.setSize(600, 500);
        this.setTitle("Eclipse User Library Maker " + UserLibMakerVersionConst.VERSION);
        
        this.getContentPane().add(pnParent);
        pnParent.setLayout(bl);
        pnParent.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        
        pnParent.add(pnNorth, BorderLayout.NORTH);
        pnParent.add(pnCenter, BorderLayout.CENTER);
        pnParent.add(pnSouth, BorderLayout.SOUTH);
        
        pnNorth.setLayout(gbl);
        pnNorth.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(
            Color.white, new Color(148, 145, 140)), BorderFactory.createEmptyBorder(0, 0, 0, 0)), "Conditions"));
        pnNorth.add(lblJarRootPath, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(txtJarRootPath, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(btnPathBrowser, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(lblSourceFolder, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(txtSourceFolder, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(lblType, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(cmbType, new GridBagConstraints(1, 2, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(lblLibName, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.NONE, new Insets(6, 6, 6, 6), 0, 0));
        pnNorth.add(txtLibName, new GridBagConstraints(1, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST
            , GridBagConstraints.HORIZONTAL, new Insets(6, 6, 0, 6), 0, 0));
        pnNorth.add(pnNSouth, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.EAST
            , GridBagConstraints.NONE, new Insets(6, 6, 0, 6), 0, 0));
        
        pnNSouth.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -3));
        pnNSouth.setLayout(fl2);
        pnNSouth.add(btnAnalysis);
        
        pnCenter.setLayout(bl2);
        pnCenter.add(scrPnResult, BorderLayout.CENTER);
        
        scrPnResult.setBorder(new TitledBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(
            Color.white, new Color(148, 145, 140)), BorderFactory.createEmptyBorder(0, 0, 0, 0)), "Result"));
        scrPnResult.getViewport().add(txtResult, null);
        
        pnSouth.setBorder(BorderFactory.createEmptyBorder(3, 0, 0, -3));
        pnSouth.setLayout(fl);
        fl.setAlignment(FlowLayout.RIGHT);
        
        pnSouth.add(btnExport);
        pnSouth.add(btnClose);
        
        lblType.setText("Generating Template:");
        lblSourceFolder.setText("Source File Directory:");
        lblJarRootPath.setText("Jar File Root Directory:");
        lblLibName.setText("Output Libray Name:");
        txtJarRootPath.setEditable(true);
        txtResult.setEditable(false);
        txtResult.setAutoscrolls(true);
        txtResult.setWrapStyleWord(true);
        txtResult.setLineWrap(true);
        btnPathBrowser.setVisible(false);
        
        txtJarRootPath.setPreferredSize(new Dimension(32767, 22));
        txtJarRootPath.setMinimumSize(new Dimension(32767, 22));
        txtJarRootPath.setMinimumSize(new Dimension(32767, 22));
        
        txtLibName.setPreferredSize(new Dimension(32767, 22));
        txtLibName.setMinimumSize(new Dimension(32767, 22));
        txtLibName.setMinimumSize(new Dimension(32767, 22));
        
        cmbType.setPreferredSize(new Dimension(32767, 22));
        cmbType.setMinimumSize(new Dimension(32767, 22));
        cmbType.setMinimumSize(new Dimension(32767, 22));
        cmbType.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                cmbType_itemStateChanged(e);
            }
        });
        
        btnPathBrowser.setPreferredSize(new Dimension(90, 22));
        btnPathBrowser.setMinimumSize(new Dimension(90, 22));
        btnPathBrowser.setMaximumSize(new Dimension(90, 22));
        btnPathBrowser.setText("Browse...");
        btnPathBrowser.setMnemonic('B');
        
        btnExport.setPreferredSize(new Dimension(130, 22));
        btnExport.setMinimumSize(new Dimension(130, 22));
        btnExport.setMaximumSize(new Dimension(130, 22));
        btnExport.setText("Export to Library");
        btnExport.setMnemonic('E');
        btnExport.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnExport_actionPerformed(e);
            }
        });
        
        btnAnalysis.setPreferredSize(new Dimension(120, 22));
        btnAnalysis.setMinimumSize(new Dimension(120, 22));
        btnAnalysis.setMaximumSize(new Dimension(120, 22));
        btnAnalysis.setText("Start Analysis");
        btnAnalysis.setMnemonic('S');
        btnAnalysis.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                btnStart_actionPerformed(e);
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
        String genType = presenter.getGeneratingType();
        cmbType.removeAllItems();
        
        cmbType.addItem("uep4x_whole_src");
        cmbType.addItem("uep4x");
        cmbType.addItem("custom");
        cmbType.setSelectedItem(genType);
        
        this.btnExport.setEnabled(false);
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
    
    private void cmbType_itemStateChanged(ItemEvent e)
    {
        try
        {
            presenter.switchGeneratingType((String) this.cmbType.getSelectedItem());
        }
        catch(IOException e1)
        {
            JOptionPane.showMessageDialog(this, e1.getMessage());
        }
    }
    
    private void btnExport_actionPerformed(ActionEvent e)
    {
        File selectedFile = selectFileFromSaveDialog(lastSavedLibFile);
        
        if(null != selectedFile)
        {
            lastSavedLibFile = selectedFile;
            System.out.println(selectedFile.getAbsolutePath());
            try
            {
                presenter.exportLib(selectedFile, this.txtResult.getText());
                JOptionPane.showMessageDialog(this, "Operation complete.\nLibrary exported to " + selectedFile.getAbsolutePath());
            }
            catch(FileNotFoundException e1)
            {
                JOptionPane.showMessageDialog(this, e1.getMessage());
            }
        }
    }

    private File selectFileFromSaveDialog(File lastSelectedFile)
    {
        File selectedFile = lastSelectedFile;
        do
        {
            selectedFile = getFileFromSaveDialog(selectedFile);
        }
        while(selectedFile != null && selectedFile.exists() && !isOverwrite());
        
        return selectedFile;
    }

    private boolean isOverwrite()
    {
      if(JOptionPane.YES_OPTION == 
          JOptionPane.showConfirmDialog(null, "File exists, overwrite?", "File exists, overwrite?", JOptionPane.YES_NO_OPTION))
      {
          return true;
      }
     
      return false;
    }

    private File getFileFromSaveDialog(File lastSelectedFile)
    {
        File selectedFile = null;
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.userlibraries", "userlibraries");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(filter);
        jFileChooser.setSelectedFile(lastSelectedFile);
        int result = jFileChooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            selectedFile = jFileChooser.getSelectedFile();
            if(!selectedFile.getName().endsWith(".userlibraries"))
            {
                selectedFile = new File(selectedFile.getPath() + ".userlibraries");
            }
        }
        
        return selectedFile;
    }
    
    private void btnStart_actionPerformed(ActionEvent e)
    {//待重构
        if(checkParams() && checkSpecialParams())
        {
            try
            {
                presenter.analysis(txtSourceFolder.getText(), 
                                   txtJarRootPath.getText(), 
                                   (String) this.cmbType.getSelectedItem(),
                                   txtLibName.getText());
                this.btnExport.setEnabled(true);
            }
            catch(IOException e1)
            {
                JOptionPane.showMessageDialog(this, e1.getMessage());
            }
        }
    }

    private boolean checkSpecialParams()
    {
        try
        {
            presenter.checkSpecialParams(txtJarRootPath.getText(), txtSourceFolder.getText(), (String)cmbType.getSelectedItem());
            return true;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
            return false;
        }
    }

    private boolean checkParams()
    {
        AntPathMatcher matcher = new AntPathMatcher();
        
        if(this.txtJarRootPath.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Jar file root directory cannot be empty.");
            txtJarRootPath.grabFocus();
            return false;
        }
        
        if(!matcher.match("?:\\*", this.txtJarRootPath.getText()))
        {
            JOptionPane.showMessageDialog(this, "Jar file root directory is not an absolute path.");
            txtJarRootPath.grabFocus();
            return false;
        }
        
        if(!new File(this.txtJarRootPath.getText()).exists())
        {
            JOptionPane.showMessageDialog(this, "Jar file root directory deos not exist.");
            txtJarRootPath.grabFocus();
            return false;
        }
        
        if(this.txtSourceFolder.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Source file directory cannot be empty.");
            txtSourceFolder.grabFocus();
            return false;
        }
        
        if(!matcher.match("?:\\*", this.txtJarRootPath.getText()))
        {
            JOptionPane.showMessageDialog(this, "Source file directory is not an absolute path.");
            txtSourceFolder.grabFocus();
            return false;
        }
        
        if(!new File(this.txtSourceFolder.getText()).exists())
        {
            JOptionPane.showMessageDialog(this, "Source file directory deos not exist.");
            txtSourceFolder.grabFocus();
            return false;
        }
        
        if(null == (String) cmbType.getSelectedItem() || "".equals(((String) cmbType.getSelectedItem())))
        {
            JOptionPane.showMessageDialog(this, "Generating type cannot be empty.");
            cmbType.grabFocus();
            return false;
        }
        
        if(this.txtLibName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "output libray name cannot be empty.");
            txtLibName.grabFocus();
            return false;
        }
        
        if(this.txtLibName.getText().contains("\\") || this.txtLibName.getText().contains("/"))
        {
            JOptionPane.showMessageDialog(this, "Eclipse library name cannot contain character \\ or /.");
            txtLibName.grabFocus();
            return false;
        }
            
        return true;
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
    
    @Override
    public void presentLibContent(String libContent)
    {
        this.txtResult.setText(libContent);
    }
    
    @Override
    public void updateUI(String srcFolder, String jarDirectory, String libName, String resultStr)
    {
        txtJarRootPath.setText(jarDirectory);
        txtSourceFolder.setText(srcFolder);
        txtLibName.setText(libName);
//        txtResult.setText(resultStr);
    }
    
    private JPanel pnParent = new JPanel();
    private BorderLayout bl = new BorderLayout();
    private BorderLayout bl2 = new BorderLayout();
    private FlowLayout fl = new FlowLayout();
    private JPanel pnNorth = new JPanel();
    private JPanel pnCenter = new JPanel();
    private JPanel pnSouth = new JPanel();
    
    private GridBagLayout gbl = new GridBagLayout();
    private JLabel lblJarRootPath = new JLabel();
    private JLabel lblSourceFolder = new JLabel();
    private JTextField txtJarRootPath = new JTextField();
    private JTextField txtSourceFolder = new JTextField();
    private JButton btnPathBrowser = new JButton();
    private JLabel lblType = new JLabel();
    private JComboBox cmbType = new JComboBox();
    private JLabel lblLibName = new JLabel();
    private JTextField txtLibName = new JTextField();
    
    private JPanel pnNSouth = new JPanel();
    private FlowLayout fl2 = new FlowLayout();
    private JButton btnAnalysis = new JButton();
    
    private JButton btnExport = new JButton();
    private JButton btnClose = new JButton();
    
    private JTextArea txtResult = new JTextArea();
    private JScrollPane scrPnResult = new JScrollPane();

}
