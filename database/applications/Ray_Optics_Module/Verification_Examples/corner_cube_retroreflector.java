/*
 * corner_cube_retroreflector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:25 by COMSOL 6.3.0.290. */
public class corner_cube_retroreflector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Retroreflectors\\corner_cube_retroreflector_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", 1);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1").set("n", new double[]{1.5, 0, 0, 0, 1.5, 0, 0, 0, 1.5});
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").selection().set(5, 6, 7);
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().set(2);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-22/sqrt(3)", 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-22/sqrt(3)-5", 1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "-22/sqrt(3)+5", 2);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nw", 1000, 0);
    model.component("comp1").physics("gop").feature("relg1").set("cax", new double[]{1, 1.3, 1});
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "pi/18");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "range(0,0.2,70)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 351, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.pidx");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u5165\u5c04\u9510\u89d2");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "gop.phii");
    model.result("pg2").feature("rtp1").set("xdata", "expr");
    model.result("pg2").feature("rtp1").set("xdataexpr", "at(0,gop.phii)");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();

    model.title("\u89d2\u9685\u53cd\u5c04\u955c");

    model
         .description("\u89d2\u9685\u53cd\u5c04\u955c\u53ef\u7528\u4e8e\u5728\u4e0e\u5149\u7ebf\u521d\u59cb\u65b9\u5411\u5b8c\u5168\u53cd\u5e73\u884c\u7684\u65b9\u5411\u53cd\u5c04\u5149\u7ebf\uff0c\u800c\u4e0e\u5165\u5c04\u89d2\u65e0\u5173\u3002\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u4f7f\u7528\u201c\u51e0\u4f55\u5149\u5b66\u201d\u63a5\u53e3\u6a21\u62df\u4e00\u675f\u5149\u7ebf\u5728\u89d2\u9685\u53cd\u5c04\u955c\u4e0a\u7684\u53cd\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("corner_cube_retroreflector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
