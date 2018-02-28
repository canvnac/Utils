package cn.canlnac.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.session.Session;

/**
 * 验证码
 * 设置验证码 和 比对验证码
 * 提示：对于shiro部分，不用的话删除相应代码即可
 */
public class ValidateCodeUtil {
    /**
     * 验证码session键
     */
    static final String VALIDATE_CODE = "validateCode";

    /**
     * 比对验证码(获取会话中的验证码进行比对)
     * @param code
     * @param session
     * @return
     */
    public static boolean validate(String code, HttpSession session){
        String validateCode = (String) session.getAttribute(VALIDATE_CODE);
        return validate(code, validateCode);
    }

    /**
     * 比对验证码(获取shiro管理的会话中的验证码进行比对)
     * @param code
     * @param session
     * @return
     */
    public static boolean validate(String code, Session session){
        String validateCode = (String) session.getAttribute(VALIDATE_CODE);
        return validate(code, validateCode);
    }

    /**
     * 比对验证码
     * @param code
     * @param validateCode
     * @return
     */
    private static boolean validate(String code, String validateCode){
        return StringUtils.isNotBlank(code) && StringUtils.equalsIgnoreCase(code, validateCode);
    }

    /**
     * 设置验证码
     * 将验证码存于会话中，并将验证码图片返回
     * @param request
     * @param response
     */
    public static void setCode(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

		/*
		 * 得到参数高，宽，都为数字时，则使用设置高宽，否则使用默认值
		 */
        int w = 70;
        int h = 28;
        String width = request.getParameter("width");
        String height = request.getParameter("height");
        if (StringUtils.isNumeric(width) && StringUtils.isNumeric(height)) {
            w = NumberUtils.toInt(width);
            h = NumberUtils.toInt(height);
        }

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();

		/*
		 * 生成背景
		 */
        createBackground(g, w, h);

		/*
		 * 生成字符
		 */
        String s = createCharacter(g);
        request.getSession().setAttribute(VALIDATE_CODE, s);

        OutputStream out = null;
        try {
            out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            g.dispose();
        }

    }

    private static Color getRandColor(int fc,int bc) {
        int f = fc;
        int b = bc;
        Random random=new Random();
        if(f>255) {
            f=255;
        }
        if(b>255) {
            b=255;
        }
        return new Color(f+random.nextInt(b-f),f+random.nextInt(b-f),f+random.nextInt(b-f));
    }

    private static String createCharacter(Graphics g) {
        char[] codeSeq = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
                'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
        String[] fontTypes = {"Arial","Arial Black","AvantGarde Bk BT","Calibri"};
        Random random = new Random();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);//random.nextInt(10));
            g.setColor(new Color(50 + random.nextInt(100), 50 + random.nextInt(100), 50 + random.nextInt(100)));
            g.setFont(new Font(fontTypes[random.nextInt(fontTypes.length)],Font.BOLD,26));
            g.drawString(r, 15 * i + 5, 19 + random.nextInt(8));
            s.append(r);
        }
        return s.toString();
    }

    private static void createBackground(Graphics g, int w, int h) {
        // 填充背景
        g.setColor(getRandColor(220,250));
        g.fillRect(0, 0, w, h);
        // 加入干扰线条
        for (int i = 0; i < 8; i++) {
            g.setColor(getRandColor(40,150));
            Random random = new Random();
            int x = random.nextInt(w);
            int y = random.nextInt(h);
            int x1 = random.nextInt(w);
            int y1 = random.nextInt(h);
            g.drawLine(x, y, x1, y1);
        }
    }
}
