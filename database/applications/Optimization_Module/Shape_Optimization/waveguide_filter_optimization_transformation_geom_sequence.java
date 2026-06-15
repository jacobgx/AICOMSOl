/*
 * waveguide_filter_optimization_transformation_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:46 by COMSOL 6.3.0.290. */
public class waveguide_filter_optimization_transformation_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.param().set("h_wg", "1[cm]");
    model.param().descr("h_wg", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("w_wg", "2*h_wg");
    model.param().descr("w_wg", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("spacing", "w_wg");
    model.param().descr("spacing", "\u8154\u4f53\u95f4\u8ddd");
    model.param().set("cavities", "3");
    model.param().descr("cavities", "\u8154\u4f53\u6570\u91cf");
    model.param().set("l_wg", "1.5*w_wg");
    model.param().descr("l_wg", "\u7aef\u53e3\u5230\u8154\u4f53\u7684\u8ddd\u79bb");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"2*l_wg+cavities*spacing", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "w_wg/2", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-l_wg-cavities/2*spacing", "0"});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_wg/12", "w_wg/4"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-l_wg-w_wg/24", "0"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "w_wg/4", 1);
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("r2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"cavities+1", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"spacing", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "w_wg/2.1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("r1");
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"0", "-w_wg/2"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"0", "w_wg/2"});
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"uni1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u63a7\u5236\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "-spacing/10");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "l_wg+cavities*spacing/2.1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", 0);
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "w_wg/2.1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel2").set("input", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u7aef\u53e3 1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "-l_wg-cavities*spacing/2.1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel4", "boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u7aef\u53e3 2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "l_wg+cavities*spacing/2.1");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u81ea\u7531\u5f62\u72b6\u57df");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmin", "-w_wg*0.001");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymin", "-w_wg*0.001");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6247\u533a\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"boxsel5"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").label("\u4e2d\u5fc3\u7ebf");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymin", "-0.001*w_wg");
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymax", "0.001*w_wg");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5bf9\u79f0/\u8f8a\u652f\u627f");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"boxsel1", "ls1", "boxsel6"});

    model.title(null);

    model.description("");

    model.label("waveguide_filter_optimization_transformation_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
