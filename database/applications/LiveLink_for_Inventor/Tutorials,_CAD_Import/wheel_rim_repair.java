/*
 * wheel_rim_repair.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:41 by COMSOL 6.3.0.290. */
public class wheel_rim_repair {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_Inventor\\Tutorials,_CAD_Import");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "wheel_rim.x_b");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("rendermesh", false);

    model.component("comp1").geom("geom1").measureFinal().selection().geom("geom1", 1);
    model.component("comp1").geom("geom1").measureFinal().selection().set(1384);
    model.component("comp1").geom("geom1").measureFinal().selection().geom("geom1", 1);
    model.component("comp1").geom("geom1").measureFinal().selection().set(1813);
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("rep1", "Repair");
    model.component("comp1").geom("geom1").feature("rep1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("rep1").set("repairtol", "3.2e-4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").geom("geom1").run("rep1");
    model.component("comp1").geom("geom1").create("rep2", "Repair");
    model.component("comp1").geom("geom1").feature("rep2").selection("input").set("rep1");
    model.component("comp1").geom("geom1").feature("rep2").set("repairtol", "9e-4");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").automatic(false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("rendermesh", true);

    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(225);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "2[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.title("\u901a\u8fc7\u4fee\u590d\u64cd\u4f5c\u79fb\u9664\u5c0f\u51e0\u4f55\u5b9e\u4f53");

    model
         .description("\u672c\u6559\u7a0b\u4e2d\u7684\u51e0\u4f55\u5305\u542b\u4e00\u4e9b\u5c0f\u7ec6\u8282\uff0c\u4f8b\u5982\u77ed\u8fb9\u3001\u5c0f\u9762\u548c\u957f\u6761\u9762\uff0c\u7528\u6237\u53ef\u4ee5\u901a\u8fc7\u201c\u4fee\u590d\u201d\u64cd\u4f5c\u5c06\u5176\u79fb\u9664\u3002\u6b64\u5916\uff0c\u672c\u6559\u7a0b\u8fd8\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u7f51\u683c\u751f\u6210\u540e\u8fd4\u56de\u7684\u8b66\u544a\u6765\u5b9a\u4f4d\u548c\u6d4b\u91cf\u5c0f\u7ec6\u8282\u7684\u5927\u5c0f\uff0c\u4ece\u800c\u4e3a\u201c\u4fee\u590d\u201d\u64cd\u4f5c\u786e\u5b9a\u5408\u9002\u7684\u5bb9\u5dee\u3002");

    model.mesh().clearMeshes();

    model.label("wheel_rim_repair.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
