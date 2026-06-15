/*
 * lamb_waves_ndt_plate_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:41 by COMSOL 6.3.0.290. */
public class lamb_waves_ndt_plate_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("D_plate", "5[mm]", "\u677f\u539a");
    model.param().set("W_plate", "15[mm]", "\u677f\u5bbd");
    model.param().set("L_plate", "50[cm]", "\u677f\u957f");
    model.param().set("H_wedge", "20[mm]", "\u6954\u5757\u9ad8\u5ea6");
    model.param().set("W_wedge", "20[mm]", "\u6954\u5757\u5bbd\u5ea6");
    model.param().set("L_wedge", "40[mm]", "\u6954\u5757\u957f\u5ea6");
    model.param().set("L_slope", "16[mm]", "\u6954\u5757\u5761\u5ea6\u957f\u5ea6");
    model.param().set("alpha", "70[deg]", "\u6954\u5757\u89d2\u5ea6");
    model.param().set("R_source", "6.25[mm]", "\u6fc0\u52b1\u533a\u534a\u5f84");
    model.param().label("\u51e0\u4f55\u53c2\u6570");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_plate", "D_plate"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "0.1*L_plate", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "1.1*L_plate", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "1.2*L_plate", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "W_plate/2");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-D_plate/2");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "-L_plate/4");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_wedge", "H_wedge", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "L_wedge", 2);
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"(W_plate - W_wedge)/2", "0", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "normalvector");
    model.component("comp1").geom("geom1").feature("wp2").set("normalvector", new String[]{"0", "cos(alpha)", "1"});
    model.component("comp1").geom("geom1").feature("wp2").setIndex("normalvector", "-sin(alpha)", 2);
    model.component("comp1").geom("geom1").feature("wp2")
         .set("normalcoord", new String[]{"0", "H_wedge - L_slope*sin(alpha)", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "R_source");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1")
         .set("pos", new String[]{"-L_slope*sin(alpha)/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("pos", "-W_plate/2", 1);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 2);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("ps1", "ParametricSurface");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin1", "W_plate/2");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax1", "W_plate");
    model.component("comp1").geom("geom1").feature("ps1").set("parmin2", "-D_plate");
    model.component("comp1").geom("geom1").feature("ps1").set("parmax2", 0);
    model.component("comp1").geom("geom1").feature("ps1").set("coord", new String[]{"s1", "s2", "0.3"});
    model.component("comp1").geom("geom1").run("ps1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.2, 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.4, 2);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pt1", "pt2");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", "W_plate");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("copy1", "mov1", "ps1", "pt1", "pt2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");
    model.component("comp1").geom("geom1").feature("igf1").selection("input").set("fin", 31);
    model.component("comp1").geom("geom1").run("igf1");

    model.title(null);

    model.description("");

    model.label("lamb_waves_ndt_plate_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
