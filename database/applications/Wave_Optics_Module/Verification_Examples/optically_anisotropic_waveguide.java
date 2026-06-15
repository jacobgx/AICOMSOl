/*
 * optically_anisotropic_waveguide.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:43 by COMSOL 6.3.0.290. */
public class optically_anisotropic_waveguide {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("mode", "ModeAnalysis");
    model.study("std1").feature("mode").set("ftplistmethod", "manual");
    model.study("std1").feature("mode").set("shiftactive", false);
    model.study("std1").feature("mode").set("linpsolnum", "auto");
    model.study("std1").feature("mode").set("outputmap", new String[]{});
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").set("ngenAUX", "1");
    model.study("std1").feature("mode").set("goalngenAUX", "1");
    model.study("std1").feature("mode").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1.55[um]", "\u5de5\u4f5c\u6ce2\u957f");
    model.param().set("f0", "c_const/lda0", "\u5de5\u4f5c\u9891\u7387");
    model.param().set("height", "4000[nm]", "\u6ce2\u5bfc\u9ad8\u5ea6");
    model.param().set("n_core_o", "sqrt(epsr_o)", "\u82af\u5c42\u6298\u5c04\u7387\uff0c\u5bfb\u5e38");
    model.param().set("n_core_eo", "sqrt(epsr_eo)", "\u82af\u5c42\u6298\u5c04\u7387\uff0c\u975e\u5bfb\u5e38");
    model.param().set("eps_clad", "2.05", "\u5305\u5c42\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570");
    model.param().set("k0_1", "2*pi/lda0", "\u771f\u7a7a\u4e2d\u7684\u6ce2\u6570");
    model.param().set("theta", "0[deg]", "x-y \u5e73\u9762\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("phi", "45[deg]", "y-z \u5e73\u9762\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("zeta", "90[deg]", "x-z \u5e73\u9762\u65cb\u8f6c\u89d2\u5ea6");
    model.param().set("epsr_eo", "2.19", "\u82af\u5c42\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u975e\u5bfb\u5e38");
    model.param().set("epsr_o", "2.31", "\u82af\u5c42\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u5bfb\u5e38");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "20[um]");
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"height", "height"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").feature("wee1").set("DisplacementFieldModel", "RelativePermittivity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u5305\u5c42");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"eps_clad"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u82af\u5c42");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"epsr_eo", "epsr_o", "epsr_o"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "height/15");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 - \u5bf9\u89d2\u6a2a\u5411\u5404\u5411\u5f02\u6027");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "height", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(500[nm],250[nm],4000[nm])", 0);
    model.study("std1").feature("param").setIndex("punit", "nm", 0);
    model.study("std1").feature("mode").set("modeFreq", "f0");
    model.study("std1").feature("mode").set("neigsactive", true);
    model.study("std1").feature("mode").set("neigs", 7);
    model.study("std1").feature("mode").set("shiftactive", true);
    model.study("std1").feature("mode").set("shift", "n_core_o");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 15, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u529f\u7387\u548c\u7535\u573a - \u5bf9\u89d2\u6a2a\u5411\u5404\u5411\u5f02\u6027");
    model.result("pg1").set("looplevel", new int[]{7, 15});
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Poavz");
    model.result("pg1").feature("surf1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747\uff0cz \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("expr", new String[]{"ewfd.Ex", "ewfd.Ey"});
    model.result("pg1").feature("arws1").set("descr", "\u7535\u573a");
    model.result("pg1").feature("arws1").set("xnumber", 30);
    model.result("pg1").feature("arws1").set("ynumber", 30);
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "ewfd.Poavz");
    model.result("pg1").feature("con1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747\uff0cz \u5206\u91cf");
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("color", "gray");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("titlenumberformat", "auto");
    model.result("pg1").set("titleprecision", 5);
    model.result("pg1")
         .set("title", "\u9ad8\u5ea6=eval(height,nm) nm \u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387=eval(ewfd.neff) \u8868\u9762\u548c\u7b49\u503c\u7ebf\uff1a\u529f\u7387 \u7bad\u5934\uff1a\u7535\u573a");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8272\u6563\u66f2\u7ebf - \u5bf9\u89d2\u6a2a\u5411\u5404\u5411\u5f02\u6027");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "ewfd.neff^2", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "ewfd.k0*height");
    model.result("pg2").feature("glob1").set("linestyle", "none");
    model.result("pg2").feature("glob1").set("linecolor", "black");
    model.result("pg2").feature("glob1").set("linemarker", "point");
    model.result("pg2").feature("glob1").set("markerpos", "interp");
    model.result("pg2").feature("glob1").set("markers", 100);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg2").setIndex("looplevelindices", "1 2 4 5 6 7", 0);
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "k*thickness");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\uff08\u6709\u6548\u6298\u5c04\u7387\uff09<sup>2</sub>");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 15);
    model.result("pg2").set("ymin", 2.05);
    model.result("pg2").set("ymax", 2.31);
    model.result("pg2").create("ann1", "Annotation");
    model.result("pg2").feature("ann1").set("text", "\\[E^{y}_{11}\\]");
    model.result("pg2").feature("ann1").set("posxexpr", 10);
    model.result("pg2").feature("ann1").set("posyexpr", 2.225);
    model.result("pg2").feature("ann1").set("latexmarkup", true);
    model.result("pg2").feature("ann1").set("showpoint", false);
    model.result("pg2").feature().duplicate("ann2", "ann1");
    model.result("pg2").run();
    model.result("pg2").feature("ann2").set("text", "\\[E^{x}_{11}\\]");
    model.result("pg2").feature("ann2").set("posyexpr", 2.125);
    model.result("pg2").feature().duplicate("ann3", "ann2");
    model.result("pg2").run();
    model.result("pg2").feature("ann3").set("text", "\\[E^{y}_{21}\\]");
    model.result("pg2").feature("ann3").set("posxexpr", 13);
    model.result("pg2").feature("ann3").set("posyexpr", 2.165);
    model.result("pg2").feature().duplicate("ann4", "ann3");
    model.result("pg2").run();
    model.result("pg2").feature("ann4").set("text", "\\[E^{y}_{12}\\]");
    model.result("pg2").feature("ann4").set("posxexpr", 13.5);
    model.result("pg2").feature("ann4").set("posyexpr", 2.15);
    model.result("pg2").feature().duplicate("ann5", "ann4");
    model.result("pg2").run();
    model.result("pg2").feature("ann5").set("text", "\\[E^{x}_{21}\\]");
    model.result("pg2").feature("ann5").set("posxexpr", 14);
    model.result("pg2").feature("ann5").set("posyexpr", 2.09);
    model.result("pg2").feature().duplicate("ann6", "ann5");
    model.result("pg2").run();
    model.result("pg2").feature("ann6").set("text", "\\[E^{x}_{12}\\]");
    model.result("pg2").feature("ann6").set("posyexpr", 2.07);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("method", "general");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"0", "phi", "zeta"});

    model.component("comp1").physics("ewfd").feature("wee1").set("coordinateSystem", "sys2");

    model.study().create("std2");
    model.study("std2").create("mode", "ModeAnalysis");
    model.study("std2").feature("mode").set("plotgroup", "Default");
    model.study("std2").feature("mode").set("ftplistmethod", "manual");
    model.study("std2").feature("mode").set("shiftactive", false);
    model.study("std2").feature("mode").set("linpsolnum", "auto");
    model.study("std2").feature("mode").set("outputmap", new String[]{});
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").set("ngenAUX", "1");
    model.study("std2").feature("mode").set("goalngenAUX", "1");
    model.study("std2").feature("mode").setSolveFor("/physics/ewfd", true);
    model.study("std2").label("\u7814\u7a76 - \u975e\u5bf9\u89d2\u7eb5\u5411\u5404\u5411\u5f02\u6027");
    model.study("std2").feature().copy("param", "std1/param");
    model.study("std2").feature("mode").set("modeFreq", "f0");
    model.study("std2").feature("mode").set("shiftactive", true);
    model.study("std2").feature("mode").set("shift", "n_core_o");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol19");
    model.sol("sol19").study("std2");
    model.sol("sol19").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol19");
    model.batch("p2").run("compute");

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ewfd)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 15, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u529f\u7387\u548c\u7535\u573a - \u975e\u5bf9\u89d2\u7eb5\u5411\u5404\u5411\u5f02\u6027");
    model.result("pg3").set("looplevel", new int[]{6, 15});
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("arws1", "pg1/arws1");
    model.result("pg3").feature().copy("con1", "pg1/con1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewfd.Poavz");
    model.result("pg3").feature("surf1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747\uff0cz \u5206\u91cf");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("titlenumberformat", "auto");
    model.result("pg3").set("titleprecision", 5);
    model.result("pg3")
         .set("title", "\u9ad8\u5ea6=eval(height,nm) nm \u6709\u6548\u6a21\u5f0f\u6298\u5c04\u7387=eval(ewfd.neff) \u8868\u9762\u548c\u7b49\u503c\u7ebf\uff1a\u529f\u7387 \u7bad\u5934\uff1a\u7535\u573a");
    model.result("pg3").set("paramindicator", "");
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u8272\u6563\u66f2\u7ebf - \u975e\u5bf9\u89d2\u7eb5\u5411\u5404\u5411\u5f02\u6027");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevelinput", "all", 0);
    model.result("pg4").run();
    model.result("pg4").feature("ann1").set("text", "\\[E^{x}_{11}\\]");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").set("text", "\\[E^{y}_{11}\\]");
    model.result("pg4").feature("ann2").set("posyexpr", 2.175);
    model.result("pg4").run();
    model.result("pg4").feature("ann3").set("text", "\\[E^{x}_{12}\\]");
    model.result("pg4").feature("ann3").set("posxexpr", 12);
    model.result("pg4").feature("ann3").set("posyexpr", 2.145);
    model.result("pg4").run();
    model.result("pg4").feature("ann4").set("text", "\\[E^{x}_{21}\\]");
    model.result("pg4").feature("ann4").set("posxexpr", 12.5);
    model.result("pg4").feature("ann4").set("posyexpr", 2.13);
    model.result("pg4").run();
    model.result("pg4").feature("ann5").set("text", "\\[E^{y}_{21}\\]");
    model.result("pg4").feature("ann5").set("posxexpr", 12.5);
    model.result("pg4").feature("ann5").set("posyexpr", 2.111);
    model.result("pg4").run();
    model.result("pg4").feature("ann6").set("text", "\\[E^{y}_{12}\\]");
    model.result("pg4").feature("ann6").set("posxexpr", 13);
    model.result("pg4").feature("ann6").set("posyexpr", 2.095);
    model.result("pg4").run();
    model.result("pg3").run();

    model.title("\u5149\u5b66\u5404\u5411\u5f02\u6027\u6ce2\u5bfc");

    model
         .description("\u672c\u6a21\u578b\u6267\u884c\u6a21\u6001\u5206\u6790\uff0c\u540c\u65f6\u5bf9 0.5 \u00b5m \u5230 4 \u00b5m \u8303\u56f4\u5185\u7684\u6ce2\u5bfc\u957f\u5ea6\u8fdb\u884c\u53c2\u6570\u5316\u626b\u63cf\uff0c\u4ece\u800c\u5f97\u5230\u5404\u5411\u5f02\u6027\u82af\u5c42\u7684\u8272\u6563\u66f2\u7ebf\u3002\u672c\u4f8b\u91c7\u7528\u4e24\u4e2a\u5355\u72ec\u7684\u5efa\u6a21\u6b65\u9aa4\u5206\u6790\u6a2a\u5411\u548c\u7eb5\u5411\u5404\u5411\u5f02\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();

    model.label("optically_anisotropic_waveguide.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
