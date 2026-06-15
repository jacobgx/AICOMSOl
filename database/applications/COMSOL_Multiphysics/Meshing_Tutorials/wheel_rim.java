/*
 * wheel_rim.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:36 by COMSOL 6.3.0.290. */
public class wheel_rim {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wheel_rim.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,72,288)");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").set("fin", 1, 2, 3, 4, 5);
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").cleanup().set("detailsize", 0.001);

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature().create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").feature("rmd1").set("detailsizetype", "absolute");
    model.component("comp1").geom("geom1").feature("rmd1").set("maxabssize", "0.001");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", false);
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", true);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").stat().selection().allGeom();

    model.title("\u8f6e\u8f8b\u51e0\u4f55\u6e05\u7406");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5bf9\u5bfc\u5165\u7684 CAD \u51e0\u4f55\u8fdb\u884c\u51e0\u4f55\u6e05\u7406\u3002\u6e05\u7406\u51e0\u4f55\u4e2d\u7684\u5c0f\u7ec6\u8282\u53ef\u4ee5\u5e2e\u52a9\u6539\u8fdb\u7f51\u683c\uff0c\u5e76\u51cf\u5c11\u7f51\u683c\u5355\u5143\u6570\uff0c\u5728\u4eff\u771f\u7684\u91cd\u8981\u533a\u57df\u4e3a\u7f51\u683c\u7ec6\u5316\u7559\u51fa\u7a7a\u95f4\u3002");

    model.mesh().clearMeshes();

    model.label("wheel_rim.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
