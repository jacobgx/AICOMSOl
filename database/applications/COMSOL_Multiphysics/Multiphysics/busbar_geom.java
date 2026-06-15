/*
 * busbar_geom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:37 by COMSOL 6.3.0.290. */
public class busbar_geom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "ec");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/emh1", true);

    model.param().set("L", "9[cm]");
    model.param().descr("L", "\u957f\u5ea6");
    model.param().set("rad_1", "6[mm]");
    model.param().descr("rad_1", "\u87ba\u6813\u534a\u5f84");
    model.param().set("tbb", "5[mm]");
    model.param().descr("tbb", "\u539a\u5ea6");
    model.param().set("wbb", "5[cm]");
    model.param().descr("wbb", "\u5bbd\u5ea6");
    model.param().set("mh", "3[mm]");
    model.param().descr("mh", "\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param().set("htc", "5[W/m^2/K]");
    model.param().descr("htc", "\u4f20\u70ed\u7cfb\u6570");
    model.param().set("Vtot", "20[mV]");
    model.param().descr("Vtot", "\u5916\u52a0\u7535\u538b");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");

    model.component("comp1").view("view2").axis().set("xmin", "-1e-2");
    model.component("comp1").view("view2").axis().set("xmax", 0.11);
    model.component("comp1").view("view2").axis().set("ymin", "-1e-2");
    model.component("comp1").view("view2").axis().set("ymax", 0.11);
    model.component("comp1").view("view2").axis().set("manualgrid", true);
    model.component("comp1").view("view2").axis().set("xspacing", "5e-3");
    model.component("comp1").view("view2").axis().set("yspacing", "5e-3");

    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L+2*tbb", "0.1[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"L+tbb", "0.1[m]-tbb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new String[]{"0", "tbb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("dif1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "tbb");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").set("fil1", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "2*tbb");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "wbb", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext1", 8);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "rad_1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "-2*tbb", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("ext1", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "rad_1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1")
         .set("pos", new String[]{"-L/2+1.5[cm]", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").setIndex("pos", "-wbb/4", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").set("disply", "wbb/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("copy1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "-2*tbb", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("fin");

    model.title("\u53c2\u6570\u5316\u6bcd\u7ebf\u677f\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u7528\u4e8e\u201c\u6bcd\u7ebf\u677f\u7126\u8033\u70ed\u201d\u793a\u4f8b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("busbar_geom.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
