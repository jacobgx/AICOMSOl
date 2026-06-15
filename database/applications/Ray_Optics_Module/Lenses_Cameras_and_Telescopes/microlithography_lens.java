/*
 * microlithography_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:17 by COMSOL 6.3.0.290. */
public class microlithography_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().create("par2");
    model.param("par2").set("NA", "0.56");
    model.param("par2").descr("NA", "\u6570\u503c\u5b54\u5f84");
    model.param("par2").set("mag", "0.25");
    model.param("par2").descr("mag", "\u653e\u5927\u500d\u6570");
    model.param("par2").set("alpha", "atan(NA)*mag");
    model.param("par2").descr("alpha", "\u9525\u89d2");
    model.param("par2").set("nhex", "25");
    model.param("par2").descr("nhex", "\u516d\u8fb9\u73af\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").label("\u5fae\u5149\u523b\u900f\u955c\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").insertFile("microlithography_lens_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties")
         .setIndex("DispersionModel", "AbsoluteVacuum", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").feature("mp1").set("n_mat", "userdef");
    model.component("comp1").physics("gop").feature("mp1")
         .set("n", new double[]{1.5084, 0, 0, 0, 1.5084, 0, 0, 0, 1.5084});
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "248[nm]");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u50cf");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_pi23_sel1");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").set("ConicalDistribution", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nctheta", "nhex", 0);
    model.component("comp1").physics("gop").feature("relg1").set("cax", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "alpha");
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").setIndex("x0", "D_0/4", 1);
    model.component("comp1").physics("gop").feature().duplicate("relg3", "relg2");
    model.component("comp1").physics("gop").feature("relg3").setIndex("x0", "D_0/2", 1);

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "5[mm]");
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_csel2_bnd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "0 1.5");
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
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "bottom");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "blue");
    model.result("pg1").feature("surf1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "\u00b5m");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("spotcoordsactive", true);
    model.result("pg2").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg2").feature("spot1").set("spotcoordsprecision", 7);
    model.result("pg2").run();
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "gop.phii");
    model.result("pg2").feature("spot1").feature("col1").set("descr", "\u5165\u5c04\u9510\u89d2");
    model.result("pg2").feature("spot1").feature("col1").set("unit", "\u00b0");
    model.result("pg2").run();

    model.title("\u5fae\u5149\u523b\u900f\u955c");

    model
         .description("\u5fae\u5149\u523b\u900f\u955c\u7528\u4e8e\u5c06\u96c6\u6210\u7535\u8def\u56fe\u50cf\u6295\u5c04\u5230\u7845\u57fa\u677f\u4e0a\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u521b\u5efa\u4e00\u4e2a\u4e3b\u8981\u9002\u7528\u4e8e 248\u00a0nm \u6ce2\u957f\uff0cNA \u503c\u4e3a 0.56 \u7684 21 \u5355\u5143\u7194\u878d\u77f3\u82f1\u900f\u955c\uff0c\u5176\u603b\u957f\u5ea6\u4e3a 1\u00a0m\uff0c\u5728 23.4\u00a0mm \u7684\u50cf\u5708\u4e0a\u5177\u6709 -0.25 \u7684\u653e\u5927\u500d\u7387\uff0c\u5177\u6709\u51fa\u8272\u7684\u6210\u50cf\u8d28\u91cf\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("microlithography_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
