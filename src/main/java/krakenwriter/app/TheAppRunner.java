package krakenwriter.app;

import java.io.File;

import krakenwriter.backend.ComputerFile;

public class TheAppRunner {

    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println(ex);
        } catch (InstantiationException ex) {
            System.err.println(ex);
        } catch (IllegalAccessException ex) {
            System.err.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.err.println(ex);
        }

        File projectsPath = new File(ComputerFile.PROJECT_PATH);
        if (!projectsPath.exists()) {
        	projectsPath.mkdirs();
        }
        
        java.awt.EventQueue.invokeLater(new TheApp());
    }
}
