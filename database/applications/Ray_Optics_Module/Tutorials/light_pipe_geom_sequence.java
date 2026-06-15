/*
 * light_pipe_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:24 by COMSOL 6.3.0.290. */
public class light_pipe_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rpipe", "2[mm]", "\u5bfc\u5149\u7ba1\u534a\u5f84");
    model.param().set("L1", "20[mm]", "\u76f4\u7ba1\u6bb5 1 \u7684\u957f\u5ea6");
    model.param().set("rb1", "10[mm]", "\u66f2\u7387\u534a\u5f84\uff0c\u5f2f\u7ba1\u6bb5 1");
    model.param().set("theta1", "30[deg]", "\u5f2f\u89d2 1");
    model.param().set("L2", "25[mm]", "\u76f4\u7ba1\u6bb5 2 \u7684\u957f\u5ea6");
    model.param().set("rb2", "20[mm]", "\u66f2\u7387\u534a\u5f84\uff0c\u5f2f\u7ba1\u6bb5 2");
    model.param().set("theta2", "90[deg]", "\u5f2f\u89d2 2");
    model.param().set("L3", "10[mm]", "\u76f4\u7ba1\u6bb5 3 \u7684\u957f\u5ea6");
    model.param().set("x0", "0", "LED \u4f4d\u7f6e\uff0cx \u5206\u91cf");
    model.param().set("y0", "0", "LED \u4f4d\u7f6e\uff0cy \u5206\u91cf");
    model.param().set("z0", "0", "LED \u4f4d\u7f6e\uff0cz \u5206\u91cf");
    model.param().set("xc1", "x0-rb1", "\u5f2f\u7ba1\u6bb5 1 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cx \u5206\u91cf");
    model.param().set("yc1", "y0", "\u5f2f\u7ba1\u6bb5 1 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cy \u5206\u91cf");
    model.param().set("zc1", "z0+L1", "\u5f2f\u7ba1\u6bb5 1 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cz \u5206\u91cf");
    model.param()
         .set("xL2", "xc1+rb1*cos(theta1)", "\u76f4\u7ba1\u6bb5 2 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cx \u5206\u91cf");
    model.param().set("yL2", "yc1", "\u76f4\u7ba1\u6bb5 2 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cy \u5206\u91cf");
    model.param()
         .set("zL2", "zc1+rb1*sin(theta1)", "\u76f4\u7ba1\u6bb5 2 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cz \u5206\u91cf");
    model.param()
         .set("xc2", "xL2-L2*sin(theta1)+rb2*cos(theta1)", "\u5f2f\u7ba1\u6bb5 2 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cx \u5206\u91cf");
    model.param().set("yc2", "yL2", "\u5f2f\u7ba1\u6bb5 2 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cy \u5206\u91cf");
    model.param()
         .set("zc2", "zL2+L2*cos(theta1)+rb2*sin(theta1)", "\u5f2f\u7ba1\u6bb5 2 \u7684\u66f2\u7387\u4e2d\u5fc3\uff0cz \u5206\u91cf");
    model.param()
         .set("xL3", "xc2-rb2*cos(theta2-theta1)", "\u76f4\u7ba1\u6bb5 3 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cx \u5206\u91cf");
    model.param().set("yL3", "yc2", "\u76f4\u7ba1\u6bb5 3 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cy \u5206\u91cf");
    model.param()
         .set("zL3", "zc2+rb2*sin(theta2-theta1)", "\u76f4\u7ba1\u6bb5 3 \u7684\u8d77\u59cb\u4f4d\u7f6e\uff0cz \u5206\u91cf");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "rpipe");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L1");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"x0", "y0", "z0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", "rb1");
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "rpipe");
    model.component("comp1").geom("geom1").feature("tor1").set("angle", "theta1");
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new String[]{"xc1", "yc1", "zc1"});
    model.component("comp1").geom("geom1").feature("tor1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("tor1").set("rot", "270-theta1");
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "rpipe");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L2");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"xL2", "yL2", "zL2"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl2").set("ax3", new String[]{"-sin(theta1)", "0", "1"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("ax3", "cos(theta1)", 2);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("tor2", "Torus");
    model.component("comp1").geom("geom1").feature("tor2").set("rmaj", "rb2");
    model.component("comp1").geom("geom1").feature("tor2").set("rmin", "rpipe");
    model.component("comp1").geom("geom1").feature("tor2").set("angle", "theta2");
    model.component("comp1").geom("geom1").feature("tor2").set("pos", new String[]{"xc2", "yc2", "zc2"});
    model.component("comp1").geom("geom1").feature("tor2").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("tor2").set("rot", "90-theta1");
    model.component("comp1").geom("geom1").run("tor2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "rpipe");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "L3");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"xL3", "yL3", "zL3"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl3").set("ax3", new String[]{"sin(theta2-theta1)", "0", "1"});
    model.component("comp1").geom("geom1").feature("cyl3").setIndex("ax3", "cos(theta2-theta1)", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "tor1", "tor2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("light_pipe_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
