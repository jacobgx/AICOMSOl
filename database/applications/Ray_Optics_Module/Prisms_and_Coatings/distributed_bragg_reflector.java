/*
 * distributed_bragg_reflector.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:20 by COMSOL 6.3.0.290. */
public class distributed_bragg_reflector {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Prisms_and_Coatings");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.param().set("ns", "1.5");
    model.param().descr("ns", "\u57fa\u677f\u7684\u6298\u5c04\u7387");
    model.param().set("nh", "2.32");
    model.param().descr("nh", "ZnS \u7684\u6298\u5c04\u7387");
    model.param().set("nl", "1.38");
    model.param().descr("nl", "MgF2 \u7684\u6298\u5c04\u7387");
    model.param().set("lam0", "550[nm]");
    model.param().descr("lam0", "\u771f\u7a7a\u6ce2\u957f");
    model.param().set("Nc", "2");
    model.param().descr("Nc", "\u57fa\u672c\u5355\u5143\u6570");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 10);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 5);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensity", 0);
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"ns"});

    model.component("comp1").physics("gop").feature("matd1")
         .set("ThinDielectricFilmsOnBoundary", "AddLayersToSurfaceRepeating");
    model.component("comp1").physics("gop").feature("matd1").set("Nft", "Nc");
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("gop").feature("matd1").set("ShowBoundaryNormal", true);
    model.component("comp1").physics("gop").feature("matd1").create("film1", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd1").feature("film1").set("nf", "nh");
    model.component("comp1").physics("gop").feature("matd1").feature("film1").set("tf", "lam0/(4*nh)");
    model.component("comp1").physics("gop").feature("matd1").create("film2", "ThinDielectricFilm", 2);
    model.component("comp1").physics("gop").feature("matd1").feature("film2").set("nf", "nl");
    model.component("comp1").physics("gop").feature("matd1").feature("film2").set("tf", "lam0/(4*nl)");
    model.component("comp1").physics("gop").feature("matd1").feature().duplicate("film3", "film1");
    model.component("comp1").physics("gop").feature("matd1").feature("film1")
         .set("RepeatLayerInMultilayerFilms", false);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 10, 2);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{0, 0, -1});
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "ListOfValues");
    model.component("comp1").physics("gop").feature("relg1")
         .setIndex("lambda0vals", "range(400[nm],(800[nm]-(400[nm]))/999,800[nm])", 0);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "ns", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "ns", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "Nc", 0);
    model.study("std1").feature("param").setIndex("plistarr", "2 5 10 20", 0);
    model.study("std1").feature("rtrac").set("tlist", "0 0.025");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
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
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u771f\u7a7a\u6ce2\u957f (nm)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387 (%)");
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "100*(gop.relg1.I0-gop.I)/gop.relg1.I0");
    model.result("pg2").feature("rtp1").set("xdata", "expr");
    model.result("pg2").feature("rtp1").set("xdataexpr", "gop.lambda0");
    model.result("pg2").feature("rtp1").set("xdataunit", "nm");
    model.result("pg2").feature("rtp1").set("legend", true);
    model.result("pg2").feature("rtp1").set("legendmethod", "evaluated");
    model.result("pg2").feature("rtp1").set("legendpattern", "eval(2*Nc+1) \u5c42");
    model.result("pg2").run();

    model.title("\u5206\u5e03\u5f0f\u5e03\u62c9\u683c\u53cd\u5c04\u955c");

    model
         .description("\u5206\u5e03\u5f0f\u5e03\u62c9\u683c\u53cd\u5c04\u955c (DBR) \u662f\u7531\u4ea4\u66ff\u7535\u4ecb\u8d28\u5c42\u5f62\u6210\u7684\u5468\u671f\u6027\u7ed3\u6784\uff0c\u53ef\u7528\u4e8e\u5728\u4e00\u5b9a\u9891\u7387\u8303\u56f4\u5185\u4ee5\u6700\u5c0f\u7684\u635f\u8017\u5b9e\u73b0\u51e0\u4e4e\u5168\u53cd\u5c04\u3002\u672c\u6559\u7a0b\u5bf9\u4e00\u4e2a\u4e2d\u5fc3\u6ce2\u957f\u4e3a 550\u00a0nm\u3001\u963b\u5e26\u4e3a 180\u00a0nm \u7684\u5e03\u62c9\u683c\u53cd\u5c04\u955c\u8fdb\u884c\u5efa\u6a21\u3002");

    model.label("distributed_bragg_reflector.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
