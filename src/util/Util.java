/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.InputMismatchException;

import static java.lang.System.err;

/**
 *
 * @author Egidius Mysliwietz
 */
public class Util {

    //Destroying OOP at its finest...
    private static Util u = new Util();

    public static class MediaInderfaces {
        public static class SoundInderface {

            static Clip clip;

            public static void playSound(String name) {
                try {
                    URL url = u.getClass().getResource("/assets/music/"+ name +".wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioIn);
                    clip.start();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

            public static void loopSound(String name) {
                try {
                    URL url = u.getClass().getResource("/assets/music/"+ name +".wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Util.MediaInderfaces.SoundInderface.clip = AudioSystem.getClip();
                    Util.MediaInderfaces.SoundInderface.clip.open(audioIn);
                    Util.MediaInderfaces.SoundInderface.clip.loop(-1);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

            public static void loopSound(String name, int times) {
                try {
                    URL url = u.getClass().getResource("/assets/music/"+ name +".wav");
                    AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
                    Util.MediaInderfaces.SoundInderface.clip = AudioSystem.getClip();
                    Util.MediaInderfaces.SoundInderface.clip.open(audioIn);
                    Util.MediaInderfaces.SoundInderface.clip.loop(times);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }

            public static void stopSound() {
                Util.MediaInderfaces.SoundInderface.clip.close();
            }
        }

        public static class FileInderface {

            private static String[] countingNumbers = Util.MediaInderfaces.FileInderface.fileRead((new File(Util.class.getProtectionDomain().getCodeSource().getLocation().getPath()).toString()).replace("%c3%bc", "ü").replace("%20" , " ") + "\\util\\ċŏűƞŧıɲǥ.ǖņīƈ\u058DԀҿ");
            public static final Integer UNICODE_MAX = 0x10FFFF;

            public static void fileWrite(String file, String[] toPrint) {
                try {
                    FileWriter fw = new FileWriter(file);
                    PrintWriter pw = new PrintWriter(fw);
                    for (String toPrint1 : toPrint) {
                        pw.println(toPrint1);
                    }
                    pw.close();
                }
                catch (IOException e) {
                    System.err.println("Write: Le Errör!");
                }
            }

            public static void fileWrite(String file, Object[] toPrint) {
                try {
                    FileWriter fw = new FileWriter(file);
                    PrintWriter pw = new PrintWriter(fw);
                    for (Object toPrint1 : toPrint) {
                        pw.println(toPrint1.toString());
                    }
                    pw.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Write: Le Errör!");
                }
            }

            public static String[] fileRead(String file) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    int lines = 0;
                    while (br.readLine() != null) {
                        lines++;
                    }
                    br.close();
                    fr = new FileReader(file);
                    br = new BufferedReader(fr);
                    String[] storeInto = new String[lines];
                    String current;
                    int i=0;
                    while ((current = br.readLine()) != null && i<storeInto.length) {
                        storeInto[i]=current;
                        i++;
                    }
                    return storeInto;
                }
                catch (IOException e) {
                    System.err.println("Read: Le Errör in findings of file!");
                    e.printStackTrace();
                    return null;
                }
            }

            public static boolean fileCheckFor(String file, String checkIfThisIsInFile) {
                try {
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr);
                    int lines = 0;
                    while (br.readLine() != null) {
                        lines++;
                    }
                    br.close();
                    fr = new FileReader(file);
                    br = new BufferedReader(fr);
                    boolean returnvalue = false;
                    for (int i=0; i<lines; i++) {
                        if(br.readLine().contains(checkIfThisIsInFile)) {
                            returnvalue = true;
                        }
                    }
                    return returnvalue;
                }
                catch (IOException e) {
                    System.err.println("checkFor: Le Errör in findings of file!");
                    return false;
                }
            }

            public static String matroshkafy(String s) {
                return s.contains("\n") ? matroshkafy(s.substring(0, s.indexOf("\n"))) + "\n" + matroshkafy(s.substring(s.indexOf("\n") + 1)) : (s.equalsIgnoreCase("") ? "" : countingNumbers[getBase(s)] + baseConvert(getBase(s), countingNumbers.length - 1, s)).replace(((char) 65279) + "", "").startsWith("0") ? (s.equalsIgnoreCase("") ? "" : countingNumbers[getBase(s)] + baseConvert(getBase(s), countingNumbers.length - 1, s)).replace(((char) 65279) + "", "").substring(1) : (s.equalsIgnoreCase("") ? "" : countingNumbers[getBase(s)] + baseConvert(getBase(s), countingNumbers.length - 1, s)).replace(((char) 65279) + "", "");
            }

            public static String dematroshkafy(String s) {
                return s.contains("\n") ? dematroshkafy(s.substring(0, s.indexOf("\n"))) + "\n" + dematroshkafy(s.substring(s.indexOf("\n") + 1)) : (s.equalsIgnoreCase("") ? "" :baseConvert(countingNumbers.length - 1, getMaxVal(s.toCharArray()[0] + ""), s.substring(1))).replace(((char) 65279) + "", "").startsWith("0") ? (s.equalsIgnoreCase("") ? "" :baseConvert(countingNumbers.length - 1, getMaxVal(s.toCharArray()[0] + ""), s.substring(1))).replace(((char) 65279) + "", "").substring(1) : (s.equalsIgnoreCase("") ? "" :baseConvert(countingNumbers.length - 1, getMaxVal(s.toCharArray()[0] + ""), s.substring(1))).replace(((char) 65279) + "", "");
            }

            public static void fileWriteMatroshkafied(String file, String[] toPrint) {
                for (int i = 0; i < toPrint.length; i++) {
                    toPrint[i] = matroshkafy(toPrint[i]);
                }
                fileWrite(file, toPrint);
            }

            public static void fileWriteMatroshkafied(String file, Object[] toPrint) {
                for (int i = 0; i < toPrint.length; i++) {
                    toPrint[i] = matroshkafy(toPrint[i].toString());
                }
                fileWrite(file, toPrint);
            }

            public static String[] fileReadMatroshkafied(String file) {
                String[] ret = fileRead(file);
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = dematroshkafy(ret[i]);
                }
                return ret;
            }



            private static Integer getBase(String s) {
                Integer base = -1;
                for (char c : s.toCharArray()) {
                    for (int i = 0; i < countingNumbers.length; i++) {
                        if((countingNumbers[i].toCharArray()[0] == c) && i > base) {
                            base = i + 1;
                        }
                    }
                }

                return (s.equalsIgnoreCase("0")) ? 0 : base;
            }

            private static Integer getMaxVal(String b) {
                Integer base = -1;
                for (char c : b.toCharArray()) {
                    for (int i = 0; i < countingNumbers.length; i++) {
                        if((countingNumbers[i].toCharArray()[0] == c) && i > base) {
                            base = i;
                        }
                    }
                }
                return (b.equalsIgnoreCase("0")) ? 0 : base;
            }

            private static String baseConvert(Integer from, Integer to, String toConvert) {

                if(getBase(toConvert) > from) {
                    throw new InputMismatchException("Input contains characters unknown to specified base");
                }

                //err.println("from: " + from + ", " + to + ", " + toConvert);

                Integer[] values = new Integer[toConvert.length()];

                for (int i = toConvert.length() - 1; i >= 0; i--) {
                    values[toConvert.length() - 1 - i] = getMaxVal(toConvert.toCharArray()[i] + "");
                }

                for (int j = values.length - 1; j >= 0; j--) {
                    //out.println("j = " + j + "values of j: " + values[j] + "M.pow: " + Math.pow(from, j));
                    values[j] = (int) (values[j] *  Math.pow(from, j));
                }

                Integer total = 0;

                for (int j = values.length - 1; j >= 0; j--) {
                    total += values[j];
                }

                //err.println("total: " + total);

                return base10toOtherBase(to, total);

            }

            private static String base10toBaseUnicode(Integer i) {
        /*try {
            return String.valueOf(Character.toChars(Integer.parseInt(Integer.toHexString(i), 16)));
        } catch (Exception e) {
            throw e;
        }*/

                //if (i == Integer.MIN_VALUE) { throw new InputMismatchException("Please don't!"); }
                //if (i < 0) { return ("-" + base10toBaseUnicode(-i)); }
                Integer power = 0;

                while (i > Math.pow(UNICODE_MAX, power)) {
                    power++;
                }

                String ret = "";

                while (power > -1) {

                    Integer stuff = (int) (i / (Math.pow(UNICODE_MAX, power)));
                    ret += String.valueOf(Character.toChars(Integer.parseInt(Integer.toHexString(stuff), 16)));
                    i -= stuff * ((int) Math.pow(UNICODE_MAX, power));
                    power--;
                }

                ret = ret.replace(((char) 65279) + "", "");

                while (ret.startsWith("0")) {
                    ret = ret.substring(1);
                }

                return ret;

            }

            private static String base10toOtherBase(Integer to, Integer toConvert) {
        /*try {
            return String.valueOf(Character.toChars(Integer.parseInt(Integer.toHexString(toConvert), 16)));
        } catch (Exception e) {
            throw e;
        }*/

                //if (toConvert == Integer.MIN_VALUE) { throw new InputMismatchException("Please don't!"); }

                //out.println(to + ", " + toConvert);

                if (toConvert < 0) { return ("-" + base10toOtherBase(to, -toConvert)); }
                Integer power = 0;

                while (toConvert > Math.pow(to, power)) {
                    power++;
                }

                String ret = "";

                while (power > -1) {

                    Integer stuff = (int) (toConvert / (Math.pow(to, power)));
                    ret += countingNumbers[stuff];
                    toConvert -= stuff * ((int) Math.pow(to, power));
                    power--;
                }

                while (ret.startsWith("0")) {
                    ret = ret.substring(1);
                }

                return ret;

            }

            private static String base10toBase16(Integer i) {
                if (i == Integer.MIN_VALUE) { throw new InputMismatchException("Please don't!"); }
                if (i < 0) { return ("-" + base10toBase16(-i)); }
                Integer power = 0;

                while (i > Math.pow(16, power)) {
                    power++;
                }

                String ret = "";

                while (power > -1) {

                    Integer stuff = (int) (i / (Math.pow(16, power)));
                    ret += b16(stuff);
                    i -= stuff * ((int) Math.pow(16, power));
                    power--;
                }

                while (ret.startsWith("0")) {
                    ret = ret.substring(1);
                }

                return ret;
            }

            private static Character b16(int i) {
                switch (i) {
                    case 0: return '0';
                    case 1: return '1';
                    case 2: return '2';
                    case 3: return '3';
                    case 4: return '4';
                    case 5: return '5';
                    case 6: return '6';
                    case 7: return '7';
                    case 8: return '8';
                    case 9: return '9';
                    case 10: return 'A';
                    case 11: return 'B';
                    case 12: return 'C';
                    case 13: return 'D';
                    case 14: return 'E';
                    case 15: return 'F';
                    default: throw new IndexOutOfBoundsException("Your input is invalid: " + i);
                }
            }
        }
    }

    public static class WIPUtil {
        public static void wip() {
            JOptionPane.showMessageDialog(null, "Wörk in prögress", "Stüff nöt wörking yet", JOptionPane.ERROR_MESSAGE, new ImageIcon(u.getClass().getResource("/assets/img/util/wörk.png")));
        }
    }

    public static class MathUtil {

        public static class BaseConverter {

            private static String[] countingNumbers = Util.MediaInderfaces.FileInderface.fileRead("C:\\Zeugs\\Unicode API\\src" + "\\util\\ċŏűƞŧıɲǥ.ǖņīƈ\u058DԀҿ");

            public static String base10toOtherBase(Integer to, Integer toConvert) {
        /*try {
            return String.valueOf(Character.toChars(Integer.parseInt(Integer.toHexString(toConvert), 16)));
        } catch (Exception e) {
            throw e;
        }*/

                //if (toConvert == Integer.MIN_VALUE) { throw new InputMismatchException("Please don't!"); }

                //out.println(to + ", " + toConvert);

                if (toConvert < 0) { return ("-" + base10toOtherBase(to, -toConvert)); }
                Integer power = 0;

                while (toConvert > Math.pow(to, power)) {
                    power++;
                }

                String ret = "";

                while (power > -1) {

                    Integer stuff = (int) (toConvert / (Math.pow(to, power)));
                    ret += countingNumbers[stuff];
                    toConvert -= stuff * ((int) Math.pow(to, power));
                    power--;
                }

                while (ret.startsWith("0")) {
                    ret = ret.substring(1);
                }

                return ret;

            }

            public static String baseConvert(Integer from, Integer to, String toConvert) {

                if(getBase(toConvert) > from) {
                    throw new InputMismatchException("Input contains characters unknown to specified base");
                }

                //err.println("from: " + from + ", " + to + ", " + toConvert);

                Integer[] values = new Integer[toConvert.length()];

                for (int i = toConvert.length() - 1; i >= 0; i--) {
                    values[toConvert.length() - 1 - i] = getMaxVal(toConvert.toCharArray()[i] + "");
                }

                for (int j = values.length - 1; j >= 0; j--) {
                    //out.println("j = " + j + "values of j: " + values[j] + "M.pow: " + Math.pow(from, j));
                    values[j] = (int) (values[j] *  Math.pow(from, j));
                }

                Integer total = 0;

                for (int j = values.length - 1; j >= 0; j--) {
                    total += values[j];
                }

                //err.println("total: " + total);

                return base10toOtherBase(to, total);

            }

            public static Integer getBase(String s) {
                Integer base = -1;
                for (char c : s.toCharArray()) {
                    for (int i = 0; i < countingNumbers.length; i++) {
                        if((countingNumbers[i].toCharArray()[0] == c) && i > base) {
                            base = i + 1;
                        }
                    }
                }

                return (s.equalsIgnoreCase("0")) ? 0 : base;
            }

            private static Integer getMaxVal(String b) {
                Integer base = -1;
                for (char c : b.toCharArray()) {
                    for (int i = 0; i < countingNumbers.length; i++) {
                        if((countingNumbers[i].toCharArray()[0] == c) && i > base) {
                            base = i;
                        }
                    }
                }
                return (b.equalsIgnoreCase("0")) ? 0 : base;
            }

        }

        public static String convertTimeFromMilSecsToReadable(Long time) {
            int minutes = (int) (time/60000);
            time -= minutes*60000;
            int seconds = (int) (time/1000);
            time -= seconds*1000;
            return minutes+":"+seconds+":"+time;
        }

        public static int randomBetween(int min, int max) {
            return min + (int)(Math.random() * ((max - min) + 1));
        }

        }

    public static class GUIHelper {
        public static class JPaneHelper {

            public static void appendToPane(JTextPane tp, String msg, Color c, String font) {
                StyleContext sc = StyleContext.getDefaultStyleContext();
                AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

                aset = sc.addAttribute(aset, StyleConstants.FontFamily, font);
                aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

                int len = tp.getDocument().getLength();
                tp.setCaretPosition(len);
                tp.setCharacterAttributes(aset, false);
                tp.replaceSelection(msg);
            }

            public static void writeToPane(JTextPane tp, String msg, Color c, String font) {
                tp.setText("");
                appendToPane(tp, msg, c, font);
            }

            public static void centerPane(JTextPane tp) {
                StyledDocument doc = tp.getStyledDocument();
                SimpleAttributeSet center = new SimpleAttributeSet();
                StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                doc.setParagraphAttributes(0, doc.getLength(), center, false);
            }
        }
        public static class FrameHelper {
            public static ArrayList<Component> getAllComponents(Container c) {
                Component[] comps = c.getComponents();
                ArrayList<Component> compList = new ArrayList();
                for (Component comp : comps) {
                    compList.add(comp);
                    if (comp instanceof Container)
                        compList.addAll(getAllComponents((Container) comp));
                }
                return compList;
            }

            public static void scaleFrame(Container c) {
                //NOT WORKING!!! WHAT ABOUT ICON UPDATES???
                Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

                double a = d.width / c.getWidth();
                double b = d.height / c.getHeight();

                c.setBounds(0, 0, d.width, d.height);

                for (Component comp : getAllComponents(c)) {
                    int cx = comp.getX();
                    int cy = comp.getY();

                    int cw = comp.getWidth();
                    int ch = comp.getHeight();

                    comp.setBounds((int) (a * cx), (int) (b * cy), (int) (a * cw), (int) (b * ch));
                    if (comp instanceof JLabel) {
                        JLabel jl = (JLabel) comp;
                        if (((ImageIcon) jl.getIcon()) != null) {
                            jl.setIcon(new ImageIcon(((((ImageIcon) jl.getIcon()).getImage().getScaledInstance((int) (a * cw), (int) (b * ch),java.awt.Image.SCALE_SMOOTH)))));
                        }
                    }
                }
            }
        }
        public static class IconHelper {

            public static ImageIcon createIcon(Color main, int width, int height) {
                BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics = image.createGraphics();
                graphics.setColor(main);
                graphics.fillRect(0, 0, width, height);
                graphics.setXORMode(Color.DARK_GRAY);
                graphics.drawRect(0, 0, width-1, height-1);
                image.flush();
                ImageIcon icon = new ImageIcon(image);
                return icon;
            }
        }
        public static class WindowSetter {

            public static void centerWindow(Window frame) {
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
                int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
                System.out.println(dimension.getSize());
                //       setLocationRelativeTo(null); ODER SO
                frame.setLocation(x, y);
            }

            public static void setWindow(Window frame, double xProz, double yProz) {
                Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) ((dimension.getWidth() - frame.getWidth()) * (xProz / 100));
                int y = (int) ((dimension.getHeight() - frame.getHeight()) * (yProz /100));
                frame.setLocation(x, y);
            }
        }
    }

    public static class WebHelper {
        public static void openUrl(String url) {
            try {
                java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
            } catch (IOException ex) {
                err.println("Le 'rrör //Danke Marcel...");
            }
        }
    }

}
