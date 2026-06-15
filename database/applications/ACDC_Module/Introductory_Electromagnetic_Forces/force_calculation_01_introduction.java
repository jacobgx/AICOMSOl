/*
 * force_calculation_01_introduction.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class force_calculation_01_introduction {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electromagnetic_Forces");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Rl", "1[m]", "\u78c1\u5316\u68d2\uff0c\u957f\u5ea6");
    model.param().set("Rd", "1[m]", "\u78c1\u5316\u68d2\uff0c\u8ddd\u79bb");
    model.param().set("Ra", "-90[deg]", "\u78c1\u5316\u68d2\uff0c\u89d2\u5ea6");
    model.param().set("Rr", "0.05[m]", "\u78c1\u5316\u68d2\uff0c\u534a\u5f84");
    model.param()
         .set("Rrf", "0.01[m]", "\u78c1\u5316\u68d2\u78c1\u6781\u4e0a\u5e94\u7528\u7684\u5706\u89d2\u534a\u5f84");
    model.param().set("Cs", "10[m]", "\u5916\u90e8\u7acb\u65b9\u4f53\u5927\u5c0f");

    model.component("comp1").geom("geom1").label("\u51e0\u4f55 1\uff08\u78c1\u529b\u9a8c\u8bc1\uff09");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"Rr", "Rl"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-Rl/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "Rr", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "Rrf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 4, 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"5*Rr", "Rl+10*Rr"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "-(Rl+10*Rr)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "5*Rr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("pointinsketch")
         .set("r2", 2, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("rev1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "Rd/2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("r", "1.5*sqrt((Rl/2)^2+(Rd/2)^2)");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rot", 90);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", 180);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").label("\u51e0\u4f55 2\uff08\u78c1\u77e9\u9a8c\u8bc1\uff09");

    model.component("comp2").view("view4").set("showgrid", false);
    model.component("comp2").view("view4").set("showaxisorientation", false);

    model.component("comp2").geom("geom2").insertFile("force_calculation_01_introduction.mph", "geom1");
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").view("view4").set("renderwireframe", true);

    model.component("comp2").geom("geom2").feature().remove("rev2");
    model.component("comp2").geom("geom2").feature().remove("wp2");
    model.component("comp2").geom("geom2").feature().remove("mov1");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").run("rev1");
    model.component("comp2").geom("geom2").create("rot1", "Rotate");
    model.component("comp2").geom("geom2").feature("rot1").selection("input").set("rev1");
    model.component("comp2").geom("geom2").feature("rot1").set("rot", "Ra");
    model.component("comp2").geom("geom2").feature("rot1").set("axistype", "y");
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("sph1", "Sphere");
    model.component("comp2").geom("geom2").feature("sph1").set("r", "Rl");
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("type", "surface");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"Cs", "Cs", "Cs"});
    model.component("comp2").geom("geom2").feature("blk1").set("base", "center");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").view("view4").set("showgrid", true);
    model.component("comp2").view("view4").set("showaxisorientation", true);
    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.title("\u529b\u8ba1\u7b97 1 - \u5165\u95e8");

    model
         .description("\u8fd9\u4e2a\u6a21\u578b\u662f\u672c\u7cfb\u5217\u540e\u7eed\u6559\u7a0b\uff08\u201c\u78c1\u529b BEM FEM\u201d\u548c\u201c\u78c1\u77e9 BEM FEM\u201d\u6559\u7a0b\uff09\u7684\u57fa\u7840\uff0c\u5176\u4e2d\u5305\u542b\u603b\u4f53\u4ecb\u7ecd\u4ee5\u53ca\u672c\u7cfb\u5217\u6559\u7a0b\u4f7f\u7528\u7684\u51e0\u4f55\uff0c\u8fd8\u5305\u542b\u8be6\u7ec6\u7684\u51e0\u4f55\u5efa\u6a21\u64cd\u4f5c\u8bf4\u660e\u3002\n\n\u7ecf\u9a8c\u4e30\u5bcc\u7684\u7528\u6237\u5982\u679c\u5bf9\u51e0\u4f55\u6784\u5efa\u4e0d\u611f\u5174\u8da3\uff0c\u53ef\u4ee5\u8df3\u8fc7\u8fd9\u90e8\u5206\uff0c\u7ee7\u7eed\u5b66\u4e60\u4e0a\u8ff0\u4efb\u4e00\u6559\u7a0b\u3002\u4e0d\u8fc7\uff0c\u5982\u679c\u60a8\u662f COMSOL \u521d\u5b66\u8005\u6216\u9996\u6b21\u5b66\u4e60\u672c\u7cfb\u5217\u6559\u7a0b\uff0c\u5219\u6709\u5fc5\u8981\u82b1\u4e00\u4e9b\u65f6\u95f4\u8fdb\u884c\u4e86\u89e3\uff0c\u4ece\u800c\u5e2e\u52a9\u60a8\u719f\u6089\u57fa\u7840\u77e5\u8bc6\u3002");

    model.label("force_calculation_01_introduction.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
