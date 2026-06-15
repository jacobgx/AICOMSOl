/*
 * antireflective_coating_multilayer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class antireflective_coating_multilayer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Prisms_and_Coatings");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("n_air", "1");
    model.param().descr("n_air", "\u7a7a\u6c14\u6298\u5c04\u7387");
    model.param().set("n_glass", "1.5");
    model.param().descr("n_glass", "\u73bb\u7483\u6298\u5c04\u7387");
    model.param().set("n_CeF3", "1.63");
    model.param().descr("n_CeF3", "CeF3 \u7684\u6298\u5c04\u7387");
    model.param().set("n_MgF2", "1.38");
    model.param().descr("n_MgF2", "MgF2 \u7684\u6298\u5c04\u7387");
    model.param().set("n_ZrO2", "2.2");
    model.param().descr("n_ZrO2", "ZrO2 \u7684\u6298\u5c04\u7387");
    model.param().set("lam0", "550[nm]");
    model.param().descr("lam0", "\u771f\u7a7a\u6ce2\u957f");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").setIndex("layer", 0.5, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_air"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_glass"});

    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("matd1").set("ShowBoundaryNormal", true);
    model.component("comp1").physics("gop").feature("matd1")
         .set("ThinDielectricFilmsOnBoundary", "AddLayersToSurface");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").feature("matd1").create("film1", "ThinDielectricFilm", 1);
    model.component("comp1").physics("gop").feature("matd1").feature("film1").set("nf", "n_CeF3");
    model.component("comp1").physics("gop").feature("matd1").feature("film1").set("tf", "lam0/(4*n_CeF3)");
    model.component("comp1").physics("gop").feature("matd1").create("film2", "ThinDielectricFilm", 1);
    model.component("comp1").physics("gop").feature("matd1").feature("film2").set("nf", "n_MgF2");
    model.component("comp1").physics("gop").feature("matd1").feature("film2").set("tf", "lam0/(4*n_MgF2)");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 0.5, 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 1, 1);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{0, -1, 0});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1")
         .setIndex("lambda0vals", "range(400[nm],(800[nm]-(400[nm]))/99,800[nm])", 0);

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("llist", "range(0,0.01,1.1)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 111, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u53cd\u5c04\u7387");
    model.result("pg2").set("data", "ray1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u591a\u5c42\u819c\u7684\u53cd\u5c04\u7387");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u771f\u7a7a\u6ce2\u957f (nm)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387 (%)");
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "100*(gop.relg1.Q0-gop.Q)/gop.relg1.Q0");
    model.result("pg2").feature("rtp1").set("xdata", "expr");
    model.result("pg2").feature("rtp1").set("xdataexpr", "gop.lambda0");
    model.result("pg2").feature("rtp1").set("xdataunit", "nm");
    model.result("pg2").feature("rtp1").set("legend", true);
    model.result("pg2").feature("rtp1").set("legendmethod", "manual");
    model.result("pg2").feature("rtp1").setIndex("legends", "\u56db\u5206\u4e4b\u4e00", 0);
    model.result("pg2").run();

    model.component("comp1").physics("gop").feature("matd1").create("film3", "ThinDielectricFilm", 1);
    model.component("comp1").physics("gop").feature("matd1").feature().move("film3", 1);
    model.component("comp1").physics("gop").feature("matd1").feature("film3").set("nf", "n_ZrO2");
    model.component("comp1").physics("gop").feature("matd1").feature("film3").set("tf", "lam0/(2*n_ZrO2)");

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("llist", "range(0,0.01,1.1)");
    model.study("std2").feature("rtrac").set("raystopcond", "noactive");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol2");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "ray2");
    model.result("pg3").setIndex("looplevel", 111, 0);
    model.result("pg3").label("\u5c04\u7ebf\u8f68\u8ff9 (gop) 1");
    model.result("pg3").create("rtrj1", "RayTrajectories");
    model.result("pg3").feature("rtrj1").set("linetype", "line");
    model.result("pg3").feature("rtrj1").create("col1", "Color");
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg3").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg3").run();

    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"gop/matd1/film3"});

    model.result("pg2").run();
    model.result("pg2").feature().duplicate("rtp2", "rtp1");
    model.result("pg2").run();
    model.result("pg2").feature("rtp2").set("data", "ray2");
    model.result("pg2").feature("rtp2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("rtp2")
         .setIndex("legends", "\u56db\u5206\u4e4b\u4e00\u52a0\u516b\u5206\u4e4b\u4e00", 0);
    model.result("pg2").run();

    model.title("\u591a\u5c42\u6297\u53cd\u5c04\u6d82\u5c42");

    model
         .description("\u6297\u53cd\u5c04\u6d82\u5c42\u5e38\u7528\u4e8e\u5149\u5b66\u7cfb\u7edf\u4e2d\uff0c\u4ee5\u51cf\u5c11\u5149\u675f\u4ece\u4e00\u79cd\u4ecb\u8d28\u8fdb\u5165\u53e6\u4e00\u79cd\u5177\u6709\u4e0d\u540c\u6298\u5c04\u7387\u7684\u4ecb\u8d28\u65f6\u4ea7\u751f\u7684\u6742\u6563\u5149\u91cf\u3002\u6297\u53cd\u5c04\u6d82\u5c42\u7684\u6700\u7b80\u5355\u793a\u4f8b\u662f\u56db\u5206\u4e4b\u4e00\u6ce2\u957f\u5c42\uff0c\u5176\u4e2d\u5355\u5c42\u7535\u4ecb\u8d28\u819c\u7684\u539a\u5ea6\u88ab\u8c03\u6574\u4e3a\u5149\u5b66\u6ce2\u957f\u7684\u56db\u5206\u4e4b\u4e00\u3002\u5c3d\u7ba1\u5355\u5c42\u6d82\u5c42\u53ef\u4ee5\u5c06\u7279\u5b9a\u6ce2\u957f\u548c\u5165\u5c04\u89d2\u4e0b\u5149\u7684\u53cd\u5c04\u7387\u964d\u4f4e\u5230\u96f6\uff0c\u4f46\u662f\u5bf9\u4e8e\u5176\u4ed6\u6ce2\u957f\uff0c\u53cd\u5c04\u7387\u53ef\u4ee5\u5927\u5f97\u591a\u3002\u4e00\u79cd\u53ef\u80fd\u7684\u89e3\u51b3\u65b9\u6848\u662f\u4f7f\u7528\u591a\u5c42\u6d82\u5c42\uff0c\u8be5\u6d82\u5c42\u5728\u8f83\u5bbd\u7684\u5149\u8c31\u5e26\u5185\u5177\u6709\u4e00\u81f4\u7684\u4f4e\u53cd\u5c04\u7387\u3002\n\n\u5728\u672c\u6559\u7a0b\u4e2d\uff0c\u5149\u5782\u76f4\u5165\u5c04\u65f6\u7a7f\u8fc7\u6298\u5c04\u7387\u4e0d\u540c\u7684\u4e24\u79cd\u4ecb\u8d28\u4e4b\u95f4\u7684\u8fb9\u754c\u3002\u5728\u5bbd\u5149\u8c31\u8303\u56f4\u5185\u6bd4\u8f83\u4ee5\u4e0b\u4e24\u79cd\u4e0d\u540c\u591a\u5c42\u6d82\u5c42\u7684\u53cd\u5c04\u7387\uff1a\u56db\u5206\u4e4b\u4e00\u6d82\u5c42\uff08\u4e24\u5c42\uff09\u548c\u5341\u516d\u5206\u4e4b\u4e00\u6d82\u5c42\uff08\u4e09\u5c42\uff09\u3002\u5341\u516d\u5206\u4e4b\u4e00\u6d82\u5c42\u5728\u5927\u591a\u6570\u53ef\u89c1\u5149\u8c31\u4e2d\u5177\u6709\u66f4\u4e00\u81f4\u7684\u4f4e\u53cd\u5c04\u7387\u3002");

    model.label("antireflective_coating_multilayer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
