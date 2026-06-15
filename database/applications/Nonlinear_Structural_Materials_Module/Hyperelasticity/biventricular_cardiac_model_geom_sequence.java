/*
 * biventricular_cardiac_model_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:30 by COMSOL 6.3.0.290. */
public class biventricular_cardiac_model_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u5fc3\u810f\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("aL", "3[cm]", "x \u534a\u8f74\uff0c\u5de6\u5fc3\u5ba4");
    model.param().set("bL", "aL", "y \u534a\u8f74\uff0c\u5de6\u5fc3\u5ba4");
    model.param().set("cL", "7[cm]", "z \u534a\u8f74\uff0c\u5de6\u5fc3\u5ba4");
    model.param().set("tL", "1.2[cm]", "\u58c1\u539a\uff0c\u5de6\u5fc3\u5ba4");
    model.param().set("aR", "5.1[cm]", "x \u534a\u8f74\uff0c\u53f3\u5fc3\u5ba4");
    model.param().set("bR", "bL", "y \u534a\u8f74\uff0c\u53f3\u5fc3\u5ba4");
    model.param().set("cR", "6[cm]", "z \u534a\u8f74\uff0c\u53f3\u5fc3\u5ba4");
    model.param().set("tR", "0.6[cm]", "\u58c1\u539a\uff0c\u53f3\u5fc3\u5ba4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("elp1", "Ellipsoid");
    model.component("comp1").geom("geom1").feature("elp1").label("\u692d\u7403\uff1a\u5de6\u5fc3\u5ba4");
    model.component("comp1").geom("geom1").feature("elp1").set("semiaxes", new String[]{"aL", "bL", "cL"});
    model.component("comp1").geom("geom1").feature("elp1").setIndex("layer", "tL", 0);
    model.component("comp1").geom("geom1").feature().duplicate("elp2", "elp1");
    model.component("comp1").geom("geom1").feature("elp2").label("\u692d\u7403\uff1a\u53f3\u5fc3\u5ba4");
    model.component("comp1").geom("geom1").feature("elp2").set("semiaxes", new String[]{"aR", "bR", "cR"});
    model.component("comp1").geom("geom1").feature("elp2").setIndex("layer", "tR", 0);
    model.component("comp1").geom("geom1").run("elp2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("elp2", 1, 3, 5, 6, 8);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "faces");
    model.component("comp1").geom("geom1").feature("pard1").selection("face").set("elp1", 16, 22);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("elp1", 2, 4, 5, 7, 9);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("pard1", 1, 2, 3, 4, 5, 6, 7, 9, 10);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").feature("fin").set("repairtol", "3e-4");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp1").geom("geom1").feature("igf1").selection("input").set("fin", 11, 20);
    model.component("comp1").geom("geom1").run("igf1");

    model.component("comp1").view("view1").set("transparency", true);

    model.title(null);

    model.description("");

    model.label("biventricular_cardiac_model_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
