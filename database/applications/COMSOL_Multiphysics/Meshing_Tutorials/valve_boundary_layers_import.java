/*
 * valve_boundary_layers_import.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class valve_boundary_layers_import {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").mesh("mesh1").create("imp1", "Import");
    model.component("comp1").mesh("mesh1").feature("imp1").set("filename", "valve_mesh.mphbin");
    model.component("comp1").mesh("mesh1").feature("imp1").importData();

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").mesh("mesh1").create("ref1", "Refine");
    model.component("comp1").mesh("mesh1").feature("ref1").set("rmethod", "longest");
    model.component("comp1").mesh("mesh1").run("ref1");
    model.component("comp1").mesh("mesh1").feature("ref1").set("rmethod", "regular");
    model.component("comp1").mesh("mesh1").run("ref1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("imp1_Wall_Boundaries");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blstretch", 1.4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhtot");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhtot", "1[mm]");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "none");
    model.component("comp1").mesh("mesh1").run();

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

    model.result("pg1").feature("mesh1").set("qualmeasure", "growth");
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothmaxdepth", 2);
    model.component("comp1").mesh("mesh1").run();

    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("qualmeasure", "skewness");
    model.result("pg1").run();

    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model
         .title("\u8fb9\u754c\u5c42\u7f51\u683c\u5212\u5206 - \u5728\u5bfc\u5165\u7684\u7f51\u683c\u4e2d\u6dfb\u52a0\u8fb9\u754c\u5c42");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5728\u5bfc\u5165\u7684\u7f51\u683c\u4e2d\u6dfb\u52a0\u8fb9\u754c\u5c42\u7f51\u683c\u3002\u60a8\u5c06\u5b66\u4e60\u5982\u4f55\u7ec6\u5316\u5bfc\u5165\u7684\u7f51\u683c\uff0c\u63d2\u5165\u8fb9\u754c\u5c42\u7f51\u683c\uff0c\u63a7\u5236\u8fb9\u754c\u5230\u5185\u90e8\u7684\u5355\u5143\u589e\u957f\u7387\uff0c\u4ee5\u53ca\u5982\u4f55\u8bbe\u7f6e\u8fb9\u754c\u5c42\u7684\u5206\u5e03\u548c\u539a\u5ea6\u3002");

    model.label("valve_boundary_layers_import.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
