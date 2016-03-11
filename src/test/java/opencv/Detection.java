package opencv;


import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JSlider;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

public class Detection extends javax.swing.JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6436735716456725095L;
	

	/**
     * Creates new form Detection
     */
    public Detection() {
        initComponents();
        setVisible(true);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                loadImage();
            }
        };
        Thread t = new Thread(r);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        imagePanel = new javax.swing.JPanel();
        hsvPanel = new javax.swing.JPanel();
        thresholdPanel = new javax.swing.JPanel();
        optionPanel = new javax.swing.JPanel();
        hMinSlider = new javax.swing.JSlider();
        hMaxSlider = new javax.swing.JSlider();
        sMinSlider = new javax.swing.JSlider();
        sMaxSlider = new javax.swing.JSlider();
        vMinSlider = new javax.swing.JSlider();
        vMaxSlider = new javax.swing.JSlider();
        hMinLabel = new javax.swing.JLabel();
        hMaxLabel = new javax.swing.JLabel();
        sMinLabel = new javax.swing.JLabel();
        sMaxLabel = new javax.swing.JLabel();
        vMinLabel = new javax.swing.JLabel();
        vMaxLabel = new javax.swing.JLabel();
        hMinValue = new javax.swing.JLabel();
        hMaxValue = new javax.swing.JLabel();
        sMinValue = new javax.swing.JLabel();
        sMaxValue = new javax.swing.JLabel();
        vMinValue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout imagePanelLayout = new javax.swing.GroupLayout(imagePanel);
        imagePanel.setLayout(imagePanelLayout);
        imagePanelLayout.setHorizontalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        imagePanelLayout.setVerticalGroup(
            imagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout hsvPanelLayout = new javax.swing.GroupLayout(hsvPanel);
        hsvPanel.setLayout(hsvPanelLayout);
        hsvPanelLayout.setHorizontalGroup(
            hsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 682, Short.MAX_VALUE)
        );
        hsvPanelLayout.setVerticalGroup(
            hsvPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );

        thresholdPanel.setPreferredSize(new java.awt.Dimension(470, 227));

        javax.swing.GroupLayout thresholdPanelLayout = new javax.swing.GroupLayout(thresholdPanel);
        thresholdPanel.setLayout(thresholdPanelLayout);
        thresholdPanelLayout.setHorizontalGroup(
            thresholdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        thresholdPanelLayout.setVerticalGroup(
            thresholdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        hMinSlider.setMaximum(255);
        hMinSlider.setValue(0);
        hMinSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hMinSliderStateChanged(evt);
            }
        });

        hMaxSlider.setMaximum(255);
        hMaxSlider.setValue(255);
        hMaxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hMaxSliderStateChanged(evt);
            }
        });

        sMinSlider.setMaximum(255);
        sMinSlider.setValue(0);
        sMinSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sMinSliderStateChanged(evt);
            }
        });

        sMaxSlider.setMaximum(255);
        sMaxSlider.setValue(255);
        sMaxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sMaxSliderStateChanged(evt);
            }
        });

        vMinSlider.setMaximum(255);
        vMinSlider.setValue(0);
        vMinSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vMinSliderStateChanged(evt);
            }
        });

        vMaxSlider.setMaximum(255);
        vMaxSlider.setValue(255);
        vMaxSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                vMaxSliderStateChanged(evt);
            }
        });

        hMinLabel.setText("H_MIN");

        hMaxLabel.setText("H_MAX");

        sMinLabel.setText("S_MIN");

        sMaxLabel.setText("S_MAX");

        vMinLabel.setText("V_MIN");

        vMaxLabel.setText("V_MAX");

        hMinValue.setText("0");

        hMaxValue.setText("255");

        sMinValue.setText("0");

        sMaxValue.setText("255");

        vMinValue.setText("0");

        jLabel6.setText("255");

        javax.swing.GroupLayout optionPanelLayout = new javax.swing.GroupLayout(optionPanel);
        optionPanel.setLayout(optionPanelLayout);
        optionPanelLayout.setHorizontalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionPanelLayout.createSequentialGroup()
                .addContainerGap(300, Short.MAX_VALUE)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(vMaxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(vMinLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(sMaxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(sMinLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(hMaxLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(optionPanelLayout.createSequentialGroup()
                        .addComponent(hMinLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hMinValue)
                    .addComponent(hMaxValue)
                    .addComponent(sMinValue)
                    .addComponent(sMaxValue)
                    .addComponent(vMinValue)
                    .addComponent(jLabel6))
                .addContainerGap())
        );
        optionPanelLayout.setVerticalGroup(
            optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hMinLabel)
                    .addComponent(hMinValue))
                .addGap(33, 33, 33)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hMaxLabel)
                    .addComponent(hMaxValue))
                .addGap(33, 33, 33)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sMinLabel)
                    .addComponent(sMinValue))
                .addGap(33, 33, 33)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sMaxLabel)
                    .addComponent(sMaxValue))
                .addGap(33, 33, 33)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vMinSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vMinLabel)
                    .addComponent(vMinValue))
                .addGap(33, 33, 33)
                .addGroup(optionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vMaxSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vMaxLabel)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(hsvPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(optionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                    .addComponent(thresholdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imagePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hsvPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thresholdPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void hMinSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        H_MIN = slider.getValue();
        hMinValue.setText(H_MIN + "");
        createHsvAndThresholdImg();
    }                                       

    private void hMaxSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        H_MAX = slider.getValue();
        hMaxValue.setText(H_MAX + "");
        createHsvAndThresholdImg();
    }                                       

    private void sMinSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        S_MIN = slider.getValue();
        sMinValue.setText(S_MIN + "");
        createHsvAndThresholdImg();
    }                                       

    private void sMaxSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        S_MAX = slider.getValue();
        sMaxValue.setText(S_MAX + "");
        createHsvAndThresholdImg();
    }                                       

    private void vMinSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        V_MIN = slider.getValue();
        vMinValue.setText(V_MIN + "");
        createHsvAndThresholdImg();
    }                                       

    private void vMaxSliderStateChanged(javax.swing.event.ChangeEvent evt) {                                        
        JSlider slider = (JSlider) evt.getSource();
        V_MAX = slider.getValue();
        jLabel6.setText(V_MAX + "");
        createHsvAndThresholdImg();
    }                                       

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Detection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Detection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Detection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Detection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Detection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel hMaxLabel;
    private javax.swing.JSlider hMaxSlider;
    private javax.swing.JLabel hMaxValue;
    private javax.swing.JLabel hMinLabel;
    private javax.swing.JSlider hMinSlider;
    private javax.swing.JLabel hMinValue;
    private javax.swing.JPanel hsvPanel;
    private javax.swing.JPanel imagePanel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel optionPanel;
    private javax.swing.JLabel sMaxLabel;
    private javax.swing.JSlider sMaxSlider;
    private javax.swing.JLabel sMaxValue;
    private javax.swing.JLabel sMinLabel;
    private javax.swing.JSlider sMinSlider;
    private javax.swing.JLabel sMinValue;
    private javax.swing.JPanel thresholdPanel;
    private javax.swing.JLabel vMaxLabel;
    private javax.swing.JSlider vMaxSlider;
    private javax.swing.JLabel vMinLabel;
    private javax.swing.JSlider vMinSlider;
    private javax.swing.JLabel vMinValue;
    // End of variables declaration                   

    private int H_MIN = 100, H_MAX = 160;
    private int S_MIN = 50, S_MAX = 100;
    private int V_MIN = 20, V_MAX = 60;
    
    private boolean isStart = true;
    
    private Mat cameraFeed;
    private final Mat hsv = new Mat(new Size(640, 480), 8, new Scalar(3,0,0,0)) ;
    private final Mat threshold = new Mat(new Size(640, 480), 8, new Scalar(1,0,0,0)); 
    
    private void loadImage() {
        try {
            if(isStart) {
                cameraFeed = bufferedImageToMat(ImageIO.read(new File("Images/Camera1.png")));
           
                Imgproc.cvtColor(cameraFeed, hsv, Imgproc.COLOR_BGR2HSV);
                Core.inRange(hsv, new Scalar(H_MIN, S_MIN, V_MIN, 0), new Scalar(H_MAX, S_MAX, V_MAX, 0), threshold);
                //Imgproc.smooth(threshold, threshold, Imgproc.CV_MEDIAN, 5, 5, 0, 0);

                int posX = 0;
                int posY = 0;

                Moments moments = Imgproc.moments(threshold, true);
                double mom10 = moments.m10;
                double mom01 = moments.m01;
                double area = moments.m00;
                posX = (int) (mom10 / area);
                posY = (int) (mom01 / area);
                // only if its a valid position
                if (posX > 0 && posY > 0) {
                    Imgproc.line(cameraFeed, new Point(posX - 5, posY), new Point(posX + 5, posY),
                            new Scalar(H_MIN, S_MIN, V_MIN, 0), 2, 8, 0);
                    Imgproc.line(cameraFeed, new Point(posX, posY - 5), new Point(posX, posY + 5),
                            new Scalar(H_MAX, S_MAX, V_MAX, 0), 2, 8, 0);
                }

                paint(cameraFeed);
                isStart = false;
            }        
            paintPanel(hsv, hsvPanel);
            paintPanel(threshold, thresholdPanel);
        } catch (IOException ex) {
            Logger.getLogger(Detection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void createHsvAndThresholdImg() {
    	try {
    		cameraFeed = bufferedImageToMat(ImageIO.read(new File("Images/0.png")));

    		Imgproc.cvtColor(cameraFeed, hsv, Imgproc.COLOR_BGR2HSV);
    		Core.inRange(hsv, new Scalar(H_MIN, S_MIN, V_MIN, 0), new Scalar(H_MAX, S_MAX, V_MAX, 0), threshold);
    		//Imgproc.smooth(threshold, threshold, Imgproc.CV_MEDIAN, 5, 5, 0, 0);

    		int posX = 0;
    		int posY = 0;

    		Moments moments = Imgproc.moments(threshold, true);
    		double mom10 = moments.m10;
    		double mom01 = moments.m01;
    		double area = moments.m00;
    		posX = (int) (mom10 / area);
    		posY = (int) (mom01 / area);
    		// only if its a valid position
    		if (posX > 0 && posY > 0) {
    		//	Imgproc.fitLine
    			Imgproc.line
    			(cameraFeed, new Point(posX - 5, posY), new Point(posX + 5, posY),
    					new Scalar(H_MIN, S_MIN, V_MIN, 0), 2, 8, 0);
    			Imgproc.line(cameraFeed, new Point(posX, posY - 5), new Point(posX, posY + 5),
    					new Scalar(H_MAX, S_MAX, V_MAX, 0), 2, 8, 0);
    		}

    		paint(cameraFeed);
    		paintPanel(hsv, hsvPanel);
    		paintPanel(threshold, thresholdPanel);
    	} catch (IOException ex) {
            Logger.getLogger(Detection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
    
    public static BufferedImage mat2Img(Mat matrix) {
        int cols = matrix.cols();  
        int rows = matrix.rows();  
        int elemSize = (int)matrix.elemSize();  
        byte[] data = new byte[cols * rows * elemSize];  
        int type;  
        matrix.get(0, 0, data);  
        switch (matrix.channels()) {  
        case 1:  
                type = BufferedImage.TYPE_BYTE_GRAY;  
                break;  
        case 3:  
                type = BufferedImage.TYPE_3BYTE_BGR;  
                // bgr to rgb  
                byte b;  
                for(int i=0; i<data.length; i=i+3) {  
                        b = data[i];  
                        data[i] = data[i+2];  
                        data[i+2] = b;  
                }  
                break;  
        default:  
                return null;  
        }  
        BufferedImage image2 = new BufferedImage(cols, rows, type);  
        image2.getRaster().setDataElements(0, 0, cols, rows, data);  
        return image2;
    } 
    
    private void paint(Mat cameraFeed) {
        imagePanel.getGraphics().drawImage(mat2Img(cameraFeed), 0, 0, null);
    }

    private void paintPanel(Mat img, JPanel panel) {
        panel.getGraphics().drawImage(mat2Img(img), 0, 0, panel.getWidth(), panel.getHeight(), null);
    }

}