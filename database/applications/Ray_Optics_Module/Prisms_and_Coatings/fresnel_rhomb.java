/*
 * fresnel_rhomb.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class fresnel_rhomb {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Prisms_and_Coatings");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

    model.param().set("delta", "45[deg]");
    model.param().descr("delta", "s \u504f\u632f\u548c p \u504f\u632f\u4e4b\u95f4\u7684\u76f8\u4f4d\u5ef6\u8fdf");
    model.param().set("n1", "1");
    model.param().descr("n1", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("n2", "1.51");
    model.param().descr("n2", "\u6298\u5c04\u7387\uff0c\u73bb\u7483");
    model.param().set("n12", "n1/n2");
    model.param().descr("n12", "\u6298\u5c04\u7387\u6bd4");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "thetai", 0, 0);
    model.component("comp1").physics("ge").feature("ge1")
         .setIndex("equation", "cos(thetai)*sqrt(sin(thetai)^2-n12^2)/sin(thetai)^2-tan(delta/2)", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "45[deg]", 0, 0);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"thetai"});
    model.result().numerical("gev1").set("descr", new String[]{"\u72b6\u6001\u53d8\u91cf thetai"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.param().set("theta", "0.84855[rad]");
    model.param().descr("theta", "\u5165\u5c04\u89d2");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 1, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cos(theta)+1", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "sin(theta)", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "cos(theta)", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "sin(theta)", 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/gop", false);

    model.component("comp1").geom("geom1").run();

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/ge", false);
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u73bb\u7483");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n2"});

    model.component("comp1").physics("gop").prop("ExteriorUnmeshedProperties").setIndex("next", "n1", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.5, 1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.5, 2);
    model.component("comp1").physics("gop").feature("relg1")
         .set("L0", new String[]{"sin(theta)", "-cos(theta)", "0"});
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarizationType", "FullyPolarized");
    model.component("comp1").physics("gop").feature("relg1").set("InitialPolarization", "UserDefined");
    model.component("comp1").physics("gop").feature("relg1").set("a20", 1);
    model.component("comp1").physics("gop").create("matd2", "MaterialDiscontinuity", 2);
    model.component("comp1").physics("gop").feature("matd2").selection().set(1, 6);
    model.component("comp1").physics("gop").feature("matd2")
         .set("ThinDielectricFilmsOnBoundary", "AntiReflectiveCoating");

    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "range(0,0.4,4)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("pointtype", "ellipse");
    model.result("pg1").feature("rtrj1").set("ellipsecount", 15);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u5149\u7a0b\u957f\u5ea6");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Magma");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "ray1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5706\u504f\u632f\u5ea6");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5706\u504f\u632f\u5ea6");
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "gop.s3/gop.s0");
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u83f2\u6d85\u5c14\u68f1\u4f53");

    model
         .description("\u83f2\u6d85\u5c14\u68f1\u4f53\u662f\u5229\u7528\u5168\u5185\u53cd\u5c04\u6765\u64cd\u63a7\u5149\u504f\u632f\u7684\u68f1\u955c\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u4e00\u675f\u5149\u5165\u5c04\u5230\u68f1\u955c\u4e2d\u53d1\u751f\u5185\u53cd\u5c04\uff0c\u5165\u5c04\u89d2\u5728 s \u504f\u632f\u8f90\u5c04\u4e0e p \u504f\u632f\u8f90\u5c04\u4e4b\u95f4\u5b58\u5728 45 \u5ea6\u7684\u76f8\u4f4d\u5ef6\u8fdf\u3002\u7ecf\u4e24\u6b21\u8fd9\u6837\u7684\u53cd\u5c04\u540e\uff0c\u68f1\u955c\u4f7f\u5165\u5c04\u7ebf\u504f\u632f\u5149\u8f6c\u6362\u4e3a\u5706\u504f\u632f\u5149\u3002");

    model.label("fresnel_rhomb.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
