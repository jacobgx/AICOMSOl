/*
 * porous_microchannel_heat_sink_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:13 by COMSOL 6.3.0.290. */
public class porous_microchannel_heat_sink_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("width", "0.7[mm]");
    model.param().descr("width", "\u603b\u5bbd\u5ea6");
    model.param().set("height", "2.5[mm]");
    model.param().descr("height", "\u603b\u9ad8\u5ea6");
    model.param().set("length", "10.6[mm]");
    model.param().descr("length", "\u603b\u957f\u5ea6");
    model.param().set("th_porous", "0.1[mm]");
    model.param().descr("th_porous", "\u591a\u5b54\u7ed3\u6784\u539a\u5ea6");
    model.param().set("th_solid", "0.025[mm]");
    model.param().descr("th_solid", "\u56fa\u4f53\u539a\u5ea6");
    model.param().set("width_channel", "width-2*(th_porous+th_solid)");
    model.param().descr("width_channel", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("height_channel", "height-2*th_solid");
    model.param().descr("height_channel", "\u901a\u9053\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"width/2", "height"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "th_solid", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"th_porous", "height_channel"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"width_channel/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "th_solid", 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "length", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("fin", 19, 24, 25, 27);
    model.component("comp1").geom("geom1").run("mcf1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("mcf1", 1);
    model.component("comp1").geom("geom1").feature("sel1").label("\u56fa\u4f53");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("mcf1", 3);
    model.component("comp1").geom("geom1").feature("sel2").label("\u591a\u5b54");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6d41\u4f53");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "comsel1"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6d41\u52a8\u57df");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u6d41\u52a8\u57df\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").add("mcf1", 1, 4, 7, 18);
    model.component("comp1").geom("geom1").feature("sel3").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("mcf1", 5, 13);
    model.component("comp1").geom("geom1").feature("sel4").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("mcf1", 11, 16);
    model.component("comp1").geom("geom1").feature("sel5").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel3", "sel4", "sel5"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u58c1\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u56fa\u4f53\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("adjsel2");

    model.title(null);

    model.description("");

    model.label("porous_microchannel_heat_sink_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
