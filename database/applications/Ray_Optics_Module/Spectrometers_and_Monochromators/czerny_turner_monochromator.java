/*
 * czerny_turner_monochromator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:21 by COMSOL 6.3.0.290. */
public class czerny_turner_monochromator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Spectrometers_and_Monochromators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").insertFile("czerny_turner_monochromator_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("lambda0min", "450[nm]");
    model.param().descr("lambda0min", "\u5149\u675f\u5e73\u5747\u6ce2\u957f");
    model.param().set("lambda0max", "850[nm]");
    model.param().descr("lambda0max", "\u6700\u5927\u771f\u7a7a\u6ce2\u957f");
    model.param().set("N", "3648");
    model.param().descr("N", "\u50cf\u7d20\u6570");
    model.param().set("wp", "8[um]");
    model.param().descr("wp", "\u50cf\u7d20\u5bbd\u5ea6");
    model.param().set("Fnum", "10");
    model.param().descr("Fnum", "F \u6570");
    model.param().set("NA", "1/(2*Fnum)");
    model.param().descr("NA", "\u6570\u503c\u5b54\u5f84");
    model.param().set("Srange", "650[nm]");
    model.param().descr("Srange", "\u5149\u8c31\u8303\u56f4");

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("WavelengthDistribution")
         .setIndex("WavelengthDistribution", "PolychromaticWavelength", 0);
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", -10, 0);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", 16.16104903340627, 1);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nw", 20, 0);
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "asin(NA)");
    model.component("comp1").physics("gop").feature("relg1").set("LDistributionFunction", "Uniform");
    model.component("comp1").physics("gop").feature("relg1").setIndex("lambda0Nval", 20, 0);
    model.component("comp1").physics("gop").feature("relg1").set("lambda0min", "lambda0min");
    model.component("comp1").physics("gop").feature("relg1").set("lambda0max", "lambda0max");
    model.component("comp1").physics("gop").create("wall1", "Wall", 1);
    model.component("comp1").physics("gop").feature("wall1").selection().set(8);
    model.component("comp1").physics("gop").create("mir1", "Mirror", 1);
    model.component("comp1").physics("gop").feature("mir1").selection().set(15, 16);
    model.component("comp1").physics("gop").create("grat1", "Grating", 1);
    model.component("comp1").physics("gop").feature("grat1").selection().set(3);
    model.component("comp1").physics("gop").feature("grat1").set("dg", "1[mm]/600");
    model.component("comp1").physics("gop").feature("grat1").set("RaysToRelease", "Reflected");
    model.component("comp1").physics("gop").feature("grat1").set("DirectionOfPeriodicity", "Reverse");
    model.component("comp1").physics("gop").feature("grat1").feature("dfo1").set("mg", 1);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.002);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.002);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("pnum", "ceil(N/2-(qx-Qdx)/(wp*cos(theta_d)))");
    model.component("comp1").variable("var1").descr("pnum", "\u50cf\u7d20\u6570");
    model.component("comp1").variable("var1").set("samefreq", "abs(gop.nu-dest(gop.nu))<1[Hz]");
    model.component("comp1").variable("var1")
         .descr("samefreq", "\u63a2\u6d4b\u5668\u4e0a\u76f8\u540c\u9891\u7387\u7684\u5c04\u7ebf\u7684\u903b\u8f91\u8868\u8fbe\u5f0f");
    model.component("comp1").variable("var1").set("distance", "sqrt((qx-dest(qx))^2+(qy-dest(qy))^2)");
    model.component("comp1").variable("var1").descr("distance", "\u5c04\u7ebf\u95f4\u8ddd");
    model.component("comp1").variable("var1").set("distance2", "gop.max(if(samefreq,distance,0))");
    model.component("comp1").variable("var1")
         .descr("distance2", "\u5230\u7ed9\u5b9a\u5c04\u7ebf\u7684\u6700\u5927\u8ddd\u79bb");
    model.component("comp1").variable("var1").set("wi", "gop.max(if(samefreq,distance2,0))");
    model.component("comp1").variable("var1").descr("wi", "\u5165\u5c04\u72ed\u7f1d\u7684\u56fe\u50cf\u5bbd\u5ea6");

    model.study("std1").feature("rtrac").set("tlist", "range(0,0.01,0.8)");
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
    model.result("pg1").setIndex("looplevel", 81, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result().dataset().create("rbin1", "RayBin");
    model.result().dataset("rbin1").set("expr", "gop.lambda0");
    model.result().dataset("rbin1").set("descr", "\u771f\u7a7a\u6ce2\u957f");
    model.result().dataset("rbin1").set("method", "tolerance");
    model.result().dataset("rbin1").set("tolerance", "1[nm]");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("linetype", "tube");
    model.result("pg1").feature("rtrj1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("rtrj1").set("tuberadiusscale", 0.025);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.lambda0");
    model.result("pg1").feature("rtrj1").feature("col1").set("descr", "\u771f\u7a7a\u6ce2\u957f");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "Spectrum");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "nm");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u50cf\u7d20\u6570");
    model.result("pg2").set("data", "rbin1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("rtp1", "Ray1D");
    model.result("pg2").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rtp1").set("linewidth", "preference");
    model.result("pg2").feature("rtp1").set("expr", "pnum");
    model.result("pg2").feature("rtp1").set("xdata", "expr");
    model.result("pg2").feature("rtp1").set("xdataexpr", "gop.lambda0");
    model.result("pg2").feature("rtp1").set("xdataunit", "nm");
    model.result("pg2").feature("rtp1").set("linemarker", "circle");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5668\u4ef6\u5206\u8fa8\u7387");
    model.result("pg3").set("data", "rbin1");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("rtp1", "Ray1D");
    model.result("pg3").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg3").feature("rtp1").set("linewidth", "preference");
    model.result("pg3").feature("rtp1").set("expr", "Srange/N*wi/wp");
    model.result("pg3").feature("rtp1").set("unit", "nm");
    model.result("pg3").feature("rtp1").set("descractive", true);
    model.result("pg3").feature("rtp1").set("descr", "\u5149\u8c31\u5206\u8fa8\u7387");
    model.result("pg3").feature("rtp1").set("xdata", "expr");
    model.result("pg3").feature("rtp1").set("xdataexpr", "gop.lambda0");
    model.result("pg3").feature("rtp1").set("xdataunit", "nm");
    model.result("pg3").feature("rtp1").set("linemarker", "circle");
    model.result("pg3").run();

    model.title("Czerny-Turner \u5355\u8272\u5668");

    model
         .description("Czerny-Turner \u5355\u8272\u5668\u4ece\u7a7a\u95f4\u4e0a\u5c06\u591a\u8272\u5149\u5206\u89e3\u6210\u4e00\u7cfb\u5217\u7684\u5355\u8272\u5149\u3002\u672c\u4f8b\u6a21\u62df\u5341\u5b57\u5f62 Czerny-Turner \u7ed3\u6784\uff0c\u5b83\u5305\u542b\u4e00\u4e2a\u7403\u5f62\u6821\u51c6\u955c\u3001\u4e00\u4e2a\u5e73\u884d\u5c04\u5149\u6805\u3001\u4e00\u4e2a\u7403\u5f62\u6210\u50cf\u955c\u4ee5\u53ca\u4e00\u4e2a\u9635\u5217\u7535\u8377\u8026\u5408\u5668\u4ef6 (CCD) \u68c0\u6d4b\u5668\u3002\u6a21\u578b\u4f7f\u7528\u201c\u51e0\u4f55\u5149\u5b66\u201d\u63a5\u53e3\u6765\u8ba1\u7b97\u5165\u5c04\u5149\u7ebf\u5728\u68c0\u6d4b\u5668\u5e73\u9762\u7684\u4f4d\u7f6e\uff0c\u4ece\u4e2d\u53ef\u4ee5\u5f97\u5230\u5668\u4ef6\u7684\u5206\u8fa8\u7387\u3002");

    model.label("czerny_turner_monochromator.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
