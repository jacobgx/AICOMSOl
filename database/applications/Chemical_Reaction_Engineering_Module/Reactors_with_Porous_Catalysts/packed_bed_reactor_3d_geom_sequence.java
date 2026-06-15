/*
 * packed_bed_reactor_3d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:26 by COMSOL 6.3.0.290. */
public class packed_bed_reactor_3d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Porous_Catalysts");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.017);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new String[]{"0.017*2+0.02", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c3", "c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3")
         .set("pos", new String[]{"0.017*4+0.02*2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c4", "c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c4")
         .set("pos", new String[]{"0.017*6+0.02*3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c5", "c4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c5")
         .set("pos", new String[]{"0.017*2+0.02", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c5").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c6", "c5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c6")
         .set("pos", new String[]{"0.017*4+0.02*2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c7", "c6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c7")
         .set("pos", new String[]{"0.017*6+0.02*3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input")
         .set("c5", "c6", "c7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c8", "c7");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c8").set("angle", 360);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c8")
         .set("pos", new String[]{"0.017*4+0.02*2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c8").set("rot", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c8");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c9", "c8");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c9")
         .set("pos", new String[]{"0.017*6+0.02*3", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("c8", "c9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", 22.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5e95\u677f");
    model.component("comp1").geom("geom1").feature("wp2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", ".2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 45);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ext1", 2, 3);
    model.component("comp1").geom("geom1").feature("sel1").label("\u5bf9\u79f0\u9762");
    model.component("comp1").geom("geom1").feature().duplicate("sel2", "sel1");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").runPre("sel2");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").clear("ext1");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ext1", 5);
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("packed_bed_reactor_3d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
