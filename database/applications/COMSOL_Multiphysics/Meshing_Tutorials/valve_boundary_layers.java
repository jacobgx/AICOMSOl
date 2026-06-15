/*
 * valve_boundary_layers.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class valve_boundary_layers {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").geom("geom1").insertFile("valve_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "0.5[m/s]");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(8);

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7f51\u683c\u56fe 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 1.2);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blstretch", 1.3);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhmin", "0.15[mm]");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhtot", "2[mm]");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").selection()
         .set(2, 3, 4, 5, 6, 9, 10, 11, 12);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp2", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blnlayers", 10);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("layerdec", 1);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimminangle", 230);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("cr1").set("minangle", 230);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimmaxangle", 65);
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "split");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").feature("bl1").set("splitdivangle", 60);
    model.component("comp1").mesh("mesh1").run("bl1");

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("cr1").set("usefilter", true);
    model.component("comp1").mesh("mesh1").feature("cr1").selection("corner").set(3, 10);
    model.component("comp1").mesh("mesh1").feature("cr1").set("filter", "exclude");
    model.component("comp1").mesh("mesh1").run();

    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("bl1").create("crp1", "CornerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("crp1").selection().set(4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("crp1").set("cornerhandling", "split");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("cr1").selection("corner").set(3, 4, 10);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model.title("\u8fb9\u754c\u5c42\u7f51\u683c\u5212\u5206 - \u63a2\u7d22\u8bbe\u7f6e");

    model
         .description("\u8ddf\u968f\u672c\u6559\u7a0b\u5b66\u4e60\u5982\u4f55\u8bbe\u7f6e\u8fb9\u754c\u5c42\u7f51\u683c\uff0c\u4ee5\u53ca\u5982\u4f55\u4fee\u6539\u81ea\u52a8\u521b\u5efa\u7684\u8fb9\u754c\u5c42\u7f51\u683c\u7684\u8bbe\u7f6e\u3002\u5728\u8fdb\u884c\u7269\u7406\u573a\u63a7\u5236\u7684\u7f51\u683c\u5212\u5206\u65f6\uff0c\u8f6f\u4ef6\u4f1a\u4e3a\u9884\u8ba1\u8fb9\u754c\u9644\u8fd1\u6709\u9661\u5ced\u68af\u5ea6\u7684 App \u81ea\u52a8\u6dfb\u52a0\u8fb9\u754c\u5c42\u7f51\u683c\u3002\u60a8\u5c06\u4e86\u89e3\u5982\u4f55\u63a7\u5236\u5c42\u6570\u3001\u5b9a\u4e49\u5c42\u539a\u5ea6\uff0c\u5e76\u4e3a\u4e0d\u540c\u7684\u8fb9\u754c\u521b\u5efa\u5177\u6709\u4e0d\u540c\u5c5e\u6027\u7684\u8fb9\u754c\u5c42\u7f51\u683c\u3002\u6b64\u5916\uff0c\u672c\u6559\u7a0b\u8fd8\u5c06\u6f14\u793a\u5728\u62d0\u89d2\u5468\u56f4\u6dfb\u52a0\u8fb9\u754c\u5c42\u7684\u53ef\u7528\u65b9\u6cd5\uff0c\u4ee5\u53ca\u7528\u4e8e\u8bc4\u4f30\u5355\u5143\u8d28\u91cf\u7684\u7f51\u683c\u56fe\u7684\u8bbe\u7f6e\u3002");

    model.label("valve_boundary_layers.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
