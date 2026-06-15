/*
 * magnetic_circuit_topology_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:48 by COMSOL 6.3.0.290. */
public class magnetic_circuit_topology_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("w_magnet", "26[mm]", "\u78c1\u4f53\u5bbd\u5ea6");
    model.param().set("h_magnet", "20[mm]", "\u78c1\u4f53\u9ad8\u5ea6");
    model.param().set("r_magnet", "25[mm]", "\u78c1\u4f53\u5185\u534a\u5f84");
    model.param().set("z_magnet", "-80[mm]", "\u78c1\u4f53\u5e95\u90e8\u7684 z \u5750\u6807");
    model.param().set("w_air", "80[mm]", "\u7a7a\u6c14\u57df\u5bbd\u5ea6");
    model.param().set("h_air", "100[mm]", "\u7a7a\u6c14\u57df\u9ad8\u5ea6");
    model.param().set("w_design", "45[mm]", "\u94c1\u5bbd");
    model.param().set("h_design", "45[mm]", "\u94c1\u9ad8");
    model.param().set("r_design", "6[mm]", "\u94c1\u7684\u5185\u534a\u5f84");
    model.param().set("z_design", "-90[mm]", "\u94c1\u5e95\u90e8\u7684 z \u5750\u6807");
    model.param().set("w_gap_i", "0.6[mm]", "\u7ebf\u5708\u5468\u56f4\u5185\u90e8\u6c14\u9699\u7684\u5bbd\u5ea6");
    model.param().set("w_gap_o", "0.2[mm]", "\u7ebf\u5708\u5468\u56f4\u5916\u90e8\u6c14\u9699\u7684\u5bbd\u5ea6");
    model.param().set("w_coil", "0.4[mm]", "\u7ebf\u5708\u5bbd\u5ea6");
    model.param().set("h_coil", "10[mm]", "\u7ebf\u5708\u9ad8\u5ea6");
    model.param().set("r_coil", "18.6[mm]", "\u7ebf\u5708\u4e2d\u5fc3\u534a\u5f84");
    model.param().set("z_coil", "-56[mm]", "\u7ebf\u5708\u4e2d\u5fc3\u7684 z \u5750\u6807");
    model.param().set("z_offset", "7[mm]", "\u97f3\u5708\u7684\u6700\u5927\u504f\u79fb\u91cf");
    model.param().set("n_points", "10", "\u8ba1\u7b97 BL \u66f2\u7ebf\u6240\u9700\u7684\u70b9\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"w_air", "h_air"});
    model.component("comp1").geom("geom1").feature("r1")
         .set("pos", new String[]{"0", "z_design+h_design/2-h_air/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2")
         .label("\u5e26\u78c1\u4f53\u548c\u7ebf\u5708\u95f4\u9699\u7684\u8bbe\u8ba1\u7a7a\u95f4");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"w_design", "h_design"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"r_design", "z_design"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u97f3\u5708\u95f4\u9699");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"w_coil+w_gap_i+w_gap_o", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "h_coil+2*z_offset", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"r_coil-w_gap_i-w_coil/2", "0"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("pos", "z_coil-h_coil/2-z_offset", 1);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").label("\u5e26\u78c1\u4f53\u8bbe\u8ba1\u7a7a\u95f4");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r3");
    model.component("comp1").geom("geom1").feature("dif1").set("selresult", true);
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u78c1\u4f53");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"w_magnet", "h_magnet"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"r_magnet", "z_magnet"});
    model.component("comp1").geom("geom1").feature("r4").set("selresult", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").label("\u97f3\u5708\u53ef\u80fd\u6240\u5904\u4f4d\u7f6e");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"w_coil", "h_coil+2*z_offset"});
    model.component("comp1").geom("geom1").feature("r5").set("base", "center");
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"r_coil", "z_coil"});
    model.component("comp1").geom("geom1").feature("r5").setIndex("layer", "h_coil/2", 0);
    model.component("comp1").geom("geom1").feature("r5").set("layertop", true);
    model.component("comp1").geom("geom1").feature("r5").set("selresult", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1")
         .label("\u97f3\u5708\u4e2d\u5fc3\u53ef\u80fd\u6240\u5904\u4f4d\u7f6e");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"r_coil", "z_coil-z_offset"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"r_coil", "z_coil+z_offset"});
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").label("\u97f3\u5708\u4e2d\u5fc3\u6700\u4f4e\u70b9");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "r_coil", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "z_coil-z_offset", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").label("\u97f3\u5708\u4e2d\u5fc3\u6d4b\u91cf\u70b9");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"1", "n_points"});
    model.component("comp1").geom("geom1").feature("arr1")
         .set("displ", new String[]{"0", "2*z_offset/(n_points-1)"});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "pnt");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2")
         .label("\u9759\u6b62\u4f4d\u7f6e\u7684\u97f3\u5708\u4e2d\u5fc3");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "r_coil", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "z_coil", 1);
    model.component("comp1").geom("geom1").feature("pt2").set("selresult", true);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6620\u5c04\u57df");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"r4", "r5"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8bbe\u8ba1\u7a7a\u95f4");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"dif1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"r4"});

    model.title(null);

    model.description("");

    model.label("magnetic_circuit_topology_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
