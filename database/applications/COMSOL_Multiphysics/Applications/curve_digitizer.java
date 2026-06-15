/*
 * curve_digitizer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class curve_digitizer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Applications");

    model.title("\u66f2\u7ebf\u6570\u5b57\u5316\u4eea");

    model
         .description("\u7528\u6237\u53ef\u4ee5\u4f7f\u7528\u8be5 App \u4ece\u56fe\u50cf\u4e2d\u63d0\u53d6\u66f2\u7ebf\uff1b\u5176\u4e2d\u63d0\u4f9b\u4e00\u79cd\u7b80\u4fbf\u7684\u65b9\u6cd5\uff0c\u53ef\u4ee5\u5bf9\u7b1b\u5361\u5c14\u5750\u6807\u7cfb\u6216\u6781\u5750\u6807\u7cfb\u4e2d\u5177\u6709\u4e0d\u540c\u8f74\u7684\u5404\u79cd\u4e00\u7ef4\u7ed8\u56fe\u8fdb\u884c\u6570\u5b57\u5316\u5904\u7406\u3002");

    model.mesh().clearMeshes();

    model.label("curve_digitizer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
