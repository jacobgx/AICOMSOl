/*
 * magnetic_signature_submarine_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class magnetic_signature_submarine_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Magnetostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("tl", "32[m]", "\u603b\u957f\u5ea6");
    model.param().set("r", "2.5[m]", "\u534a\u5f84");
    model.param().set("tr", "1[m]", "\u5c3e\u90e8\u534a\u5f84");
    model.param().set("tsl", "7[m]", "\u5c3e\u90e8\u957f\u5ea6");
    model.param().set("th", "1.5[m]", "\u6307\u6325\u5854\u9ad8\u5ea6");
    model.param().set("dw", "50[m]", "\u57df\u5bbd\u5ea6");
    model.param().set("dl", "100[m]", "\u57df\u957f\u5ea6");
    model.param().set("tas", "2.5[m]", "\u6307\u6325\u5854 a \u534a\u8f74");
    model.param().set("tbs", "0.625[m]", "\u6307\u6325\u5854 b \u534a\u8f74");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point1", new String[]{"-tl/2+r", "r"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1")
         .set("point2", new String[]{"-tl/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ca1").set("angle1", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"-tl/2+r", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"tl/2-tsl", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new String[]{"-tl/2+r", "r"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new String[]{"tl/2-tsl", "r"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "tl/2-tsl", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1")
         .setIndex("p", "tl/2-(tsl*4/7)", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1")
         .setIndex("p", "tl/2-(tsl*4/7)", 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "tl/2", 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "r", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "tr", 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", "tr", 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord1", new String[]{"tl/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord2", new String[]{"tl/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord1", new String[]{"tl/2", "tr"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3")
         .set("coord1", new String[]{"tl/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3")
         .set("coord2", new String[]{"-tl/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("ca1", "cb1", "ls1", "ls2", "ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("e1")
         .set("semiaxes", new String[]{"tas", "tbs"});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "r+th", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ext1", "rev1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature("uni1").label("\u6f5c\u8247");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"dl", "dw", "dw"});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.title(null);

    model.description("");

    model.label("magnetic_signature_submarine_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
