/*
 * bracket_uncertainty_quantification_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:34 by COMSOL 6.3.0.290. */
public class bracket_uncertainty_quantification_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Uncertainty_Quantification_Module\\Tutorials");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("cd", "lp/2+ts", "\u5de5\u4f5c\u5e73\u9762\u4f4d\u7f6e");
    model.param().set("hf", "wp", "\u6cd5\u5170\u9ad8\u5ea6");
    model.param().set("da_r3", "(lp-wf)/2", "\u6cd5\u5170\u5b54 x \u4f4d\u7f6e");
    model.param().set("db_r3", "hf/5", "\u6cd5\u5170\u5b54 y \u4f4d\u7f6e");
    model.param().set("r2", "hm/2", "\u5927\u4fa7\u5706\u89d2");
    model.param().set("ts", "0.008[m]", "\u6750\u6599\u539a\u5ea6");
    model.param().set("lp", "0.19[m]", "\u6a2a\u677f\u957f\u5ea6");
    model.param().set("ls", "0.35[m]", "\u8fb9\u957f");
    model.param().set("hm", "0.1[m]", "\u4fa7\u9762\u9ad8\u5ea6");
    model.param().set("wp", "0.1075[m]", "\u6a2a\u677f\u5bbd\u5ea6");
    model.param().set("wf", "0.0625[m]", "\u6cd5\u5170\u5bbd\u5ea6");
    model.param().set("r3", "0.007[m]", "\u87ba\u6813\u5b54\u534a\u5f84");
    model.param().set("r1", "0.025[m]", "\u9500\u5b54\u534a\u5f84");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").set("quickx", "-cd");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"ls", "hm"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-ls", "-hm/2"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "ts", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "-hm/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new String[]{"lp", "wp"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"-lp/2", "-wp"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "ts", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "hm/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("size", new String[]{"wf", "hf"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"-lp/2", "-hf"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "r3");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1")
         .set("pos", new String[]{"-da_r3", "-db_r3"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1")
         .set("pos", new String[]{"0", "-hf/2"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("dif1").selection("input2")
         .set("c1", "mir1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "ts", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("ext3");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "1.1*lp*2");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-1.1*lp", "-ls+r2", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1", "mir1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "hm/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "1.1*lp*2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"-1.1*lp", "-ls+r2", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"1.1*lp*2", "r2*2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "r2*2", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "-ls+r2", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("cyl2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"1.1*lp*2", "hm/2", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "hm", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "-ls+hm/4", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk2", "dif2");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("dif3").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("dif3").selection("input2").set("int1");
    model.component("comp1").geom("geom1").run("dif3");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("dif3", "ext2", "ext3", "mir2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");

    model.title(null);

    model.description("");

    model.label("bracket_uncertainty_quantification_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
