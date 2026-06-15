/*
 * pinched_flow_fractionation_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:54 by COMSOL 6.3.0.290. */
public class pinched_flow_fractionation_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Li", "0.6[mm]", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("Wi", "0.1[mm]", "\u5165\u53e3\u5bbd\u5ea6");
    model.param().set("Ai", "60[deg]", "\u4e24\u4e2a\u5165\u53e3\u7684\u5939\u89d2");
    model.param().set("HAi", "0.5*Ai", "\u4e24\u4e2a\u5165\u53e3\u5939\u89d2\u7684\u4e00\u534a");
    model.param().set("Lc", "140[um]", "\u5fae\u901a\u9053\u957f\u5ea6");
    model.param().set("Wc", "20[um]", "\u5fae\u901a\u9053\u5bbd\u5ea6");
    model.param().set("HWc", "0.5*Wc", "\u5fae\u901a\u9053\u5bbd\u5ea6\u7684\u4e00\u534a");
    model.param().set("Lb", "1[mm]", "\u6bcf\u4e2a\u51fa\u53e3\u652f\u7ba1\u7684\u957f\u5ea6");
    model.param().set("Wb", "0.1[mm]", "\u6bcf\u4e2a\u51fa\u53e3\u652f\u7ba1\u7684\u5bbd\u5ea6");
    model.param().set("Nb", "12", "\u51fa\u53e3\u652f\u7ba1\u6570");
    model.param().set("Ldbr", "0.5", "\u6392\u6c34\u7ba1\u4e0e\u652f\u7ba1\u957f\u5ea6\u4e4b\u6bd4");
    model.param().set("Ld", "(1-Ldbr)*Lb", "\u6392\u6c34\u901a\u9053\u7684\u957f\u5ea6");
    model.param().set("Pc", "cos(HAi)*Li-HWc*tan(Ai)", "\u901a\u9053\u7684\u5de6\u89d2\u9876\u70b9");
    model.param().set("Pb", "Pc+Lc", "\u7b2c\u4e00\u4e2a\u51fa\u53e3\u652f\u7ba1\u7684\u5de6\u89d2\u9876\u70b9");
    model.param().set("Cr", "Pb+0.5*Wb", "\u51fa\u53e3\u652f\u7ba1\u7684\u65cb\u8f6c\u4e2d\u5fc3");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Li", "Wi"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-sin(HAi)*Li"});
    model.component("comp1").geom("geom1").feature("r1").set("rot", "HAi");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-0.1*Wi*cos(Ai)", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "sin(HAi)*Li-0.1*Wi*sin(Ai)", 1);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "-0.9*Wi*cos(Ai)", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "sin(HAi)*Li-0.9*Wi*sin(Ai)", 1);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("mir1", "pt1", "pt2", "r1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 9, 10, 11, 13, 15, 16);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"Pc", "HWc"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"Pc", "-HWc"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("del1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("ls1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").feature().create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init();
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("par1", 2);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"Lc", "Wc"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"Pc", "-HWc"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"Wb", "Lb"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"Pb", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,-180/Nb,-180)");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"Cr", "0"});
    model.component("comp1").geom("geom1").feature("rot1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("rot1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("rot1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"Wb", "Ld+0.01*Lb"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"Pb", "-Lb*1.01"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r4");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("pinched_flow_fractionation_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
