/*
 * tunable_cavity_filter_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:37 by COMSOL 6.3.0.290. */
public class tunable_cavity_filter_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Filters");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("thickness", "60[mil]", "\u57fa\u677f\u539a\u5ea6");
    model.param().set("l_slot", "40[mm]", "\u69fd\u957f\u5ea6");
    model.param().set("w_slot", "1.5[mm]", "\u69fd\u5bbd\u5ea6");
    model.param().set("x_slot", "32[mm]", "\u69fd\u4f4d\u7f6e");
    model.param().set("l_feed", "10[mm]", "\u9988\u7ebf\u957f\u5ea6");
    model.param()
         .set("gap_post", "150[um]", "\u538b\u7535\u6267\u884c\u5668\u4e0e\u91d1\u5c5e\u67f1\u7684\u95f4\u9699");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u8154\u4f53");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 50, 50});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").label("\u57fa\u677f");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"25", "50", "thickness"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-37.5", "0", "25+thickness/2"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("\u7a7a\u6c14\u957f\u65b9\u4f53");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{25, 50, 10});
    model.component("comp1").geom("geom1").feature("blk3").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{-37.5, 0, 30});
    model.component("comp1").geom("geom1").run("blk3");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").label("\u9988\u7ebf");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"l_feed+w_slot", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", 3.2, 1);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "thickness", 2);
    model.component("comp1").geom("geom1").feature("blk4").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new String[]{"-x_slot-l_feed/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "25+thickness/2", 2);
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 25);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"w_slot", "l_slot"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-x_slot", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("blk2", "blk3", "blk4", "wp1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").label("\u67f1");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"15", "15", "50-gap_post"});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new double[]{-7.5, -7.5, -25});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").label("\u538b\u7535\u9a71\u52a8\u5668");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 21);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, 0, 25});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk5");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("tunable_cavity_filter_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
