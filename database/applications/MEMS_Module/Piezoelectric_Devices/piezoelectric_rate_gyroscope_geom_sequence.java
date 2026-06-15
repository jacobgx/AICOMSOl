/*
 * piezoelectric_rate_gyroscope_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:59 by COMSOL 6.3.0.290. */
public class piezoelectric_rate_gyroscope_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("tQz", "0.5[mm]", "\u77f3\u82f1\u539a\u5ea6");
    model.param().set("w_m", "1[mm]", "\u5b89\u88c5\u5bbd\u5ea6");
    model.param().set("l_w", "(l_f-w_m)/2-w_tbf", "\u7ffc\u957f");
    model.param().set("w_w", "0.25[mm]", "\u7ffc\u5bbd");
    model.param().set("l_f", "4[mm]", "\u6846\u67b6\u957f\u5ea6");
    model.param().set("w_f", "3[mm]", "\u6846\u67b6\u5bbd\u5ea6");
    model.param().set("w_tbf", "0.4[mm]", "\u6846\u67b6\u9876\u90e8/\u5e95\u90e8\u539a\u5ea6");
    model.param().set("w_sf", "0.25[mm]", "\u6846\u67b6\u8fb9\u539a\u5ea6");
    model.param().set("l_d", "6[mm]", "\u9a71\u52a8\u9f7f\u957f\u5ea6");
    model.param().set("w_d", "0.4[mm]", "\u9a71\u52a8\u9f7f\u5bbd\u5ea6");
    model.param().set("l_s", "5.5[mm]", "\u611f\u5e94\u9f7f\u957f\u5ea6");
    model.param().set("w_s", "0.4[mm]", "\u611f\u5e94\u9f7f\u5bbd\u5ea6");
    model.param().set("gap", "1[mm]", "\u9f7f\u95f4\u9699");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").repairTolType("relative");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-tQz/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"w_f", "l_f"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"w_f-2*w_sf", "l_f-2*w_tbf"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "w_m");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"w_w", "l_w"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"-w_w/2", "w_m/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"w_w", "l_w"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"-w_w/2", "-w_m/2-l_w"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("size", new String[]{"w_d", "l_d"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5")
         .set("pos", new String[]{"gap/2", "l_f/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("size", new String[]{"w_s", "l_s"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6")
         .set("pos", new String[]{"gap/2", "-l_f/2-l_s"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r5", "r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("dif1", "mir1", "r3", "r4", "r5", "r6", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "tQz", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp2").selection("offsetvertex").set("ext1", 21);
    model.component("comp1").geom("geom1").feature("wp2").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "0.4*w_m");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"w_d*0.6", "l_d*0.8"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"gap/2+w_d*0.2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").setIndex("pos", "l_f/2+l_d*0.1", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("wp2.mir1", "wp2.r1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 2);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "tQz"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp3").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp3").selection("offsetvertex").set("ext1", 58);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"l_d*0.8", "tQz*0.6"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"(l_f+l_d)/2", "0"});
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("arr2").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr2").set("linearsize", 2);
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"-w_d", "0", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("arr2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp4").set("quickoffsettype", "vertex");
    model.component("comp1").geom("geom1").feature("wp4").selection("offsetvertex").set("ext1", 58);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"l_s*0.8", "tQz*0.2"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("pos", new String[]{"-(l_f+l_s)/2", "0"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").setIndex("pos", "-tQz*0.3", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("arr3").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr3").set("linearsize", 2);
    model.component("comp1").geom("geom1").feature("arr3").set("displ", new String[]{"-w_s", "0", "0"});
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("arr3");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6").set("quickplane", "yz");
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("piezoelectric_rate_gyroscope_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
