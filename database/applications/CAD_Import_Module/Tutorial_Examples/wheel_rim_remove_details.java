/*
 * wheel_rim_remove_details.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class wheel_rim_remove_details {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CAD_Import_Module\\Tutorial_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wheel_rim.x_b");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").measureFinal().selection().geom("geom1", 1);
    model.component("comp1").geom("geom1").measureFinal().selection().set(39);
    model.component("comp1").geom("geom1").feature("rmd1").set("detailsizetype", "absolute");
    model.component("comp1").geom("geom1").feature("rmd1").set("maxabssize", 8.5E-4);
    model.component("comp1").geom("geom1").run("rmd1");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", "off");
    model.component("comp1").geom("geom1").runPre("rmd1/aclf1");
    model.component("comp1").geom("geom1").run("rmd1/aclf1");
    model.component("comp1").geom("geom1").run("rmd1/aige1");
    model.component("comp1").geom("geom1").run("rmd1/aigv1");
    model.component("comp1").geom("geom1").runPre("rmd1/acfr1");
    model.component("comp1").geom("geom1").run("rmd1/acfr1");
    model.component("comp1").geom("geom1").feature("rmd1").set("automatic", true);

    model.component("comp1").mesh("mesh1").run();

    model.title("\u901a\u8fc7\u79fb\u9664\u7ec6\u8282\u64cd\u4f5c\u79fb\u9664\u5c0f\u51e0\u4f55\u5b9e\u4f53");

    model
         .description("\u60a8\u53ef\u4ee5\u6309\u7167\u672c\u6559\u7a0b\u5b66\u4e60\u5982\u4f55\u4f7f\u7528\u201c\u79fb\u9664\u7ec6\u8282\u201d\u64cd\u4f5c\u4ece\u51e0\u4f55\u4e2d\u81ea\u52a8\u79fb\u9664\u5c0f\u7ec6\u8282\u3002\u201c\u79fb\u9664\u7ec6\u8282\u201d\u529f\u80fd\u53ef\u4ee5\u8bbe\u7f6e\u4e00\u7cfb\u5217\u865a\u62df\u51e0\u4f55\u64cd\u4f5c\uff0c\u60a8\u53ef\u4ee5\u6839\u636e\u9700\u8981\u8fdb\u884c\u4fee\u6539\uff0c\u4f8b\u5982\uff0c\u9632\u6b62\u79fb\u9664\u9009\u5b9a\u7684\u7ec6\u8282\u3002");

    model.mesh().clearMeshes();

    model.label("wheel_rim_remove_details.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
