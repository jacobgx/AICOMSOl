/*
 * lumped_receiver_vibroacoustic_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class lumped_receiver_vibroacoustic_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ltube", "9.00[mm]", "\u7845\u80f6\u7ba1\u957f\u5ea6");
    model.param().set("Ttube", "0.5[mm]", "\u7845\u80f6\u7ba1\u539a\u5ea6");
    model.param().set("d_2cc", "18.00[mm]", "\u8026\u5408\u5668\u76f4\u5f84");
    model.param().set("L_2cc", "7.00[mm]", "\u8026\u5408\u5668\u957f\u5ea6");
    model.param().set("SToffset", "0.5[mm]", "\u7845\u80f6\u7ba1\u7ba1\u53e3\u504f\u79fb\u91cf");
    model.param().set("CToffset", "1.5[mm]", "\u7845\u80f6\u7ba1\u8026\u5408\u5668\u504f\u79fb\u91cf");
    model.param().set("LtubeC", "2.5[mm]", "\u8026\u5408\u5668\u7ba1\u9002\u914d\u5668\u957f\u5ea6");
    model.param().set("Lx", "7.870000e-03[m]", "\u63a5\u6536\u76d2\u957f\u5ea6\uff08\u5c40\u90e8 x\uff09");
    model.param().set("Ly", "4.090000e-03[m]", "\u63a5\u6536\u76d2\u5bbd\u5ea6\uff08\u5c40\u90e8 y\uff09");
    model.param().set("Lz", "2.790000e-03[m]", "\u63a5\u6536\u76d2\u9ad8\u5ea6\uff08\u5c40\u90e8 z\uff09");
    model.param()
         .set("TL", "1.600000e-03[m]", "\u81ea\u76d2\u8868\u9762\u7684\u63a5\u6536\u7ba1\u957f\u5ea6\uff08\u5c40\u90e8 x\uff09");
    model.param().set("Td", "1.00e-03[m]", "\u63a5\u6536\u7ba1\u5185\u5f84");
    model.param()
         .set("Th", "3.250000e-04[m]", "\u76f8\u5bf9\u4e8e\u76d2\u4e2d\u5fc3\u7684\u63a5\u6536\u7ba1\u8f74\u4f4d\u7f6e\uff08\u5c40\u90e8 z\uff09");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx", "Ly", "Lz"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Td/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "TL");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"Lx/2", "0", "Th"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Td/2+Ttube");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "Ltube");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"Lx/2+SToffset", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "Th", 2);
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "Ttube", 0);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "Td/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "LtubeC");
    model.component("comp1").geom("geom1").feature("cyl3")
         .set("pos", new String[]{"Lx/2+SToffset+Ltube-(LtubeC-CToffset)", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("pos", "Th", 2);
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "d_2cc/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "L_2cc");
    model.component("comp1").geom("geom1").feature("cyl4")
         .set("pos", new String[]{"Lx/2+SToffset+Ltube+CToffset", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl4").setIndex("pos", "Th", 2);
    model.component("comp1").geom("geom1").feature("cyl4").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cyl1", 4);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("cyl3", 3);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input")
         .set("blk1", "cyl1", "cyl3", "cyl4", "par2");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "1[mm]");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "1[mm]");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 30);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"1[mm]", "1[mm]", "0"});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("lumped_receiver_vibroacoustic_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
