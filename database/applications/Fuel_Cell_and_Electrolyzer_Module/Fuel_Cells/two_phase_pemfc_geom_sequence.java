/*
 * two_phase_pemfc_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:11 by COMSOL 6.3.0.290. */
public class two_phase_pemfc_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W_rib", "0.8[mm]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("W_ch", "0.8[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_ribch", "W_rib+W_ch", "\u808b+\u901a\u9053\u5bbd\u5ea6");
    model.param().set("N_ch", "2", "\u901a\u9053\u6570");
    model.param().set("W_act", "N_ch*(W_ribch)", "\u6d3b\u6027\u533a\u57df\u5bbd\u5ea6");
    model.param().set("H_act", "5[cm]", "\u6d3b\u6027\u533a\u57df\u9ad8\u5ea6");
    model.param().set("H_ch", "0.8[mm]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("ang", "45[deg]", "\u6b67\u7ba1\u4e2d\u7684\u901a\u9053\u5f2f\u66f2\u89d2\u5ea6");
    model.param().set("R_inner", "2[mm]", "\u5f2f\u66f2\u5904\u7684\u5185\u534a\u5f84");
    model.param().set("H_gdl", "150[um]", "GDL \u539a\u5ea6");
    model.param().set("H_mem", "9[um]", "\u819c\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "H_mem/2+H_gdl");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R_inner");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", "ang");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"W_rib/2+W_ch+R_inner", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "R_inner+W_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_ch", "W_act*2/4/sin(ang)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"W_rib/2", "-(W_act*2/4/sin(ang))"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"W_act", "H_act"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"W_rib/2", "-(W_act*2/4/sin(ang))-H_act"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "ang");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("pos", new String[]{"W_rib/2+W_ch+R_inner", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input")
         .set("dif1", "rot1(1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"N_ch", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input2").set("rot1(2)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("dif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2")
         .set("pos", new String[]{"W_act/2", "H_act/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"W_ch", "H_act"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"W_rib/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("fullsize", new String[]{"N_ch", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_ch", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_act", "H_act", "H_gdl"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "H_mem/2"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"W_act", "H_act", "H_mem"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-H_mem/2"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("blk1", "ext1");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"W_act/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ext1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("blk1", 1);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("blk2", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("rot1(1)", 1);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("rot1(2)");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel5"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"sel2", "sel4"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "-1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "W_act+1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "-1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "H_act+1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "H_mem/2+H_gdl-1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "H_mem/2+H_gdl+1e-6");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel2", "boxsel1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "-H_mem/2-H_gdl-1e-6");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "-H_mem/2-H_gdl+1e-6");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 35);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 32);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"sel6", "sel7"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 5, 23);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("fin", 117, 124);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("sel10", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel10").selection("selection").set("fin", 108, 123);
    model.component("comp1").geom("geom1").run("sel10");
    model.component("comp1").geom("geom1").create("sel11", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel11").selection("selection").set("fin", 1, 11);
    model.component("comp1").geom("geom1").run("sel11");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel5").set("input", new String[]{"sel8", "sel9"});
    model.component("comp1").geom("geom1").run("unisel5");
    model.component("comp1").geom("geom1").create("unisel6", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel6").set("input", new String[]{"sel10", "sel11"});
    model.component("comp1").geom("geom1").run("unisel6");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel5"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"unisel6"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("outputdim", 3);

    model.title(null);

    model.description("");

    model.label("two_phase_pemfc_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
