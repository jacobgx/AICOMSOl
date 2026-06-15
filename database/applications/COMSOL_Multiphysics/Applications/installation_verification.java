/*
 * installation_verification.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class installation_verification {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    app.form("mainForm").formObject("collection2").setIndex("vertscroll", false, 0);
    app.form("mainForm").formObject("collection2").setIndex("vertscroll", false, 1);
    app.form("mainForm").formObject("collection2").setIndex("vertscroll", false, 2);

    model.title("\u5b89\u88c5\u9a8c\u8bc1");

    model
         .description("\u5b89\u88c5\u9a8c\u8bc1 App \u65e8\u5728\u786e\u4fdd\u60a8\u7684 COMSOL Multiphysics\u00ae \u6216 COMSOL Server\u2122 \u5b89\u88c5\u5728\u786c\u4ef6\u5e73\u53f0\u548c\u64cd\u4f5c\u7cfb\u7edf\u4e0a\u80fd\u591f\u5982\u671f\u8fd0\u884c\uff0c\u901a\u8fc7\u81ea\u52a8\u52a0\u8f7d\u5e76\u8fd0\u884c\u4e00\u5957\u6d4b\u8bd5\u6a21\u578b\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u5b58\u50a8\u7684\u9884\u671f\u503c\u8fdb\u884c\u6bd4\u8f83\uff0c\u5176\u4e2d\u4e00\u7ec4\u6d4b\u8bd5\u5c06\u5f53\u524d\u8ba1\u7b97\u7ed3\u679c\u4e0e\u4ece\u5b9e\u9a8c\u6216\u5176\u4ed6\u7c7b\u578b\u7684\u8ba1\u7b97\u6216\u5206\u6790\u7ed3\u679c\u4e2d\u83b7\u5f97\u7684\u5df2\u53d1\u5e03\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\uff0c\u53e6\u4e00\u7ec4\u6d4b\u8bd5\u5219\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u5148\u524d\u8ba1\u7b97\u7684\u5b58\u50a8\u7ed3\u679c\uff08\u901a\u5e38\u91c7\u7528\u7ec6\u5316\u7f51\u683c\uff09\u8fdb\u884c\u6bd4\u8f83\u3002\u8ba1\u7b97\u8981\u6c42\u6700\u9ad8\u7684\u6d4b\u8bd5\u6a21\u578b\u53ef\u80fd\u9700\u8981\u9ad8\u8fbe 32\u00a0GB \u7684\u5185\u5b58\u3002");

    model.label("installation_verification.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
